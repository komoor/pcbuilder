package pl.komoor.pcbuilder.payload.request.builds;

import java.util.List;

public class PcPartsListRequest {

    private Long userId;
    private String listName;
    private Long cpuId;
    private Long motherboardId;
    private List<String> memoryId;
    private Long psuId;
    private Long gpuId;
    private List<String> storageId;
    private Long cpuCoolerId;
    private Long caseId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public Long getCpuId() {
        return cpuId;
    }

    public void setCpuId(Long cpuId) {
        this.cpuId = cpuId;
    }

    public Long getMotherboardId() {
        return motherboardId;
    }

    public void setMotherboardId(Long motherboardId) {
        this.motherboardId = motherboardId;
    }

    public List<String> getMemoryId() {
        return memoryId;
    }

    public void setMemoryId(List<String> memoryId) {
        this.memoryId = memoryId;
    }

    public Long getPsuId() {
        return psuId;
    }

    public void setPsuId(Long psuId) {
        this.psuId = psuId;
    }

    public Long getGpuId() {
        return gpuId;
    }

    public void setGpuId(Long gpuId) {
        this.gpuId = gpuId;
    }

    public List<String> getStorageId() {
        return storageId;
    }

    public void setStorageId(List<String> storageId) {
        this.storageId = storageId;
    }

    public Long getCpuCoolerId() {
        return cpuCoolerId;
    }

    public void setCpuCoolerId(Long cpuCoolerId) {
        this.cpuCoolerId = cpuCoolerId;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }
}
