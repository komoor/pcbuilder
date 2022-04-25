package pl.komoor.pcbuilder.services.productsDetails;

import pl.komoor.pcbuilder.models.productDetails.InterfaceType;

import java.util.List;
import java.util.Optional;

public interface InterfaceTypeService {

    List<InterfaceType> findAll();

    Optional<InterfaceType> findById(Long id);

}
