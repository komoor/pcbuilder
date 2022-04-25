package pl.komoor.pcbuilder.services.dto.productsDetails.motherboard;

import pl.komoor.pcbuilder.models.productDetails.motherboard.MotherboardFormFactor;

public class MotherboardFormFactorDTO {

    private Long id;
    private String name;

    public MotherboardFormFactorDTO() {
    }

    public MotherboardFormFactorDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static MotherboardFormFactorDTO build(MotherboardFormFactor motherboardFormFactor) {

        return new MotherboardFormFactorDTO(
                motherboardFormFactor.getId(),
                motherboardFormFactor.getMotherboardFormFactorName()
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
