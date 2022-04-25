package pl.komoor.pcbuilder.repository.products;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.komoor.pcbuilder.models.products.Product;
import pl.komoor.pcbuilder.models.tools.FileToDatabase;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {


    Optional<Product> findById(Long id);

    Optional<Product> findByFileToDatabase(FileToDatabase file);

}
