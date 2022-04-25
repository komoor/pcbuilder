package pl.komoor.pcbuilder.services.impl.productsDetails.cpuCooler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.komoor.pcbuilder.models.productDetails.cpuCooler.CpuCoolerWaterCooled;
import pl.komoor.pcbuilder.repository.productsDetails.cpuCooler.CpuCoolerWaterCooledRepository;
import pl.komoor.pcbuilder.services.productsDetails.cpuCooler.CpuCoolerWaterCooledService;

import java.util.List;
import java.util.Optional;

@Service
public class CpuCollerWaterCooledServiceImpl implements CpuCoolerWaterCooledService {

    @Autowired
    CpuCoolerWaterCooledRepository cpuCoolerWaterCooledRepository;

    @Override
    public List<CpuCoolerWaterCooled> findAll() {
        return cpuCoolerWaterCooledRepository.findAll();
    }

    @Override
    public Optional<CpuCoolerWaterCooled> findById(Long id) {
        return cpuCoolerWaterCooledRepository.findById(id);
    }
}
