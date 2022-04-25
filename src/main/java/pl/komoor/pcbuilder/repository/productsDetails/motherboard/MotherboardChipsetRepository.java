package pl.komoor.pcbuilder.repository.productsDetails.motherboard;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.komoor.pcbuilder.models.productDetails.motherboard.MotherboardChipset;

import java.util.List;
import java.util.Optional;

public interface MotherboardChipsetRepository extends JpaRepository<MotherboardChipset, Long> {

    List<MotherboardChipset> findAll();
    Optional<MotherboardChipset> findById(Long id);

}
