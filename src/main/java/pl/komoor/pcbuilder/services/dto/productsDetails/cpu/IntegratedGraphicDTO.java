package pl.komoor.pcbuilder.services.dto.productsDetails.cpu;

import pl.komoor.pcbuilder.models.productDetails.cpu.IntegratedGraphic;

public class IntegratedGraphicDTO {

    private Long id;
    private String name;


    public IntegratedGraphicDTO() {
    }

    public IntegratedGraphicDTO(Long id, String integratedGraphicName) {
        this.id = id;
        this.name = integratedGraphicName;
    }

    public static IntegratedGraphicDTO build(IntegratedGraphic integratedGraphic) {

        return new IntegratedGraphicDTO(
                integratedGraphic.getId(),
                integratedGraphic.getIntegratedGraphicName()
        );


    }

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
