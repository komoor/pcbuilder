package pl.komoor.pcbuilder.payload.response.builds;

import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.services.dto.builds.PcPartsDTO;
import pl.komoor.pcbuilder.services.dto.builds.PcPartsListDTO;

public class PcPartsListResponse extends SuccessResponse {

    PcPartsListDTO pcPartsList;


    public PcPartsListResponse(PcPartsListDTO pcPartsList) {
        addFullMessage("Pobrano listę części.");
        this.pcPartsList = pcPartsList;
    }


    public PcPartsListDTO getPcPartsList() {
        return pcPartsList;
    }

    public void setPcPartsList(PcPartsListDTO pcPartsList) {
        this.pcPartsList = pcPartsList;
    }
}
