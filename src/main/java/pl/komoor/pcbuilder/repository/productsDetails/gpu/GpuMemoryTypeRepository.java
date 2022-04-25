package pl.komoor.pcbuilder.repository.productsDetails.gpu;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.komoor.pcbuilder.models.productDetails.gpu.GpuMemoryType;

import java.util.List;
import java.util.Optional;

public interface GpuMemoryTypeRepository extends JpaRepository<GpuMemoryType, Long> {

    List<GpuMemoryType> findAll();

    Optional<GpuMemoryType> findById(Long id);

}
