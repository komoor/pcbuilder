package pl.komoor.pcbuilder.payload.response.products.cases;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.services.dto.products.CaseDTO;

public class CaseResponse extends SuccessResponse {

    @JsonProperty("case")
    CaseDTO caseDTO;


    public CaseResponse(CaseDTO caseDTO) {
        this.caseDTO = caseDTO;
        addFullMessage("Pobrano obudowę");
    }

    @JsonProperty("case")
    public CaseDTO getCaseDTO() {
        return caseDTO;
    }

    @JsonProperty("case")
    public void setCaseDTO(CaseDTO caseDTO) {
        this.caseDTO = caseDTO;
    }
}
