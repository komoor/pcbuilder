package pl.komoor.pcbuilder.repository.productsDetails.psu;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.komoor.pcbuilder.models.productDetails.motherboard.MotherboardFormFactor;
import pl.komoor.pcbuilder.models.productDetails.psu.PsuType;

import java.util.List;
import java.util.Optional;

public interface PsuTypeRepository extends JpaRepository<PsuType, Long> {

    List<PsuType> findAll();
    Optional<PsuType> findById(Long id);

}
