package pl.komoor.pcbuilder.payload.response.productsDetails;

import pl.komoor.pcbuilder.models.productDetails.Manufacturer;
import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;

import java.util.List;

public class ManufacturerListResponse extends SuccessResponse {

    List<Manufacturer> manufacturers;

    public ManufacturerListResponse(List<Manufacturer> manufacturers) {

        this.manufacturers = manufacturers;
        addFullMessage("Pomyślnie pobrano dane.");
    }

    public List<Manufacturer> getManufacturers() {
        return manufacturers;
    }

    public void setManufacturers(List<Manufacturer> manufacturers) {
        this.manufacturers = manufacturers;
    }
}
