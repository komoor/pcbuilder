package pl.komoor.pcbuilder.payload.response.products.motherboard;

import pl.komoor.pcbuilder.payload.response.basic.PageMeta;
import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.services.dto.products.MemoryDTO;
import pl.komoor.pcbuilder.services.dto.products.MotherboardDTO;

import java.util.List;

public class MotherboardListResponse extends SuccessResponse {

    private PageMeta pageMeta;
    private List<MotherboardDTO> motherboards;


    public MotherboardListResponse(List<MotherboardDTO> motherboards, PageMeta pageMeta) {
        this.pageMeta = pageMeta;
        this.motherboards = motherboards;
        addFullMessage("Pobrano listę płyt głównych");
    }

    public PageMeta getPageMeta() {
        return pageMeta;
    }

    public void setPageMeta(PageMeta pageMeta) {
        this.pageMeta = pageMeta;
    }

    public List<MotherboardDTO> getMotherboards() {
        return motherboards;
    }

    public void setMotherboards(List<MotherboardDTO> motherboards) {
        this.motherboards = motherboards;
    }
}
