package pl.komoor.pcbuilder.payload.response.products.gpu;

import pl.komoor.pcbuilder.payload.response.basic.PageMeta;
import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.services.dto.products.GpuDTO;

import java.util.List;

public class GpuListResponse extends SuccessResponse {

    private PageMeta pageMeta;
    private List<GpuDTO> gpus;

    public GpuListResponse( List<GpuDTO> gpus, PageMeta pageMeta) {
        this.pageMeta = pageMeta;
        this.gpus = gpus;
        addFullMessage("Pobrano listę GPU.");
    }

    public PageMeta getPageMeta() {
        return pageMeta;
    }

    public void setPageMeta(PageMeta pageMeta) {
        this.pageMeta = pageMeta;
    }

    public List<GpuDTO> getGpus() {
        return gpus;
    }

    public void setGpus(List<GpuDTO> gpus) {
        this.gpus = gpus;
    }
}
