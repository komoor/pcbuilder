package pl.komoor.pcbuilder.services.dto;

import java.util.List;

public class CompatibilityCheckDTO {

    List<String> compatibilityErrors;


    public CompatibilityCheckDTO(List<String> compatibilityErrors) {
        this.compatibilityErrors = compatibilityErrors;
    }

    public List<String> getCompatibilityErrors() {
        return compatibilityErrors;
    }

    public void setCompatibilityErrors(List<String> compatibilityErrors) {
        this.compatibilityErrors = compatibilityErrors;
    }
}
