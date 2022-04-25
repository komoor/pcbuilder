package pl.komoor.pcbuilder.services.products.memory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import pl.komoor.pcbuilder.models.products.Cpu;
import pl.komoor.pcbuilder.models.products.Memory;
import pl.komoor.pcbuilder.payload.request.products.CpuRequest;
import pl.komoor.pcbuilder.payload.request.products.MemoryRequest;

import java.util.List;
import java.util.Optional;

public interface MemoryService {

    public void saveMemoryRequest(MemoryRequest memoryRequest);

    public void updateMemory(MemoryRequest memoryRequest, Long id);

    public void deleteMemory(Memory memory);

    List<Memory> findAll();

    Page<Memory> findAllMemories(Specification<Memory> spec, Pageable pageable);

    Page<Memory> findAll(Specification spec, Pageable pageRequest);

    Optional<Memory> findById(Long id);

    boolean existsById(Long id);

    boolean existsByModel(String model);

}
