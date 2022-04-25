package pl.komoor.pcbuilder.services.dto.productsDetails.psu;

import pl.komoor.pcbuilder.models.productDetails.psu.PsuEfficiencyRating;

public class PsuEfficiencyRatingDTO {

    private Long id;
    private String name;

    public PsuEfficiencyRatingDTO() {
    }

    public PsuEfficiencyRatingDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static PsuEfficiencyRatingDTO build(PsuEfficiencyRating psuEfficiencyRating) {

        return new PsuEfficiencyRatingDTO(
                psuEfficiencyRating.getId(),
                psuEfficiencyRating.getPsuEfficiencyRatingName()
        );

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
