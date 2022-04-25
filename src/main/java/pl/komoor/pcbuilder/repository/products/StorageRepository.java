package pl.komoor.pcbuilder.repository.products;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import pl.komoor.pcbuilder.models.products.Memory;
import pl.komoor.pcbuilder.models.products.Storage;

import java.util.List;
import java.util.Optional;

public interface StorageRepository extends JpaRepository<Storage, Long>, JpaSpecificationExecutor<Storage> {

    List<Storage> findAll();

    Page<Storage> findAll(Specification spec, Pageable pageRequest);

    Optional<Storage> findById(Long id);

    boolean existsById(Long id);

    boolean existsByModel(String model);


}
