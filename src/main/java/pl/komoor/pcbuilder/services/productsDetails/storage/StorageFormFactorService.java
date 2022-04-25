package pl.komoor.pcbuilder.services.productsDetails.storage;

import pl.komoor.pcbuilder.models.productDetails.storage.StorageFormFactor;

import java.util.List;
import java.util.Optional;

public interface StorageFormFactorService {

    List<StorageFormFactor> findAll();

    Optional<StorageFormFactor> findById(Long id);


}
