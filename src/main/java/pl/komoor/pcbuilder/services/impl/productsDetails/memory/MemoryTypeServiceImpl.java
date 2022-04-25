package pl.komoor.pcbuilder.services.impl.productsDetails.memory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.komoor.pcbuilder.models.productDetails.memory.MemoryType;
import pl.komoor.pcbuilder.repository.productsDetails.memory.MemoryTypeRepository;
import pl.komoor.pcbuilder.services.productsDetails.memory.MemoryTypeService;

import java.util.List;
import java.util.Optional;

@Service
public class MemoryTypeServiceImpl implements MemoryTypeService {


    @Autowired
    MemoryTypeRepository memoryTypeRepository;


    @Override
    public Optional<MemoryType> findById(Long id) {
        return memoryTypeRepository.findById(id);
    }

    @Override
    public List<MemoryType> findAll() {
        return memoryTypeRepository.findAll();
    }
}
