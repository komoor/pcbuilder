package pl.komoor.pcbuilder.repository.productsDetails.cases;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.komoor.pcbuilder.models.productDetails.cases.CaseType;
import pl.komoor.pcbuilder.models.products.Case;

import java.util.List;
import java.util.Optional;

public interface CaseTypeRepository extends JpaRepository<CaseType, Long> {

    List<CaseType> findAll();

    Optional<CaseType> findById(Long id);

}
