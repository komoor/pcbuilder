package pl.komoor.pcbuilder.services.impl.products.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import pl.komoor.pcbuilder.models.productDetails.*;
import pl.komoor.pcbuilder.models.productDetails.storage.StorageFormFactor;
import pl.komoor.pcbuilder.models.productDetails.InterfaceType;
import pl.komoor.pcbuilder.models.productDetails.storage.StorageType;
import pl.komoor.pcbuilder.models.products.Product;
import pl.komoor.pcbuilder.models.products.Storage;
import pl.komoor.pcbuilder.models.tools.FileToDatabase;
import pl.komoor.pcbuilder.payload.request.products.StorageRequest;
import pl.komoor.pcbuilder.repository.products.StorageRepository;
import pl.komoor.pcbuilder.services.products.product.ProductService;
import pl.komoor.pcbuilder.services.products.storage.StorageService;
import pl.komoor.pcbuilder.services.productsDetails.*;
import pl.komoor.pcbuilder.services.productsDetails.storage.StorageFormFactorService;
import pl.komoor.pcbuilder.services.productsDetails.storage.StorageTypeService;
import pl.komoor.pcbuilder.services.tools.FileToDatabaseService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ManufacturerService manufacturerService;

    @Autowired
    FileToDatabaseService fileToDatabaseService;

    @Autowired
    StorageRepository storageRepository;

    @Autowired
    StorageTypeService storageTypeService;

    @Autowired
    StorageFormFactorService storageFormFactorService;

    @Autowired
    InterfaceTypeService interfaceTypeService;


    @Override
    @Transactional
    public void saveStorage(StorageRequest storageRequest) {

        Storage storage = new Storage();
        Product product = new Product();

        Optional<Category> category = categoryService.findByCategoryName("storage");
        product.setCategory(category.get());


        if(storageRequest.getImageId().isEmpty()) {

            product.setFileToDatabase(null);

        } else {

            Optional<FileToDatabase> fileToDatabase = fileToDatabaseService.findById(storageRequest.getImageId());

            if(fileToDatabase.isPresent()) {
                fileToDatabase.ifPresent(product::setFileToDatabase);
            } else
            {
                product.setFileToDatabase(null);
            }
        }

        productService.saveProduct(product);


        Optional<Manufacturer> manufacturer = manufacturerService.findById(storageRequest.getManufacturerId());
        Optional<StorageType> storageType = storageTypeService.findById(storageRequest.getStorageTypeId());
        Optional<StorageFormFactor> storageFormFactor = storageFormFactorService.findById(storageRequest.getStorageFormFactorId());
        Optional<InterfaceType> storageInterface = interfaceTypeService.findById(storageRequest.getStorageInterfaceId());


        storage.setProductId(product);
        storage.setManufacturerId(manufacturer.get());
        storage.setModel(storageRequest.getModel());
        storage.setStorageTypeId(storageType.get());
        storage.setStorageFormFactorId(storageFormFactor.get());
        storage.setInterfaceTypeId(storageInterface.get());
        storage.setCapacity(storageRequest.getCapacity());
        storage.setCache(storageRequest.getCache());
        storage.setNmve(storageRequest.getNmve());

        storageRepository.save(storage);

    }

    @Override
    @Transactional
    public void updateStorage(StorageRequest storageRequest, Long id) {

        Optional<Storage> storage = findById(id);
        Optional<Product> product = productService.findById(storage.get().getProductId().getId());


        if(storageRequest.getImageId().isEmpty()) {

            product.get().setFileToDatabase(null);

        } else {

            Optional<FileToDatabase> fileToDatabase = fileToDatabaseService.findById(storageRequest.getImageId());

            if(fileToDatabase.isPresent()) {
                fileToDatabase.ifPresent(product.get()::setFileToDatabase);
            } else
            {
                product.get().setFileToDatabase(null);
            }
        }


        Optional<Manufacturer> manufacturer = manufacturerService.findById(storageRequest.getManufacturerId());
        Optional<StorageType> storageType = storageTypeService.findById(storageRequest.getStorageTypeId());
        Optional<StorageFormFactor> storageFormFactor = storageFormFactorService.findById(storageRequest.getStorageFormFactorId());
        Optional<InterfaceType> storageInterface = interfaceTypeService.findById(storageRequest.getStorageInterfaceId());

        manufacturer.ifPresent(storage.get()::setManufacturerId);
        storage.get().setModel(storageRequest.getModel());
        storageType.ifPresent(storage.get()::setStorageTypeId);
        storageFormFactor.ifPresent(storage.get()::setStorageFormFactorId);
        storageInterface.ifPresent(storage.get()::setInterfaceTypeId);
        storage.get().setCapacity(storageRequest.getCapacity());
        storage.get().setCache(storageRequest.getCache());
        storage.get().setNmve(storageRequest.getNmve());

        storageRepository.save(storage.get());


    }

    @Override
    public void deleteStorage(Storage storage) {

        storageRepository.delete(storage);

    }

    @Override
    public List<Storage> findAll() {
        return storageRepository.findAll();
    }

    @Override
    public Page<Storage> findAll(Specification<Storage> spec, Pageable pageRequest) {
        return storageRepository.findAll(spec, pageRequest);
    }

    @Override
    public Optional<Storage> findById(Long id) {
        return storageRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return storageRepository.existsById(id);
    }

    @Override
    public boolean existsByModel(String model) {
        return storageRepository.existsByModel(model);
    }
}
