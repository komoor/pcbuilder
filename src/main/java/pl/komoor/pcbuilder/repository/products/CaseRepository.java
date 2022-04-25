package pl.komoor.pcbuilder.repository.products;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import pl.komoor.pcbuilder.models.products.Case;

import java.util.List;
import java.util.Optional;

public interface CaseRepository extends JpaRepository<Case, Long>, JpaSpecificationExecutor<Case> {

    List<Case> findAll();

    Page<Case> findAll(Specification spec, Pageable pageRequest);

    Optional<Case> findById(Long id);

    boolean existsById(Long id);

    boolean existsByModel(String model);

}
