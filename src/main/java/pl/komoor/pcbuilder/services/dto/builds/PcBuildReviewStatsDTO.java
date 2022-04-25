package pl.komoor.pcbuilder.services.dto.builds;

public class PcBuildReviewStatsDTO {

    private Double ratingAvarage;
    private Integer ratingCount;


    public PcBuildReviewStatsDTO() {
    }

    public PcBuildReviewStatsDTO(Double ratingAvarage, Integer ratingCount) {
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
