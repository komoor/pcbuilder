package pl.komoor.pcbuilder.services.productsDetails.cpu;

import pl.komoor.pcbuilder.models.productDetails.cpu.CpuSocket;

import java.util.List;
import java.util.Optional;

public interface CpuSocketService {

    Optional<CpuSocket> findById(Long id);

    List<CpuSocket> findAll();

}
