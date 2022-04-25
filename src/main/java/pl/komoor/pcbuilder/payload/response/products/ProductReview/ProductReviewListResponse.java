package pl.komoor.pcbuilder.payload.response.products.ProductReview;

import pl.komoor.pcbuilder.payload.response.basic.PageMeta;
import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.services.dto.productReview.ProductReviewDTO;

import java.util.List;

public class ProductReviewListResponse extends SuccessResponse {

    private PageMeta pageMeta;
    private List<ProductReviewDTO> productReviews;


    public ProductReviewListResponse(List<ProductReviewDTO> productReviews, PageMeta pageMeta) {
        addFullMessage("Pobrano recenzje.");
        this.pageMeta = pageMeta;
        this.productReviews = productReviews;
    }


    public PageMeta getPageMeta() {
        return pageMeta;
    }

    public void setPageMeta(PageMeta pageMeta) {
        this.pageMeta = pageMeta;
    }

    public List<ProductReviewDTO> getProductReviews() {
        return productReviews;
    }

    public void setProductReviews(List<ProductReviewDTO> productReviews) {
        this.productReviews = productReviews;
    }
}
