package pl.komoor.pcbuilder.services.impl.productsDetails.cpu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.komoor.pcbuilder.models.productDetails.cpu.IntegratedGraphic;
import pl.komoor.pcbuilder.repository.productsDetails.cpu.IntegratedGraphicRepository;
import pl.komoor.pcbuilder.services.productsDetails.cpu.CpuIntegratedGraphicService;

import java.util.List;
import java.util.Optional;

@Service
public class CpuIntegratedGraphicServiceImpl implements CpuIntegratedGraphicService {

    @Autowired
    IntegratedGraphicRepository integratedGraphicRepository;

    @Override
    public Optional<IntegratedGraphic> findById(Long id) {
        return integratedGraphicRepository.findById(id);
    }

    @Override
    public List<IntegratedGraphic> findAll() {
        return integratedGraphicRepository.findAll();
    }
}
