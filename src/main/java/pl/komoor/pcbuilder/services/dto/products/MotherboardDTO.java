package pl.komoor.pcbuilder.services.dto.products;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.komoor.pcbuilder.models.productDetails.memory.MemoryType;
import pl.komoor.pcbuilder.models.products.Motherboard;

import java.util.List;
import java.util.stream.Collectors;
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MotherboardDTO {

    private Long id;
    private Long productId;
    private String manufacturer;
    private String model;
    private String cpuSocket;
    private String motherboardFormFactor;
    private String motherboardChipset;
    private Integer memoryMax;
    private List<String> memoryType;
    private Integer memorySlots;
    private Integer m2Slots;
    private Integer pcieX16Slots;
    private Integer pcieX8Slots;
    private Integer pcieX4Slots;
    private Integer pcieX1Slots;
    private Integer pciSlots;
    private Integer sata3GbPorts;
    private Integer sata6GbPorts;
    private Integer sataExpressPorts;
    private Integer usb20Headers;
    private Integer usb32gen1Headers;
    private Integer usb32gen2Headers;
    private String motherboardEthernet;
    private String imageUrl;

    public MotherboardDTO() {
    }

    public MotherboardDTO(Long id, String manufacturer, String model) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
    }

    public MotherboardDTO(Long id, Long productId, String manufacturer, String model, String cpuSocket, String motherboardFormFactor, String motherboardChipset, Integer memoryMax, List<String> memoryType, Integer memorySlots, Integer m2Slots, Integer pcieX16Slots, Integer pcieX8Slots, Integer pcieX4Slots, Integer pcieX1Slots, Integer pciSlots, Integer sata3GbPorts, Integer sata6GbPorts, Integer sataExpressPorts, Integer usb20Headers, Integer usb32gen1Headers, Integer usb32gen2Headers, String motherboardEthernet, String imageUrl) {
        this.id = id;
        this.productId = productId;
        this.manufacturer = manufacturer;
        this.model = model;
        this.cpuSocket = cpuSocket;
        this.motherboardFormFactor = motherboardFormFactor;
        this.motherboardChipset = motherboardChipset;
        this.memoryMax = memoryMax;
        this.memoryType = memoryType;
        this.memorySlots = memorySlots;
        this.m2Slots = m2Slots;
        this.pcieX16Slots = pcieX16Slots;
        this.pcieX8Slots = pcieX8Slots;
        this.pcieX4Slots = pcieX4Slots;
        this.pcieX1Slots = pcieX1Slots;
        this.pciSlots = pciSlots;
        this.sata3GbPorts = sata3GbPorts;
        this.sata6GbPorts = sata6GbPorts;
        this.sataExpressPorts = sataExpressPorts;
        this.usb20Headers = usb20Headers;
        this.usb32gen1Headers = usb32gen1Headers;
        this.usb32gen2Headers = usb32gen2Headers;
        this.motherboardEthernet = motherboardEthernet;
        this.imageUrl = imageUrl;
    }

    public static MotherboardDTO build(Motherboard motherboard) {

        String imageUrl = null;
        List<String> memoryTypes = motherboard.getMemoryTypeId().stream().map(MemoryType::getTypeName).collect(Collectors.toList());

        if(motherboard.getProductId().getFileToDatabase() != null)
            imageUrl = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/images/")
                    .path(motherboard.getProductId().getFileToDatabase().getId())
                    .toUriString();

        return new MotherboardDTO(
                motherboard.getId(),
                motherboard.getProductId().getId(),
                motherboard.getManufacturerId().getManufacturerName(),
                motherboard.getModel(),
                motherboard.getCpuSocketId().getSocketName(),
                motherboard.getMotherboardFormFactorId().getMotherboardFormFactorName(),
                motherboard.getMotherboardChipsetId().getMotherboardChipsetName(),
                motherboard.getMemoryMax(),
                memoryTypes,
                motherboard.getMemorySlots(),
                motherboard.getM2Slots(),
                motherboard.getPcieX16Slots(),
                motherboard.getPcieX8Slots(),
                motherboard.getPcieX4Slots(),
                motherboard.getPcieX1Slots(),
                motherboard.getPciSlots(),
                motherboard.getSata3GbPorts(),
                motherboard.getSata6GbPorts(),
                motherboard.getSataExpressPorts(),
                motherboard.getUsb20Headers(),
                motherboard.getUsb32gen1Headers(),
                motherboard.getUsb32gen2Headers(),
                motherboard.getMotherboardEthernetId().getMotherboardEthernetName(),
                imageUrl

        );
    }

    public static MotherboardDTO buildShort(Motherboard motherboard) {

        return new MotherboardDTO(
                motherboard.getId(),
                motherboard.getManufacturerId().getManufacturerName(),
                motherboard.getModel()

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

    public String getCpuSocket() {
        return cpuSocket;
    }

    public void setCpuSocket(String cpuSocket) {
        this.cpuSocket = cpuSocket;
    }

    public String getMotherboardFormFactor() {
        return motherboardFormFactor;
    }

    public void setMotherboardFormFactor(String motherboardFormFactor) {
        this.motherboardFormFactor = motherboardFormFactor;
    }

    public String getMotherboardChipset() {
        return motherboardChipset;
    }

    public void setMotherboardChipset(String motherboardChipset) {
        this.motherboardChipset = motherboardChipset;
    }

    public Integer getMemoryMax() {
        return memoryMax;
    }

    public void setMemoryMax(Integer memoryMax) {
        this.memoryMax = memoryMax;
    }

    public List<String> getMemoryType() {
        return memoryType;
    }

    public void setMemoryType(List<String> memoryType) {
        this.memoryType = memoryType;
    }

    public Integer getMemorySlots() {
        return memorySlots;
    }

    public void setMemorySlots(Integer memorySlots) {
        this.memorySlots = memorySlots;
    }

    public Integer getM2Slots() {
        return m2Slots;
    }

    public void setM2Slots(Integer m2Slots) {
        this.m2Slots = m2Slots;
    }

    public Integer getPcieX16Slots() {
        return pcieX16Slots;
    }

    public void setPcieX16Slots(Integer pcieX16Slots) {
        this.pcieX16Slots = pcieX16Slots;
    }

    public Integer getPcieX8Slots() {
        return pcieX8Slots;
    }

    public void setPcieX8Slots(Integer pcieX8Slots) {
        this.pcieX8Slots = pcieX8Slots;
    }

    public Integer getPcieX4Slots() {
        return pcieX4Slots;
    }

    public void setPcieX4Slots(Integer pcieX4Slots) {
        this.pcieX4Slots = pcieX4Slots;
    }

    public Integer getPcieX1Slots() {
        return pcieX1Slots;
    }

    public void setPcieX1Slots(Integer pcieX1Slots) {
        this.pcieX1Slots = pcieX1Slots;
    }

    public Integer getPciSlots() {
        return pciSlots;
    }

    public void setPciSlots(Integer pciSlots) {
        this.pciSlots = pciSlots;
    }

    public Integer getSata3GbPorts() {
        return sata3GbPorts;
    }

    public void setSata3GbPorts(Integer sata3GbPorts) {
        this.sata3GbPorts = sata3GbPorts;
    }

    public Integer getSata6GbPorts() {
        return sata6GbPorts;
    }

    public void setSata6GbPorts(Integer sata6GbPorts) {
        this.sata6GbPorts = sata6GbPorts;
    }

    public Integer getSataExpressPorts() {
        return sataExpressPorts;
    }

    public void setSataExpressPorts(Integer sataExpressPorts) {
        this.sataExpressPorts = sataExpressPorts;
    }

    public Integer getUsb20Headers() {
        return usb20Headers;
    }

    public void setUsb20Headers(Integer usb20Headers) {
        this.usb20Headers = usb20Headers;
    }

    public Integer getUsb32gen1Headers() {
        return usb32gen1Headers;
    }

    public void setUsb32gen1Headers(Integer usb32gen1Headers) {
        this.usb32gen1Headers = usb32gen1Headers;
    }

    public Integer getUsb32gen2Headers() {
        return usb32gen2Headers;
    }

    public void setUsb32gen2Headers(Integer usb32gen2Headers) {
        this.usb32gen2Headers = usb32gen2Headers;
    }

    public String getMotherboardEthernet() {
        return motherboardEthernet;
    }

    public void setMotherboardEthernet(String motherboardEthernet) {
        this.motherboardEthernet = motherboardEthernet;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
