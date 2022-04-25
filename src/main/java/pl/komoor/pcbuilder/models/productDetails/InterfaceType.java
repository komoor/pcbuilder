package pl.komoor.pcbuilder.models.productDetails;

import javax.persistence.*;

@Entity
@Table(name = "interface_type")
public class InterfaceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String interfaceName;

    public InterfaceType() {
    }

    public InterfaceType(Long id, String interfaceName) {
        this.id = id;
        this.interfaceName = interfaceName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }
}
