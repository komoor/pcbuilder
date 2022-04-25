package pl.komoor.pcbuilder.payload.response.products.cpu;

import pl.komoor.pcbuilder.payload.response.basic.PageMeta;
import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.services.dto.products.CpuDTO;

import java.util.List;


public class CpuListResponse extends SuccessResponse {

        private PageMeta pageMeta;
        private List<CpuDTO> cpus;


    public CpuListResponse(List<CpuDTO> cpus, PageMeta pageMeta) {
        this.pageMeta = pageMeta;
        this.cpus = cpus;
        addFullMessage("Pobrano CPUs");
    }

    public PageMeta getPageMeta() {
        return pageMeta;
    }

    public List<CpuDTO> getCpus() {
        return cpus;
    }

}

