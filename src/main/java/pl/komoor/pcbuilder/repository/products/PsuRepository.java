package pl.komoor.pcbuilder.repository.products;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import pl.komoor.pcbuilder.models.products.Psu;

import java.util.List;
import java.util.Optional;

public interface PsuRepository extends JpaRepository<Psu, Long>, JpaSpecificationExecutor<Psu> {

    List<Psu> findAll();

    Page<Psu> findAll(Specification spec, Pageable pageRequest);

    Optional<Psu> findById(Long id);

    boolean existsById(Long id);

    boolean existsByModel(String model);


}
