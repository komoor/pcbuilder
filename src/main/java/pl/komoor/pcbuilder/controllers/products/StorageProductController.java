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
import pl.komoor.pcbuilder.models.products.Memory;
import pl.komoor.pcbuilder.models.products.Storage;
import pl.komoor.pcbuilder.payload.request.products.MemoryRequest;
import pl.komoor.pcbuilder.payload.request.products.StorageRequest;
import pl.komoor.pcbuilder.payload.response.basic.AppResponse;
import pl.komoor.pcbuilder.payload.response.basic.ErrorResponse;
import pl.komoor.pcbuilder.payload.response.basic.PageMeta;
import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.payload.response.products.memory.MemoryListResponse;
import pl.komoor.pcbuilder.payload.response.products.memory.MemoryResponse;
import pl.komoor.pcbuilder.payload.response.products.storage.StorageListResponse;
import pl.komoor.pcbuilder.payload.response.products.storage.StorageResponse;
import pl.komoor.pcbuilder.services.dto.products.MemoryDTO;
import pl.komoor.pcbuilder.services.dto.products.StorageDTO;
import pl.komoor.pcbuilder.services.products.memory.MemoryService;
import pl.komoor.pcbuilder.services.products.storage.StorageService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/products")
public class StorageProductController {

    @Autowired
    StorageService storageService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/storage")
    public ResponseEntity<AppResponse> addStorage(@Valid @RequestBody StorageRequest storageRequest) {


        if (storageService.existsByModel(storageRequest.getModel())) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponse("Wybrany model już istnieje w bazie"));
        }

        storageService.saveStorage(storageRequest);

        return ResponseEntity.ok(new SuccessResponse("Pomyślnie dodano produkt " + storageRequest.getModel()));
    }


    @GetMapping("/storage")
    public ResponseEntity<AppResponse> getAllStorages(
            @Join(path = "manufacturerId", alias="mId")
            @Join(path = "storageTypeId", alias="stId")
            @Join(path = "storageFormFactorId", alias="sffId")
            @Join(path = "interfaceTypeId", alias="siId")
            @Join(path = "productId", alias="prodId")
            @And({
                    @Spec(path = "prodId.id", params = "prodID", paramSeparator = ',', spec = In.class),
                    @Spec(path = "mId.id", params = "mID", paramSeparator = ',', spec = In.class),
                    @Spec(path = "stId.id", params = "stID", paramSeparator = ',', spec = In.class),
                    @Spec(path = "sffId.id", params = "sffID", paramSeparator = ',', spec = In.class),
                    @Spec(path = "siId.id", params = "siID", paramSeparator = ',', spec = In.class),
                    @Spec(path = "nmve", params = "nmve", paramSeparator = ',', spec = In.class),
                    @Spec(path = "capacity", params = "capacityGT", paramSeparator = ',', spec = GreaterThanOrEqual.class),
                    @Spec(path = "capacity", params = "capacityLT", paramSeparator = ',', spec = LessThanOrEqual.class),
                    @Spec(path = "cache", params = "cacheGT", paramSeparator = ',', spec = GreaterThanOrEqual.class),
                    @Spec(path = "cache", params = "cacheLT", paramSeparator = ',', spec = LessThanOrEqual.class),
            }) Specification<Storage> spec,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "page_size", defaultValue = "10") int pageSize,
            @RequestParam(value = "sort_by", defaultValue = "id") String sortBy,
            HttpServletRequest request) {

        Pageable pageable = getPageable(page, pageSize, sortBy);
        Page<Storage> storages = storageService.findAll(spec, pageable);

        List<StorageDTO> storageDTOList = storages.stream().map(StorageDTO::build).collect(Collectors.toList());

        return new ResponseEntity<>(new StorageListResponse(storageDTOList, PageMeta.build(storages, request.getRequestURI())), HttpStatus.OK);
    }


    @GetMapping("/storage/{id}")
    public ResponseEntity<AppResponse> getStorageById(@PathVariable Long id,
                                                     HttpServletRequest request) {

        Optional<Storage> storage = storageService.findById(id);


        if (storage.isPresent()) {

            Optional<StorageDTO> storageDTO = storage.map(StorageDTO::build);

            return new ResponseEntity<>(new StorageResponse(storageDTO.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ErrorResponse("Nie znaleziono żądanego dysku."), HttpStatus.NOT_FOUND);
        }

    }
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/storage/{id}")
    public ResponseEntity<?> updateStorage(@PathVariable Long id, @Valid @RequestBody StorageRequest storageRequest) {

        if(!storageService.existsById(id)){
            return new ResponseEntity<>(new ErrorResponse("Nie znaleziono żądanego dysku."), HttpStatus.NOT_FOUND);
        }

        storageService.updateStorage(storageRequest, id);

        return ResponseEntity.ok(new SuccessResponse("Aktualizacja przebiegła pomyślnie"));

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/storage/{id}")
    public ResponseEntity<AppResponse> deleteStorage(@PathVariable Long id) {

        Optional<Storage> storage = storageService.findById(id);

        if (storage.isPresent()) {

            storageService.deleteStorage(storage.get());
            return ResponseEntity.ok(new SuccessResponse("Dysk pomyślnie usunięty."));
        } else {
            return new ResponseEntity<>(new ErrorResponse("Nie znaleziono żądanego dysku"), HttpStatus.NOT_FOUND);
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
