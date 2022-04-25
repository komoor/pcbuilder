package pl.komoor.pcbuilder.services.dto.products;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.komoor.pcbuilder.models.productDetails.motherboard.MotherboardFormFactor;
import pl.komoor.pcbuilder.models.products.Case;

import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CaseDTO {

    private Long id;
    private Long productId;
    private String manufacturer;
    private String model;
    private String caseType;
    private List<String> motherboardFormFactor;
    private Integer maxGpuCardLength;
    private Integer ex35bays;
    private Integer ex25bays;
    private Integer in35bays;
    private Integer in25bays;
    private String imageUrl;

    public CaseDTO() {
    }

    public CaseDTO(Long id, String manufacturer, String model) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
    }

    public CaseDTO(Long id, Long productId, String manufacturer, String model, String caseType, List<String> motherboardFormFactor, Integer maxGpuCardLength, Integer ex35bays, Integer ex25bays, Integer in35bays, Integer in25bays, String imageUrl) {
        this.id = id;
        this.productId = productId;
        this.manufacturer = manufacturer;
        this.model = model;
        this.caseType = caseType;
        this.motherboardFormFactor = motherboardFormFactor;
        this.maxGpuCardLength = maxGpuCardLength;
        this.ex35bays = ex35bays;
        this.ex25bays = ex25bays;
        this.in35bays = in35bays;
        this.in25bays = in25bays;
        this.imageUrl = imageUrl;
    }


    public static CaseDTO build(Case cases) {

        String imageUrl = null;
        List<String> motherboardFromFactors = cases.getMotherboardFormFactorId().stream().map(MotherboardFormFactor::getMotherboardFormFactorName).collect(Collectors.toList());

        if(cases.getProductId().getFileToDatabase() != null)
            imageUrl = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/images/")
                    .path(cases.getProductId().getFileToDatabase().getId())
                    .toUriString();

        return new CaseDTO(
                cases.getId(),
                cases.getProductId().getId(),
                cases.getManufacturerId().getManufacturerName(),
                cases.getModel(),
                cases.getCaseTypeId().getCaseTypeName(),
                motherboardFromFactors,
                cases.getMaxGpuCardLength(),
                cases.getEx35bays(),
                cases.getEx25bays(),
                cases.getIn35bays(),
                cases.getIn25bays(),
                imageUrl
        );
    }

    public static CaseDTO buildShort(Case cases) {

        return new CaseDTO(
                cases.getId(),
                cases.getManufacturerId().getManufacturerName(),
                cases.getModel()
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

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public List<String> getMotherboardFormFactor() {
        return motherboardFormFactor;
    }

    public void setMotherboardFormFactor(List<String> motherboardFormFactor) {
        this.motherboardFormFactor = motherboardFormFactor;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
