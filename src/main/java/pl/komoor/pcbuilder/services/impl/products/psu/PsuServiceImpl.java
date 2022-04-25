package pl.komoor.pcbuilder.services.impl.products.psu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import pl.komoor.pcbuilder.models.productDetails.Category;
import pl.komoor.pcbuilder.models.productDetails.Manufacturer;
import pl.komoor.pcbuilder.models.productDetails.psu.PsuEfficiencyRating;
import pl.komoor.pcbuilder.models.productDetails.psu.PsuType;
import pl.komoor.pcbuilder.models.products.Product;
import pl.komoor.pcbuilder.models.products.Psu;
import pl.komoor.pcbuilder.models.tools.FileToDatabase;
import pl.komoor.pcbuilder.payload.request.products.PsuRequest;
import pl.komoor.pcbuilder.repository.products.PsuRepository;
import pl.komoor.pcbuilder.services.products.product.ProductService;
import pl.komoor.pcbuilder.services.products.psu.PsuService;
import pl.komoor.pcbuilder.services.productsDetails.CategoryService;
import pl.komoor.pcbuilder.services.productsDetails.ManufacturerService;
import pl.komoor.pcbuilder.services.productsDetails.psu.PsuEfficiencyRatingService;
import pl.komoor.pcbuilder.services.productsDetails.psu.PsuTypeService;
import pl.komoor.pcbuilder.services.tools.FileToDatabaseService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PsuServiceImpl implements PsuService {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @Autowired
    FileToDatabaseService fileToDatabaseService;

    @Autowired
    ManufacturerService manufacturerService;

    @Autowired
    PsuTypeService psuTypeService;

    @Autowired
    PsuEfficiencyRatingService psuEfficiencyRatingService;

    @Autowired
    PsuRepository psuRepository;

    @Override
    @Transactional
    public void savePsu(PsuRequest psuRequest) {

        Psu psu = new Psu();
        Product product = new Product();

        Optional<Category> category = categoryService.findByCategoryName("psu");
        product.setCategory(category.get());


        if(psuRequest.getImageId().isEmpty()) {

            product.setFileToDatabase(null);

        } else {

            Optional<FileToDatabase> fileToDatabase = fileToDatabaseService.findById(psuRequest.getImageId());

            if(fileToDatabase.isPresent()) {
                fileToDatabase.ifPresent(product::setFileToDatabase);
            } else
            {
                product.setFileToDatabase(null);
            }
        }

        productService.saveProduct(product);

        Optional<Manufacturer> manufacturer = manufacturerService.findById(psuRequest.getManufacturerId());
        Optional<PsuType> psuType = psuTypeService.findById(psuRequest.getPsuTypeId());
        Optional<PsuEfficiencyRating> psuEfficiencyRating = psuEfficiencyRatingService.findById(psuRequest.getPsuEfficiencyRatingId());

        psu.setProductId(product);
        psu.setManufacturerId(manufacturer.get());
        psu.setModel(psuRequest.getModel());
        psu.setPsuTypeId(psuType.get());
        psu.setPsuEfficiencyRatingId(psuEfficiencyRating.get());
        psu.setWattage(psuRequest.getWattage());
        psu.setModular(psuRequest.getModular());
        psu.setPcie8pin(psuRequest.getPcie8pin());
        psu.setPcie62pin(psuRequest.getPcie62pin());
        psu.setPcie6pin(psuRequest.getPcie6pin());
        psu.setSata(psuRequest.getSata());
        psu.setMolex4pin(psuRequest.getMolex4pin());


        psuRepository.save(psu);

    }

    @Override
    @Transactional
    public void updatePsu(PsuRequest psuRequest, Long id) {

        Optional<Psu> psu = findById(id);
        Optional<Product> product = productService.findById(psu.get().getProductId().getId());


        if(psuRequest.getImageId().isEmpty()) {

            product.get().setFileToDatabase(null);

        } else {

            Optional<FileToDatabase> fileToDatabase = fileToDatabaseService.findById(psuRequest.getImageId());

            if(fileToDatabase.isPresent()) {
                fileToDatabase.ifPresent(product.get()::setFileToDatabase);
            } else
            {
                product.get().setFileToDatabase(null);
            }
        }


        Optional<Manufacturer> manufacturer = manufacturerService.findById(psuRequest.getManufacturerId());
        Optional<PsuType> psuType = psuTypeService.findById(psuRequest.getPsuTypeId());
        Optional<PsuEfficiencyRating> psuEfficiencyRating = psuEfficiencyRatingService.findById(psuRequest.getPsuEfficiencyRatingId());

        manufacturer.ifPresent(psu.get()::setManufacturerId);
        psu.get().setModel(psuRequest.getModel());
        psuType.ifPresent(psu.get()::setPsuTypeId);
        psuEfficiencyRating.ifPresent(psu.get()::setPsuEfficiencyRatingId);
        psu.get().setPsuTypeId(psuType.get());
        psu.get().setPsuEfficiencyRatingId(psuEfficiencyRating.get());
        psu.get().setWattage(psuRequest.getWattage());
        psu.get().setModular(psuRequest.getModular());
        psu.get().setPcie8pin(psuRequest.getPcie8pin());
        psu.get().setPcie62pin(psuRequest.getPcie62pin());
        psu.get().setPcie6pin(psuRequest.getPcie6pin());
        psu.get().setSata(psuRequest.getSata());
        psu.get().setMolex4pin(psuRequest.getMolex4pin());

        psuRepository.save(psu.get());


    }

    @Override
    public void deletePsu(Psu psu) {

        psuRepository.delete(psu);

    }

    @Override
    public List<Psu> findAll() {
        return psuRepository.findAll();
    }

    @Override
    public Page<Psu> findAll(Specification spec, Pageable pageRequest) {
        return psuRepository.findAll(spec, pageRequest);
    }

    @Override
    public Optional<Psu> findById(Long id) {
        return psuRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return psuRepository.existsById(id);
    }

    @Override
    public boolean existsByModel(String model) {
        return psuRepository.existsByModel(model);
    }
}
