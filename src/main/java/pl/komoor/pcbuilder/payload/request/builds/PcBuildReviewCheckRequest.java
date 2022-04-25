package pl.komoor.pcbuilder.payload.request.builds;

public class PcBuildReviewCheckRequest {

    private Long userId;
    private Long pcBuildId;

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
}
