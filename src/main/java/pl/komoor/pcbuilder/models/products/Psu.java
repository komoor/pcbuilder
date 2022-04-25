package pl.komoor.pcbuilder.models.products;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.kaczmarzyk.spring.data.jpa.domain.In;
import pl.komoor.pcbuilder.models.productDetails.Manufacturer;
import pl.komoor.pcbuilder.models.productDetails.cases.CaseType;
import pl.komoor.pcbuilder.models.productDetails.psu.PsuEfficiencyRating;
import pl.komoor.pcbuilder.models.productDetails.psu.PsuType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "psu")
public class Psu {

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
    @JoinColumn(name="psuEfficiencyRatingId", referencedColumnName = "id", nullable=false)
    private PsuEfficiencyRating psuEfficiencyRatingId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="psuTypeId", referencedColumnName = "id", nullable=false)
    private PsuType psuTypeId;

    private Integer wattage;

    private String modular;

    private Integer pcie8pin;

    private Integer pcie62pin;

    private Integer pcie6pin;

    private Integer sata;

    private Integer molex4pin;

    public Psu() {
    }

    public Psu(Long id, Product productId, Manufacturer manufacturerId, @NotBlank @Size(max = 50) String model, PsuEfficiencyRating psuEfficiencyRatingId, PsuType psuTypeId, Integer wattage, String modular, Integer pcie8pin, Integer pcie62pin, Integer pcie6pin, Integer sata, Integer molex4pin) {
        this.id = id;
        this.productId = productId;
        this.manufacturerId = manufacturerId;
        this.model = model;
        this.psuEfficiencyRatingId = psuEfficiencyRatingId;
        this.psuTypeId = psuTypeId;
        this.wattage = wattage;
        this.modular = modular;
        this.pcie8pin = pcie8pin;
        this.pcie62pin = pcie62pin;
        this.pcie6pin = pcie6pin;
        this.sata = sata;
        this.molex4pin = molex4pin;
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

    public PsuEfficiencyRating getPsuEfficiencyRatingId() {
        return psuEfficiencyRatingId;
    }

    public void setPsuEfficiencyRatingId(PsuEfficiencyRating psuEfficiencyRatingId) {
        this.psuEfficiencyRatingId = psuEfficiencyRatingId;
    }

    public PsuType getPsuTypeId() {
        return psuTypeId;
    }

    public void setPsuTypeId(PsuType psuTypeId) {
        this.psuTypeId = psuTypeId;
    }

    public Integer getWattage() {
        return wattage;
    }

    public void setWattage(Integer wattage) {
        this.wattage = wattage;
    }

    public String getModular() {
        return modular;
    }

    public void setModular(String modular) {
        this.modular = modular;
    }

    public Integer getPcie8pin() {
        return pcie8pin;
    }

    public void setPcie8pin(Integer pcie8pin) {
        this.pcie8pin = pcie8pin;
    }

    public Integer getPcie62pin() {
        return pcie62pin;
    }

    public void setPcie62pin(Integer pcie62pin) {
        this.pcie62pin = pcie62pin;
    }

    public Integer getPcie6pin() {
        return pcie6pin;
    }

    public void setPcie6pin(Integer pcie6pin) {
        this.pcie6pin = pcie6pin;
    }

    public Integer getSata() {
        return sata;
    }

    public void setSata(Integer sata) {
        this.sata = sata;
    }

    public Integer getMolex4pin() {
        return molex4pin;
    }

    public void setMolex4pin(Integer molex4pin) {
        this.molex4pin = molex4pin;
    }
}
