package pl.komoor.pcbuilder.services.impl.productsDetails.motherboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.komoor.pcbuilder.models.productDetails.motherboard.MotherboardChipset;
import pl.komoor.pcbuilder.repository.productsDetails.motherboard.MotherboardChipsetRepository;
import pl.komoor.pcbuilder.services.productsDetails.motherboard.MotherboardChipsetService;

import java.util.List;
import java.util.Optional;

@Service
public class MotherboardChipsetServiceImpl implements MotherboardChipsetService {

    @Autowired
    MotherboardChipsetRepository motherboardChipsetRepository;

    @Override
    public List<MotherboardChipset> findAll() {
        return motherboardChipsetRepository.findAll();
    }

    @Override
    public Optional<MotherboardChipset> findById(Long id) {
        return motherboardChipsetRepository.findById(id);
    }
}
