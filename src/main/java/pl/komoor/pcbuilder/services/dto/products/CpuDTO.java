package pl.komoor.pcbuilder.services.dto.products;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.komoor.pcbuilder.models.products.Cpu;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CpuDTO {

    private Long id;
    private Long productId;
    private String manufacturer;
    private String model;
    private String socket;
    private Integer cores;
    private Double baseClock;
    private Double boostClock;
    private Integer tdp;
    private String integratedGraphic;
    private String imageUrl;


    public CpuDTO() {


    }

    public CpuDTO(Long id, String manufacturer, String model) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
    }

    public CpuDTO(Long id, Long productId, String manufacturer, String model, String socket, Integer cores, Double baseClock, Double boostClock, Integer tdp) {

        this.id = id;
        this.productId = productId;
        this.manufacturer = manufacturer;
        this.model = model;
        this.socket = socket;
        this.cores = cores;
        this.baseClock = baseClock;
        this.boostClock = boostClock;
        this.tdp = tdp;
    }

    public CpuDTO(Long id, Long productId, String manufacturer, String model, String socket, Integer cores, Double baseClock, Double boostClock, Integer tdp, String integratedGraphic, String imageUrl) {

        this.id = id;
        this.productId = productId;
        this.manufacturer = manufacturer;
        this.model = model;
        this.socket = socket;
        this.cores = cores;
        this.baseClock = baseClock;
        this.boostClock = boostClock;
        this.tdp = tdp;
        this.integratedGraphic = integratedGraphic;
        this.imageUrl = imageUrl;
    }

    public static CpuDTO build(Cpu cpu) {

        String integratedGraphicName = null;
        String imageUrl = null;

        if(cpu.getProductId().getFileToDatabase() != null)
            imageUrl = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/images/")
                    .path(cpu.getProductId().getFileToDatabase().getId())
                    .toUriString();

        if(cpu.getIntegratedGraphic() != null)
            integratedGraphicName = cpu.getIntegratedGraphic().getIntegratedGraphicName();


        return new CpuDTO(
                cpu.getId(),
                cpu.getProductId().getId(),
                cpu.getManufacturerId().getManufacturerName(),
                cpu.getModel(),
                cpu.getSocketId().getSocketName(),
                cpu.getCores(),
                cpu.getBaseClock(),
                cpu.getBoostClock(),
                cpu.getTdp(),
                integratedGraphicName,
                imageUrl
        );
    }

    public static CpuDTO buildShort(Cpu cpu) {

        return new CpuDTO(
                cpu.getId(),
                cpu.getManufacturerId().getManufacturerName(),
                cpu.getModel()
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

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public Integer getCores() {
        return cores;
    }

    public void setCores(Integer cores) {
        this.cores = cores;
    }

    public Double getBaseClock() {
        return baseClock;
    }

    public void setBaseClock(Double baseClock) {
        this.baseClock = baseClock;
    }

    public Double getBoostClock() {
        return boostClock;
    }

    public void setBoostClock(Double boostClock) {
        this.boostClock = boostClock;
    }

    public Integer getTdp() {
        return tdp;
    }

    public void setTdp(Integer tdp) {
        this.tdp = tdp;
    }

    public String getIntegratedGraphic() {
        return integratedGraphic;
    }

    public void setIntegratedGraphic(String integratedGraphic) {
        this.integratedGraphic = integratedGraphic;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
