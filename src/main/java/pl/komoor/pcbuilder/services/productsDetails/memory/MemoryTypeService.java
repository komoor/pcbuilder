package pl.komoor.pcbuilder.services.productsDetails.memory;

import pl.komoor.pcbuilder.models.productDetails.memory.MemoryType;

import java.util.List;
import java.util.Optional;

public interface MemoryTypeService {

    Optional<MemoryType> findById(Long id);

    List<MemoryType> findAll();
}
