package pl.komoor.pcbuilder.services.dto.productsDetails;

import pl.komoor.pcbuilder.models.productDetails.Category;
import pl.komoor.pcbuilder.models.productDetails.Manufacturer;

import java.util.Set;

public class ManufacturerDTO {

    private Long id;
    private String name;
    Set<Category> categories;

    public ManufacturerDTO(Long id, String manufacturerName, Set<Category> categories) {
        this.id = id;
        this.name = manufacturerName;
        this.categories = categories;
    }

    public static ManufacturerDTO build(Manufacturer manufacturer) {

        return new ManufacturerDTO(
                manufacturer.getId(),
                manufacturer.getManufacturerName(),
                manufacturer.getManufacturerCategory()
        );


    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
