package pl.komoor.pcbuilder.services.productsDetails.motherboard;

import pl.komoor.pcbuilder.models.productDetails.motherboard.MotherboardEthernet;
import pl.komoor.pcbuilder.repository.productsDetails.motherboard.MotherboardEthernetRepository;

import java.util.List;
import java.util.Optional;

public interface MotherboardEthernetService {

    List<MotherboardEthernet> findAll();
    Optional<MotherboardEthernet> findById(Long id);

}
