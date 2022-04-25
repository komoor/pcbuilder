package pl.komoor.pcbuilder.services.products.gpu;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import pl.komoor.pcbuilder.models.products.Gpu;
import pl.komoor.pcbuilder.models.products.Storage;
import pl.komoor.pcbuilder.payload.request.products.GpuRequest;
import pl.komoor.pcbuilder.payload.request.products.StorageRequest;

import java.util.List;
import java.util.Optional;

public interface GpuService {

    public void saveGpu(GpuRequest gpuRequest);

    public void updateGpu(GpuRequest gpuRequest, Long id);

    public void deleteGpu(Gpu gpu);

    List<Gpu> findAll();

    Page<Gpu> findAll(Specification<Gpu> spec, Pageable pageRequest);

    Optional<Gpu> findById(Long id);

    boolean existsById(Long id);

    boolean existsByModel(String model);

}
