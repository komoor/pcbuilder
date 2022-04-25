package pl.komoor.pcbuilder.services.productsDetails.storage;

import pl.komoor.pcbuilder.models.productDetails.storage.StorageType;

import java.util.List;
import java.util.Optional;

public interface StorageTypeService {


    List<StorageType> findAll();

    Optional<StorageType> findById(Long id);
}
