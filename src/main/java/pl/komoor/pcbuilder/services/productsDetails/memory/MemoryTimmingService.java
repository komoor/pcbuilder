package pl.komoor.pcbuilder.services.productsDetails.memory;

import pl.komoor.pcbuilder.models.productDetails.memory.MemoryTimming;

import java.util.List;
import java.util.Optional;

public interface MemoryTimmingService {

    List<MemoryTimming> findAll();

    Optional<MemoryTimming> findById(Long id);

}
