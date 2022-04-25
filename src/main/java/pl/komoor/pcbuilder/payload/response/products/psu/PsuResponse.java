package pl.komoor.pcbuilder.payload.response.products.psu;

import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.services.dto.products.PsuDTO;

public class PsuResponse extends SuccessResponse {

    PsuDTO psu;

    public PsuResponse(PsuDTO psu) {
        this.psu = psu;
        addFullMessage("Pobrano PSU.");
    }


    public PsuDTO getPsu() {
        return psu;
    }

    public void setPsu(PsuDTO psu) {
        this.psu = psu;
    }
}
