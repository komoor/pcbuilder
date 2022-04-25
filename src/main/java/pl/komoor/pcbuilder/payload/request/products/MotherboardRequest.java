package pl.komoor.pcbuilder.payload.request.products;

import java.util.Set;

public class MotherboardRequest {

    private Long manufacturerId;
    private String model;
    private Long cpuSocketId;
    private Long motherboardFormFactorId;
    private Long motherboardChipsetId;
    private Integer memoryMax;
    private Set<String> memoryTypeId;
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
    private Long motherboardEthernetId;
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

    public Long getCpuSocketId() {
        return cpuSocketId;
    }

    public void setCpuSocketId(Long cpuSocketId) {
        this.cpuSocketId = cpuSocketId;
    }

    public Long getMotherboardFormFactorId() {
        return motherboardFormFactorId;
    }

    public void setMotherboardFormFactorId(Long motherboardFormFactorId) {
        this.motherboardFormFactorId = motherboardFormFactorId;
    }

    public Long getMotherboardChipsetId() {
        return motherboardChipsetId;
    }

    public void setMotherboardChipsetId(Long motherboardChipsetId) {
        this.motherboardChipsetId = motherboardChipsetId;
    }

    public Integer getMemoryMax() {
        return memoryMax;
    }

    public void setMemoryMax(Integer memoryMax) {
        this.memoryMax = memoryMax;
    }

    public Set<String> getMemoryTypeId() {
        return memoryTypeId;
    }

    public void setMemoryTypeId(Set<String> memoryTypeId) {
        this.memoryTypeId = memoryTypeId;
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

    public Long getMotherboardEthernetId() {
        return motherboardEthernetId;
    }

    public void setMotherboardEthernetId(Long motherboardEthernetId) {
        this.motherboardEthernetId = motherboardEthernetId;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
}
