package pl.komoor.pcbuilder.models.productDetails.cpu;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cpu_socket")
public class CpuSocket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Size(max = 20)
    private String socketName;



    public CpuSocket() {
    }

    public CpuSocket(Long id, @Size(max = 20) String socketName) {
        this.id = id;
        this.socketName = socketName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSocketName() {
        return socketName;
    }

    public void setSocketName(String socketName) {
        this.socketName = socketName;
    }
}
