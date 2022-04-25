package pl.komoor.pcbuilder.services.dto.productsDetails.cases;

import pl.komoor.pcbuilder.models.productDetails.cases.CaseType;
import pl.komoor.pcbuilder.models.products.Case;

public class CaseTypeDTO {

    private Long id;
    private String name;

    public CaseTypeDTO() {
    }

    public CaseTypeDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static CaseTypeDTO build(CaseType caseType) {

        return new CaseTypeDTO(
                caseType.getId(),
                caseType.getCaseTypeName()
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
