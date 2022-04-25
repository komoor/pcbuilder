package pl.komoor.pcbuilder.payload.response.builds;

import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.services.dto.builds.PcBuildDTO;

public class PcBuildResponse extends SuccessResponse {

    PcBuildDTO pcBuild;

    public PcBuildResponse(PcBuildDTO pcBuild) {
        addFullMessage("Pobrano zestaw.");
        this.pcBuild = pcBuild;
    }


    public PcBuildDTO getPcBuild() {
        return pcBuild;
    }

    public void setPcBuild(PcBuildDTO pcBuild) {
        this.pcBuild = pcBuild;
    }
}
