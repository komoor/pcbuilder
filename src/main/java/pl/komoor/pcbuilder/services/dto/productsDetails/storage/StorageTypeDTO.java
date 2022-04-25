package pl.komoor.pcbuilder.services.dto.productsDetails.storage;

import pl.komoor.pcbuilder.models.productDetails.storage.StorageType;

public class StorageTypeDTO {

    private Long id;
    private String name;

    public StorageTypeDTO() {
    }

    public StorageTypeDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static StorageTypeDTO build(StorageType storageType) {

        return new StorageTypeDTO(
                storageType.getId(),
                storageType.getTypeName()
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
