package pl.komoor.pcbuilder.payload.response.products.ProductReview;

import org.springframework.http.HttpStatus;
import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.services.dto.productReview.ProductReviewCheckDTO;

public class ProductReviewCheckResponse extends SuccessResponse {

    ProductReviewCheckDTO productReviewCheck;

    public ProductReviewCheckResponse(ProductReviewCheckDTO productReviewCheck) {
        addFullMessage("Pobrano walidację.");
        this.productReviewCheck = productReviewCheck;
    }

    public ProductReviewCheckDTO getProductReviewCheck() {
        return productReviewCheck;
    }

    public void setProductReviewCheck(ProductReviewCheckDTO productReviewCheck) {
        this.productReviewCheck = productReviewCheck;
    }
}
