package pl.komoor.pcbuilder.models.products;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.komoor.pcbuilder.models.productDetails.cpuCooler.CpuCoolerWaterCooled;
import pl.komoor.pcbuilder.models.productDetails.Manufacturer;
import pl.komoor.pcbuilder.models.productDetails.cpu.CpuSocket;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(	name = "cpu_cooler")
public class CpuCooler {

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "cpu_cooler_cpu_socket", joinColumns = @JoinColumn(name = "cpu_cooler_id"),
            inverseJoinColumns = @JoinColumn(name = "cpu_socket_id"))
    private Set<CpuSocket> socketId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="waterCooledId", referencedColumnName = "id", nullable=false)
    private CpuCoolerWaterCooled waterCooledId;

    private Integer height;

    private String fanless;

    public CpuCooler() {
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

    public Set<CpuSocket> getSocketId() {
        return socketId;
    }

    public void setSocketId(Set<CpuSocket> socketId) {
        this.socketId = socketId;
    }

    public CpuCoolerWaterCooled getWaterCooledId() {
        return waterCooledId;
    }

    public void setWaterCooledId(CpuCoolerWaterCooled waterCooledId) {
        this.waterCooledId = waterCooledId;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getFanless() {
        return fanless;
    }

    public void setFanless(String fanless) {
        this.fanless = fanless;
    }
}
