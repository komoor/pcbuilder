package pl.komoor.pcbuilder.services.productsDetails.cpu;

import pl.komoor.pcbuilder.models.productDetails.cpu.CpuSocket;
import pl.komoor.pcbuilder.models.productDetails.cpu.IntegratedGraphic;

import java.util.List;
import java.util.Optional;

public interface CpuIntegratedGraphicService {

    Optional<IntegratedGraphic> findById(Long id);

    List<IntegratedGraphic> findAll();

}
