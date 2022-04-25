package pl.komoor.pcbuilder.services.products.product;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.komoor.pcbuilder.models.products.Product;
import pl.komoor.pcbuilder.models.tools.FileToDatabase;
import pl.komoor.pcbuilder.models.users.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProductService {

    Optional<Product> findById(Long id);

    Optional<Product> findByImage(FileToDatabase file);

    Set<Product> getAllProductUsedInBuildsByUserId(User user);

    public void saveProduct(Product product);

    List<Product> findAll();


}
