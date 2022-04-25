package pl.komoor.pcbuilder.models.productDetails.psu;

import javax.persistence.*;

@Entity
@Table(	name = "psu_type")
public class PsuType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String psuTypeName;

    public PsuType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPsuTypeName() {
        return psuTypeName;
    }

    public void setPsuTypeName(String psuTypeName) {
        this.psuTypeName = psuTypeName;
    }
}
