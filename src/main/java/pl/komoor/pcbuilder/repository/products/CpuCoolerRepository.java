package pl.komoor.pcbuilder.repository.products;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import pl.komoor.pcbuilder.models.products.CpuCooler;

import java.util.List;
import java.util.Optional;

public interface CpuCoolerRepository extends JpaRepository<CpuCooler, Long>, JpaSpecificationExecutor<CpuCooler> {

    List<CpuCooler> findAll();

    Page<CpuCooler> findAll(Specification spec, Pageable pageRequest);

    Optional<CpuCooler> findById(Long id);

    boolean existsById(Long id);

    boolean existsByModel(String model);


}
