package pl.komoor.pcbuilder.services.dto.products;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.komoor.pcbuilder.models.products.Storage;
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class StorageDTO {

    private Long id;
    private Long productId;
    private String manufacturer;
    private String model;
    private String storageType;
    private String storageFormFactor;
    private String storageInterface;
    private Integer capacity;
    private Integer cache;
    private String nmve;
    private String imageUrl;

    public StorageDTO() {
    }

    public StorageDTO(Long id, String manufacturer, String model) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
    }

    public StorageDTO(Long id, Long productId, String manufacturer, String model, String storageType, String storageFormFactor, String storageInterface, Integer capacity, Integer cache, String nmve, String imageUrl) {
        this.id = id;
        this.productId = productId;
        this.manufacturer = manufacturer;
        this.model = model;
        this.storageType = storageType;
        this.storageFormFactor = storageFormFactor;
        this.storageInterface = storageInterface;
        this.capacity = capacity;
        this.cache = cache;
        this.nmve = nmve;
        this.imageUrl = imageUrl;
    }

    public static StorageDTO build(Storage storage) {

        String imageUrl = null;

        if(storage.getProductId().getFileToDatabase() != null)
            imageUrl = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/images/")
                    .path(storage.getProductId().getFileToDatabase().getId())
                    .toUriString();

        return new StorageDTO(
                storage.getId(),
                storage.getProductId().getId(),
                storage.getManufacturerId().getManufacturerName(),
                storage.getModel(),
                storage.getStorageTypeId().getTypeName(),
                storage.getStorageFormFactorId().getFormFactorName(),
                storage.getInterfaceTypeId().getInterfaceName(),
                storage.getCapacity(),
                storage.getCache(),
                storage.getNmve(),
                imageUrl
        );

    }

    public static StorageDTO buildShort(Storage storage) {

        return new StorageDTO(
                storage.getId(),
                storage.getManufacturerId().getManufacturerName(),
                storage.getModel()
        );

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public String getStorageFormFactor() {
        return storageFormFactor;
    }

    public void setStorageFormFactor(String storageFormFactor) {
        this.storageFormFactor = storageFormFactor;
    }

    public String getStorageInterface() {
        return storageInterface;
    }

    public void setStorageInterface(String storageInterface) {
        this.storageInterface = storageInterface;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
