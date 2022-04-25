package pl.komoor.pcbuilder.models.productDetails.memory;

import pl.komoor.pcbuilder.models.enums.EMemorySpeed;

import javax.persistence.*;

@Entity
@Table(name = "memory_type")
public class MemoryType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String typeName;

    public MemoryType() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}