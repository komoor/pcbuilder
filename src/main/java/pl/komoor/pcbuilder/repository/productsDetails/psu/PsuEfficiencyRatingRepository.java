package pl.komoor.pcbuilder.repository.productsDetails.psu;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.komoor.pcbuilder.models.productDetails.psu.PsuEfficiencyRating;

import java.util.List;
import java.util.Optional;

public interface PsuEfficiencyRatingRepository extends JpaRepository<PsuEfficiencyRating, Long> {

    List<PsuEfficiencyRating> findAll();
    Optional<PsuEfficiencyRating> findById(Long id);

}
