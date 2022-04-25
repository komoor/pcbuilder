package pl.komoor.pcbuilder.services.dto.productsDetails.memory;

import pl.komoor.pcbuilder.models.productDetails.memory.MemoryType;

public class MemoryTypeDTO {

    private Long id;
    private String name;

    public MemoryTypeDTO() {
    }

    public MemoryTypeDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static MemoryTypeDTO build(MemoryType memoryType) {

        return new MemoryTypeDTO(
                memoryType.getId(),
                memoryType.getTypeName()
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
