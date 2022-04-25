package pl.komoor.pcbuilder.services.productsDetails.cases;

import pl.komoor.pcbuilder.models.productDetails.cases.CaseType;
import pl.komoor.pcbuilder.models.products.Case;

import java.util.List;
import java.util.Optional;

public interface CaseTypeService {

    List<CaseType> findAll();

    Optional<CaseType> findById(Long id);

}
