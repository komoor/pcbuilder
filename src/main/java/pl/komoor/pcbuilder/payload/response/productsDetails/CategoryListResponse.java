package pl.komoor.pcbuilder.payload.response.productsDetails;

import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.services.dto.productsDetails.CategoryDTO;

import java.util.List;

public class CategoryListResponse extends SuccessResponse {

    List<CategoryDTO> categories;

    public CategoryListResponse(List<CategoryDTO> categories) {
        this.categories = categories;
        addFullMessage("Pobrano kategorie");
    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDTO> categories) {
        this.categories = categories;
    }

}
