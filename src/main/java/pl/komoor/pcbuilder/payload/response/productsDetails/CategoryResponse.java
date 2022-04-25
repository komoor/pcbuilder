package pl.komoor.pcbuilder.payload.response.productsDetails;

import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.services.dto.productsDetails.CategoryDTO;

import java.util.List;

public class CategoryResponse extends SuccessResponse {

    CategoryDTO category;

    public CategoryResponse(CategoryDTO category) {
        this.category = category;
        addFullMessage("Pobrano kategorię.");
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }
}
