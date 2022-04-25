package pl.komoor.pcbuilder.repository.products;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import pl.komoor.pcbuilder.models.products.Gpu;

import java.util.List;
import java.util.Optional;

public interface GpuRepository extends JpaRepository<Gpu, Long>, JpaSpecificationExecutor<Gpu> {

    List<Gpu> findAll();

    Page<Gpu> findAll(Specification spec, Pageable pageRequest);

    Optional<Gpu> findById(Long id);

    boolean existsById(Long id);

    boolean existsByModel(String model);

}
