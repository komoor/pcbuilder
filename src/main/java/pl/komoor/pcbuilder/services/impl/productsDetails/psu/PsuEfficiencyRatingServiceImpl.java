package pl.komoor.pcbuilder.services.impl.productsDetails.psu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.komoor.pcbuilder.models.productDetails.psu.PsuEfficiencyRating;
import pl.komoor.pcbuilder.repository.productsDetails.psu.PsuEfficiencyRatingRepository;
import pl.komoor.pcbuilder.services.productsDetails.psu.PsuEfficiencyRatingService;

import java.util.List;
import java.util.Optional;

@Service
public class PsuEfficiencyRatingServiceImpl implements PsuEfficiencyRatingService {

    @Autowired
    PsuEfficiencyRatingRepository psuEfficiencyRatingRepository;

    @Override
    public List<PsuEfficiencyRating> findAll() {
        return psuEfficiencyRatingRepository.findAll();
    }

    @Override
    public Optional<PsuEfficiencyRating> findById(Long id) {
        return psuEfficiencyRatingRepository.findById(id);
    }
}
