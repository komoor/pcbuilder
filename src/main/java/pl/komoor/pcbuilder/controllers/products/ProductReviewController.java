package pl.komoor.pcbuilder.controllers.products;


import net.kaczmarzyk.spring.data.jpa.domain.GreaterThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.In;
import net.kaczmarzyk.spring.data.jpa.domain.LessThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.komoor.pcbuilder.models.builds.PcBuildReview;
import pl.komoor.pcbuilder.models.productDetails.Category;
import pl.komoor.pcbuilder.models.products.Cpu;
import pl.komoor.pcbuilder.models.products.ProductReview;
import pl.komoor.pcbuilder.models.users.User;
import pl.komoor.pcbuilder.payload.request.productReview.ProductReviewCheckRequest;
import pl.komoor.pcbuilder.payload.request.productReview.ProductReviewRequest;
import pl.komoor.pcbuilder.payload.response.basic.AppResponse;
import pl.komoor.pcbuilder.payload.response.basic.ErrorResponse;
import pl.komoor.pcbuilder.payload.response.basic.PageMeta;
import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.payload.response.products.ProductReview.ProductReviewCheckResponse;
import pl.komoor.pcbuilder.payload.response.products.ProductReview.ProductReviewListResponse;
import pl.komoor.pcbuilder.payload.response.products.ProductReview.ProductReviewStatsResponse;
import pl.komoor.pcbuilder.repository.products.ProductReviewRepository;
import pl.komoor.pcbuilder.services.dto.productReview.ProductReviewCheckDTO;
import pl.komoor.pcbuilder.services.dto.productReview.ProductReviewDTO;
import pl.komoor.pcbuilder.services.products.product.ProductReviewService;
import pl.komoor.pcbuilder.services.productsDetails.CategoryService;
import pl.komoor.pcbuilder.services.users.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/productReview")
public class ProductReviewController {

    @Autowired
    ProductReviewService productReviewService;


    @PreAuthorize("hasRole('USER') OR hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<AppResponse> addProductReview(@Valid @RequestBody ProductReviewRequest productReviewRequest) {


        productReviewService.saveProductReview(productReviewRequest);

        return ResponseEntity.ok(new SuccessResponse("Pomyślnie dodano recenzję"));
    }

    @PostMapping("/check")
    public ResponseEntity<AppResponse> checkIfProductExistsInUserPcBuilds(@Valid @RequestBody ProductReviewCheckRequest productReviewCheckRequest) {

        Boolean checkIfExists;
        Boolean checkIfProductExistsInUserPcBuilds;

        checkIfExists = productReviewService.existsProductReview(productReviewCheckRequest.getUserId(), productReviewCheckRequest.getProductId());
        checkIfProductExistsInUserPcBuilds = productReviewService.checkExistProductIdInUserPcBuilds(productReviewCheckRequest.getUserId(), productReviewCheckRequest.getProductId());

        ProductReviewCheckDTO productReviewCheckDTO = new ProductReviewCheckDTO(checkIfExists, checkIfProductExistsInUserPcBuilds);


        return new ResponseEntity<>(new ProductReviewCheckResponse(productReviewCheckDTO), HttpStatus.OK);
    }


    @GetMapping("/")
    public ResponseEntity<AppResponse> getAllProductReviews(
            @RequestParam(name = "productCategoryId", required = false) Long productCategoryId,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "page_size", defaultValue = "5") int pageSize,
            @RequestParam(value = "sort_by", defaultValue = "id") String sortBy,
            HttpServletRequest request) {

        Pageable pageable = getPageable(page, pageSize, sortBy);
        Page<ProductReview> productReviews = productReviewService.findAllProductReviews(productCategoryId, pageable);

        List<ProductReviewDTO> productReviewDTOList = productReviews.stream().map(ProductReviewDTO::build).collect(Collectors.toList());

        return new ResponseEntity<>(new ProductReviewListResponse(productReviewDTOList, PageMeta.build(productReviews, request.getRequestURI())), HttpStatus.OK);
    }

    @GetMapping("/productId/{productId}")
    public ResponseEntity<AppResponse> getAllProductReviewsByProductId(
            @PathVariable Long productId,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "page_size", defaultValue = "5") int pageSize,
            @RequestParam(value = "sort_by", defaultValue = "id") String sortBy,
            HttpServletRequest request) {

        Pageable pageable = getPageable(page, pageSize, sortBy);
        Page<ProductReview> productReviews = productReviewService.findAllProductReviewsByProductId(productId, pageable);

        List<ProductReviewDTO> productReviewDTOList = productReviews.stream().map(ProductReviewDTO::build).collect(Collectors.toList());

        return new ResponseEntity<>(new ProductReviewListResponse(productReviewDTOList, PageMeta.build(productReviews, request.getRequestURI())), HttpStatus.OK);
    }


    @GetMapping("/userId/{userId}")
    public ResponseEntity<AppResponse> getAllProductReviewsByUserId(
            @PathVariable Long userId,
            @RequestParam(name = "productCategoryId", required = false) Long productCategoryId,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "page_size", defaultValue = "5") int pageSize,
            @RequestParam(value = "sort_by", defaultValue = "id") String sortBy,
            HttpServletRequest request) {


        Pageable pageable = getPageable(page, pageSize, sortBy);

        if(!Objects.isNull(productCategoryId)) {


            Page<ProductReview> productReviews = productReviewService.findByUserIdAndProductIdCategory(userId, productCategoryId, pageable);

            List<ProductReviewDTO> productReviewDTOList = productReviews.stream().map(ProductReviewDTO::build).collect(Collectors.toList());

            return new ResponseEntity<>(new ProductReviewListResponse(productReviewDTOList, PageMeta.build(productReviews, request.getRequestURI())), HttpStatus.OK);

        } else {


            Page<ProductReview> productReviews = productReviewService.findAllProductReviewsByUserId(userId, pageable);

            List<ProductReviewDTO> productReviewDTOList = productReviews.stream().map(ProductReviewDTO::build).collect(Collectors.toList());

            return new ResponseEntity<>(new ProductReviewListResponse(productReviewDTOList, PageMeta.build(productReviews, request.getRequestURI())), HttpStatus.OK);
        }

    }

    @GetMapping("/productId/{productId}/stats")
    public ResponseEntity<AppResponse> getAllProductReviewsByProductIdStats(
            @PathVariable Long productId,
            HttpServletRequest request) {

        return new ResponseEntity<>(new ProductReviewStatsResponse(productReviewService.getProductReviewStatsByProductId(productId)), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER') OR hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<AppResponse> deleteProductReview(@PathVariable Long id) {

        Optional<ProductReview> productReview = productReviewService.findById(id);

        if (productReview.isPresent()) {

            productReviewService.deleteProductReview(productReview.get());
            return ResponseEntity.ok(new SuccessResponse("Pomyślnie usunięto recenzję produktu"));
        } else {
            return new ResponseEntity<>(new ErrorResponse("Nie znaleziono recenzji produktu do usunięcia."), HttpStatus.NOT_FOUND);
        }
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
