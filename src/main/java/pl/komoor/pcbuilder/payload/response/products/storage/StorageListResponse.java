package pl.komoor.pcbuilder.payload.response.products.storage;

import pl.komoor.pcbuilder.payload.response.basic.PageMeta;
import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.services.dto.products.MemoryDTO;
import pl.komoor.pcbuilder.services.dto.products.StorageDTO;

import java.util.List;

public class StorageListResponse extends SuccessResponse {

    private PageMeta pageMeta;
    private List<StorageDTO> storages;

    public StorageListResponse(List<StorageDTO> storages, PageMeta pageMeta) {
        this.pageMeta = pageMeta;
        this.storages = storages;
        addFullMessage("Pobrano dyski");
    }

    public PageMeta getPageMeta() {
        return pageMeta;
    }

    public void setPageMeta(PageMeta pageMeta) {
        this.pageMeta = pageMeta;
    }

    public List<StorageDTO> getStorages() {
        return storages;
    }

    public void setStorages(List<StorageDTO> storages) {
        this.storages = storages;
    }
}
