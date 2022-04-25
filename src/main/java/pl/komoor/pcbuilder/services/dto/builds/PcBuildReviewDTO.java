package pl.komoor.pcbuilder.services.dto.builds;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.komoor.pcbuilder.models.builds.PcBuildReview;
import pl.komoor.pcbuilder.services.dto.products.GpuDTO;

import java.util.Date;
import java.util.Optional;

public class PcBuildReviewDTO {

    private Long id;
    private String username;
    private String userAvatarUrl;
    private String review;
    private Integer rating;
    private PcBuildDTO pcBuild;
    private Date createDate;

    public PcBuildReviewDTO() {
    }

    public PcBuildReviewDTO(Long id, String username, String userAvatarUrl, String review, Integer rating, Date createDate,  PcBuildDTO pcBuild) {
        this.id = id;
        this.username = username;
        this.userAvatarUrl = userAvatarUrl;
        this.review = review;
        this.rating = rating;
        this.createDate = createDate;
        this.pcBuild = pcBuild;
    }


    public static PcBuildReviewDTO build(PcBuildReview pcBuildReview) {

        String imageUrl = null;

        PcBuildDTO pcBuild = Optional.ofNullable(pcBuildReview.getPcBuildId()).map(PcBuildDTO::build).get();

        if(pcBuildReview.getUserId().getFileToDatabase() != null)
            imageUrl = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/images/")
                    .path(pcBuildReview.getUserId().getFileToDatabase().getId())
                    .toUriString();

        return new PcBuildReviewDTO(
                pcBuildReview.getId(),
                pcBuildReview.getUserId().getUsername(),
                imageUrl,
                pcBuildReview.getReview(),
                pcBuildReview.getRating(),
                pcBuildReview.getCreateDate(),
                pcBuild
        );



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

    public String getUserAvatarUrl() {
        return userAvatarUrl;
    }

    public void setUserAvatarUrl(String userAvatarUrl) {
        this.userAvatarUrl = userAvatarUrl;
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

    public PcBuildDTO getPcBuild() {
        return pcBuild;
    }

    public void setPcBuild(PcBuildDTO pcBuild) {
        this.pcBuild = pcBuild;
    }
}
