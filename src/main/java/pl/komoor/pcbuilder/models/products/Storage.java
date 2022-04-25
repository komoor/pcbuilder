package pl.komoor.pcbuilder.models.products;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.komoor.pcbuilder.models.productDetails.*;
import pl.komoor.pcbuilder.models.productDetails.storage.StorageFormFactor;
import pl.komoor.pcbuilder.models.productDetails.InterfaceType;
import pl.komoor.pcbuilder.models.productDetails.storage.StorageType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "storage")
public class Storage {

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
    @JoinColumn(name="storageTypeId", referencedColumnName = "id", nullable=false)
    private StorageType storageTypeId; // ssd,hdd,rpm itp

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="storageFormFactorId", referencedColumnName = "id", nullable=false)
    private StorageFormFactor storageFormFactorId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="storageInterfaceId", referencedColumnName = "id", nullable=false)
    private InterfaceType interfaceTypeId;

    private Integer capacity;

    private Integer cache;

    private String nmve;

    public Storage() {
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

    public StorageType getStorageTypeId() {
        return storageTypeId;
    }

    public void setStorageTypeId(StorageType storageTypeId) {
        this.storageTypeId = storageTypeId;
    }

    public StorageFormFactor getStorageFormFactorId() {
        return storageFormFactorId;
    }

    public void setStorageFormFactorId(StorageFormFactor storageFormFactorId) {
        this.storageFormFactorId = storageFormFactorId;
    }

    public InterfaceType getInterfaceTypeId() {
        return interfaceTypeId;
    }

    public void setInterfaceTypeId(InterfaceType interfaceTypeId) {
        this.interfaceTypeId = interfaceTypeId;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getCache() {
        return cache;
    }

    public void setCache(Integer cache) {
        this.cache = cache;
    }

    public String getNmve() {
        return nmve;
    }

    public void setNmve(String nmve) {
        this.nmve = nmve;
    }
}
