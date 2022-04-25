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
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import pl.komoor.pcbuilder.models.products.Cpu;
import pl.komoor.pcbuilder.payload.request.products.CpuRequest;
import pl.komoor.pcbuilder.payload.response.basic.*;
import pl.komoor.pcbuilder.payload.response.products.cpu.CpuListResponse;
import pl.komoor.pcbuilder.payload.response.products.cpu.CpuResponse;
import pl.komoor.pcbuilder.repository.products.CpuRepository;
import pl.komoor.pcbuilder.services.dto.products.CpuDTO;
import pl.komoor.pcbuilder.services.products.cpu.CpuService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/products")
public class CpuProductController {

    @Autowired
    CpuService cpuService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/cpu")
    public ResponseEntity<AppResponse> addCpu(@Valid @RequestBody CpuRequest cpuRequest) {


        if (cpuService.existsByModel(cpuRequest.getModel())) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponse("Wybrany model już istnieje w bazie"));
        }


        cpuService.saveCpuRequest(cpuRequest);

        return ResponseEntity.ok(new SuccessResponse("Pomyślnie dodano produkt " + cpuRequest.getModel()));
    }


    @GetMapping("/cpu")
    public ResponseEntity<AppResponse> getAllCpus(
                    @Join(path = "manufacturerId", alias="mId")
                    @Join(path = "socketId", alias="sId")
                    @Join(path = "integratedGraphic", alias="igId")
                    @Join(path = "productId", alias="prodId")
                    @And({
                            @Spec(path = "prodId.id", params = "prodID", paramSeparator = ',', spec = In.class),
                            @Spec(path = "mId.id", params = "mID", paramSeparator = ',', spec = In.class),
                            @Spec(path = "sId.id", params = "sID", paramSeparator = ',', spec = In.class),
                            @Spec(path = "igId.id", params = "igID", paramSeparator = ',', spec = In.class),
                            @Spec(path = "cores", params = "cGT", paramSeparator = ',', spec = GreaterThanOrEqual.class),
                            @Spec(path = "cores", params = "cLT", paramSeparator = ',', spec = LessThanOrEqual.class),
                            @Spec(path = "baseClock", params = "baCGT", paramSeparator = ',', spec = GreaterThanOrEqual.class),
                            @Spec(path = "baseClock", params = "baCLT", paramSeparator = ',', spec = LessThanOrEqual.class),
                            @Spec(path = "boostClock", params = "boCGT", paramSeparator = ',', spec = GreaterThanOrEqual.class),
                            @Spec(path = "boostClock", params = "boCLT", paramSeparator = ',', spec = LessThanOrEqual.class),
                            @Spec(path = "tdp", params = "tGT", paramSeparator = ',', spec = GreaterThanOrEqual.class),
                            @Spec(path = "tdp", params = "tLT", paramSeparator = ',', spec = LessThanOrEqual.class)

                    }) Specification<Cpu> spec,
                    @RequestParam(value = "page", defaultValue = "1") int page,
                    @RequestParam(value = "page_size", defaultValue = "10") int pageSize,
                    @RequestParam(value = "sort_by", defaultValue = "id") String sortBy,
            HttpServletRequest request) {

        Pageable pageable = getPageable(page, pageSize, sortBy);
        Page<Cpu> cpus = cpuService.findAllCpu(spec, pageable);

        List<CpuDTO> cpuDTOList = cpus.stream().map(CpuDTO::build).collect(Collectors.toList());


        return new ResponseEntity<>(new CpuListResponse(cpuDTOList, PageMeta.build(cpus, request.getRequestURI())), HttpStatus.OK);
    }

    @GetMapping("/cpu/{id}")
    public ResponseEntity<AppResponse> getCpuById(@PathVariable Long id,
                             HttpServletRequest request) {

        Optional<Cpu> cpu = cpuService.findById(id);



        if (cpu.isPresent()) {

            Optional<CpuDTO> cpus = cpu.map(CpuDTO::build);
            return new ResponseEntity<>(new CpuResponse(cpus.get()), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(new ErrorResponse("Nie znaleziono żądanego CPU"), HttpStatus.NOT_FOUND);
        }

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/cpu/{id}")
    public ResponseEntity<?> updateCpu(@PathVariable Long id, @Valid @RequestBody CpuRequest cpuRequest) {

        if(!cpuService.existsById(id)){
            return new ResponseEntity<>(new ErrorResponse("Nie znaleziono żądanego CPU"), HttpStatus.NOT_FOUND);
        }

        cpuService.updateCpu(cpuRequest, id);

        return ResponseEntity.ok(new SuccessResponse("Aktualizacja przebiegła pomyślnie"));

    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/cpu/{id}")
    public ResponseEntity<AppResponse> deleteCpu(@PathVariable Long id) {

        Optional<Cpu> cpu= cpuService.findById(id);

        if (cpu.isPresent()) {

            cpuService.deleteCpu(cpu.get());
            return ResponseEntity.ok(new SuccessResponse("Procesor pomyślnie usunięty"));
        } else {
            return new ResponseEntity<>(new ErrorResponse("Nie znaleziono żądanego CPU"), HttpStatus.NOT_FOUND);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AppResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(new ErrorResponse(errors) {
        }, HttpStatus.BAD_REQUEST);
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
