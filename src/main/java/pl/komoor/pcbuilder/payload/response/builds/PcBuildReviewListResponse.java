package pl.komoor.pcbuilder.payload.response.builds;

import pl.komoor.pcbuilder.payload.response.basic.PageMeta;
import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.services.dto.builds.PcBuildReviewDTO;

import java.util.List;

public class PcBuildReviewListResponse extends SuccessResponse {

    private PageMeta pageMeta;
    private List<PcBuildReviewDTO> pcBuildReviews;


    public PcBuildReviewListResponse(List<PcBuildReviewDTO> pcBuildReviews, PageMeta pageMeta) {
        this.pageMeta = pageMeta;
        this.pcBuildReviews = pcBuildReviews;
        addFullMessage("Pobrano recenzje zestawów.");

    }

    public PageMeta getPageMeta() {
        return pageMeta;
    }

    public void setPageMeta(PageMeta pageMeta) {
        this.pageMeta = pageMeta;
    }

    public List<PcBuildReviewDTO> getPcBuildReviews() {
        return pcBuildReviews;
    }

    public void setPcBuildReview(List<PcBuildReviewDTO> pcBuildReviews) {
        this.pcBuildReviews = pcBuildReviews;
    }
}
