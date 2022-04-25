package pl.komoor.pcbuilder.models.productDetails.cases;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "case_type")
public class CaseType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Size(max = 20)
    private String caseTypeName;

    public CaseType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaseTypeName() {
        return caseTypeName;
    }

    public void setCaseTypeName(String caseTypeName) {
        this.caseTypeName = caseTypeName;
    }
}
