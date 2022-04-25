package pl.komoor.pcbuilder.services.productsDetails.psu;

import pl.komoor.pcbuilder.models.productDetails.psu.PsuType;

import java.util.List;
import java.util.Optional;

public interface PsuTypeService {

    List<PsuType> findAll();
    Optional<PsuType> findById(Long id);

}
