package pl.komoor.pcbuilder.services.productsDetails.gpu;

import pl.komoor.pcbuilder.models.productDetails.gpu.GpuChipset;

import java.util.List;
import java.util.Optional;

public interface GpuChipsetService {

    List<GpuChipset> findAll();

    Optional<GpuChipset> findById(Long id);


}
