package pl.komoor.pcbuilder.payload.request.products;

public class PsuRequest {

    private Long manufacturerId;
    private String model;
    private Long psuEfficiencyRatingId;
    private Long psuTypeId;
    private Integer wattage;
    private String modular;
    private Integer pcie8pin;
    private Integer pcie62pin;
    private Integer pcie6pin;
    private Integer sata;
    private Integer molex4pin;
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

    public Long getPsuEfficiencyRatingId() {
        return psuEfficiencyRatingId;
    }

    public void setPsuEfficiencyRatingId(Long psuEfficiencyRatingId) {
        this.psuEfficiencyRatingId = psuEfficiencyRatingId;
    }

    public Long getPsuTypeId() {
        return psuTypeId;
    }

    public void setPsuTypeId(Long psuTypeId) {
        this.psuTypeId = psuTypeId;
    }

    public Integer getWattage() {
        return wattage;
    }

    public void setWattage(Integer wattage) {
        this.wattage = wattage;
    }

    public String getModular() {
        return modular;
    }

    public void setModular(String modular) {
        this.modular = modular;
    }

    public Integer getPcie8pin() {
        return pcie8pin;
    }

    public void setPcie8pin(Integer pcie8pin) {
        this.pcie8pin = pcie8pin;
    }

    public Integer getPcie62pin() {
        return pcie62pin;
    }

    public void setPcie62pin(Integer pcie62pin) {
        this.pcie62pin = pcie62pin;
    }

    public Integer getPcie6pin() {
        return pcie6pin;
    }

    public void setPcie6pin(Integer pcie6pin) {
        this.pcie6pin = pcie6pin;
    }

    public Integer getSata() {
        return sata;
    }

    public void setSata(Integer sata) {
        this.sata = sata;
    }

    public Integer getMolex4pin() {
        return molex4pin;
    }

    public void setMolex4pin(Integer molex4pin) {
        this.molex4pin = molex4pin;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
}
