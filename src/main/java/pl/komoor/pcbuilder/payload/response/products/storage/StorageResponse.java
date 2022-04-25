package pl.komoor.pcbuilder.payload.response.products.storage;

import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.services.dto.products.StorageDTO;

public class StorageResponse extends SuccessResponse {

    private StorageDTO storage;

    public StorageResponse(StorageDTO storage) {
        this.storage = storage;
        addFullMessage("Pobrano dysk.");
    }


    public StorageDTO getStorage() {
        return storage;
    }

    public void setStorage(StorageDTO storage) {
        this.storage = storage;
    }
}
