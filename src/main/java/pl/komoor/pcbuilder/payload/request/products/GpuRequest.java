package pl.komoor.pcbuilder.payload.request.products;

public class GpuRequest {

    private Long manufacturerId;
    private String model;
    private Long gpuChipsetId;
    private Long gpuMemoryTypeId;
    private Long gpuInterfaceId;
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

    public Long getGpuChipsetId() {
        return gpuChipsetId;
    }

    public void setGpuChipsetId(Long gpuChipsetId) {
        this.gpuChipsetId = gpuChipsetId;
    }

    public Long getGpuMemoryTypeId() {
        return gpuMemoryTypeId;
    }

    public void setGpuMemoryTypeId(Long gpuMemoryTypeId) {
        this.gpuMemoryTypeId = gpuMemoryTypeId;
    }

    public Long getGpuInterfaceId() {
        return gpuInterfaceId;
    }

    public void setGpuInterfaceId(Long gpuInterfaceId) {
        this.gpuInterfaceId = gpuInterfaceId;
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

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
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
}
