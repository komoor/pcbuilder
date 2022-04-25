package pl.komoor.pcbuilder.services.impl.productsDetails.motherboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.komoor.pcbuilder.models.productDetails.motherboard.MotherboardEthernet;
import pl.komoor.pcbuilder.repository.productsDetails.motherboard.MotherboardEthernetRepository;
import pl.komoor.pcbuilder.services.productsDetails.motherboard.MotherboardEthernetService;

import java.util.List;
import java.util.Optional;

@Service
public class MotherboardEthernetServiceImpl implements MotherboardEthernetService {

    @Autowired
    MotherboardEthernetRepository motherboardEthernetRepository;

    @Override
    public List<MotherboardEthernet> findAll() {
        return motherboardEthernetRepository.findAll();
    }

    @Override
    public Optional<MotherboardEthernet> findById(Long id) {
        return motherboardEthernetRepository.findById(id);
    }
}
