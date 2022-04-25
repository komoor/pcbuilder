package pl.komoor.pcbuilder.models.productDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.komoor.pcbuilder.models.productDetails.cases.CaseType;
import pl.komoor.pcbuilder.models.productDetails.cpuCooler.CpuCoolerWaterCooled;
import pl.komoor.pcbuilder.models.productDetails.cpu.CpuSocket;
import pl.komoor.pcbuilder.models.productDetails.cpu.IntegratedGraphic;
import pl.komoor.pcbuilder.models.productDetails.gpu.GpuChipset;
import pl.komoor.pcbuilder.models.productDetails.gpu.GpuMemoryType;
import pl.komoor.pcbuilder.models.productDetails.memory.MemoryTimming;
import pl.komoor.pcbuilder.models.productDetails.memory.MemoryType;
import pl.komoor.pcbuilder.models.productDetails.motherboard.MotherboardChipset;
import pl.komoor.pcbuilder.models.productDetails.motherboard.MotherboardEthernet;
import pl.komoor.pcbuilder.models.productDetails.motherboard.MotherboardFormFactor;
import pl.komoor.pcbuilder.models.productDetails.psu.PsuEfficiencyRating;
import pl.komoor.pcbuilder.models.productDetails.psu.PsuType;
import pl.komoor.pcbuilder.models.productDetails.storage.StorageFormFactor;
import pl.komoor.pcbuilder.models.productDetails.storage.StorageType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String categoryName;

    //wspolne

    @JsonIgnoreProperties("manufacturerCategory")
    @ManyToMany(mappedBy="manufacturerCategory", fetch = FetchType.LAZY)
    List<Manufacturer> manufacturers;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "interface_type_category", joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "interface_type_id"))
    List<InterfaceType> interfaceTypes;

    //cpu

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "cpu_socket_category", joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "socket_id"))
    List<CpuSocket> cpuSockets;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "integrated_graphic_category", joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "integrated_graphic_id"))
    List<IntegratedGraphic> integratedGraphics;

    //memory

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "memory_type_category", joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "memory_type_id"))
    List<MemoryType> memoryTypes;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "memory_timming_category", joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "memory_timming_id"))
    List<MemoryTimming> memoryTimmings;

    //storage

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "storage_type_category", joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "storage_type_id"))
    List<StorageType> storageTypes;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "storage_form_factor_category", joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "storage_form_factor_id"))
    List<StorageFormFactor> storageFormFactors;


    //gpu

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "gpu_chipset_category", joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "gpu_chipset_id"))
    List<GpuChipset> gpuChipsets;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "gpu_memory_type_category", joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "gpu_memory_type_id"))
    List<GpuMemoryType> gpuMemoryTypes;

    //cpu cooler

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "cpu_cooler_water_cooled_category", joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "cpu_cooler_water_cooled_type_id"))
    List<CpuCoolerWaterCooled> cpuCoolerWaterCooleds;


    //cases
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "case_type_category", joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "case_type_id"))
    List<CaseType> caseTypes;

    //motherboard

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "motherboard_form_factor_category", joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "motherboard_form_factor_id"))
    List<MotherboardFormFactor> motherboardFormFactors;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "motherboard_ethernet_category", joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "motherboard_ethernet_id"))
    List<MotherboardEthernet> motherboardEthernets;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "motherboard_chipset_category", joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "motherboard_chipset_id"))
    List<MotherboardChipset> motherboardChipsets;

    //psu

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "psu_type_category", joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "psu_type_id"))
    List<PsuType> psuTypes;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "psu_efficiency_rating_category", joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "psu_efficiency_rating_id"))
    List<PsuEfficiencyRating> psuEfficiencyRatings;


    public Category() {

    }

    public Category(Long id, @NotBlank @Size(max = 20) String categoryName, List<Manufacturer> manufacturers, List<InterfaceType> interfaceTypes, List<CpuSocket> cpuSockets, List<IntegratedGraphic> integratedGraphics, List<MemoryType> memoryTypes, List<MemoryTimming> memoryTimmings, List<StorageType> storageTypes, List<StorageFormFactor> storageFormFactors, List<GpuChipset> gpuChipsets, List<GpuMemoryType> gpuMemoryTypes, List<CpuCoolerWaterCooled> cpuCoolerWaterCooleds, List<CaseType> caseTypes, List<MotherboardFormFactor> motherboardFormFactors, List<MotherboardEthernet> motherboardEthernets, List<MotherboardChipset> motherboardChipsets, List<PsuType> psuTypes, List<PsuEfficiencyRating> psuEfficiencyRatings) {
        this.id = id;
        this.categoryName = categoryName;
        this.manufacturers = manufacturers;
        this.interfaceTypes = interfaceTypes;
        this.cpuSockets = cpuSockets;
        this.integratedGraphics = integratedGraphics;
        this.memoryTypes = memoryTypes;
        this.memoryTimmings = memoryTimmings;
        this.storageTypes = storageTypes;
        this.storageFormFactors = storageFormFactors;
        this.gpuChipsets = gpuChipsets;
        this.gpuMemoryTypes = gpuMemoryTypes;
        this.cpuCoolerWaterCooleds = cpuCoolerWaterCooleds;
        this.caseTypes = caseTypes;
        this.motherboardFormFactors = motherboardFormFactors;
        this.motherboardEthernets = motherboardEthernets;
        this.motherboardChipsets = motherboardChipsets;
        this.psuTypes = psuTypes;
        this.psuEfficiencyRatings = psuEfficiencyRatings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Manufacturer> getManufacturers() {
        return manufacturers;
    }

    public void setManufacturers(List<Manufacturer> manufacturers) {
        this.manufacturers = manufacturers;
    }

    public List<InterfaceType> getInterfaceTypes() {
        return interfaceTypes;
    }

    public void setInterfaceTypes(List<InterfaceType> interfaceTypes) {
        this.interfaceTypes = interfaceTypes;
    }

    public List<CpuSocket> getCpuSockets() {
        return cpuSockets;
    }

    public void setCpuSockets(List<CpuSocket> cpuSockets) {
        this.cpuSockets = cpuSockets;
    }

    public List<IntegratedGraphic> getIntegratedGraphics() {
        return integratedGraphics;
    }

    public void setIntegratedGraphics(List<IntegratedGraphic> integratedGraphics) {
        this.integratedGraphics = integratedGraphics;
    }

    public List<MemoryType> getMemoryTypes() {
        return memoryTypes;
    }

    public void setMemoryTypes(List<MemoryType> memoryTypes) {
        this.memoryTypes = memoryTypes;
    }

    public List<MemoryTimming> getMemoryTimmings() {
        return memoryTimmings;
    }

    public void setMemoryTimmings(List<MemoryTimming> memoryTimmings) {
        this.memoryTimmings = memoryTimmings;
    }

    public List<StorageType> getStorageTypes() {
        return storageTypes;
    }

    public void setStorageTypes(List<StorageType> storageTypes) {
        this.storageTypes = storageTypes;
    }

    public List<StorageFormFactor> getStorageFormFactors() {
        return storageFormFactors;
    }

    public void setStorageFormFactors(List<StorageFormFactor> storageFormFactors) {
        this.storageFormFactors = storageFormFactors;
    }

    public List<GpuChipset> getGpuChipsets() {
        return gpuChipsets;
    }

    public void setGpuChipsets(List<GpuChipset> gpuChipsets) {
        this.gpuChipsets = gpuChipsets;
    }

    public List<GpuMemoryType> getGpuMemoryTypes() {
        return gpuMemoryTypes;
    }

    public void setGpuMemoryTypes(List<GpuMemoryType> gpuMemoryTypes) {
        this.gpuMemoryTypes = gpuMemoryTypes;
    }

    public List<CpuCoolerWaterCooled> getCpuCoolerWaterCooleds() {
        return cpuCoolerWaterCooleds;
    }

    public void setCpuCoolerWaterCooleds(List<CpuCoolerWaterCooled> cpuCoolerWaterCooleds) {
        this.cpuCoolerWaterCooleds = cpuCoolerWaterCooleds;
    }

    public List<CaseType> getCaseTypes() {
        return caseTypes;
    }

    public void setCaseTypes(List<CaseType> caseTypes) {
        this.caseTypes = caseTypes;
    }

    public List<MotherboardFormFactor> getMotherboardFormFactors() {
        return motherboardFormFactors;
    }

    public void setMotherboardFormFactors(List<MotherboardFormFactor> motherboardFormFactors) {
        this.motherboardFormFactors = motherboardFormFactors;
    }

    public List<PsuType> getPsuTypes() {
        return psuTypes;
    }

    public void setPsuTypes(List<PsuType> psuTypes) {
        this.psuTypes = psuTypes;
    }

    public List<PsuEfficiencyRating> getPsuEfficiencyRatings() {
        return psuEfficiencyRatings;
    }

    public void setPsuEfficiencyRatings(List<PsuEfficiencyRating> psuEfficiencyRatings) {
        this.psuEfficiencyRatings = psuEfficiencyRatings;
    }

    public List<MotherboardEthernet> getMotherboardEthernets() {
        return motherboardEthernets;
    }

    public void setMotherboardEthernets(List<MotherboardEthernet> motherboardEthernets) {
        this.motherboardEthernets = motherboardEthernets;
    }

    public List<MotherboardChipset> getMotherboardChipsets() {
        return motherboardChipsets;
    }

    public void setMotherboardChipsets(List<MotherboardChipset> motherboardChipsets) {
        this.motherboardChipsets = motherboardChipsets;
    }
}