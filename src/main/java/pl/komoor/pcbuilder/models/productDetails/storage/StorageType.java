package pl.komoor.pcbuilder.models.productDetails.storage;

import javax.persistence.*;

@Entity
@Table(name = "storage_type")
public class StorageType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String typeName;

    public StorageType() {
    }

    public StorageType(Long id, String typeName) {
        this.id = id;
        this.typeName = typeName;
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
