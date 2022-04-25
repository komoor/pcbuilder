package pl.komoor.pcbuilder.repository.productsDetails.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.komoor.pcbuilder.models.productDetails.storage.StorageType;

import java.util.List;
import java.util.Optional;

public interface StorageTypeRepository extends JpaRepository<StorageType, Long> {

    List<StorageType> findAll();

    Optional<StorageType> findById(Long id);

}
