package pl.komoor.pcbuilder.services.impl.products.cpuCooler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import pl.komoor.pcbuilder.models.productDetails.Category;
import pl.komoor.pcbuilder.models.productDetails.cpuCooler.CpuCoolerWaterCooled;
import pl.komoor.pcbuilder.models.productDetails.Manufacturer;
import pl.komoor.pcbuilder.models.productDetails.cpu.CpuSocket;
import pl.komoor.pcbuilder.models.products.CpuCooler;
import pl.komoor.pcbuilder.models.products.Product;
import pl.komoor.pcbuilder.models.tools.FileToDatabase;
import pl.komoor.pcbuilder.payload.request.products.CpuCoolerRequest;
import pl.komoor.pcbuilder.repository.products.CpuCoolerRepository;
import pl.komoor.pcbuilder.services.products.product.ProductService;
import pl.komoor.pcbuilder.services.products.cpuCooler.CpuCoolerService;
import pl.komoor.pcbuilder.services.productsDetails.CategoryService;
import pl.komoor.pcbuilder.services.productsDetails.cpuCooler.CpuCoolerWaterCooledService;
import pl.komoor.pcbuilder.services.productsDetails.ManufacturerService;
import pl.komoor.pcbuilder.services.productsDetails.cpu.CpuSocketService;
import pl.komoor.pcbuilder.services.tools.FileToDatabaseService;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CpuCoolerServiceImpl implements CpuCoolerService {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ManufacturerService manufacturerService;

    @Autowired
    FileToDatabaseService fileToDatabaseService;

    @Autowired
    CpuCoolerWaterCooledService cpuCoolerWaterCooledService;

    @Autowired
    CpuSocketService cpuSocketService;


    @Autowired
    CpuCoolerRepository cpuCoolerRepository;

    @Override
    @Transactional
    public void saveCpuCooler(CpuCoolerRequest cpuCoolerRequest) {

        CpuCooler cpuCooler = new CpuCooler();
        Product product = new Product();

        Optional<Category> category = categoryService.findByCategoryName("cpu_cooler");
        product.setCategory(category.get());


        if(cpuCoolerRequest.getImageId().isEmpty()) {

            product.setFileToDatabase(null);

        } else {

            Optional<FileToDatabase> fileToDatabase = fileToDatabaseService.findById(cpuCoolerRequest.getImageId());

            if(fileToDatabase.isPresent()) {
                fileToDatabase.ifPresent(product::setFileToDatabase);
            } else
            {
                product.setFileToDatabase(null);
            }
        }

        productService.saveProduct(product);


        Optional<Manufacturer> manufacturer = manufacturerService.findById(cpuCoolerRequest.getManufacturerId());
        Optional<CpuCoolerWaterCooled> cpuCoolerWaterCooled = cpuCoolerWaterCooledService.findById(cpuCoolerRequest.getWaterCooledId());

        cpuCooler.setProductId(product);
        cpuCooler.setManufacturerId(manufacturer.get());
        cpuCooler.setModel(cpuCoolerRequest.getModel());
        cpuCooler.setWaterCooledId(cpuCoolerWaterCooled.get());
        cpuCooler.setHeight(cpuCoolerRequest.getHeight());
        cpuCooler.setFanless(cpuCoolerRequest.getFanless());

        //Dodawanie Cpu Sockets

        Set<String> cpuSocketsString = cpuCoolerRequest.getSocketId();

        Set<CpuSocket> cpuSockets = new HashSet<>();

        cpuSocketsString.forEach(cpuSocketItem -> {
            CpuSocket cpuSocket = cpuSocketService.findById(Long.valueOf(cpuSocketItem))
                       .orElseThrow(() -> new RuntimeException("Socket not found."));

            cpuSockets.add(cpuSocket);
        });

        cpuCooler.setSocketId(cpuSockets);

        cpuCoolerRepository.save(cpuCooler);

    }

    @Override
    @Transactional
    public void updateCpuCooler(CpuCoolerRequest cpuCoolerRequest, Long id) {

        Optional<CpuCooler> cpuCooler = findById(id);
        Optional<Product> product = productService.findById(cpuCooler.get().getProductId().getId());


        if(cpuCoolerRequest.getImageId().isEmpty()) {

            product.get().setFileToDatabase(null);

        } else {

            Optional<FileToDatabase> fileToDatabase = fileToDatabaseService.findById(cpuCoolerRequest.getImageId());

            if(fileToDatabase.isPresent()) {
                fileToDatabase.ifPresent(product.get()::setFileToDatabase);
            } else
            {
                product.get().setFileToDatabase(null);
            }
        }


        Optional<Manufacturer> manufacturer = manufacturerService.findById(cpuCoolerRequest.getManufacturerId());
        Optional<CpuCoolerWaterCooled> cpuCoolerWaterCooled = cpuCoolerWaterCooledService.findById(cpuCoolerRequest.getWaterCooledId());

        manufacturer.ifPresent(cpuCooler.get()::setManufacturerId);
        cpuCooler.get().setModel(cpuCoolerRequest.getModel());
        cpuCoolerWaterCooled.ifPresent(cpuCooler.get()::setWaterCooledId);
        cpuCooler.get().setHeight(cpuCoolerRequest.getHeight());
        cpuCooler.get().setFanless(cpuCoolerRequest.getFanless());

        //Dodawanie Cpu Sockets

        Set<String> cpuSocketsString = cpuCoolerRequest.getSocketId();

        Set<CpuSocket> cpuSockets = new HashSet<>();

        cpuSocketsString.forEach(cpuSocketItem -> {
            CpuSocket cpuSocket = cpuSocketService.findById(Long.valueOf(cpuSocketItem))
                    .orElseThrow(() -> new RuntimeException("Socket not found."));

            cpuSockets.add(cpuSocket);
        });

        cpuCooler.get().setSocketId(cpuSockets);

        cpuCoolerRepository.save(cpuCooler.get());
    }

    @Override
    public void deleteCpuCooler(CpuCooler cpuCooler) {
        cpuCoolerRepository.delete(cpuCooler);
    }

    @Override
    public List<CpuCooler> findAll() {
        return cpuCoolerRepository.findAll();
    }

    @Override
    public Page<CpuCooler> findAll(Specification<CpuCooler> spec, Pageable pageRequest) {
        return cpuCoolerRepository.findAll(spec, pageRequest);
    }

    @Override
    public Optional<CpuCooler> findById(Long id) {
        return cpuCoolerRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return cpuCoolerRepository.existsById(id);
    }

    @Override
    public boolean existsByModel(String model) {
        return cpuCoolerRepository.existsByModel(model);
    }
}
