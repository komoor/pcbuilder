package pl.komoor.pcbuilder.services.dto.builds;

public class PcBuildReviewCheckDTO {

    boolean exists;

    public PcBuildReviewCheckDTO(boolean exists) {
        this.exists = exists;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }
}
