package pl.komoor.pcbuilder.services.productsDetails.cpuCooler;

import pl.komoor.pcbuilder.models.productDetails.cpuCooler.CpuCoolerWaterCooled;

import java.util.List;
import java.util.Optional;

public interface CpuCoolerWaterCooledService {

    List<CpuCoolerWaterCooled> findAll();

    Optional<CpuCoolerWaterCooled> findById(Long id);


}
