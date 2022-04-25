package pl.komoor.pcbuilder.models.productDetails.storage;

import javax.persistence.*;

@Entity
@Table(name = "storage_form_factor")
public class StorageFormFactor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String formFactorName;

    public StorageFormFactor() {
    }

    public StorageFormFactor(Long id, String formFactorName) {
        this.id = id;
        this.formFactorName = formFactorName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFormFactorName() {
        return formFactorName;
    }

    public void setFormFactorName(String formFactorName) {
        this.formFactorName = formFactorName;
    }
}
