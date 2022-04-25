package pl.komoor.pcbuilder.models.productDetails.psu;

import pl.komoor.pcbuilder.models.products.Psu;

import javax.persistence.*;

@Entity
@Table(	name = "psu_efficiency_rating")
public class PsuEfficiencyRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String psuEfficiencyRatingName;

    public PsuEfficiencyRating() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPsuEfficiencyRatingName() {
        return psuEfficiencyRatingName;
    }

    public void setPsuEfficiencyRatingName(String psuEfficiencyRatingName) {
        this.psuEfficiencyRatingName = psuEfficiencyRatingName;
    }

}
