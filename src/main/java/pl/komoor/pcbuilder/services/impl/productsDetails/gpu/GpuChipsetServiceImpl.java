package pl.komoor.pcbuilder.services.impl.productsDetails.gpu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.komoor.pcbuilder.models.productDetails.gpu.GpuChipset;
import pl.komoor.pcbuilder.repository.productsDetails.gpu.GpuChipsetRepository;
import pl.komoor.pcbuilder.services.productsDetails.gpu.GpuChipsetService;

import java.util.List;
import java.util.Optional;

@Service
public class GpuChipsetServiceImpl implements GpuChipsetService {

    @Autowired
    GpuChipsetRepository gpuChipsetRepository;

    @Override
    public List<GpuChipset> findAll() {
        return gpuChipsetRepository.findAll();
    }

    @Override
    public Optional<GpuChipset> findById(Long id) {
        return gpuChipsetRepository.findById(id);
    }
}
