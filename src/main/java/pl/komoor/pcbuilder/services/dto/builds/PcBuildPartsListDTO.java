package pl.komoor.pcbuilder.services.dto.builds;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.komoor.pcbuilder.models.builds.PcBuild;
import pl.komoor.pcbuilder.services.dto.products.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PcBuildPartsListDTO {

    private CpuDTO cpu;
    private CpuCoolerDTO cpuCooler;
    private MotherboardDTO motherboard;
    private GpuDTO gpu;
    private PsuDTO psu;
    @JsonProperty("case")
    private CaseDTO caseDTO;
    private List<MemoryDTO> memory;
    private List<StorageDTO> storage;


    public PcBuildPartsListDTO() {
    }

    public PcBuildPartsListDTO(CpuDTO cpu, CpuCoolerDTO cpuCooler, MotherboardDTO motherboard, GpuDTO gpu, PsuDTO psu, CaseDTO caseDTO, List<MemoryDTO> memory, List<StorageDTO> storage) {
        this.cpu = cpu;
        this.cpuCooler = cpuCooler;
        this.motherboard = motherboard;
        this.gpu = gpu;
        this.psu = psu;
        this.caseDTO = caseDTO;
        this.memory = memory;
        this.storage = storage;
    }

    public static PcBuildPartsListDTO build(PcBuild pcBuild) {


        List<MemoryDTO> memoryDTOList = pcBuild.getMemory().stream().map(MemoryDTO::build).collect(Collectors.toList());
        List<StorageDTO> storageDTOList = pcBuild.getStorage().stream().map(StorageDTO::build).collect(Collectors.toList());

        GpuDTO gpuDTO = Optional.ofNullable(pcBuild.getGpuId()).map(GpuDTO::build).get();
        CpuDTO cpuDTO = Optional.ofNullable(pcBuild.getCpuId()).map(CpuDTO::build).get();
        CpuCoolerDTO cpuCoolerDTO = Optional.ofNullable(pcBuild.getCpuCoolerId()).map(CpuCoolerDTO::build).get();
        MotherboardDTO motherboardDTO = Optional.ofNullable(pcBuild.getMotherboardId()).map(MotherboardDTO::build).get();
        PsuDTO psuDTO = Optional.ofNullable(pcBuild.getPsuId()).map(PsuDTO::build).get();
        CaseDTO caseDTO = Optional.ofNullable(pcBuild.getCaseId()).map(CaseDTO::build).get();

        return new PcBuildPartsListDTO(
                cpuDTO,
                cpuCoolerDTO,
                motherboardDTO,
                gpuDTO,
                psuDTO,
                caseDTO,
                memoryDTOList,
                storageDTOList
        );
    }

    public CpuDTO getCpu() {
        return cpu;
    }

    public void setCpu(CpuDTO cpu) {
        this.cpu = cpu;
    }

    public CpuCoolerDTO getCpuCooler() {
        return cpuCooler;
    }

    public void setCpuCooler(CpuCoolerDTO cpuCooler) {
        this.cpuCooler = cpuCooler;
    }

    public MotherboardDTO getMotherboard() {
        return motherboard;
    }

    public void setMotherboard(MotherboardDTO motherboard) {
        this.motherboard = motherboard;
    }

    public GpuDTO getGpu() {
        return gpu;
    }

    public void setGpu(GpuDTO gpu) {
        this.gpu = gpu;
    }

    public PsuDTO getPsu() {
        return psu;
    }

    public void setPsu(PsuDTO psu) {
        this.psu = psu;
    }

    public CaseDTO getCaseDTO() {
        return caseDTO;
    }

    public void setCaseDTO(CaseDTO caseDTO) {
        this.caseDTO = caseDTO;
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
}
