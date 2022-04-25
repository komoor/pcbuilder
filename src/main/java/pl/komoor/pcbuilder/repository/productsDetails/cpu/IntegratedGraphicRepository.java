package pl.komoor.pcbuilder.repository.productsDetails.cpu;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.komoor.pcbuilder.models.productDetails.cpu.CpuSocket;
import pl.komoor.pcbuilder.models.productDetails.cpu.IntegratedGraphic;

import java.util.List;
import java.util.Optional;

public interface IntegratedGraphicRepository extends JpaRepository<IntegratedGraphic, Long> {


    List<IntegratedGraphic> findAll();

    Optional<IntegratedGraphic> findById(Long id);


}
