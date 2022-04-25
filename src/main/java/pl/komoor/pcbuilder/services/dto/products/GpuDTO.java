package pl.komoor.pcbuilder.services.dto.products;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.komoor.pcbuilder.models.products.Gpu;
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GpuDTO {

    private Long id;
    private Long productId;
    private String manufacturer;
    private String model;
    private String gpuChipset;
    private String gpuMemoryType;
    private String gpuInterface;
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
    private String imageUrl;

    public GpuDTO() {
    }

    public GpuDTO(Long id, String manufacturer, String model) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
    }

    public GpuDTO(Long id, Long productId, String manufacturer, String model, String gpuChipset, String gpuMemoryType, String gpuInterface, Double baseClock, Double boostClock, String frameSync, Double memoryGB, Integer tdp, Integer portsDvi, Integer portsHdmi, Integer portsMiniHdmi, Integer portsDisplayPort, Integer portsMiniDisplayPort, String imageUrl) {
        this.id = id;
        this.productId = productId;
        this.manufacturer = manufacturer;
        this.model = model;
        this.gpuChipset = gpuChipset;
        this.gpuMemoryType = gpuMemoryType;
        this.gpuInterface = gpuInterface;
        this.baseClock = baseClock;
        this.boostClock = boostClock;
        this.frameSync = frameSync;
        this.memoryGB = memoryGB;
        this.tdp = tdp;
        this.portsDvi = portsDvi;
        this.portsHdmi = portsHdmi;
        this.portsMiniHdmi = portsMiniHdmi;
        this.portsDisplayPort = portsDisplayPort;
        this.portsMiniDisplayPort = portsMiniDisplayPort;
        this.imageUrl = imageUrl;
    }

    public static GpuDTO build(Gpu gpu) {

        String imageUrl = null;

        if(gpu.getProductId().getFileToDatabase() != null)
            imageUrl = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/images/")
                    .path(gpu.getProductId().getFileToDatabase().getId())
                    .toUriString();

        return new GpuDTO(
                gpu.getId(),
                gpu.getProductId().getId(),
                gpu.getManufacturerId().getManufacturerName(),
                gpu.getModel(),
                gpu.getGpuChipsetId().getGpuChipsetName(),
                gpu.getGpuMemoryTypeId().getGpuMemoryTypeName(),
                gpu.getInterfaceTypeId().getInterfaceName(),
                gpu.getBaseClock(),
                gpu.getBoostClock(),
                gpu.getFrameSync(),
                gpu.getMemoryGB(),
                gpu.getTdp(),
                gpu.getPortsDvi(),
                gpu.getPortsHdmi(),
                gpu.getPortsMiniHdmi(),
                gpu.getPortsDisplayPort(),
                gpu.getPortsMiniDisplayPort(),
                imageUrl
        );

    }

    public static GpuDTO buildShort(Gpu gpu) {

        return new GpuDTO(
                gpu.getId(),
                gpu.getManufacturerId().getManufacturerName(),
                gpu.getModel()
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

    public String getGpuChipset() {
        return gpuChipset;
    }

    public void setGpuChipset(String gpuChipset) {
        this.gpuChipset = gpuChipset;
    }

    public String getGpuMemoryType() {
        return gpuMemoryType;
    }

    public void setGpuMemoryType(String gpuMemoryType) {
        this.gpuMemoryType = gpuMemoryType;
    }

    public String getGpuInterface() {
        return gpuInterface;
    }

    public void setGpuInterface(String gpuInterface) {
        this.gpuInterface = gpuInterface;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
