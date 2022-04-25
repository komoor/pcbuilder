package pl.komoor.pcbuilder.payload.request.products;

import java.util.Set;

public class CpuCoolerRequest {

    private Long manufacturerId;
    private String model;
    private Set<String> socketId;
    private Long waterCooledId;
    private Integer height;
    private String fanless;
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

    public Set<String> getSocketId() {
        return socketId;
    }

    public void setSocketId(Set<String> socketId) {
        this.socketId = socketId;
    }

    public Long getWaterCooledId() {
        return waterCooledId;
    }

    public void setWaterCooledId(Long waterCooledId) {
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

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
}
