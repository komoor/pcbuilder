package pl.komoor.pcbuilder.services.dto.products;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import pl.komoor.pcbuilder.models.products.Cpu;
import pl.komoor.pcbuilder.models.products.Motherboard;
import pl.komoor.pcbuilder.models.products.Product;
import pl.komoor.pcbuilder.repository.products.CpuRepository;
import pl.komoor.pcbuilder.repository.products.MotherboardRepository;
import pl.komoor.pcbuilder.services.products.cpu.CpuService;
import pl.komoor.pcbuilder.services.products.motherboard.MotherboardService;

import java.util.Optional;

public class ProductDTO {

    private Long productId;
    private Long productCategoryId;


    public ProductDTO() {
    }

    public ProductDTO(Long productId, Long productCategoryId) {
        this.productId = productId;
        this.productCategoryId = productCategoryId;

    }

    public static ProductDTO build(Product product) {

        return new ProductDTO(
                product.getId(),
                product.getCategory().getId()
        );

    }


    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }


}
