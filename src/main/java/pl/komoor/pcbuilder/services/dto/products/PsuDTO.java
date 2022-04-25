package pl.komoor.pcbuilder.services.dto.products;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.komoor.pcbuilder.models.products.Psu;
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PsuDTO {

    private Long id;
    private Long productId;
    private String manufacturer;
    private String model;
    private String psuEfficiencyRating;
    private String psuType;
    private Integer wattage;
    private String modular;
    private Integer pcie8pin;
    private Integer pcie62pin;
    private Integer pcie6pin;
    private Integer sata;
    private Integer molex4pin;
    private String imageUrl;

    public PsuDTO() {
    }

    public PsuDTO(Long id, String manufacturer, String model) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
    }

    public PsuDTO(Long id, Long productId, String manufacturer, String model, String psuEfficiencyRating, String psuType, Integer wattage, String modular, Integer pcie8pin, Integer pcie62pin, Integer pcie6pin, Integer sata, Integer molex4pin, String imageUrl) {
        this.id = id;
        this.productId = productId;
        this.manufacturer = manufacturer;
        this.model = model;
        this.psuEfficiencyRating = psuEfficiencyRating;
        this.psuType = psuType;
        this.wattage = wattage;
        this.modular = modular;
        this.pcie8pin = pcie8pin;
        this.pcie62pin = pcie62pin;
        this.pcie6pin = pcie6pin;
        this.sata = sata;
        this.molex4pin = molex4pin;
        this.imageUrl = imageUrl;
    }

    public static PsuDTO build(Psu psu) {

        String imageUrl = null;

        if(psu.getProductId().getFileToDatabase() != null)
            imageUrl = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/images/")
                    .path(psu.getProductId().getFileToDatabase().getId())
                    .toUriString();

        return new PsuDTO(
                psu.getId(),
                psu.getProductId().getId(),
                psu.getManufacturerId().getManufacturerName(),
                psu.getModel(),
                psu.getPsuEfficiencyRatingId().getPsuEfficiencyRatingName(),
                psu.getPsuTypeId().getPsuTypeName(),
                psu.getWattage(),
                psu.getModular(),
                psu.getPcie8pin(),
                psu.getPcie62pin(),
                psu.getPcie6pin(),
                psu.getSata(),
                psu.getMolex4pin(),
                imageUrl
        );
    }

    public static PsuDTO buildShort(Psu psu) {

        return new PsuDTO(
                psu.getId(),
                psu.getManufacturerId().getManufacturerName(),
                psu.getModel()

        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPsuEfficiencyRating() {
        return psuEfficiencyRating;
    }

    public void setPsuEfficiencyRating(String psuEfficiencyRating) {
        this.psuEfficiencyRating = psuEfficiencyRating;
    }

    public String getPsuType() {
        return psuType;
    }

    public void setPsuType(String psuType) {
        this.psuType = psuType;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
