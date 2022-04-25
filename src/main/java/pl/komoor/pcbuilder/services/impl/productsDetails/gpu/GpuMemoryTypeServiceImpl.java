package pl.komoor.pcbuilder.services.impl.productsDetails.gpu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.komoor.pcbuilder.models.productDetails.gpu.GpuMemoryType;
import pl.komoor.pcbuilder.repository.productsDetails.gpu.GpuMemoryTypeRepository;
import pl.komoor.pcbuilder.services.productsDetails.gpu.GpuMemoryTypeService;

import java.util.List;
import java.util.Optional;

@Service
public class GpuMemoryTypeServiceImpl implements GpuMemoryTypeService {

    @Autowired
    GpuMemoryTypeRepository gpuMemoryTypeRepository;

    @Override
    public List<GpuMemoryType> findAll() {
        return gpuMemoryTypeRepository.findAll();
    }

    @Override
    public Optional<GpuMemoryType> findById(Long id) {
        return gpuMemoryTypeRepository.findById(id);
    }
}
