package pl.komoor.pcbuilder.services.productsDetails.gpu;

import pl.komoor.pcbuilder.models.productDetails.gpu.GpuMemoryType;

import java.util.List;
import java.util.Optional;

public interface GpuMemoryTypeService {

    List<GpuMemoryType> findAll();

    Optional<GpuMemoryType> findById(Long id);


}
