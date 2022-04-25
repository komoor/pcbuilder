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
import pl.komoor.pcbuilder.models.products.Cpu;
import pl.komoor.pcbuilder.models.products.Memory;
import pl.komoor.pcbuilder.payload.request.products.CpuRequest;
import pl.komoor.pcbuilder.payload.request.products.MemoryRequest;
import pl.komoor.pcbuilder.payload.response.basic.AppResponse;
import pl.komoor.pcbuilder.payload.response.basic.ErrorResponse;
import pl.komoor.pcbuilder.payload.response.basic.PageMeta;
import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.payload.response.products.cpu.CpuListResponse;
import pl.komoor.pcbuilder.payload.response.products.cpu.CpuResponse;
import pl.komoor.pcbuilder.payload.response.products.memory.MemoryListResponse;
import pl.komoor.pcbuilder.payload.response.products.memory.MemoryResponse;
import pl.komoor.pcbuilder.services.dto.products.CpuDTO;
import pl.komoor.pcbuilder.services.dto.products.MemoryDTO;
import pl.komoor.pcbuilder.services.products.memory.MemoryService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/products")
public class MemoryProductController {

    @Autowired
    MemoryService memoryService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/memory")
    public ResponseEntity<AppResponse> addMemory(@Valid @RequestBody MemoryRequest memoryRequest) {


        if (memoryService.existsByModel(memoryRequest.getModel())) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponse("Wybrany model już istnieje w bazie"));
        }

        memoryService.saveMemoryRequest(memoryRequest);

        return ResponseEntity.ok(new SuccessResponse("Pomyślnie dodano produkt " + memoryRequest.getModel()));
    }

    @GetMapping("/memory")
    public ResponseEntity<AppResponse> getAllMemories(
            @Join(path = "manufacturerId", alias="mId")
            @Join(path = "memoryTypeId", alias="mtId")
            @Join(path = "memoryTimmingId", alias="mtiId")
            @Join(path = "productId", alias="prodId")
            @And({
                    @Spec(path = "prodId.id", params = "prodID", paramSeparator = ',', spec = In.class),
                    @Spec(path = "mId.id", params = "mID", paramSeparator = ',', spec = In.class),
                    @Spec(path = "mtId.id", params = "mtID", paramSeparator = ',', spec = In.class),
                    @Spec(path = "mtiId.id", params = "mtiID", paramSeparator = ',', spec = In.class),
                    @Spec(path = "numberOfModules", params = "nomGT", paramSeparator = ',', spec = GreaterThanOrEqual.class),
                    @Spec(path = "numberOfModules", params = "nomLT", paramSeparator = ',', spec = LessThanOrEqual.class),
                    @Spec(path = "memoryGB", params = "mGBCGT", paramSeparator = ',', spec = GreaterThanOrEqual.class),
                    @Spec(path = "memoryGB", params = "mGBCLT", paramSeparator = ',', spec = LessThanOrEqual.class),
                    @Spec(path = "casLatency", params = "clCGT", paramSeparator = ',', spec = GreaterThanOrEqual.class),
                    @Spec(path = "casLatency", params = "clCLT", paramSeparator = ',', spec = LessThanOrEqual.class),
                    @Spec(path = "voltage", params = "vGT", paramSeparator = ',', spec = GreaterThanOrEqual.class),
                    @Spec(path = "voltage", params = "vLT", paramSeparator = ',', spec = LessThanOrEqual.class),
                    @Spec(path = "heatSpreader", params = "hs", paramSeparator = ',', spec = In.class)

            }) Specification<Memory> spec,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "page_size", defaultValue = "10") int pageSize,
            @RequestParam(value = "sort_by", defaultValue = "id") String sortBy,
            HttpServletRequest request) {

        Pageable pageable = getPageable(page, pageSize, sortBy);
        Page<Memory> memories = memoryService.findAllMemories(spec, pageable);

        List<MemoryDTO> memoryDTOList = memories.stream().map(MemoryDTO::build).collect(Collectors.toList());

        return new ResponseEntity<>(new MemoryListResponse(memoryDTOList, PageMeta.build(memories, request.getRequestURI())), HttpStatus.OK);
    }


    @GetMapping("/memory/{id}")
    public ResponseEntity<AppResponse> getMemoryById(@PathVariable Long id,
                                                  HttpServletRequest request) {

        Optional<Memory> memory = memoryService.findById(id);


        if (memory.isPresent()) {

            Optional<MemoryDTO> memoryDTO = memory.map(MemoryDTO::build);

            return new ResponseEntity<>(new MemoryResponse(memoryDTO.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ErrorResponse("Nie znaleziono żądanej pamięci."), HttpStatus.NOT_FOUND);
        }

    }
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/memory/{id}")
    public ResponseEntity<?> updateMemory(@PathVariable Long id, @Valid @RequestBody MemoryRequest memoryRequest) {

        if(!memoryService.existsById(id)){
            return new ResponseEntity<>(new ErrorResponse("Nie znaleziono żądanej pamięci"), HttpStatus.NOT_FOUND);
        }

        memoryService.updateMemory(memoryRequest, id);

        return ResponseEntity.ok(new SuccessResponse("Aktualizacja przebiegła pomyślnie"));

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/memory/{id}")
    public ResponseEntity<AppResponse> deleteMemory(@PathVariable Long id) {

        Optional<Memory> memory = memoryService.findById(id);

        if (memory.isPresent()) {

            memoryService.deleteMemory(memory.get());
            return ResponseEntity.ok(new SuccessResponse("Pamięć pomyślnie usunięta."));
        } else {
            return new ResponseEntity<>(new ErrorResponse("Nie znaleziono żądanego CPU"), HttpStatus.NOT_FOUND);
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
