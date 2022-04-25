package pl.komoor.pcbuilder.repository.products;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import pl.komoor.pcbuilder.models.products.Cpu;
import pl.komoor.pcbuilder.models.products.Memory;

import java.util.List;
import java.util.Optional;

public interface MemoryRepository extends JpaRepository<Memory, Long>, JpaSpecificationExecutor<Memory> {

    List<Memory> findAll();

    Page<Memory> findAll(Specification spec, Pageable pageRequest);

    Optional<Memory> findById(Long id);

    boolean existsById(Long id);

    boolean existsByModel(String model);

}
