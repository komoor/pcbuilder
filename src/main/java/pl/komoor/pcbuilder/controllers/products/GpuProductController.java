package pl.komoor.pcbuilder.controllers.products;

import net.kaczmarzyk.spring.data.jpa.domain.GreaterThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.In;
import net.kaczmarzyk.spring.data.jpa.domain.LessThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.komoor.pcbuilder.models.products.Gpu;
import pl.komoor.pcbuilder.payload.request.products.GpuRequest;
import pl.komoor.pcbuilder.payload.response.basic.AppResponse;
import pl.komoor.pcbuilder.payload.response.basic.ErrorResponse;
import pl.komoor.pcbuilder.payload.response.basic.PageMeta;
import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.payload.response.products.gpu.GpuListResponse;
import pl.komoor.pcbuilder.payload.response.products.gpu.GpuResponse;
import pl.komoor.pcbuilder.services.dto.products.GpuDTO;
import pl.komoor.pcbuilder.services.products.gpu.GpuService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/products")
public class GpuProductController {

    @Autowired
    GpuService gpuService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/gpu")
    public ResponseEntity<AppResponse> addGpu(@Valid @RequestBody GpuRequest gpuRequest) {


        if (gpuService.existsByModel(gpuRequest.getModel())) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponse("Wybrany model już istnieje w bazie"));
        }

        gpuService.saveGpu(gpuRequest);

        return ResponseEntity.ok(new SuccessResponse("Pomyślnie dodano produkt " + gpuRequest.getModel()));
    }


    @GetMapping("/gpu")
    public ResponseEntity<AppResponse> getAllGpus(
            @Join(path = "manufacturerId", alias="mId")
            @Join(path = "gpuChipsetId", alias="gcId")
            @Join(path = "gpuMemoryTypeId", alias="gmtId")
            @Join(path = "interfaceTypeId", alias="gitId")
            @Join(path = "productId", alias="prodId")
            @And({
                    @Spec(path = "prodId.id", params = "prodID", paramSeparator = ',', spec = In.class),
                    @Spec(path = "mId.id", params = "mID", paramSeparator = ',', spec = In.class),
                    @Spec(path = "gcId.id", params = "gcID", paramSeparator = ',', spec = In.class),
                    @Spec(path = "gmtId.id", params = "gmtID", paramSeparator = ',', spec = In.class),
                    @Spec(path = "gitId.id", params = "gitID", paramSeparator = ',', spec = In.class),
                    @Spec(path = "baseClock", params = "baseClockGT", paramSeparator = ',', spec = GreaterThanOrEqual.class),
                    @Spec(path = "baseClock", params = "baseClockLT", paramSeparator = ',', spec = LessThanOrEqual.class),
                    @Spec(path = "boostClock", params = "boostClockGT", paramSeparator = ',', spec = GreaterThanOrEqual.class),
                    @Spec(path = "boostClock", params = "boostClockLT", paramSeparator = ',', spec = LessThanOrEqual.class),
                    @Spec(path = "portsDvi", params = "portsDviGT", paramSeparator = ',', spec = GreaterThanOrEqual.class),
                    @Spec(path = "portsDvi", params = "portsDviLT", paramSeparator = ',', spec = LessThanOrEqual.class),
                    @Spec(path = "portsHdmi", params = "portsHdmiGT", paramSeparator = ',', spec = GreaterThanOrEqual.class),
                    @Spec(path = "portsHdmi", params = "portsHdmiLT", paramSeparator = ',', spec = LessThanOrEqual.class),
                    @Spec(path = "portsMiniHdmi", params = "portsMiniHdmiGT", paramSeparator = ',', spec = GreaterThanOrEqual.class),
                    @Spec(path = "portsMiniHdmi", params = "portsMiniHdmiLT", paramSeparator = ',', spec = LessThanOrEqual.class),
                    @Spec(path = "portsDisplayPort", params = "portsDisplayPortGT", paramSeparator = ',', spec = GreaterThanOrEqual.class),
                    @Spec(path = "portsDisplayPort", params = "portsDisplayPortLT", paramSeparator = ',', spec = LessThanOrEqual.class),
                    @Spec(path = "portsMiniDisplayPort", params = "portsMiniDisplayPortGT", paramSeparator = ',', spec = GreaterThanOrEqual.class),
                    @Spec(path = "portsMiniDisplayPort", params = "portsMiniDisplayPortLT", paramSeparator = ',', spec = LessThanOrEqual.class),
                    @Spec(path = "frameSync", params = "fs", paramSeparator = ',', spec = In.class),
            }) Specification<Gpu> spec,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "page_size", defaultValue = "10") int pageSize,
            @RequestParam(value = "sort_by", defaultValue = "id") String sortBy,
            HttpServletRequest request) {

        Pageable pageable = getPageable(page, pageSize, sortBy);
        Page<Gpu> gpus = gpuService.findAll(spec, pageable);

        List<GpuDTO> gpuDTOList = gpus.stream().map(GpuDTO::build).collect(Collectors.toList());

        return new ResponseEntity<>(new GpuListResponse(gpuDTOList, PageMeta.build(gpus, request.getRequestURI())), HttpStatus.OK);
    }


    @GetMapping("/gpu/{id}")
    public ResponseEntity<AppResponse> getGpuById(@PathVariable Long id,
                                                      HttpServletRequest request) {

        Optional<Gpu> gpu = gpuService.findById(id);


        if (gpu.isPresent()) {

            Optional<GpuDTO> gpuDTO = gpu.map(GpuDTO::build);

            return new ResponseEntity<>(new GpuResponse(gpuDTO.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ErrorResponse("Nie znaleziono żądanego GPU."), HttpStatus.NOT_FOUND);
        }

    }
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/gpu/{id}")
    public ResponseEntity<?> updateGpu(@PathVariable Long id, @Valid @RequestBody GpuRequest gpuRequest) {

        if(!gpuService.existsById(id)){
            return new ResponseEntity<>(new ErrorResponse("Nie znaleziono żądanego GPU."), HttpStatus.NOT_FOUND);
        }

        gpuService.updateGpu(gpuRequest, id);

        return ResponseEntity.ok(new SuccessResponse("Aktualizacja przebiegła pomyślnie"));

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/gpu/{id}")
    public ResponseEntity<AppResponse> deleteGpu(@PathVariable Long id) {

        Optional<Gpu> gpu = gpuService.findById(id);

        if (gpu.isPresent()) {

            gpuService.deleteGpu(gpu.get());
            return ResponseEntity.ok(new SuccessResponse("GPU pomyślnie usunięty."));
        } else {
            return new ResponseEntity<>(new ErrorResponse("Nie znaleziono żądanego GPU"), HttpStatus.NOT_FOUND);
        }
    }

    private Pageable getPageable(int page, int pageSize, String sortBy) {
        Sort.Direction direction;

        if (page <= 0)
            page = 1;

        if (pageSize <= 0)
            pageSize = 5;

        if(sortBy.substring(0,1).equals("-")) {
            direction = Sort.Direction.DESC;
            sortBy = sortBy.substring(1);
        } else {
            direction = Sort.Direction.ASC;
        }

        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, direction, sortBy);
        return pageRequest;
    }


}
