package pl.komoor.pcbuilder.services.dto.productReview;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.komoor.pcbuilder.models.productDetails.cpu.CpuSocket;
import pl.komoor.pcbuilder.models.products.Product;
import pl.komoor.pcbuilder.models.products.ProductReview;
import pl.komoor.pcbuilder.services.dto.products.ProductDTO;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductReviewDTO {

    private Long id;
    private String username;
    private String userAvatarUrl;
    private String review;
    private Integer rating;
    private Date createDate;
    private ProductDTO product;

    public ProductReviewDTO() {
    }

    public ProductReviewDTO(Long id, String username, String userAvatarUrl, String review, Integer rating, Date createDate, ProductDTO product) {
        this.id = id;
        this.username = username;
        this.userAvatarUrl = userAvatarUrl;
        this.review = review;
        this.rating = rating;
        this.createDate = createDate;
        this.product = product;
    }

    public static ProductReviewDTO build(ProductReview productReview) {
        String imageUrl = null;

        if(productReview.getUserId().getFileToDatabase() != null)
            imageUrl = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/images/")
                    .path(productReview.getUserId().getFileToDatabase().getId())
                    .toUriString();

        ProductDTO product = Optional.of(productReview.getProductId()).map(ProductDTO::build).get();

        return new ProductReviewDTO(
                productReview.getId(),
                productReview.getUserId().getUsername(),
                imageUrl,
                productReview.getReview(),
                productReview.getRating(),
                productReview.getCreateDate(),
                product
        );


    }

    public String getUserAvatarUrl() {
        return userAvatarUrl;
    }

    public void setUserAvatarUrl(String userAvatarUrl) {
        this.userAvatarUrl = userAvatarUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }
}
