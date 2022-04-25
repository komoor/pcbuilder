package pl.komoor.pcbuilder.payload.response.builds;

import pl.komoor.pcbuilder.payload.response.basic.PageMeta;
import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.services.dto.builds.PcPartsListDTO;

import java.util.List;

public class PcPartsListListResponse extends SuccessResponse {

    private PageMeta pageMeta;
    private List<PcPartsListDTO> pcPartsLists;


    public PcPartsListListResponse(List<PcPartsListDTO> pcPartsLists, PageMeta pageMeta) {
        this.pageMeta = pageMeta;
        this.pcPartsLists = pcPartsLists;
        addFullMessage("Pobrano listę.");
    }

    public PageMeta getPageMeta() {
        return pageMeta;
    }

    public void setPageMeta(PageMeta pageMeta) {
        this.pageMeta = pageMeta;
    }

    public List<PcPartsListDTO> getPcPartsLists() {
        return pcPartsLists;
    }

    public void setPcPartsLists(List<PcPartsListDTO> pcPartsLists) {
        this.pcPartsLists = pcPartsLists;
    }
}
