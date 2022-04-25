package pl.komoor.pcbuilder.payload.response.products.ProductReview;

import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.services.dto.productReview.ProductReviewStatsDTO;

public class ProductReviewStatsResponse extends SuccessResponse {

    private ProductReviewStatsDTO productReviewStats;

    public ProductReviewStatsResponse(ProductReviewStatsDTO productReviewStats) {
        addFullMessage("Pobrano statystyki");
        this.productReviewStats = productReviewStats;
    }

    public ProductReviewStatsDTO getProductReviewStats() {
        return productReviewStats;
    }

    public void setProductReviewStats(ProductReviewStatsDTO productReviewStats) {
        this.productReviewStats = productReviewStats;
    }
}
