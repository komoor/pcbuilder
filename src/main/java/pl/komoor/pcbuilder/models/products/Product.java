package pl.komoor.pcbuilder.models.products;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.komoor.pcbuilder.models.productDetails.Category;
import pl.komoor.pcbuilder.models.tools.FileToDatabase;

import javax.persistence.*;

@Entity
@Table(	name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="category", referencedColumnName = "id")
    private Category category;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="imageId", nullable = true, referencedColumnName = "id", updatable = true)
    //referencedColumnName = "id"
    private FileToDatabase fileToDatabase;

    public Product() {

    }

    public Product(Long id, Category category, FileToDatabase fileToDatabase) {
        this.id = id;
        this.category = category;
        this.fileToDatabase = fileToDatabase;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public FileToDatabase getFileToDatabase() {
        return fileToDatabase;
    }

    public void setFileToDatabase(FileToDatabase fileToDatabase) {
        this.fileToDatabase = fileToDatabase;
    }
}
