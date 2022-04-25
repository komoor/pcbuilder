package pl.komoor.pcbuilder.services.dto.builds;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.komoor.pcbuilder.models.products.*;
import pl.komoor.pcbuilder.services.dto.products.*;

import java.util.List;
import java.util.stream.Collectors;

public class PcBuildFilterDTO {

    List<CpuDTO> cpu;
    List<CpuCoolerDTO> cpuCooler;
    List<MotherboardDTO> motherboard;
    List<GpuDTO> gpu;
    List<PsuDTO> psu;
    List<MemoryDTO> memory;
    List<StorageDTO> storage;
    @JsonProperty("case")
    List<CaseDTO> varCase;

    public PcBuildFilterDTO() {
    }

    public PcBuildFilterDTO(List<CpuDTO> cpu, List<CpuCoolerDTO> cpuCooler, List<MotherboardDTO> motherboard, List<GpuDTO> gpu, List<PsuDTO> psu, List<MemoryDTO> memory, List<StorageDTO> storage, List<CaseDTO> varCase) {
        this.cpu = cpu;
        this.cpuCooler = cpuCooler;
        this.motherboard = motherboard;
        this.gpu = gpu;
        this.psu = psu;
        this.memory = memory;
        this.storage = storage;
        this.varCase = varCase;
    }

    public static PcBuildFilterDTO build(List<Cpu> cpus, List<CpuCooler> cpuCoolers, List<Motherboard> motherboards, List<Gpu> gpus, List<Psu> psus, List<Memory> memories, List<Storage> storages, List<Case> cases) {

        List<CpuDTO> cpuDTOList = cpus.stream().map(CpuDTO::buildShort).collect(Collectors.toList());
        List<CpuCoolerDTO> cpuCoolerDTOList = cpuCoolers.stream().map(CpuCoolerDTO::buildShort).collect(Collectors.toList());
        List<MotherboardDTO> motherboardDTOList = motherboards.stream().map(MotherboardDTO::buildShort).collect(Collectors.toList());
        List<GpuDTO> gpuDTOList = gpus.stream().map(GpuDTO::buildShort).collect(Collectors.toList());
        List<PsuDTO> psuDTOList = psus.stream().map(PsuDTO::buildShort).collect(Collectors.toList());
        List<MemoryDTO> memoryDTOList = memories.stream().map(MemoryDTO::buildShort).collect(Collectors.toList());
        List<StorageDTO> storageDTOList = storages.stream().map(StorageDTO::buildShort).collect(Collectors.toList());
        List<CaseDTO> caseDTOList = cases.stream().map(CaseDTO::buildShort).collect(Collectors.toList());

        return new PcBuildFilterDTO(
          cpuDTOList,
          cpuCoolerDTOList,
          motherboardDTOList,
          gpuDTOList,
          psuDTOList,
          memoryDTOList,
          storageDTOList,
          caseDTOList
        );

    }

    public List<CpuDTO> getCpu() {
        return cpu;
    }

    public void setCpu(List<CpuDTO> cpu) {
        this.cpu = cpu;
    }

    public List<CpuCoolerDTO> getCpuCooler() {
        return cpuCooler;
    }

    public void setCpuCooler(List<CpuCoolerDTO> cpuCooler) {
        this.cpuCooler = cpuCooler;
    }

    public List<MotherboardDTO> getMotherboard() {
        return motherboard;
    }

    public void setMotherboard(List<MotherboardDTO> motherboard) {
        this.motherboard = motherboard;
    }

    public List<GpuDTO> getGpu() {
        return gpu;
    }

    public void setGpu(List<GpuDTO> gpu) {
        this.gpu = gpu;
    }

    public List<PsuDTO> getPsu() {
        return psu;
    }

    public void setPsu(List<PsuDTO> psu) {
        this.psu = psu;
    }

    public List<MemoryDTO> getMemory() {
        return memory;
    }

    public void setMemory(List<MemoryDTO> memory) {
        this.memory = memory;
    }

    public List<StorageDTO> getStorage() {
        return storage;
    }

    public void setStorage(List<StorageDTO> storage) {
        this.storage = storage;
    }

    public List<CaseDTO> getVarCase() {
        return varCase;
    }

    public void setVarCase(List<CaseDTO> varCase) {
        this.varCase = varCase;
    }
}
