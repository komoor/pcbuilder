package pl.komoor.pcbuilder.services.dto.productsDetails.cpuCooler;

import pl.komoor.pcbuilder.models.productDetails.cpuCooler.CpuCoolerWaterCooled;

public class CpuCoolerWaterCooledDTO {

    private Long id;
    private String name;

    public CpuCoolerWaterCooledDTO() {
    }

    public CpuCoolerWaterCooledDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static CpuCoolerWaterCooledDTO build(CpuCoolerWaterCooled cpuCoolerWaterCooled) {

        return new CpuCoolerWaterCooledDTO(
                cpuCoolerWaterCooled.getId(),
                cpuCoolerWaterCooled.getCpuCoolerWaterCooledName()
        );

    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
