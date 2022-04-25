package pl.komoor.pcbuilder.repository.products;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.komoor.pcbuilder.models.productDetails.Category;
import pl.komoor.pcbuilder.models.products.Product;
import pl.komoor.pcbuilder.models.products.ProductReview;
import pl.komoor.pcbuilder.models.users.User;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Long>, JpaSpecificationExecutor<ProductReview> {

    Page<ProductReview> findAll(Specification spec, Pageable pageRequest);

    Page<ProductReview> findByProductId(Product productId, Pageable pageRequest);

    Page<ProductReview> findByUserId(User user, Pageable pageRequest);

    Page<ProductReview> findByProductIdCategory(Category category, Pageable pageable);

    Page<ProductReview> findByUserIdAndProductIdCategory(User user, Category category, Pageable pageable);

    Boolean existsByUserIdAndProductId(User user, Product product);

    @Query("SELECT COALESCE(ROUND(AVG(pr.rating),2),0) FROM ProductReview pr WHERE pr.productId = :productId")
    Double getRatingAvarage(@Param("productId") Product productId);

    @Query("SELECT COALESCE(COUNT(pr.rating),0) FROM ProductReview pr WHERE pr.productId = :productId")
    Integer getRatingCount(@Param("productId") Product productId);

}
