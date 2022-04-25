package pl.komoor.pcbuilder.services.dto.productsDetails;

import pl.komoor.pcbuilder.models.productDetails.InterfaceType;

public class InterfaceDTO {

    private Long id;
    private String name;

    public InterfaceDTO() {
    }

    public InterfaceDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static InterfaceDTO build(InterfaceType interfaceType) {

        return new InterfaceDTO(
                interfaceType.getId(),
                interfaceType.getInterfaceName()
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
