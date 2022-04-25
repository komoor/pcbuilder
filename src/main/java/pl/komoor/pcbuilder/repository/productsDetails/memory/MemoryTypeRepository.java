package pl.komoor.pcbuilder.repository.productsDetails.memory;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.komoor.pcbuilder.models.productDetails.memory.MemoryType;

import java.util.List;
import java.util.Optional;

public interface MemoryTypeRepository extends JpaRepository<MemoryType, Long> {

    List<MemoryType> findAll();

    Optional<MemoryType> findById(Long id);

}
