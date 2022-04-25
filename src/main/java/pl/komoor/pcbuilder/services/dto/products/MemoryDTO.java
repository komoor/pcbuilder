package pl.komoor.pcbuilder.services.dto.products;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.komoor.pcbuilder.models.products.Memory;
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MemoryDTO {

    private Long id;
    private Long productId;
    private String manufacturer;
    private String model;
    private String memoryType;
    private String memoryTimming;
    private Integer numberOfModules;
    private Integer memoryGB;
    private Double voltage;
    private Integer casLatency;
    private String heatSpreader;
    private String imageUrl;

    public MemoryDTO() {
    }

    public MemoryDTO(Long id, String manufacturer, String model) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
    }

    public MemoryDTO(Long id, Long productId, String manufacturer, String model, String memoryType, String memoryTimming, Integer numberOfModules, Integer memoryGB, Double voltage, Integer casLatency, String heatSpreader, String imageUrl) {
        this.id = id;
        this.productId = productId;
        this.manufacturer = manufacturer;
        this.model = model;
        this.memoryType = memoryType;
        this.memoryTimming = memoryTimming;
        this.numberOfModules = numberOfModules;
        this.memoryGB = memoryGB;
        this.voltage = voltage;
        this.casLatency = casLatency;
        this.heatSpreader = heatSpreader;
        this.imageUrl = imageUrl;
    }

    public static MemoryDTO build(Memory memory){

        String imageUrl = null;

        if(memory.getProductId().getFileToDatabase() != null)
            imageUrl = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/images/")
                    .path(memory.getProductId().getFileToDatabase().getId())
                    .toUriString();

        return new MemoryDTO(
                memory.getId(),
                memory.getProductId().getId(),
                memory.getManufacturerId().getManufacturerName(),
                memory.getModel(),
                memory.getMemoryTypeId().getTypeName(),
                memory.getMemoryTimmingId().getTimmingName(),
                memory.getNumberOfModules(),
                memory.getMemoryGB(),
                memory.getVoltage(),
                memory.getCasLatency(),
                memory.getHeatSpreader(),
                imageUrl
        );

    }

    public static MemoryDTO buildShort(Memory memory){

        return new MemoryDTO(
                memory.getId(),
                memory.getManufacturerId().getManufacturerName(),
                memory.getModel()
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

    public String getMemoryType() {
        return memoryType;
    }

    public void setMemoryType(String memoryType) {
        this.memoryType = memoryType;
    }

    public String getMemoryTimming() {
        return memoryTimming;
    }

    public void setMemoryTimming(String memoryTimming) {
        this.memoryTimming = memoryTimming;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
