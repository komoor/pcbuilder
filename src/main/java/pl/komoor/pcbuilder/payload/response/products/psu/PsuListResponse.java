package pl.komoor.pcbuilder.payload.response.products.psu;

import pl.komoor.pcbuilder.payload.response.basic.PageMeta;
import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.services.dto.products.MemoryDTO;
import pl.komoor.pcbuilder.services.dto.products.PsuDTO;

import java.util.List;

public class PsuListResponse extends SuccessResponse {


    private PageMeta pageMeta;
    private List<PsuDTO> psus;

    public PsuListResponse(List<PsuDTO> psus, PageMeta pageMeta) {
        this.pageMeta = pageMeta;
        this.psus = psus;
        addFullMessage("Pobrano PSU");
    }


    public PageMeta getPageMeta() {
        return pageMeta;
    }

    public void setPageMeta(PageMeta pageMeta) {
        this.pageMeta = pageMeta;
    }

    public List<PsuDTO> getPsus() {
        return psus;
    }

    public void setPsus(List<PsuDTO> psus) {
        this.psus = psus;
    }
}
