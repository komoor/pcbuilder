package pl.komoor.pcbuilder.models.productDetails.memory;

import javax.persistence.*;

@Entity
@Table(name = "memory_timming")
public class MemoryTimming {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String timmingName;

    public MemoryTimming() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTimmingName() {
        return timmingName;
    }

    public void setTimmingName(String timmingName) {
        this.timmingName = timmingName;
    }
}
