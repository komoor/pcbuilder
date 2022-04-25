package pl.komoor.pcbuilder.services.products.storage;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import pl.komoor.pcbuilder.models.products.Memory;
import pl.komoor.pcbuilder.models.products.Storage;
import pl.komoor.pcbuilder.payload.request.products.MemoryRequest;
import pl.komoor.pcbuilder.payload.request.products.StorageRequest;

import java.util.List;
import java.util.Optional;

public interface StorageService {

    public void saveStorage(StorageRequest storageRequest);

    public void updateStorage(StorageRequest storageRequest, Long id);

    public void deleteStorage(Storage storage);

    List<Storage> findAll();

    Page<Storage> findAll(Specification<Storage> spec, Pageable pageRequest);

    Optional<Storage> findById(Long id);

    boolean existsById(Long id);

    boolean existsByModel(String model);


}
