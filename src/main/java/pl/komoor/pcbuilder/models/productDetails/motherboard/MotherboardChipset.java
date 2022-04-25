package pl.komoor.pcbuilder.models.productDetails.motherboard;

import javax.persistence.*;

@Entity
@Table(name = "motherboard_chipset")
public class MotherboardChipset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String motherboardChipsetName;

    public MotherboardChipset() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMotherboardChipsetName() {
        return motherboardChipsetName;
    }

    public void setMotherboardChipsetName(String motherboardChipsetName) {
        this.motherboardChipsetName = motherboardChipsetName;
    }

}
