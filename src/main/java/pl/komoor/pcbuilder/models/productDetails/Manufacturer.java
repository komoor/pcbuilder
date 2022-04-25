package pl.komoor.pcbuilder.models.productDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(	name = "manufacturer")
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String manufacturerName;

    @JsonIgnoreProperties("manufacturers")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "manufacturer_category", joinColumns = @JoinColumn(name = "manufacturer_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> manufacturerCategory;

    public Manufacturer() {

    }

    public Manufacturer(@NotBlank @Size(max = 20) String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public Set<Category> getManufacturerCategory() {
        return manufacturerCategory;
    }

    public void setManufacturerCategory(Set<Category> manufacturerCategory) {
        this.manufacturerCategory = manufacturerCategory;
    }
}
