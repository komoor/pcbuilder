package pl.komoor.pcbuilder.services.productsDetails;

import pl.komoor.pcbuilder.models.productDetails.Category;
import pl.komoor.pcbuilder.services.dto.productsDetails.CategoryDTO;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<CategoryDTO> findAll();

    Optional<Category> findByCategoryName(String categoryName);

    Optional<Category> findById(Long id);

}
