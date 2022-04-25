package pl.komoor.pcbuilder.payload.response.products.cases;

import pl.komoor.pcbuilder.payload.response.basic.PageMeta;
import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.services.dto.products.CaseDTO;
import pl.komoor.pcbuilder.services.dto.products.CpuDTO;

import java.util.List;

public class CaseListResponse extends SuccessResponse {

    private PageMeta pageMeta;
    private List<CaseDTO> cases;


    public CaseListResponse( List<CaseDTO> cases, PageMeta pageMeta) {
        this.pageMeta = pageMeta;
        this.cases = cases;
        addFullMessage("Podbrano listę obudów");
    }

    public PageMeta getPageMeta() {
        return pageMeta;
    }

    public void setPageMeta(PageMeta pageMeta) {
        this.pageMeta = pageMeta;
    }

    public List<CaseDTO> getCases() {
        return cases;
    }

    public void setCases(List<CaseDTO> cases) {
        this.cases = cases;
    }
}
