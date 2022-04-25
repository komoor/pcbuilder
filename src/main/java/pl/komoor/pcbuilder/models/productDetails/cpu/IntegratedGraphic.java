package pl.komoor.pcbuilder.models.productDetails.cpu;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cpu_integrated_graphic")
public class IntegratedGraphic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Size(max = 50)
    private String integratedGraphicName;

    public IntegratedGraphic() {
    }

    public IntegratedGraphic(Long id, String integratedGraphicName) {
        this.id = id;
        this.integratedGraphicName = integratedGraphicName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIntegratedGraphicName() {
        return integratedGraphicName;
    }

    public void setIntegratedGraphicName(String integratedGraphicName) {
        this.integratedGraphicName = integratedGraphicName;
    }
}
