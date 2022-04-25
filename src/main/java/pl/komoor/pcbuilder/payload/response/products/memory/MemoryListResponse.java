package pl.komoor.pcbuilder.payload.response.products.memory;

import pl.komoor.pcbuilder.payload.response.basic.PageMeta;
import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.services.dto.products.CpuDTO;
import pl.komoor.pcbuilder.services.dto.products.MemoryDTO;

import java.util.List;

public class MemoryListResponse extends SuccessResponse {

    private PageMeta pageMeta;
    private List<MemoryDTO> memories;

    public MemoryListResponse(List<MemoryDTO> memories, PageMeta pageMeta) {
        this.pageMeta = pageMeta;
        this.memories = memories;
        addFullMessage("Pobrano listę pamięci");
    }

    public PageMeta getPageMeta() {
        return pageMeta;
    }

    public void setPageMeta(PageMeta pageMeta) {
        this.pageMeta = pageMeta;
    }

    public List<MemoryDTO> getMemories() {
        return memories;
    }

    public void setMemories(List<MemoryDTO> memories) {
        this.memories = memories;
    }
}
