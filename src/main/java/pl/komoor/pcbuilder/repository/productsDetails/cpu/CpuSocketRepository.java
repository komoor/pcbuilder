package pl.komoor.pcbuilder.repository.productsDetails.cpu;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.komoor.pcbuilder.models.productDetails.cpu.CpuSocket;

import java.util.List;
import java.util.Optional;

public interface CpuSocketRepository extends JpaRepository<CpuSocket, Long> {

    List<CpuSocket> findAll();

    Optional<CpuSocket> findById(Long id);

}
