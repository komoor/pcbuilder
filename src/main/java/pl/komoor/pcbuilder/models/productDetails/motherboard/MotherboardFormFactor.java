package pl.komoor.pcbuilder.models.productDetails.motherboard;

import javax.persistence.*;

@Entity
@Table(	name = "motherboard_form_factor")
public class MotherboardFormFactor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String motherboardFormFactorName;

    public MotherboardFormFactor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMotherboardFormFactorName() {
        return motherboardFormFactorName;
    }

    public void setMotherboardFormFactorName(String motherboardFormFactorName) {
        this.motherboardFormFactorName = motherboardFormFactorName;
    }
}
