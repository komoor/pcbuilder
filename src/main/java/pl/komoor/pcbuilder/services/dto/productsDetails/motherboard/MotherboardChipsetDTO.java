package pl.komoor.pcbuilder.services.dto.productsDetails.motherboard;

import pl.komoor.pcbuilder.models.productDetails.motherboard.MotherboardChipset;

public class MotherboardChipsetDTO {

    private Long id;
    private String name;

    public MotherboardChipsetDTO() {
    }

    public MotherboardChipsetDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static MotherboardChipsetDTO build(MotherboardChipset motherboardChipset) {

        return new MotherboardChipsetDTO(
                motherboardChipset.getId(),
                motherboardChipset.getMotherboardChipsetName()
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
