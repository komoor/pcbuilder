package pl.komoor.pcbuilder.payload.response.builds;

import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.services.dto.builds.PcBuildReviewStatsDTO;
import pl.komoor.pcbuilder.services.dto.productReview.ProductReviewStatsDTO;

public class PcBuildReviewStatsResponse extends SuccessResponse {

    private PcBuildReviewStatsDTO pcBuildReviewStats;


    public PcBuildReviewStatsResponse(PcBuildReviewStatsDTO pcBuildReviewStats) {
        addFullMessage("Pobrano statystyki");
        this.pcBuildReviewStats = pcBuildReviewStats;
    }

    public PcBuildReviewStatsDTO getPcBuildReviewStats() {
        return pcBuildReviewStats;
    }

    public void setPcBuildReviewStats(PcBuildReviewStatsDTO pcBuildReviewStats) {
        this.pcBuildReviewStats = pcBuildReviewStats;
    }
}
