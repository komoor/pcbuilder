package pl.komoor.pcbuilder.payload.request.builds;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class PcBuildRequest {


    private String buildName;
    private String buildDescription;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date buildDate;
    private Long userId;
    private String imageId;
    private Long cpuId;
    private Long motherboardId;
    private List<String> memoryId;
    private Long psuId;
    private Long gpuId;
    private List<String> storageId;
    private Long cpuCoolerId;
    private Long caseId;

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public String getBuildDescription() {
        return buildDescription;
    }

    public void setBuildDescription(String buildDescription) {
        this.buildDescription = buildDescription;
    }

    public Date getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(Date buildDate) {
        this.buildDate = buildDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
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
