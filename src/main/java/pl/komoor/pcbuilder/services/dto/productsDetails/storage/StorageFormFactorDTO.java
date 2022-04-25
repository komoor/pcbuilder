package pl.komoor.pcbuilder.services.dto.productsDetails.storage;

import pl.komoor.pcbuilder.models.productDetails.storage.StorageFormFactor;

public class StorageFormFactorDTO {

    private Long id;
    private String name;


    public StorageFormFactorDTO() {
    }

    public StorageFormFactorDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static StorageFormFactorDTO build(StorageFormFactor storageFormFactor) {

        return new StorageFormFactorDTO(
                storageFormFactor.getId(),
                storageFormFactor.getFormFactorName()
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
