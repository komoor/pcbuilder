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
import pl.komoor.pcbuilder.models.products.CpuCooler;
import pl.komoor.pcbuilder.models.products.Storage;
import pl.komoor.pcbuilder.payload.request.products.CpuCoolerRequest;
import pl.komoor.pcbuilder.payload.request.products.StorageRequest;
import pl.komoor.pcbuilder.payload.response.basic.AppResponse;
import pl.komoor.pcbuilder.payload.response.basic.ErrorResponse;
import pl.komoor.pcbuilder.payload.response.basic.PageMeta;
import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.payload.response.products.cpuCooler.CpuCoolerListResponse;
import pl.komoor.pcbuilder.payload.response.products.cpuCooler.CpuCoolerResponse;
import pl.komoor.pcbuilder.payload.response.products.storage.StorageListResponse;
import pl.komoor.pcbuilder.payload.response.products.storage.StorageResponse;
import pl.komoor.pcbuilder.services.dto.products.CpuCoolerDTO;
import pl.komoor.pcbuilder.services.dto.products.StorageDTO;
import pl.komoor.pcbuilder.services.products.cpuCooler.CpuCoolerService;
import pl.komoor.pcbuilder.services.products.storage.StorageService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/products")
public class CpuCoolerProductController {

    @Autowired
    CpuCoolerService cpuCoolerService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/cpuCooler")
    public ResponseEntity<AppResponse> addCpuCooler(@Valid @RequestBody CpuCoolerRequest cpuCoolerRequest) {


        if (cpuCoolerService.existsByModel(cpuCoolerRequest.getModel())) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponse("Wybrany model już istnieje w bazie"));
        }

        cpuCoolerService.saveCpuCooler(cpuCoolerRequest);

        return ResponseEntity.ok(new SuccessResponse("Pomyślnie dodano produkt " + cpuCoolerRequest.getModel()));
    }


    @GetMapping("/cpuCooler")
    public ResponseEntity<AppResponse> getAllCpuCoolers(
            @Join(path = "manufacturerId", alias="mId")
            @Join(path = "socketId", alias="sId")
            @Join(path = "waterCooledId", alias="wcId")
            @Join(path = "productId", alias="prodId")
            @And({
                    @Spec(path = "prodId.id", params = "prodID", paramSeparator = ',', spec = In.class),
                    @Spec(path = "mId.id", params = "mID", paramSeparator = ',', spec = In.class),
                    @Spec(path = "sId.id", params = "sID", paramSeparator = ',', spec = In.class),
                    @Spec(path = "wcId.id", params = "wcID", paramSeparator = ',', spec = In.class),
                    @Spec(path = "fanless", params = "fID", paramSeparator = ',', spec = In.class),
                    @Spec(path = "height", params = "heightGT", paramSeparator = ',', spec = GreaterThanOrEqual.class),
                    @Spec(path = "height", params = "heightLT", paramSeparator = ',', spec = LessThanOrEqual.class),
            }) Specification<CpuCooler> spec,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "page_size", defaultValue = "10") int pageSize,
            @RequestParam(value = "sort_by", defaultValue = "id") String sortBy,
            HttpServletRequest request) {

        Pageable pageable = getPageable(page, pageSize, sortBy);
        Page<CpuCooler> cpuCoolers = cpuCoolerService.findAll(spec, pageable);

        List<CpuCoolerDTO> cpuCoolerDTOList = cpuCoolers.stream().map(CpuCoolerDTO::build).collect(Collectors.toList());

        return new ResponseEntity<>(new CpuCoolerListResponse(cpuCoolerDTOList, PageMeta.build(cpuCoolers, request.getRequestURI())), HttpStatus.OK);
    }


    @GetMapping("/cpuCooler/{id}")
    public ResponseEntity<AppResponse> getCpuCoolerById(@PathVariable Long id,
                                                      HttpServletRequest request) {

        Optional<CpuCooler> cpuCooler = cpuCoolerService.findById(id);


        if (cpuCooler.isPresent()) {

            Optional<CpuCoolerDTO> cpuCoolerDTO = cpuCooler.map(CpuCoolerDTO::build);

            return new ResponseEntity<>(new CpuCoolerResponse(cpuCoolerDTO.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ErrorResponse("Nie znaleziono żądanego Cpu Cooler."), HttpStatus.NOT_FOUND);
        }

    }
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/cpuCooler/{id}")
    public ResponseEntity<?> updateCpuCooler(@PathVariable Long id, @Valid @RequestBody CpuCoolerRequest cpuCoolerRequest) {

        if(!cpuCoolerService.existsById(id)){
            return new ResponseEntity<>(new ErrorResponse("Nie znaleziono żądanego Cpu Cooler."), HttpStatus.NOT_FOUND);
        }

        cpuCoolerService.updateCpuCooler(cpuCoolerRequest, id);

        return ResponseEntity.ok(new SuccessResponse("Aktualizacja przebiegła pomyślnie"));

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/cpuCooler/{id}")
    public ResponseEntity<AppResponse> deleteCpuCooler(@PathVariable Long id) {

        Optional<CpuCooler> cpuCooler = cpuCoolerService.findById(id);

        if (cpuCooler.isPresent()) {

            cpuCoolerService.deleteCpuCooler(cpuCooler.get());
            return ResponseEntity.ok(new SuccessResponse("Cooler pomyślnie usunięty."));
        } else {
            return new ResponseEntity<>(new ErrorResponse("Nie znaleziono żądanego Cpu Cooler"), HttpStatus.NOT_FOUND);
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
