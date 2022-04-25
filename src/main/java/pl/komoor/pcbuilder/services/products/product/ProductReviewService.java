package pl.komoor.pcbuilder.services.products.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import pl.komoor.pcbuilder.models.products.Cpu;
import pl.komoor.pcbuilder.models.products.ProductReview;
import pl.komoor.pcbuilder.payload.request.products.CpuRequest;
import pl.komoor.pcbuilder.payload.request.productReview.ProductReviewRequest;
import pl.komoor.pcbuilder.services.dto.productReview.ProductReviewStatsDTO;

import java.util.Optional;

public interface ProductReviewService {

    public void saveProductReview(ProductReviewRequest productReviewRequest);

    public void updateProductReview(ProductReviewRequest productReviewRequest, Long id);

    public void deleteProductReview(ProductReview productReview);

    public Page<ProductReview> findAllProductReviews(Long categoryId, Pageable pageable);

    public Page<ProductReview> findAllProductReviewsByProductId(Long productId, Pageable pageable);

    public Page<ProductReview> findAllProductReviewsByUserId(Long id, Pageable pageable);

    public Page<ProductReview> findByUserIdAndProductIdCategory(Long userId, Long categoryId, Pageable pageable);



    public boolean existsProductReview(Long userId, Long productId);

    public boolean checkExistProductIdInUserPcBuilds(Long userId, Long productId);

    public ProductReviewStatsDTO getProductReviewStatsByProductId(Long productId);

    Optional<ProductReview> findById(Long id);



}
