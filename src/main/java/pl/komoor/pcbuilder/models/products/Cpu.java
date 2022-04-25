package pl.komoor.pcbuilder.models.products;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.komoor.pcbuilder.models.productDetails.cpu.CpuSocket;
import pl.komoor.pcbuilder.models.productDetails.cpu.IntegratedGraphic;
import pl.komoor.pcbuilder.models.productDetails.Manufacturer;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "cpu")
public class Cpu {

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
    @JoinColumn(name="socketId", referencedColumnName = "id", nullable=false)
    private CpuSocket socketId;

    private Integer cores;

    private Double baseClock;

    private Double boostClock;

    private Integer tdp;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="integratedGraphic", referencedColumnName = "id", nullable=true)
    private IntegratedGraphic integratedGraphic;

    public Cpu() {

    }

    public Cpu(Product productId, Manufacturer manufacturerId, String model, CpuSocket socketId, Integer cores, Double baseClock, Double boostClock, Integer tdp, IntegratedGraphic integratedGraphic) {
        this.productId = productId;
        this.manufacturerId = manufacturerId;
        this.model = model;
        this.socketId = socketId;
        this.cores = cores;
        this.baseClock = baseClock;
        this.boostClock = boostClock;
        this.tdp = tdp;
        this.integratedGraphic = integratedGraphic;
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

    public CpuSocket getSocketId() {
        return socketId;
    }

    public void setSocketId(CpuSocket socketId) {
        this.socketId = socketId;
    }

    public Integer getCores() {
        return cores;
    }

    public void setCores(Integer cores) {
        this.cores = cores;
    }

    public Double getBaseClock() {
        return baseClock;
    }

    public void setBaseClock(Double baseClock) {
        this.baseClock = baseClock;
    }

    public Double getBoostClock() {
        return boostClock;
    }

    public void setBoostClock(Double boostClock) {
        this.boostClock = boostClock;
    }

    public Integer getTdp() {
        return tdp;
    }

    public void setTdp(Integer tdp) {
        this.tdp = tdp;
    }

    public IntegratedGraphic getIntegratedGraphic() {
        return integratedGraphic;
    }

    public void setIntegratedGraphic(IntegratedGraphic integratedGraphic) {
        this.integratedGraphic = integratedGraphic;
    }
}
