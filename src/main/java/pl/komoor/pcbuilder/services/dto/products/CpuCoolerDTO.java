package pl.komoor.pcbuilder.services.dto.products;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.komoor.pcbuilder.models.productDetails.cpu.CpuSocket;
import pl.komoor.pcbuilder.models.products.CpuCooler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CpuCoolerDTO {

    private Long id;
    private Long productId;
    private String manufacturer;
    private String model;
    private List<String> sockets;
    private String waterCooled;
    private Integer height;
    private String fanless;
    private String imageUrl;

    public CpuCoolerDTO() {
    }

    public CpuCoolerDTO(Long id, String manufacturer, String model) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
    }

    public CpuCoolerDTO(Long id, Long productId, String manufacturer, String model, List<String> sockets, String waterCooled, Integer height, String fanless, String imageUrl) {
        this.id = id;
        this.productId = productId;
        this.manufacturer = manufacturer;
        this.model = model;
        this.sockets = sockets;
        this.waterCooled = waterCooled;
        this.height = height;
        this.fanless = fanless;
        this.imageUrl = imageUrl;
    }

    public static CpuCoolerDTO build(CpuCooler cpuCooler) {

        String imageUrl = null;
        List<String> cpuSockets = cpuCooler.getSocketId().stream().map(CpuSocket::getSocketName).collect(Collectors.toList());

        if(cpuCooler.getProductId().getFileToDatabase() != null)
            imageUrl = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/images/")
                    .path(cpuCooler.getProductId().getFileToDatabase().getId())
                    .toUriString();


        return new CpuCoolerDTO(
                cpuCooler.getId(),
                cpuCooler.getProductId().getId(),
                cpuCooler.getManufacturerId().getManufacturerName(),
                cpuCooler.getModel(),
                cpuSockets,
                cpuCooler.getWaterCooledId().getCpuCoolerWaterCooledName(),
                cpuCooler.getHeight(),
                cpuCooler.getFanless(),
                imageUrl
        );

    };

    public static CpuCoolerDTO buildShort(CpuCooler cpuCooler) {

        return new CpuCoolerDTO(
                cpuCooler.getId(),
                cpuCooler.getManufacturerId().getManufacturerName(),
                cpuCooler.getModel()
        );

    };

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

    public List<String> getSockets() {
        return sockets;
    }

    public void setSockets(List<String> sockets) {
        this.sockets = sockets;
    }

    public String getWaterCooled() {
        return waterCooled;
    }

    public void setWaterCooled(String waterCooled) {
        this.waterCooled = waterCooled;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getFanless() {
        return fanless;
    }

    public void setFanless(String fanless) {
        this.fanless = fanless;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
