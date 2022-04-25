package pl.komoor.pcbuilder.repository.products;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import pl.komoor.pcbuilder.models.products.Cpu;
import pl.komoor.pcbuilder.models.products.Motherboard;
import pl.komoor.pcbuilder.models.products.Product;

import java.util.List;
import java.util.Optional;

public interface MotherboardRepository extends JpaRepository<Motherboard, Long>, JpaSpecificationExecutor<Motherboard> {

    List<Motherboard> findAll();

    Page<Motherboard> findAll(Specification spec, Pageable pageRequest);

    Optional<Motherboard> findById(Long id);

    Optional<Motherboard> findByProductId(Product product);

    boolean existsById(Long id);

    boolean existsByModel(String model);


}
