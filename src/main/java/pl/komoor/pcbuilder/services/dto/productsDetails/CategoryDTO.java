package pl.komoor.pcbuilder.services.dto.productsDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import pl.komoor.pcbuilder.models.productDetails.Category;
import pl.komoor.pcbuilder.services.dto.productsDetails.cases.CaseTypeDTO;
import pl.komoor.pcbuilder.services.dto.productsDetails.cpu.CpuSocketDTO;
import pl.komoor.pcbuilder.services.dto.productsDetails.cpu.IntegratedGraphicDTO;
import pl.komoor.pcbuilder.services.dto.productsDetails.cpuCooler.CpuCoolerWaterCooledDTO;
import pl.komoor.pcbuilder.services.dto.productsDetails.gpu.GpuChipsetDTO;
import pl.komoor.pcbuilder.services.dto.productsDetails.gpu.GpuMemoryTypeDTO;
import pl.komoor.pcbuilder.services.dto.productsDetails.memory.MemoryTimmingDTO;
import pl.komoor.pcbuilder.services.dto.productsDetails.memory.MemoryTypeDTO;
import pl.komoor.pcbuilder.services.dto.productsDetails.motherboard.MotherboardChipsetDTO;
import pl.komoor.pcbuilder.services.dto.productsDetails.motherboard.MotherboardEthernetDTO;
import pl.komoor.pcbuilder.services.dto.productsDetails.motherboard.MotherboardFormFactorDTO;
import pl.komoor.pcbuilder.services.dto.productsDetails.psu.PsuEfficiencyRatingDTO;
import pl.komoor.pcbuilder.services.dto.productsDetails.psu.PsuTypeDTO;
import pl.komoor.pcbuilder.services.dto.productsDetails.storage.StorageFormFactorDTO;
import pl.komoor.pcbuilder.services.dto.productsDetails.storage.StorageTypeDTO;

import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CategoryDTO {

    private Long id;
    private String name;

    //wspolne

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List<ManufacturerDTO> manufacturers;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List<InterfaceDTO> intefacesType;

    //cpu

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List<CpuSocketDTO> cpuSockets;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List<IntegratedGraphicDTO> integratedGraphics;

    //memory

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List<MemoryTypeDTO> memoryTypes;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List<MemoryTimmingDTO> memoryTimmings;

    //storage

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List<StorageTypeDTO> storageTypes;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List<StorageFormFactorDTO> storageFormFactors;


    //gpu

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List<GpuChipsetDTO> gpuChipsets;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List<GpuMemoryTypeDTO> gpuMemoryTypes;

    //cpu cooler

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List<CpuCoolerWaterCooledDTO> cpuCoolerWaterCooleds;

    //case
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List<CaseTypeDTO> caseTypes;

    //motherboard

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List<MotherboardFormFactorDTO> motherboardFormFactors;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List<MotherboardChipsetDTO> motherboardChipsets;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List<MotherboardEthernetDTO> motherboardEthernets;

    //psu

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List<PsuTypeDTO> psuTypes;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List<PsuEfficiencyRatingDTO> psuEfficiencyRatings;

    public CategoryDTO(Long id, String name, List<ManufacturerDTO> manufacturers, List<InterfaceDTO> intefacesType, List<CpuSocketDTO> cpuSockets, List<IntegratedGraphicDTO> integratedGraphics, List<MemoryTypeDTO> memoryTypes, List<MemoryTimmingDTO> memoryTimmings, List<StorageTypeDTO> storageTypes, List<StorageFormFactorDTO> storageFormFactors, List<GpuChipsetDTO> gpuChipsets, List<GpuMemoryTypeDTO> gpuMemoryTypes, List<CpuCoolerWaterCooledDTO> cpuCoolerWaterCooleds, List<CaseTypeDTO> caseTypes, List<MotherboardFormFactorDTO> motherboardFormFactors, List<MotherboardChipsetDTO> motherboardChipsets, List<MotherboardEthernetDTO> motherboardEthernets, List<PsuTypeDTO> psuTypes, List<PsuEfficiencyRatingDTO> psuEfficiencyRatings) {
        this.id = id;
        this.name = name;
        this.manufacturers = manufacturers;
        this.intefacesType = intefacesType;
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
        this.motherboardChipsets = motherboardChipsets;
        this.motherboardEthernets = motherboardEthernets;
        this.psuTypes = psuTypes;
        this.psuEfficiencyRatings = psuEfficiencyRatings;
    }

    public static CategoryDTO build(Category category) {

        //wspolne
        List<ManufacturerDTO> manufacturerDTOList = category.getManufacturers().stream().map(ManufacturerDTO::build).collect(Collectors.toList());
        List<InterfaceDTO> interfaceDTOList = category.getInterfaceTypes().stream().map(InterfaceDTO::build).collect(Collectors.toList());



        //cpu
        List<CpuSocketDTO> cpuSocketDTOList = category.getCpuSockets().stream().map(CpuSocketDTO::build).collect(Collectors.toList());
        List<IntegratedGraphicDTO> integratedGraphicDTOList = category.getIntegratedGraphics().stream().map(IntegratedGraphicDTO::build).collect(Collectors.toList());

        //memory

        List<MemoryTypeDTO> memoryTypeDTOList = category.getMemoryTypes().stream().map(MemoryTypeDTO::build).collect(Collectors.toList());
        List<MemoryTimmingDTO> memoryTimmingDTOList = category.getMemoryTimmings().stream().map(MemoryTimmingDTO::build).collect(Collectors.toList());

        //storage

        List<StorageTypeDTO> storageTypeDTOList = category.getStorageTypes().stream().map(StorageTypeDTO::build).collect(Collectors.toList());
        List<StorageFormFactorDTO> storageFormFactorDTOList = category.getStorageFormFactors().stream().map(StorageFormFactorDTO::build).collect(Collectors.toList());

        //gpu

        List<GpuChipsetDTO> gpuChipsetDTOList = category.getGpuChipsets().stream().map(GpuChipsetDTO::build).collect(Collectors.toList());
        List<GpuMemoryTypeDTO> gpuMemoryTypeDTOList = category.getGpuMemoryTypes().stream().map(GpuMemoryTypeDTO::build).collect(Collectors.toList());

        //cpu cooler

        List<CpuCoolerWaterCooledDTO> cpuCoolerWaterCooledDTOList = category.getCpuCoolerWaterCooleds().stream().map(CpuCoolerWaterCooledDTO::build).collect(Collectors.toList());

        //case

        List<CaseTypeDTO> caseTypeDTOList = category.getCaseTypes().stream().map(CaseTypeDTO::build).collect(Collectors.toList());

        //motherboard
        List<MotherboardFormFactorDTO> motherboardFormFactorDTOList = category.getMotherboardFormFactors().stream().map(MotherboardFormFactorDTO::build).collect(Collectors.toList());
        List<MotherboardChipsetDTO> motherboardChipsetDTOList = category.getMotherboardChipsets().stream().map(MotherboardChipsetDTO::build).collect(Collectors.toList());
        List<MotherboardEthernetDTO> motherboardEthernetDTOList = category.getMotherboardEthernets().stream().map(MotherboardEthernetDTO::build).collect(Collectors.toList());

        //psu
        List<PsuTypeDTO> psuTypeDTOList = category.getPsuTypes().stream().map(PsuTypeDTO::build).collect(Collectors.toList());
        List<PsuEfficiencyRatingDTO> psuEfficiencyRatingDTOList = category.getPsuEfficiencyRatings().stream().map(PsuEfficiencyRatingDTO::build).collect(Collectors.toList());



        return new CategoryDTO(
                category.getId(),
                category.getCategoryName(),
                manufacturerDTOList,
                interfaceDTOList,
                cpuSocketDTOList,
                integratedGraphicDTOList,
                memoryTypeDTOList,
                memoryTimmingDTOList,
                storageTypeDTOList,
                storageFormFactorDTOList,
                gpuChipsetDTOList,
                gpuMemoryTypeDTOList,
                cpuCoolerWaterCooledDTOList,
                caseTypeDTOList,
                motherboardFormFactorDTOList,
                motherboardChipsetDTOList,
                motherboardEthernetDTOList,
                psuTypeDTOList,
                psuEfficiencyRatingDTOList
        );

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ManufacturerDTO> getManufacturers() {
        return manufacturers;
    }

    public void setManufacturers(List<ManufacturerDTO> manufacturers) {
        this.manufacturers = manufacturers;
    }

    public List<InterfaceDTO> getIntefacesType() {
        return intefacesType;
    }

    public void setIntefacesType(List<InterfaceDTO> intefacesType) {
        intefacesType = intefacesType;
    }

    public List<CpuSocketDTO> getCpuSockets() {
        return cpuSockets;
    }

    public void setCpuSockets(List<CpuSocketDTO> cpuSockets) {
        this.cpuSockets = cpuSockets;
    }

    public List<IntegratedGraphicDTO> getIntegratedGraphics() {
        return integratedGraphics;
    }

    public void setIntegratedGraphics(List<IntegratedGraphicDTO> integratedGraphics) {
        this.integratedGraphics = integratedGraphics;
    }

    public List<MemoryTypeDTO> getMemoryTypes() {
        return memoryTypes;
    }

    public void setMemoryTypes(List<MemoryTypeDTO> memoryTypes) {
        this.memoryTypes = memoryTypes;
    }

    public List<MemoryTimmingDTO> getMemoryTimmings() {
        return memoryTimmings;
    }

    public void setMemoryTimmings(List<MemoryTimmingDTO> memoryTimmings) {
        this.memoryTimmings = memoryTimmings;
    }

    public List<StorageTypeDTO> getStorageTypes() {
        return storageTypes;
    }

    public void setStorageTypes(List<StorageTypeDTO> storageTypes) {
        this.storageTypes = storageTypes;
    }

    public List<StorageFormFactorDTO> getStorageFormFactors() {
        return storageFormFactors;
    }

    public void setStorageFormFactors(List<StorageFormFactorDTO> storageFormFactors) {
        this.storageFormFactors = storageFormFactors;
    }

    public List<GpuChipsetDTO> getGpuChipsets() {
        return gpuChipsets;
    }

    public void setGpuChipsets(List<GpuChipsetDTO> gpuChipsets) {
        this.gpuChipsets = gpuChipsets;
    }

    public List<GpuMemoryTypeDTO> getGpuMemoryTypes() {
        return gpuMemoryTypes;
    }

    public void setGpuMemoryTypes(List<GpuMemoryTypeDTO> gpuMemoryTypes) {
        this.gpuMemoryTypes = gpuMemoryTypes;
    }

    public List<CpuCoolerWaterCooledDTO> getCpuCoolerWaterCooleds() {
        return cpuCoolerWaterCooleds;
    }

    public void setCpuCoolerWaterCooleds(List<CpuCoolerWaterCooledDTO> cpuCoolerWaterCooleds) {
        this.cpuCoolerWaterCooleds = cpuCoolerWaterCooleds;
    }

    public List<CaseTypeDTO> getCaseTypes() {
        return caseTypes;
    }

    public void setCaseTypes(List<CaseTypeDTO> caseTypes) {
        this.caseTypes = caseTypes;
    }

    public List<MotherboardFormFactorDTO> getMotherboardFormFactors() {
        return motherboardFormFactors;
    }

    public void setMotherboardFormFactors(List<MotherboardFormFactorDTO> motherboardFormFactors) {
        this.motherboardFormFactors = motherboardFormFactors;
    }

    public List<PsuTypeDTO> getPsuTypes() {
        return psuTypes;
    }

    public void setPsuTypes(List<PsuTypeDTO> psuTypes) {
        this.psuTypes = psuTypes;
    }

    public List<PsuEfficiencyRatingDTO> getPsuEfficiencyRatings() {
        return psuEfficiencyRatings;
    }

    public void setPsuEfficiencyRatings(List<PsuEfficiencyRatingDTO> psuEfficiencyRatings) {
        this.psuEfficiencyRatings = psuEfficiencyRatings;
    }

    public List<MotherboardChipsetDTO> getMotherboardChipsets() {
        return motherboardChipsets;
    }

    public void setMotherboardChipsets(List<MotherboardChipsetDTO> motherboardChipsets) {
        this.motherboardChipsets = motherboardChipsets;
    }

    public List<MotherboardEthernetDTO> getMotherboardEthernets() {
        return motherboardEthernets;
    }

    public void setMotherboardEthernets(List<MotherboardEthernetDTO> motherboardEthernets) {
        this.motherboardEthernets = motherboardEthernets;
    }
}
