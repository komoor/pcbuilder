package pl.komoor.pcbuilder.payload.request.products;

public class MemoryRequest {

    private Long manufacturerId;

    private String model;

    private Long memoryTypeId;

    private Long memoryTimmingId;

    private Integer numberOfModules;

    private Integer memoryGB;

    private Double voltage;

    private Integer casLatency;

    private String heatSpreader;

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

    public Long getMemoryTypeId() {
        return memoryTypeId;
    }

    public void setMemoryTypeId(Long memoryTypeId) {
        this.memoryTypeId = memoryTypeId;
    }

    public Long getMemoryTimmingId() {
        return memoryTimmingId;
    }

    public void setMemoryTimmingId(Long memoryTimmingId) {
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

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
}
