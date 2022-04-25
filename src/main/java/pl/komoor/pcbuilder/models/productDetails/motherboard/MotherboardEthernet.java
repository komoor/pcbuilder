package pl.komoor.pcbuilder.models.productDetails.motherboard;

import javax.persistence.*;

@Entity
@Table(name = "motherboard_ethernet")
public class MotherboardEthernet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String motherboardEthernetName;

    public MotherboardEthernet() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMotherboardEthernetName() {
        return motherboardEthernetName;
    }

    public void setMotherboardEthernetName(String motherboardEthernetName) {
        this.motherboardEthernetName = motherboardEthernetName;
    }
}
