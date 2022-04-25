package pl.komoor.pcbuilder.services.impl.productsDetails.cpu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.komoor.pcbuilder.models.productDetails.cpu.CpuSocket;
import pl.komoor.pcbuilder.repository.productsDetails.cpu.CpuSocketRepository;
import pl.komoor.pcbuilder.services.productsDetails.cpu.CpuSocketService;

import java.util.List;
import java.util.Optional;

@Service
public class CpuSocketServiceImpl implements CpuSocketService {

    @Autowired
    CpuSocketRepository cpuSocketRepository;

    @Override
    public Optional<CpuSocket> findById(Long id) {

        return cpuSocketRepository.findById(id);

    }

    @Override
    public List<CpuSocket> findAll() {
        return cpuSocketRepository.findAll();
    }
}
