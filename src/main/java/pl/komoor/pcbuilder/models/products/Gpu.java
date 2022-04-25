package pl.komoor.pcbuilder.models.products;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.komoor.pcbuilder.models.productDetails.InterfaceType;
import pl.komoor.pcbuilder.models.productDetails.gpu.GpuChipset;
import pl.komoor.pcbuilder.models.productDetails.gpu.GpuMemoryType;
import pl.komoor.pcbuilder.models.productDetails.Manufacturer;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "gpu")
public class Gpu {

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
    @JoinColumn(name="gpuChipsetId", referencedColumnName = "id", nullable=false)
    private GpuChipset gpuChipsetId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="gpuMemoryTypeId", referencedColumnName = "id", nullable=false)
    private GpuMemoryType gpuMemoryTypeId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="gpuInterfaceId", referencedColumnName = "id", nullable=false)
    private InterfaceType interfaceTypeId;

    private Double baseClock;

    private Double boostClock;

    private String frameSync;

    private Double memoryGB;

    private Integer tdp;

    private Integer portsDvi;

    private Integer portsHdmi;

    private Integer portsMiniHdmi;

    private Integer portsDisplayPort;

    private Integer portsMiniDisplayPort;

    public Gpu() {
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

    public GpuChipset getGpuChipsetId() {
        return gpuChipsetId;
    }

    public void setGpuChipsetId(GpuChipset gpuChipsetId) {
        this.gpuChipsetId = gpuChipsetId;
    }

    public GpuMemoryType getGpuMemoryTypeId() {
        return gpuMemoryTypeId;
    }

    public void setGpuMemoryTypeId(GpuMemoryType gpuMemoryTypeId) {
        this.gpuMemoryTypeId = gpuMemoryTypeId;
    }

    public InterfaceType getInterfaceTypeId() {
        return interfaceTypeId;
    }

    public void setInterfaceTypeId(InterfaceType interfaceTypeId) {
        this.interfaceTypeId = interfaceTypeId;
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

    public String getFrameSync() {
        return frameSync;
    }

    public void setFrameSync(String frameSync) {
        this.frameSync = frameSync;
    }

    public Double getMemoryGB() {
        return memoryGB;
    }

    public void setMemoryGB(Double memoryGB) {
        this.memoryGB = memoryGB;
    }

    public Integer getTdp() {
        return tdp;
    }

    public void setTdp(Integer tdp) {
        this.tdp = tdp;
    }

    public Integer getPortsDvi() {
        return portsDvi;
    }

    public void setPortsDvi(Integer portsDvi) {
        this.portsDvi = portsDvi;
    }

    public Integer getPortsHdmi() {
        return portsHdmi;
    }

    public void setPortsHdmi(Integer portsHdmi) {
        this.portsHdmi = portsHdmi;
    }

    public Integer getPortsMiniHdmi() {
        return portsMiniHdmi;
    }

    public void setPortsMiniHdmi(Integer portsMiniHdmi) {
        this.portsMiniHdmi = portsMiniHdmi;
    }

    public Integer getPortsDisplayPort() {
        return portsDisplayPort;
    }

    public void setPortsDisplayPort(Integer portsDisplayPort) {
        this.portsDisplayPort = portsDisplayPort;
    }

    public Integer getPortsMiniDisplayPort() {
        return portsMiniDisplayPort;
    }

    public void setPortsMiniDisplayPort(Integer portsMiniDisplayPort) {
        this.portsMiniDisplayPort = portsMiniDisplayPort;
    }
}
