package pl.komoor.pcbuilder.services.impl.productsDetails.memory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.komoor.pcbuilder.models.productDetails.memory.MemoryTimming;
import pl.komoor.pcbuilder.repository.productsDetails.memory.MemoryTimmingRepository;
import pl.komoor.pcbuilder.services.productsDetails.memory.MemoryTimmingService;

import java.util.List;
import java.util.Optional;

@Service
public class MemoryTimmingServiceImpl implements MemoryTimmingService {

    @Autowired
    MemoryTimmingRepository memoryTimmingRepository;

    @Override
    public List<MemoryTimming> findAll() {
        return memoryTimmingRepository.findAll();
    }

    @Override
    public Optional<MemoryTimming> findById(Long id) {
        return memoryTimmingRepository.findById(id);
    }
}
