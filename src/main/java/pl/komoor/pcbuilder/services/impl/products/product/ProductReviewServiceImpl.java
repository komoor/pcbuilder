package pl.komoor.pcbuilder.services.impl.products.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import pl.komoor.pcbuilder.models.builds.PcBuild;
import pl.komoor.pcbuilder.models.productDetails.Category;
import pl.komoor.pcbuilder.models.products.*;
import pl.komoor.pcbuilder.models.users.User;
import pl.komoor.pcbuilder.payload.request.products.CpuRequest;
import pl.komoor.pcbuilder.payload.request.productReview.ProductReviewRequest;
import pl.komoor.pcbuilder.repository.products.ProductReviewRepository;
import pl.komoor.pcbuilder.services.builds.PcBuildService;
import pl.komoor.pcbuilder.services.dto.productReview.ProductReviewStatsDTO;
import pl.komoor.pcbuilder.services.products.product.ProductReviewService;
import pl.komoor.pcbuilder.services.products.product.ProductService;
import pl.komoor.pcbuilder.services.productsDetails.CategoryService;
import pl.komoor.pcbuilder.services.users.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductReviewServiceImpl implements ProductReviewService {

    @Autowired
    ProductReviewRepository productReviewRepository;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    PcBuildService pcBuildService;

    @Autowired
    CategoryService categoryService;


    @Override
    public void saveProductReview(ProductReviewRequest productReviewRequest) {

        Optional<User> user = userService.findById(productReviewRequest.getUserId());
        Optional<Product> product = productService.findById(productReviewRequest.getProductId());

        ProductReview productReview = new ProductReview();
        productReview.setProductId(product.get());
        productReview.setUserId(user.get());
        productReview.setReview(productReviewRequest.getReview());
        productReview.setRating(productReviewRequest.getRating());

        productReviewRepository.save(productReview);
    }

    @Override
    public void updateProductReview(ProductReviewRequest productReviewRequest, Long id) {

    }

    @Override
    public void deleteProductReview(ProductReview productReview) {
        productReviewRepository.delete(productReview);
    }


    @Override
    public Page<ProductReview> findAllProductReviews(Long categoryId, Pageable pageable) {

        Optional<Category> category = categoryService.findById(categoryId);

        return productReviewRepository.findByProductIdCategory(category.get(), pageable);
    }

    @Override
    public Page<ProductReview> findAllProductReviewsByProductId(Long productId, Pageable pageable) {

        Optional<Product> product = productService.findById(productId);

        return productReviewRepository.findByProductId(product.get(), pageable);
    }

    @Override
    public Page<ProductReview> findAllProductReviewsByUserId(Long id, Pageable pageable) {

        Optional<User> user = userService.findById(id);

        return productReviewRepository.findByUserId(user.get(), pageable);

    }

    @Override
    public Page<ProductReview> findByUserIdAndProductIdCategory(Long userId, Long categoryId, Pageable pageable) {

        Optional<User> user = userService.findById(userId);
        Optional<Category> category = categoryService.findById(categoryId);

        return productReviewRepository.findByUserIdAndProductIdCategory(user.get(), category.get(), pageable);

    }

    @Override
    public boolean existsProductReview(Long userId, Long productId) {

        Optional<User> user = userService.findById(userId);
        Optional<Product> product = productService.findById(productId);

        return productReviewRepository.existsByUserIdAndProductId(user.get(), product.get());
    }

    @Override
    public boolean checkExistProductIdInUserPcBuilds(Long userId, Long productId) {

        Optional<Product> product = productService.findById(productId);
        Optional<User> user = userService.findById(userId);

        List<PcBuild> pcBuilds = pcBuildService.findByUserId(user.get());

        Set<Product> products = new HashSet<>();

        pcBuilds.stream().forEach(pcBuild -> {

            Product cpuProduct = pcBuild.getCpuId().getProductId();
            Product cpuCoolerProduct = pcBuild.getCpuCoolerId().getProductId();
            Product motherboardProduct = pcBuild.getMotherboardId().getProductId();
            Product gpuProduct = pcBuild.getGpuId().getProductId();
            Product psuProduct = pcBuild.getPsuId().getProductId();
            Product caseProduct = pcBuild.getCaseId().getProductId();


            List<Memory> memoryList = pcBuild.getMemory();
            List<Storage> storageList = pcBuild.getStorage();

            Set<Product> memoryProduct = memoryList.stream().map(Memory::getProductId).collect(Collectors.toSet());

            Set<Product> storageProduct = storageList.stream().map(Storage::getProductId).collect(Collectors.toSet());

            products.add(cpuProduct);
            products.add(cpuCoolerProduct);
            products.add(motherboardProduct);
            products.add(gpuProduct);
            products.add(psuProduct);
            products.add(caseProduct);
            products.addAll(memoryProduct);
            products.addAll(storageProduct);

        });

        if(products.stream().anyMatch(product1 -> product1.getId().equals(product.get().getId())))
            return true;

        return false;
    }

    @Override
    public ProductReviewStatsDTO getProductReviewStatsByProductId(Long productId) {

        Optional<Product> product = productService.findById(productId);

        ProductReviewStatsDTO productReviewStatsDTO = new ProductReviewStatsDTO();

        productReviewStatsDTO.setRatingAvarage(productReviewRepository.getRatingAvarage(product.get()));
        productReviewStatsDTO.setRatingCount(productReviewRepository.getRatingCount(product.get()));

        return productReviewStatsDTO;
    }

    @Override
    public Optional<ProductReview> findById(Long id) {

            return productReviewRepository.findById(id);

    }
}
