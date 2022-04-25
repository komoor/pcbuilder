package pl.komoor.pcbuilder.payload.response.builds;

import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.services.dto.builds.PcBuildFilterDTO;

public class PcBuildFilterResponse extends SuccessResponse {

    PcBuildFilterDTO pcBuildFilter;


    public PcBuildFilterResponse(PcBuildFilterDTO pcBuildFilter) {
        addFullMessage("Pobrano filtr.");
        this.pcBuildFilter = pcBuildFilter;
    }

    public PcBuildFilterDTO getPcBuildFilter() {
        return pcBuildFilter;
    }

    public void setPcBuildFilter(PcBuildFilterDTO pcBuildFilter) {
        this.pcBuildFilter = pcBuildFilter;
    }
}
