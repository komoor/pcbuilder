package pl.komoor.pcbuilder.repository.productsDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.komoor.pcbuilder.models.productDetails.InterfaceType;

import java.util.List;
import java.util.Optional;

public interface InterfaceTypeRepository extends JpaRepository<InterfaceType, Long> {

    List<InterfaceType> findAll();

    Optional<InterfaceType> findById(Long id);
}
