package pl.komoor.pcbuilder.services.dto.productsDetails.motherboard;

import pl.komoor.pcbuilder.models.productDetails.motherboard.MotherboardEthernet;

public class MotherboardEthernetDTO {

    private Long id;
    private String name;

    public MotherboardEthernetDTO() {
    }

    public MotherboardEthernetDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static MotherboardEthernetDTO build(MotherboardEthernet motherboardEthernet) {

        return new MotherboardEthernetDTO(
                motherboardEthernet.getId(),
                motherboardEthernet.getMotherboardEthernetName()
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
