package pl.komoor.pcbuilder.payload.request.products;

import java.util.Set;

public class CaseRequest {

    private Long manufacturerId;
    private String model;
    private Long caseTypeId;
    private Set<String> motherboardFormFactorId;
    private Integer maxGpuCardLength;
    private Integer ex35bays;
    private Integer ex25bays;
    private Integer in35bays;
    private Integer in25bays;
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

    public Long getCaseTypeId() {
        return caseTypeId;
    }

    public void setCaseTypeId(Long caseTypeId) {
        this.caseTypeId = caseTypeId;
    }

    public Set<String> getMotherboardFormFactorId() {
        return motherboardFormFactorId;
    }

    public void setMotherboardFormFactorId(Set<String> motherboardFormFactorId) {
        this.motherboardFormFactorId = motherboardFormFactorId;
    }

    public Integer getMaxGpuCardLength() {
        return maxGpuCardLength;
    }

    public void setMaxGpuCardLength(Integer maxGpuCardLength) {
        this.maxGpuCardLength = maxGpuCardLength;
    }

    public Integer getEx25bays() {
        return ex25bays;
    }

    public void setEx25bays(Integer ex25bays) {
        this.ex25bays = ex25bays;
    }

    public Integer getIn25bays() {
        return in25bays;
    }

    public void setIn25bays(Integer in25bays) {
        this.in25bays = in25bays;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public Integer getEx35bays() {
        return ex35bays;
    }

    public void setEx35bays(Integer ex35bays) {
        this.ex35bays = ex35bays;
    }

    public Integer getIn35bays() {
        return in35bays;
    }

    public void setIn35bays(Integer in35bays) {
        this.in35bays = in35bays;
    }
}
