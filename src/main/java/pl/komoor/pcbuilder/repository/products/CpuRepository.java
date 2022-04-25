package pl.komoor.pcbuilder.repository.products;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import pl.komoor.pcbuilder.models.products.Cpu;
import pl.komoor.pcbuilder.models.products.Product;

import java.util.List;
import java.util.Optional;

public interface CpuRepository extends JpaRepository<Cpu, Long>, JpaSpecificationExecutor<Cpu> {

    List<Cpu> findAll();

    Page<Cpu> findAll(Specification spec, Pageable pageRequest);

    Optional<Cpu> findById(Long id);

    Optional<Cpu> findByProductId(Product product);

    boolean existsById(Long id);

    boolean existsByModel(String model);

}
