package pl.komoor.pcbuilder.services.dto.productsDetails.memory;

import pl.komoor.pcbuilder.models.productDetails.memory.MemoryTimming;

public class MemoryTimmingDTO {

    private Long id;
    private String name;


    public MemoryTimmingDTO() {
    }

    public MemoryTimmingDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static MemoryTimmingDTO build(MemoryTimming memoryTimming) {

        return new MemoryTimmingDTO(
                memoryTimming.getId(),
                memoryTimming.getTimmingName()
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
