package pl.komoor.pcbuilder.services.productsDetails;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.komoor.pcbuilder.models.productDetails.Category;
import pl.komoor.pcbuilder.models.productDetails.Manufacturer;
import pl.komoor.pcbuilder.models.products.Cpu;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {

    Optional<Manufacturer> findById(Long id);

    public void saveManufacturer(Manufacturer manufacturer);

    public List<Manufacturer> findAllManufacturer();

    List<Manufacturer> findAllByManufacturerCategory(Category category);

    boolean existsByManufacturerName(String manufacturerName);


}
