package pl.komoor.pcbuilder.repository.productsDetails.memory;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.komoor.pcbuilder.models.productDetails.memory.MemoryTimming;

import java.util.List;
import java.util.Optional;

public interface MemoryTimmingRepository extends JpaRepository<MemoryTimming, Long> {

    List<MemoryTimming> findAll();

    Optional<MemoryTimming> findById(Long id);

}
