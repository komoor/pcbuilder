package pl.komoor.pcbuilder.services.dto.productReview;

import net.kaczmarzyk.spring.data.jpa.domain.In;

public class ProductReviewStatsDTO {

    private Double ratingAvarage;
    private Integer ratingCount;

    public ProductReviewStatsDTO() {
    }

    public ProductReviewStatsDTO(Double ratingAvarage, Integer ratingCount) {
        this.ratingAvarage = ratingAvarage;
        this.ratingCount = ratingCount;
    }

    public Double getRatingAvarage() {
        return ratingAvarage;
    }

    public void setRatingAvarage(Double ratingAvarage) {
        this.ratingAvarage = ratingAvarage;
    }

    public Integer getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
    }
}
