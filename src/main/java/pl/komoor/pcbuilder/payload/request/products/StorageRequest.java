package pl.komoor.pcbuilder.payload.request.products;

public class StorageRequest {

    private Long manufacturerId;

    private String model;

    private Long storageTypeId;

    private Long storageFormFactorId;

    private Long storageInterfaceId;

    private Integer capacity;

    private Integer cache;

    private String nmve;

    private String imageId;

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getStorageTypeId() {
        return storageTypeId;
    }

    public void setStorageTypeId(Long storageTypeId) {
        this.storageTypeId = storageTypeId;
    }

    public Long getStorageFormFactorId() {
        return storageFormFactorId;
    }

    public void setStorageFormFactorId(Long storageFormFactorId) {
        this.storageFormFactorId = storageFormFactorId;
    }

    public Long getStorageInterfaceId() {
        return storageInterfaceId;
    }

    public void setStorageInterfaceId(Long storageInterfaceId) {
        this.storageInterfaceId = storageInterfaceId;
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

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
}
