package pl.komoor.pcbuilder.repository.productsDetails.cpuCooler;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.komoor.pcbuilder.models.productDetails.cpuCooler.CpuCoolerWaterCooled;

import java.util.List;
import java.util.Optional;

public interface CpuCoolerWaterCooledRepository extends JpaRepository<CpuCoolerWaterCooled, Long> {

    List<CpuCoolerWaterCooled> findAll();

    Optional<CpuCoolerWaterCooled> findById(Long id);

}
