package pl.komoor.pcbuilder.repository.productsDetails.gpu;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.komoor.pcbuilder.models.productDetails.gpu.GpuChipset;

import java.util.List;
import java.util.Optional;

public interface GpuChipsetRepository extends JpaRepository<GpuChipset, Long> {

    List<GpuChipset> findAll();

    Optional<GpuChipset> findById(Long id);
}
