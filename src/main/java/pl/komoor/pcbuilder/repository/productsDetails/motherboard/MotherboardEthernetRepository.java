package pl.komoor.pcbuilder.repository.productsDetails.motherboard;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.komoor.pcbuilder.models.productDetails.motherboard.MotherboardEthernet;

import java.util.List;
import java.util.Optional;

public interface MotherboardEthernetRepository extends JpaRepository<MotherboardEthernet, Long> {

    List<MotherboardEthernet> findAll();
    Optional<MotherboardEthernet> findById(Long id);

}
