package pl.komoor.pcbuilder.controllers.products;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.komoor.pcbuilder.models.products.Product;
import pl.komoor.pcbuilder.models.users.User;
import pl.komoor.pcbuilder.services.dto.products.ProductDTO;
import pl.komoor.pcbuilder.services.products.product.ProductService;
import pl.komoor.pcbuilder.services.users.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @GetMapping("/")
    public List<Product> getAllProducts(@RequestParam(value = "page", defaultValue = "1") int page,
                                             @RequestParam(value = "page_size", defaultValue = "5") int pageSize,
                                             @RequestParam(value = "sort_by", defaultValue = "id") String sortBy,
                                             HttpServletRequest request) {

        List<Product> products = this.productService.findAll();

        return products;



    }


    @GetMapping("/user/{id}")
    public List<ProductDTO> getAllProductsUsedInPcBuildsByUser(
            @PathVariable Long id,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "page_size", defaultValue = "20") int pageSize,
            @RequestParam(value = "sort_by", defaultValue = "id") String sortBy,
            HttpServletRequest request) {

        Pageable pageable = getPageable(page, pageSize, sortBy);
        User user = userService.findById(id).get();


        Set<Product> products = productService.getAllProductUsedInBuildsByUserId(user);

        List<ProductDTO> productDTOS = products.stream().map(ProductDTO::build).collect(Collectors.toList());

        return productDTOS;
    }

    private Pageable getPageable(int page, int pageSize, String sortBy) {
        Sort.Direction direction;

        if (page <= 0)
            page = 1;

        if (pageSize <= 0)
            pageSize = 5;

        if(sortBy.substring(0,1).equals("-")) {
            direction = Sort.Direction.DESC;
            sortBy = sortBy.substring(1);
        } else {
            direction = Sort.Direction.ASC;
        }

        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, direction, sortBy);
        return pageRequest;
    }

}
