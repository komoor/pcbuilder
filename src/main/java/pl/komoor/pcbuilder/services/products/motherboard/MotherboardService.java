package pl.komoor.pcbuilder.services.products.motherboard;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import pl.komoor.pcbuilder.models.products.Motherboard;
import pl.komoor.pcbuilder.models.products.Product;
import pl.komoor.pcbuilder.payload.request.products.MotherboardRequest;

import java.util.List;
import java.util.Optional;

public interface MotherboardService {


    public void saveMotherboard(MotherboardRequest motherboardRequest);

    public void updateMotherboard(MotherboardRequest motherboardRequest, Long id);

    public void deleteMotherboard(Motherboard motherboard);

    List<Motherboard> findAll();

    Page<Motherboard> findAll(Specification spec, Pageable pageRequest);

    Optional<Motherboard> findById(Long id);
    Optional<Motherboard> findByProductId(Product product);

    boolean existsById(Long id);

    boolean existsByModel(String model);


}
