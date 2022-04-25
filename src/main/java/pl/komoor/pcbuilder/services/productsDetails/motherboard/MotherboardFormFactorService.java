package pl.komoor.pcbuilder.services.productsDetails.motherboard;

import pl.komoor.pcbuilder.models.productDetails.motherboard.MotherboardFormFactor;

import java.util.List;
import java.util.Optional;

public interface MotherboardFormFactorService {

    List<MotherboardFormFactor> findAll();

    Optional<MotherboardFormFactor> findById(Long id);

}
