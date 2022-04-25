package pl.komoor.pcbuilder.payload.request.builds;

public class PcBuildReviewRequest {

    private Long userId;

    private Long pcBuildId;

    private String review;

    private Integer rating;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPcBuildId() {
        return pcBuildId;
    }

    public void setPcBuildId(Long pcBuildId) {
        this.pcBuildId = pcBuildId;
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
}
