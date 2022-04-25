package pl.komoor.pcbuilder.services.impl.products.cases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import pl.komoor.pcbuilder.models.productDetails.Category;
import pl.komoor.pcbuilder.models.productDetails.Manufacturer;
import pl.komoor.pcbuilder.models.productDetails.cases.CaseType;
import pl.komoor.pcbuilder.models.productDetails.motherboard.MotherboardFormFactor;
import pl.komoor.pcbuilder.models.products.Case;
import pl.komoor.pcbuilder.models.products.Product;
import pl.komoor.pcbuilder.models.tools.FileToDatabase;
import pl.komoor.pcbuilder.payload.request.products.CaseRequest;
import pl.komoor.pcbuilder.repository.products.CaseRepository;
import pl.komoor.pcbuilder.services.products.product.ProductService;
import pl.komoor.pcbuilder.services.products.cases.CaseService;
import pl.komoor.pcbuilder.services.productsDetails.CategoryService;
import pl.komoor.pcbuilder.services.productsDetails.ManufacturerService;
import pl.komoor.pcbuilder.services.productsDetails.cases.CaseTypeService;
import pl.komoor.pcbuilder.services.productsDetails.motherboard.MotherboardFormFactorService;
import pl.komoor.pcbuilder.services.tools.FileToDatabaseService;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CaseServiceImpl implements CaseService {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ManufacturerService manufacturerService;

    @Autowired
    FileToDatabaseService fileToDatabaseService;

    @Autowired
    CaseTypeService caseTypeService;

    @Autowired
    MotherboardFormFactorService motherboardFormFactorService;

    @Autowired
    CaseRepository caseRepository;


    @Override
    @Transactional
    public void saveCase(CaseRequest caseRequest) {

        Case cases = new Case();
        Product product = new Product();

        Optional<Category> category = categoryService.findByCategoryName("case");
        product.setCategory(category.get());


        if(caseRequest.getImageId().isEmpty()) {

            product.setFileToDatabase(null);

        } else {

            Optional<FileToDatabase> fileToDatabase = fileToDatabaseService.findById(caseRequest.getImageId());

            if(fileToDatabase.isPresent()) {
                fileToDatabase.ifPresent(product::setFileToDatabase);
            } else
            {
                product.setFileToDatabase(null);
            }
        }

        productService.saveProduct(product);


        Optional<Manufacturer> manufacturer = manufacturerService.findById(caseRequest.getManufacturerId());
        Optional<CaseType> caseType = caseTypeService.findById(caseRequest.getCaseTypeId());

        cases.setProductId(product);
        cases.setManufacturerId(manufacturer.get());
        cases.setModel(caseRequest.getModel());

        cases.setCaseTypeId(caseType.get());
        cases.setEx25bays(caseRequest.getEx25bays());
        cases.setEx35bays(caseRequest.getEx35bays());
        cases.setIn25bays(caseRequest.getIn25bays());
        cases.setIn35bays(caseRequest.getIn35bays());
        cases.setMaxGpuCardLength(caseRequest.getMaxGpuCardLength());


        //Dodawanie MotherboardFormFactor

        Set<String> motherboardFormFactorString = caseRequest.getMotherboardFormFactorId();

        Set<MotherboardFormFactor> motherboardFormFactors = new HashSet<>();

        motherboardFormFactorString.forEach(motherboardFormFactorItem -> {
            MotherboardFormFactor motherboardFormFactor = motherboardFormFactorService.findById(Long.valueOf(motherboardFormFactorItem))
                    .orElseThrow(() -> new RuntimeException("MotherboardFormFactor not found."));

            motherboardFormFactors.add(motherboardFormFactor);
        });

        cases.setMotherboardFormFactorId(motherboardFormFactors);

        caseRepository.save(cases);

    }

    @Override
    @Transactional
    public void updateCase(CaseRequest caseRequest, Long id) {

        Optional<Case> cases = findById(id);
        Optional<Product> product = productService.findById(cases.get().getProductId().getId());


        if(caseRequest.getImageId().isEmpty()) {

            product.get().setFileToDatabase(null);

        } else {

            Optional<FileToDatabase> fileToDatabase = fileToDatabaseService.findById(caseRequest.getImageId());

            if(fileToDatabase.isPresent()) {
                fileToDatabase.ifPresent(product.get()::setFileToDatabase);
            } else
            {
                product.get().setFileToDatabase(null);
            }
        }


        Optional<Manufacturer> manufacturer = manufacturerService.findById(caseRequest.getManufacturerId());
        Optional<CaseType> caseType = caseTypeService.findById(caseRequest.getCaseTypeId());


        manufacturer.ifPresent(cases.get()::setManufacturerId);
        cases.get().setModel(caseRequest.getModel());

        cases.get().setCaseTypeId(caseType.get());
        cases.get().setEx25bays(caseRequest.getEx25bays());
        cases.get().setEx35bays(caseRequest.getEx35bays());
        cases.get().setIn25bays(caseRequest.getIn25bays());
        cases.get().setIn35bays(caseRequest.getIn35bays());
        cases.get().setMaxGpuCardLength(caseRequest.getMaxGpuCardLength());

        //Dodawanie MotherboardFormFactor

        Set<String> motherboardFormFactorString = caseRequest.getMotherboardFormFactorId();

        Set<MotherboardFormFactor> motherboardFormFactors = new HashSet<>();

        motherboardFormFactorString.forEach(motherboardFormFactorItem -> {
            MotherboardFormFactor motherboardFormFactor = motherboardFormFactorService.findById(Long.valueOf(motherboardFormFactorItem))
                    .orElseThrow(() -> new RuntimeException("MotherboardFormFactor not found."));

            motherboardFormFactors.add(motherboardFormFactor);
        });

        cases.get().setMotherboardFormFactorId(motherboardFormFactors);

        caseRepository.save(cases.get());

    }

    @Override
    public void deleteCase(Case cases) {

        caseRepository.delete(cases);

    }

    @Override
    public List<Case> findAll() {
        return caseRepository.findAll();
    }

    @Override
    public Page<Case> findAll(Specification spec, Pageable pageRequest) {
        return caseRepository.findAll(spec, pageRequest);
    }

    @Override
    public Optional<Case> findById(Long id) {
        return caseRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return caseRepository.existsById(id);
    }

    @Override
    public boolean existsByModel(String model) {
        return caseRepository.existsByModel(model);
    }
}
