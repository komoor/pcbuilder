package pl.komoor.pcbuilder.services.productsDetails.psu;

import pl.komoor.pcbuilder.models.productDetails.psu.PsuEfficiencyRating;

import java.util.List;
import java.util.Optional;

public interface PsuEfficiencyRatingService {

    List<PsuEfficiencyRating> findAll();
    Optional<PsuEfficiencyRating> findById(Long id);


}
