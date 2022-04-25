package pl.komoor.pcbuilder.repository.productsDetails.motherboard;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.komoor.pcbuilder.models.productDetails.InterfaceType;
import pl.komoor.pcbuilder.models.productDetails.motherboard.MotherboardFormFactor;

import java.util.List;
import java.util.Optional;

public interface MotherboardFormFactorRepository extends JpaRepository<MotherboardFormFactor, Long> {

    List<MotherboardFormFactor> findAll();

    Optional<MotherboardFormFactor> findById(Long id);

}
