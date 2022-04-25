package pl.komoor.pcbuilder.services.impl.products.memory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import pl.komoor.pcbuilder.models.productDetails.*;
import pl.komoor.pcbuilder.models.productDetails.memory.MemoryTimming;
import pl.komoor.pcbuilder.models.productDetails.memory.MemoryType;
import pl.komoor.pcbuilder.models.products.Memory;
import pl.komoor.pcbuilder.models.products.Product;
import pl.komoor.pcbuilder.models.tools.FileToDatabase;
import pl.komoor.pcbuilder.payload.request.products.MemoryRequest;
import pl.komoor.pcbuilder.repository.products.MemoryRepository;
import pl.komoor.pcbuilder.services.products.product.ProductService;
import pl.komoor.pcbuilder.services.products.memory.MemoryService;
import pl.komoor.pcbuilder.services.productsDetails.CategoryService;
import pl.komoor.pcbuilder.services.productsDetails.ManufacturerService;
import pl.komoor.pcbuilder.services.productsDetails.memory.MemoryTimmingService;
import pl.komoor.pcbuilder.services.productsDetails.memory.MemoryTypeService;
import pl.komoor.pcbuilder.services.tools.FileToDatabaseService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MemoryServiceImpl implements MemoryService {

    @Autowired
    MemoryRepository memoryRepository;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @Autowired
    FileToDatabaseService fileToDatabaseService;

    @Autowired
    ManufacturerService manufacturerService;

    @Autowired
    MemoryTypeService memoryTypeService;

    @Autowired
    MemoryTimmingService memoryTimmingService;


    @Override
    @Transactional
    public void saveMemoryRequest(MemoryRequest memoryRequest) {

        Memory memory = new Memory();
        Product product = new Product();

        Optional<Category> category = categoryService.findByCategoryName("memory");
        product.setCategory(category.get());


        if(memoryRequest.getImageId().isEmpty()) {

            product.setFileToDatabase(null);

        } else {

            Optional<FileToDatabase> fileToDatabase = fileToDatabaseService.findById(memoryRequest.getImageId());

            if(fileToDatabase.isPresent()) {
                fileToDatabase.ifPresent(product::setFileToDatabase);
            } else
            {
                product.setFileToDatabase(null);
            }
        }

        productService.saveProduct(product);


        Optional<Manufacturer> manufacturer = manufacturerService.findById(memoryRequest.getManufacturerId());
        Optional<MemoryType> memoryType = memoryTypeService.findById(memoryRequest.getMemoryTypeId());
        Optional<MemoryTimming> memoryTimming = memoryTimmingService.findById(memoryRequest.getMemoryTimmingId());


        memory.setProductId(product);
        memory.setManufacturerId(manufacturer.get());
        memory.setModel(memoryRequest.getModel());
        memory.setMemoryTypeId(memoryType.get());
        memory.setMemoryTimmingId(memoryTimming.get());
        memory.setMemoryGB(memoryRequest.getMemoryGB());
        memory.setNumberOfModules(memoryRequest.getNumberOfModules());
        memory.setVoltage(memoryRequest.getVoltage());
        memory.setCasLatency(memoryRequest.getCasLatency());
        memory.setHeatSpreader(memoryRequest.getHeatSpreader());

        memoryRepository.save(memory);


    };

    @Override
    @Transactional
    public void updateMemory(MemoryRequest memoryRequest, Long id) {

        Optional<Memory> memory = memoryRepository.findById(id);
        Optional<Product> product = productService.findById(memory.get().getProductId().getId());


        if(memoryRequest.getImageId().isEmpty()) {
            product.get().setFileToDatabase(null);
        } else {

            Optional<FileToDatabase> fileToDatabase = fileToDatabaseService.findById(memoryRequest.getImageId());

            if(fileToDatabase.isPresent()) {

                fileToDatabase.ifPresent(product.get()::setFileToDatabase);
            } else
            {
                product.get().setFileToDatabase(null);
            }
        }


        Optional<Manufacturer> manufacturer = manufacturerService.findById(memoryRequest.getManufacturerId());
        Optional<MemoryType> memoryType = memoryTypeService.findById(memoryRequest.getMemoryTypeId());
        Optional<MemoryTimming> memoryTimming = memoryTimmingService.findById(memoryRequest.getMemoryTimmingId());

        manufacturer.ifPresent(memory.get()::setManufacturerId);
        memory.get().setModel(memoryRequest.getModel());
        memoryType.ifPresent(memory.get()::setMemoryTypeId);
        memoryTimming.ifPresent(memory.get()::setMemoryTimmingId);
        memory.get().setMemoryGB(memoryRequest.getMemoryGB());
        memory.get().setNumberOfModules(memoryRequest.getNumberOfModules());
        memory.get().setVoltage(memoryRequest.getVoltage());
        memory.get().setCasLatency(memoryRequest.getCasLatency());
        memory.get().setHeatSpreader(memoryRequest.getHeatSpreader());

        memoryRepository.save(memory.get());

    }

    @Override
    public void deleteMemory(Memory memory) {
        memoryRepository.delete(memory);
    }

    @Override
    public Page<Memory> findAllMemories(Specification<Memory> spec, Pageable pageable) {
        return memoryRepository.findAll(spec, pageable);
    }

    @Override
    public List<Memory> findAll() {

        return memoryRepository.findAll();

    }

    @Override
    public Page<Memory> findAll(Specification spec, Pageable pageRequest) {
        return null;
    }

    @Override
    public Optional<Memory> findById(Long id) {
        return memoryRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return memoryRepository.existsById(id);
    }

    @Override
    public boolean existsByModel(String model) {
        return memoryRepository.existsByModel(model);
    }
}
