package pl.komoor.pcbuilder.models.products;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.komoor.pcbuilder.models.productDetails.Manufacturer;
import pl.komoor.pcbuilder.models.productDetails.memory.MemoryTimming;
import pl.komoor.pcbuilder.models.productDetails.memory.MemoryType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "memory")
public class Memory {

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
    @JoinColumn(name="memoryTypeId", referencedColumnName = "id", nullable=false)
    private MemoryType memoryTypeId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="memoryTimmingId", referencedColumnName = "id", nullable=false)
    private MemoryTimming memoryTimmingId;


    private Integer numberOfModules;

    private Integer memoryGB;

    private Double voltage;

    private Integer casLatency;

    private String heatSpreader;


    public Memory() {
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

    public MemoryType getMemoryTypeId() {
        return memoryTypeId;
    }

    public void setMemoryTypeId(MemoryType memoryTypeId) {
        this.memoryTypeId = memoryTypeId;
    }

    public MemoryTimming getMemoryTimmingId() {
        return memoryTimmingId;
    }

    public void setMemoryTimmingId(MemoryTimming memoryTimmingId) {
        this.memoryTimmingId = memoryTimmingId;
    }

    public Integer getNumberOfModules() {
        return numberOfModules;
    }

    public void setNumberOfModules(Integer numberOfModules) {
        this.numberOfModules = numberOfModules;
    }

    public Integer getMemoryGB() {
        return memoryGB;
    }

    public void setMemoryGB(Integer memoryGB) {
        this.memoryGB = memoryGB;
    }

    public Double getVoltage() {
        return voltage;
    }

    public void setVoltage(Double voltage) {
        this.voltage = voltage;
    }

    public Integer getCasLatency() {
        return casLatency;
    }

    public void setCasLatency(Integer casLatency) {
        this.casLatency = casLatency;
    }

    public String getHeatSpreader() {
        return heatSpreader;
    }

    public void setHeatSpreader(String heatSpreader) {
        this.heatSpreader = heatSpreader;
    }
}
