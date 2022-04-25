package pl.komoor.pcbuilder.services.impl.productsDetails.psu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.komoor.pcbuilder.models.productDetails.psu.PsuType;
import pl.komoor.pcbuilder.repository.productsDetails.psu.PsuTypeRepository;
import pl.komoor.pcbuilder.services.productsDetails.psu.PsuTypeService;

import java.util.List;
import java.util.Optional;

@Service
public class PsuTypeServiceImpl implements PsuTypeService {

    @Autowired
    PsuTypeRepository psuTypeRepository;

    @Override
    public List<PsuType> findAll() {
        return psuTypeRepository.findAll();
    }

    @Override
    public Optional<PsuType> findById(Long id) {
        return psuTypeRepository.findById(id);
    }
}
