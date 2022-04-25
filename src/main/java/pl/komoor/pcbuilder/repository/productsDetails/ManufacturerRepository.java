package pl.komoor.pcbuilder.repository.productsDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.komoor.pcbuilder.models.productDetails.Category;
import pl.komoor.pcbuilder.models.productDetails.Manufacturer;

import java.util.List;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

    boolean existsByManufacturerName(String manufacturerName);

    List<Manufacturer> findAllByManufacturerCategory(Category category);

    List<Manufacturer> findAll();


}
