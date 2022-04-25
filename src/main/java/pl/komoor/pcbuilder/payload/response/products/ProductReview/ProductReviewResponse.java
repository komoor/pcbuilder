package pl.komoor.pcbuilder.payload.response.products.ProductReview;

import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.services.dto.productReview.ProductReviewDTO;

public class ProductReviewResponse extends SuccessResponse {

    private ProductReviewDTO productReview;


    public ProductReviewResponse(ProductReviewDTO productReview) {
        addFullMessage("Pobrano komentarz");
        this.productReview = productReview;
    }

    public ProductReviewDTO getProductReview() {
        return productReview;
    }

    public void setProductReview(ProductReviewDTO productReview) {
        this.productReview = productReview;
    }
}
