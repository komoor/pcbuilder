package pl.komoor.pcbuilder.payload.response;

import pl.komoor.pcbuilder.payload.response.basic.ErrorResponse;
import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.services.dto.CompatibilityCheckDTO;

import java.util.List;

public class CompatibilityCheckResponse extends SuccessResponse {

    List<String> compatibilityErrors;

    public CompatibilityCheckResponse(List<String> compatibilityErrors) {
        addFullMessage("Wykryto błędy kompatybilności");
        this.compatibilityErrors = compatibilityErrors;

    }

    public List<String> getCompatibilityErrors() {
        return compatibilityErrors;
    }

    public void setCompatibilityErrors(List<String> compatibilityErrors) {
        this.compatibilityErrors = compatibilityErrors;
    }


}
