package pl.komoor.pcbuilder.services.impl.productsDetails.motherboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.komoor.pcbuilder.models.productDetails.motherboard.MotherboardFormFactor;
import pl.komoor.pcbuilder.repository.productsDetails.motherboard.MotherboardFormFactorRepository;
import pl.komoor.pcbuilder.services.productsDetails.motherboard.MotherboardFormFactorService;

import java.util.List;
import java.util.Optional;

@Service
public class MotherboardFormFactorServiceImpl implements MotherboardFormFactorService {

    @Autowired
    MotherboardFormFactorRepository motherboardFormFactorRepository;


    @Override
    public List<MotherboardFormFactor> findAll() {
        return motherboardFormFactorRepository.findAll();
    }

    @Override
    public Optional<MotherboardFormFactor> findById(Long id) {
        return motherboardFormFactorRepository.findById(id);
    }
}
