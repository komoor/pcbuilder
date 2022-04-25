package pl.komoor.pcbuilder.payload.response.builds;

import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.services.dto.builds.PcBuildReviewCheckDTO;
import pl.komoor.pcbuilder.services.dto.productReview.ProductReviewCheckDTO;

public class PcBuildReviewCheckResponse extends SuccessResponse {

    PcBuildReviewCheckDTO pcBuildReviewCheck;

    public PcBuildReviewCheckResponse(PcBuildReviewCheckDTO pcBuildReviewCheck) {
        addFullMessage("Pobrano walidację.");
        this.pcBuildReviewCheck = pcBuildReviewCheck;
    }

    public PcBuildReviewCheckDTO getPcBuildReviewCheck() {
        return pcBuildReviewCheck;
    }

    public void setPcBuildReviewCheck(PcBuildReviewCheckDTO pcBuildReviewCheck) {
        this.pcBuildReviewCheck = pcBuildReviewCheck;
    }
}
