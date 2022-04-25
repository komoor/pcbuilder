package pl.komoor.pcbuilder.services.products.cpu;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import pl.komoor.pcbuilder.models.products.Cpu;
import pl.komoor.pcbuilder.payload.request.products.CpuRequest;
import pl.komoor.pcbuilder.services.dto.products.CpuDTO;

import java.util.List;
import java.util.Optional;


public interface CpuService {

    public void saveCpuRequest(CpuRequest cpuRequest);

    public void updateCpu(CpuRequest cpuRequest, Long id);

    public void deleteCpu(Cpu cpu);

    public Page<Cpu> findAllCpu(Specification spec, Pageable pageable);

    Optional<Cpu> findById(Long id);

    public boolean existsById(Long id);

    public boolean existsByModel(String model);


}
