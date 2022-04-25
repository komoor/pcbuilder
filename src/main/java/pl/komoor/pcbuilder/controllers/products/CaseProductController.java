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
import pl.komoor.pcbuilder.models.products.Case;
import pl.komoor.pcbuilder.models.products.CpuCooler;
import pl.komoor.pcbuilder.payload.request.products.CaseRequest;
import pl.komoor.pcbuilder.payload.response.basic.AppResponse;
import pl.komoor.pcbuilder.payload.response.basic.ErrorResponse;
import pl.komoor.pcbuilder.payload.response.basic.PageMeta;
import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.payload.response.products.cases.CaseListResponse;
import pl.komoor.pcbuilder.payload.response.products.cases.CaseResponse;
import pl.komoor.pcbuilder.services.dto.products.CaseDTO;
import pl.komoor.pcbuilder.services.products.cases.CaseService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/products")
public class CaseProductController {

    @Autowired
    CaseService caseService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/case")
    public ResponseEntity<AppResponse> addCase(@Valid @RequestBody CaseRequest caseRequest) {


        if (caseService.existsByModel(caseRequest.getModel())) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponse("Wybrany model już istnieje w bazie"));
        }

        caseService.saveCase(caseRequest);

        return ResponseEntity.ok(new SuccessResponse("Pomyślnie dodano produkt " + caseRequest.getModel()));
    }


    @GetMapping("/case")
    public ResponseEntity<AppResponse> getAllCases(
            @Join(path = "manufacturerId", alias="mId")
            @Join(path = "caseTypeId", alias="ctId")
            @Join(path = "motherboardFormFactorId", alias="mffId")
            @Join(path = "productId", alias="prodId")
            @And({
                    @Spec(path = "prodId.id", params = "prodID", paramSeparator = ',', spec = In.class),
                    @Spec(path = "mId.id", params = "mID", paramSeparator = ',', spec = In.class),
                    @Spec(path = "ctId.id", params = "ctID", paramSeparator = ',', spec = In.class),
                    @Spec(path = "mffId.id", params = "mffID", paramSeparator = ',', spec = In.class),
                    @Spec(path = "ex35bays", params = "ex35baysGT", paramSeparator = ',', spec = GreaterThanOrEqual.class),
                    @Spec(path = "ex35bays", params = "ex35baysLT", paramSeparator = ',', spec = LessThanOrEqual.class),
                    @Spec(path = "ex25bays", params = "ex25baysGT", paramSeparator = ',', spec = GreaterThanOrEqual.class),
                    @Spec(path = "ex25bays", params = "ex25baysLT", paramSeparator = ',', spec = LessThanOrEqual.class),
                    @Spec(path = "in35bays", params = "in35baysGT", paramSeparator = ',', spec = GreaterThanOrEqual.class),
                    @Spec(path = "in35bays", params = "in35baysLT", paramSeparator = ',', spec = LessThanOrEqual.class),
                    @Spec(path = "in25bays", params = "in25baysGT", paramSeparator = ',', spec = GreaterThanOrEqual.class),
                    @Spec(path = "in25bays", params = "in25baysLT", paramSeparator = ',', spec = LessThanOrEqual.class),
            }) Specification<Case> spec,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "page_size", defaultValue = "10") int pageSize,
            @RequestParam(value = "sort_by", defaultValue = "id") String sortBy,
            HttpServletRequest request) {

        Pageable pageable = getPageable(page, pageSize, sortBy);
        Page<Case> cases = caseService.findAll(spec, pageable);

        List<CaseDTO> caseDTOList = cases.stream().map(CaseDTO::build).collect(Collectors.toList());

        return new ResponseEntity<>(new CaseListResponse(caseDTOList, PageMeta.build(cases, request.getRequestURI())), HttpStatus.OK);
    }


    @GetMapping("/case/{id}")
    public ResponseEntity<AppResponse> getCaseById(@PathVariable Long id,
                                                        HttpServletRequest request) {

        Optional<Case> cases = caseService.findById(id);


        if (cases.isPresent()) {

            Optional<CaseDTO> caseDTO = cases.map(CaseDTO::build);

            return new ResponseEntity<>(new CaseResponse(caseDTO.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ErrorResponse("Nie znaleziono żądanej obudowy."), HttpStatus.NOT_FOUND);
        }

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/case/{id}")
    public ResponseEntity<?> updateCase(@PathVariable Long id, @Valid @RequestBody CaseRequest caseRequest) {

        if(!caseService.existsById(id)){
            return new ResponseEntity<>(new ErrorResponse("Nie znaleziono żądanej obudowy."), HttpStatus.NOT_FOUND);
        }

        caseService.updateCase(caseRequest, id);

        return ResponseEntity.ok(new SuccessResponse("Aktualizacja przebiegła pomyślnie"));

    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/case/{id}")
    public ResponseEntity<AppResponse> deleteCase(@PathVariable Long id) {

        Optional<Case> cases = caseService.findById(id);

        if (cases.isPresent()) {

            caseService.deleteCase(cases.get());
            return ResponseEntity.ok(new SuccessResponse("Obudowa pomyślnie usunięta."));
        } else {
            return new ResponseEntity<>(new ErrorResponse("Nie znaleziono żądanej obudowy"), HttpStatus.NOT_FOUND);
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
