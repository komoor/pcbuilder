package pl.komoor.pcbuilder.services.dto.productReview;

public class ProductReviewCheckDTO {

    boolean exists;
    boolean usedInBuild;


    public ProductReviewCheckDTO() {
    }

    public ProductReviewCheckDTO(boolean exists, boolean usedInBuild) {
        this.exists = exists;
        this.usedInBuild = usedInBuild;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public boolean isUsedInBuild() {
        return usedInBuild;
    }

    public void setUsedInBuild(boolean usedInBuild) {
        this.usedInBuild = usedInBuild;
    }
}
