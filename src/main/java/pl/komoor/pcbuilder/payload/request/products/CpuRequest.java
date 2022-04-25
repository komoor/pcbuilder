package pl.komoor.pcbuilder.payload.request.products;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CpuRequest {

    private Long manufacturerId;

    @NotBlank(message = "pole nie może być puste")
    @Size(max = 50)
    private String model;

    @NotNull(message = "pole nie może być puste")
    private Long socketId;

    private Integer cores;

    private Double baseClock;

    private Double boostClock;

    private Integer tdp;

    private Long integratedGraphicId;

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

    public Long getSocketId() {
        return socketId;
    }

    public void setSocketId(Long socketId) {
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

    public Long getIntegratedGraphicId() {
        return integratedGraphicId;
    }

    public void setIntegratedGraphicId(Long integratedGraphicId) {
        this.integratedGraphicId = integratedGraphicId;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
}
