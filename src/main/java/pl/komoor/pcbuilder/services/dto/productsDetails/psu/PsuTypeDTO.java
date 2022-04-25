package pl.komoor.pcbuilder.services.dto.productsDetails.psu;

import pl.komoor.pcbuilder.models.productDetails.psu.PsuType;

public class PsuTypeDTO {

    private Long id;
    private String name;

    public PsuTypeDTO() {
    }

    public PsuTypeDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static PsuTypeDTO build(PsuType psuType) {
        return new PsuTypeDTO(
                psuType.getId(),
                psuType.getPsuTypeName()
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
