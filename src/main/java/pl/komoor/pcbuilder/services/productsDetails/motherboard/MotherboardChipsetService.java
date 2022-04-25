package pl.komoor.pcbuilder.services.productsDetails.motherboard;

import pl.komoor.pcbuilder.models.productDetails.motherboard.MotherboardChipset;
import pl.komoor.pcbuilder.repository.productsDetails.motherboard.MotherboardChipsetRepository;

import java.util.List;
import java.util.Optional;

public interface MotherboardChipsetService {

    List<MotherboardChipset> findAll();
    Optional<MotherboardChipset> findById(Long id);


}
