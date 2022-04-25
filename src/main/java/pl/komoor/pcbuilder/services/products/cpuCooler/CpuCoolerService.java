package pl.komoor.pcbuilder.services.products.cpuCooler;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import pl.komoor.pcbuilder.models.products.CpuCooler;
import pl.komoor.pcbuilder.models.products.Storage;
import pl.komoor.pcbuilder.payload.request.products.CpuCoolerRequest;
import pl.komoor.pcbuilder.payload.request.products.StorageRequest;

import java.util.List;
import java.util.Optional;

public interface CpuCoolerService {

    public void saveCpuCooler(CpuCoolerRequest cpuCoolerRequest);

    public void updateCpuCooler(CpuCoolerRequest cpuCoolerRequest, Long id);

    public void deleteCpuCooler(CpuCooler cpuCooler);

    List<CpuCooler> findAll();

    Page<CpuCooler> findAll(Specification<CpuCooler> spec, Pageable pageRequest);

    Optional<CpuCooler> findById(Long id);

    boolean existsById(Long id);

    boolean existsByModel(String model);


}
