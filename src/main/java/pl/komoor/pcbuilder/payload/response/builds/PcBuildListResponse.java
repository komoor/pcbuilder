package pl.komoor.pcbuilder.payload.response.builds;

import pl.komoor.pcbuilder.payload.response.basic.PageMeta;
import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.services.dto.builds.PcBuildDTO;

import java.util.List;

public class PcBuildListResponse extends SuccessResponse {

    private PageMeta pageMeta;
    private List<PcBuildDTO> pcBuilds;


    public PcBuildListResponse(List<PcBuildDTO> pcBuilds, PageMeta pageMeta) {
        addFullMessage("Pomyślnie pobrano zestawy");
        this.pageMeta = pageMeta;
        this.pcBuilds = pcBuilds;
    }

    public PageMeta getPageMeta() {
        return pageMeta;
    }

    public void setPageMeta(PageMeta pageMeta) {
        this.pageMeta = pageMeta;
    }

    public List<PcBuildDTO> getPcBuilds() {
        return pcBuilds;
    }

    public void setPcBuilds(List<PcBuildDTO> pcBuilds) {
        this.pcBuilds = pcBuilds;
    }
}
