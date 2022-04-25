package pl.komoor.pcbuilder.repository.productsDetails.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.komoor.pcbuilder.models.productDetails.storage.StorageFormFactor;

import java.util.List;
import java.util.Optional;

public interface StorageFormFactorRepository extends JpaRepository<StorageFormFactor, Long> {

    List<StorageFormFactor> findAll();

    Optional<StorageFormFactor> findById(Long id);


}
