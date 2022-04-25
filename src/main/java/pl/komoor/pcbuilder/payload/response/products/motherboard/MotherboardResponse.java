package pl.komoor.pcbuilder.payload.response.products.motherboard;
import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.services.dto.products.MotherboardDTO;

public class MotherboardResponse extends SuccessResponse {

    private MotherboardDTO motherboard;


    public MotherboardResponse(MotherboardDTO motherboard) {
        this.motherboard = motherboard;
        addFullMessage("Pobrano płytę główną.");
    }

    public MotherboardDTO getMotherboard() {
        return motherboard;
    }

    public void setMotherboard(MotherboardDTO motherboard) {
        this.motherboard = motherboard;
    }
}
