package pl.komoor.pcbuilder.models.products;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.komoor.pcbuilder.models.productDetails.Manufacturer;
import pl.komoor.pcbuilder.models.productDetails.cases.CaseType;
import pl.komoor.pcbuilder.models.productDetails.motherboard.MotherboardFormFactor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(	name = "cases")
public class Case {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="productId", referencedColumnName = "id")
    private Product productId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="manufacturerId", referencedColumnName = "id", nullable=false)
    private Manufacturer manufacturerId;

    @NotBlank
    @Size(max = 50)
    private String model;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="caseTypeId", referencedColumnName = "id", nullable=false)
    private CaseType caseTypeId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "case_motherboard_form_factor", joinColumns = @JoinColumn(name = "case_id"),
            inverseJoinColumns = @JoinColumn(name = "motherboard_form_factor_id"))
    private Set<MotherboardFormFactor> motherboardFormFactorId;

    private Integer maxGpuCardLength;

    private Integer ex35bays;

    private Integer ex25bays;

    private Integer in35bays;

    private Integer in25bays;

    public Case() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public Manufacturer getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Manufacturer manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public CaseType getCaseTypeId() {
        return caseTypeId;
    }

    public void setCaseTypeId(CaseType caseTypeId) {
        this.caseTypeId = caseTypeId;
    }

    public Set<MotherboardFormFactor> getMotherboardFormFactorId() {
        return motherboardFormFactorId;
    }

    public void setMotherboardFormFactorId(Set<MotherboardFormFactor> motherboardFormFactorId) {
        this.motherboardFormFactorId = motherboardFormFactorId;
    }

    public Integer getMaxGpuCardLength() {
        return maxGpuCardLength;
    }

    public void setMaxGpuCardLength(Integer maxGpuCardLength) {
        this.maxGpuCardLength = maxGpuCardLength;
    }

    public Integer getEx25bays() {
        return ex25bays;
    }

    public void setEx25bays(Integer ex25bays) {
        this.ex25bays = ex25bays;
    }

    public Integer getIn25bays() {
        return in25bays;
    }

    public void setIn25bays(Integer in25bays) {
        this.in25bays = in25bays;
    }

    public Integer getEx35bays() {
        return ex35bays;
    }

    public void setEx35bays(Integer ex35bays) {
        this.ex35bays = ex35bays;
    }

    public Integer getIn35bays() {
        return in35bays;
    }

    public void setIn35bays(Integer in35bays) {
        this.in35bays = in35bays;
    }
}
