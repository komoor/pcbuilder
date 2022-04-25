package pl.komoor.pcbuilder.payload.request.productsDetails;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class ManufacturerRequest {

    @NotBlank
    @Size(max = 20)
    private String manufacturerName;

    private Set<String> categories;


    public ManufacturerRequest(String manufacturerName, Set<String> categories) {
        this.manufacturerName = manufacturerName;
        this.categories = categories;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "ManufacturerRequest{" +
                "manufacturerName='" + manufacturerName + '\'' +
                ", categories=" + categories +
                '}';
    }
}
