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
import pl.komoor.pcbuilder.models.products.Psu;
import pl.komoor.pcbuilder.models.products.Storage;
import pl.komoor.pcbuilder.payload.request.products.PsuRequest;
import pl.komoor.pcbuilder.payload.request.products.StorageRequest;
import pl.komoor.pcbuilder.payload.response.basic.AppResponse;
import pl.komoor.pcbuilder.payload.response.basic.ErrorResponse;
import pl.komoor.pcbuilder.payload.response.basic.PageMeta;
import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.payload.response.products.psu.PsuListResponse;
import pl.komoor.pcbuilder.payload.response.products.psu.PsuResponse;
import pl.komoor.pcbuilder.payload.response.products.storage.StorageListResponse;
import pl.komoor.pcbuilder.payload.response.products.storage.StorageResponse;
import pl.komoor.pcbuilder.services.dto.products.PsuDTO;
import pl.komoor.pcbuilder.services.dto.products.StorageDTO;
import pl.komoor.pcbuilder.services.products.psu.PsuService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/products")
public class PsuProductController {

    @Autowired
    PsuService psuService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/psu")
    public ResponseEntity<AppResponse> addPsu(@Valid @RequestBody PsuRequest psuRequest) {


        if (psuService.existsByModel(psuRequest.getModel())) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponse("Wybrany model już istnieje w bazie"));
        }

        psuService.savePsu(psuRequest);

        return ResponseEntity.ok(new SuccessResponse("Pomyślnie dodano produkt " + psuRequest.getModel()));
    }


    @GetMapping("/psu")
    public ResponseEntity<AppResponse> getAllPsus(
            @Join(path = "manufacturerId", alias="mId")
            @Join(path = "psuEfficiencyRatingId", alias="perId")
            @Join(path = "psuTypeId", alias="ptId")
            @Join(path = "productId", alias="prodId")
            @And({
                    @Spec(path = "prodId.id", params = "prodID", paramSeparator = ',', spec = In.class),
                    @Spec(path = "mId.id", params = "mID", paramSeparator = ',', spec = In.class),
                    @Spec(path = "perId.id", params = "perID", paramSeparator = ',', spec = In.class),
                    @Spec(path = "ptId.id", params = "ptID", paramSeparator = ',', spec = In.class),
                    @Spec(path = "modular", params = "modID", paramSeparator = ',', spec = In.class),
                    @Spec(path = "wattage", params = "wattageGT", paramSeparator = ',', spec = GreaterThanOrEqual.class),
                    @Spec(path = "wattage", params = "wattageLT", paramSeparator = ',', spec = LessThanOrEqual.class),
                    @Spec(path = "pcie8pin", params = "pcie8pinGT", paramSeparator = ',', spec = GreaterThanOrEqual.class),
                    @Spec(path = "pcie8pin", params = "pcie8pinLT", paramSeparator = ',', spec = LessThanOrEqual.class),
                    @Spec(path = "pcie62pin", params = "pcie62pinGT", paramSeparator = ',', spec = GreaterThanOrEqual.class),
                    @Spec(path = "pcie62pin", params = "pcie62pinLT", paramSeparator = ',', spec = LessThanOrEqual.class),
                    @Spec(path = "pcie6pin", params = "pcie6pinGT", paramSeparator = ',', spec = GreaterThanOrEqual.class),
                    @Spec(path = "pcie6pin", params = "pcie6pinLT", paramSeparator = ',', spec = LessThanOrEqual.class),
                    @Spec(path = "sata", params = "sataGT", paramSeparator = ',', spec = GreaterThanOrEqual.class),
                    @Spec(path = "sata", params = "sataLT", paramSeparator = ',', spec = LessThanOrEqual.class),
                    @Spec(path = "molex4pin", params = "molex4pinGT", paramSeparator = ',', spec = GreaterThanOrEqual.class),
                    @Spec(path = "molex4pin", params = "molex4pinLT", paramSeparator = ',', spec = LessThanOrEqual.class),
            }) Specification<Psu> spec,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "page_size", defaultValue = "10") int pageSize,
            @RequestParam(value = "sort_by", defaultValue = "id") String sortBy,
            HttpServletRequest request) {

        Pageable pageable = getPageable(page, pageSize, sortBy);
        Page<Psu> psus = psuService.findAll(spec, pageable);

        List<PsuDTO> psuDTOList = psus.stream().map(PsuDTO::build).collect(Collectors.toList());

        return new ResponseEntity<>(new PsuListResponse(psuDTOList, PageMeta.build(psus, request.getRequestURI())), HttpStatus.OK);
    }


    @GetMapping("/psu/{id}")
    public ResponseEntity<AppResponse> getPsuById(@PathVariable Long id,
                                                      HttpServletRequest request) {

        Optional<Psu> psu = psuService.findById(id);


        if (psu.isPresent()) {

            Optional<PsuDTO> psuDTO = psu.map(PsuDTO::build);

            return new ResponseEntity<>(new PsuResponse(psuDTO.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ErrorResponse("Nie znaleziono żądanego PSU."), HttpStatus.NOT_FOUND);
        }

    }
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/psu/{id}")
    public ResponseEntity<?> updatePsu(@PathVariable Long id, @Valid @RequestBody PsuRequest psuRequest) {

        if(!psuService.existsById(id)){
            return new ResponseEntity<>(new ErrorResponse("Nie znaleziono żądanego PSU."), HttpStatus.NOT_FOUND);
        }

        psuService.updatePsu(psuRequest, id);

        return ResponseEntity.ok(new SuccessResponse("Aktualizacja przebiegła pomyślnie"));

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/psu/{id}")
    public ResponseEntity<AppResponse> deletePsu(@PathVariable Long id) {

        Optional<Psu> psu = psuService.findById(id);

        if (psu.isPresent()) {

            psuService.deletePsu(psu.get());
            return ResponseEntity.ok(new SuccessResponse("PSU pomyślnie usunięte."));
        } else {
            return new ResponseEntity<>(new ErrorResponse("Nie znaleziono żądanego PSU"), HttpStatus.NOT_FOUND);
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
