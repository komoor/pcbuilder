package pl.komoor.pcbuilder.models.products;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.komoor.pcbuilder.models.productDetails.Manufacturer;
import pl.komoor.pcbuilder.models.productDetails.cpu.CpuSocket;
import pl.komoor.pcbuilder.models.productDetails.memory.MemoryType;
import pl.komoor.pcbuilder.models.productDetails.motherboard.MotherboardChipset;
import pl.komoor.pcbuilder.models.productDetails.motherboard.MotherboardEthernet;
import pl.komoor.pcbuilder.models.productDetails.motherboard.MotherboardFormFactor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "motherboard")
public class Motherboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="productId", referencedColumnName = "id")
    private Product productId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="manufacturerId", referencedColumnName = "id", nullable=false)
    private Manufacturer manufacturerId;

    @NotBlank
    @Size(max = 50)
    private String model;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cpuSocketId", referencedColumnName = "id", nullable=false)
    private CpuSocket cpuSocketId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="motherboardFormFactorId", referencedColumnName = "id", nullable=false)
    private MotherboardFormFactor motherboardFormFactorId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="motherboardChipsetId", referencedColumnName = "id", nullable=false)
    private MotherboardChipset motherboardChipsetId;

    private Integer memoryMax;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "motherboard_memory_type", joinColumns = @JoinColumn(name = "motherboard_id"),
            inverseJoinColumns = @JoinColumn(name = "memory_type_id"))
    private Set<MemoryType> memoryTypeId;

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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="motherboardEthernetId", referencedColumnName = "id", nullable=false)
    private MotherboardEthernet motherboardEthernetId;


    public Motherboard() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public Manufacturer getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Manufacturer manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public CpuSocket getCpuSocketId() {
        return cpuSocketId;
    }

    public void setCpuSocketId(CpuSocket cpuSocketId) {
        this.cpuSocketId = cpuSocketId;
    }

    public MotherboardFormFactor getMotherboardFormFactorId() {
        return motherboardFormFactorId;
    }

    public void setMotherboardFormFactorId(MotherboardFormFactor motherboardFormFactorId) {
        this.motherboardFormFactorId = motherboardFormFactorId;
    }

    public MotherboardChipset getMotherboardChipsetId() {
        return motherboardChipsetId;
    }

    public void setMotherboardChipsetId(MotherboardChipset motherboardChipsetId) {
        this.motherboardChipsetId = motherboardChipsetId;
    }

    public Integer getMemoryMax() {
        return memoryMax;
    }

    public void setMemoryMax(Integer memoryMax) {
        this.memoryMax = memoryMax;
    }

    public Set<MemoryType> getMemoryTypeId() {
        return memoryTypeId;
    }

    public void setMemoryTypeId(Set<MemoryType> memoryTypeId) {
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

    public MotherboardEthernet getMotherboardEthernetId() {
        return motherboardEthernetId;
    }

    public void setMotherboardEthernetId(MotherboardEthernet motherboardEthernetId) {
        this.motherboardEthernetId = motherboardEthernetId;
    }
}
