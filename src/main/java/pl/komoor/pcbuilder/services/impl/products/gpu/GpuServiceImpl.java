package pl.komoor.pcbuilder.services.impl.products.gpu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import pl.komoor.pcbuilder.models.productDetails.Category;
import pl.komoor.pcbuilder.models.productDetails.InterfaceType;
import pl.komoor.pcbuilder.models.productDetails.Manufacturer;
import pl.komoor.pcbuilder.models.productDetails.gpu.GpuChipset;
import pl.komoor.pcbuilder.models.productDetails.gpu.GpuMemoryType;
import pl.komoor.pcbuilder.models.products.Gpu;
import pl.komoor.pcbuilder.models.products.Product;
import pl.komoor.pcbuilder.models.tools.FileToDatabase;
import pl.komoor.pcbuilder.payload.request.products.GpuRequest;
import pl.komoor.pcbuilder.repository.products.GpuRepository;
import pl.komoor.pcbuilder.services.products.product.ProductService;
import pl.komoor.pcbuilder.services.products.gpu.GpuService;
import pl.komoor.pcbuilder.services.productsDetails.CategoryService;
import pl.komoor.pcbuilder.services.productsDetails.InterfaceTypeService;
import pl.komoor.pcbuilder.services.productsDetails.ManufacturerService;
import pl.komoor.pcbuilder.services.productsDetails.gpu.GpuChipsetService;
import pl.komoor.pcbuilder.services.productsDetails.gpu.GpuMemoryTypeService;
import pl.komoor.pcbuilder.services.tools.FileToDatabaseService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class GpuServiceImpl implements GpuService {

    @Autowired
    GpuRepository gpuRepository;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @Autowired
    FileToDatabaseService fileToDatabaseService;

    @Autowired
    ManufacturerService manufacturerService;

    @Autowired
    GpuChipsetService gpuChipsetService;

    @Autowired
    GpuMemoryTypeService gpuMemoryTypeService;

    @Autowired
    InterfaceTypeService interfaceTypeService;

    @Override
    @Transactional
    public void saveGpu(GpuRequest gpuRequest) {

        Gpu gpu = new Gpu();
        Product product = new Product();

        Optional<Category> category = categoryService.findByCategoryName("gpu");
        product.setCategory(category.get());


        if(gpuRequest.getImageId().isEmpty()) {

            product.setFileToDatabase(null);

        } else {

            Optional<FileToDatabase> fileToDatabase = fileToDatabaseService.findById(gpuRequest.getImageId());

            if(fileToDatabase.isPresent()) {
                fileToDatabase.ifPresent(product::setFileToDatabase);
            } else
            {
                product.setFileToDatabase(null);
            }
        }

        productService.saveProduct(product);


        Optional<Manufacturer> manufacturer = manufacturerService.findById(gpuRequest.getManufacturerId());
        Optional<GpuChipset> gpuChipset = gpuChipsetService.findById(gpuRequest.getGpuChipsetId());
        Optional<GpuMemoryType> gpuMemoryType = gpuMemoryTypeService.findById(gpuRequest.getGpuMemoryTypeId());
        Optional<InterfaceType> interfaceType = interfaceTypeService.findById(gpuRequest.getGpuInterfaceId());


        gpu.setProductId(product);
        gpu.setManufacturerId(manufacturer.get());
        gpu.setModel(gpuRequest.getModel());
        gpu.setGpuChipsetId(gpuChipset.get());
        gpu.setGpuMemoryTypeId(gpuMemoryType.get());
        gpu.setInterfaceTypeId(interfaceType.get());
        gpu.setBaseClock(gpuRequest.getBaseClock());
        gpu.setBoostClock(gpuRequest.getBoostClock());
        gpu.setFrameSync(gpuRequest.getFrameSync());
        gpu.setMemoryGB(gpuRequest.getMemoryGB());
        gpu.setTdp(gpuRequest.getTdp());
        gpu.setPortsDvi(gpuRequest.getPortsDvi());
        gpu.setPortsHdmi(gpuRequest.getPortsHdmi());
        gpu.setPortsMiniHdmi(gpuRequest.getPortsMiniHdmi());
        gpu.setPortsDisplayPort(gpuRequest.getPortsDisplayPort());
        gpu.setPortsMiniDisplayPort(gpuRequest.getPortsMiniDisplayPort());

        gpuRepository.save(gpu);

    }

    @Override
    @Transactional
    public void updateGpu(GpuRequest gpuRequest, Long id) {

        Optional<Gpu> gpu = gpuRepository.findById(id);
        Optional<Product> product = productService.findById(gpu.get().getProductId().getId());


        if(gpuRequest.getImageId().isEmpty()) {
            product.get().setFileToDatabase(null);
        } else {

            Optional<FileToDatabase> fileToDatabase = fileToDatabaseService.findById(gpuRequest.getImageId());

            if(fileToDatabase.isPresent()) {

                fileToDatabase.ifPresent(product.get()::setFileToDatabase);
            } else
            {
                product.get().setFileToDatabase(null);
            }
        }


        Optional<Manufacturer> manufacturer = manufacturerService.findById(gpuRequest.getManufacturerId());
        Optional<GpuChipset> gpuChipset = gpuChipsetService.findById(gpuRequest.getGpuChipsetId());
        Optional<GpuMemoryType> gpuMemoryType = gpuMemoryTypeService.findById(gpuRequest.getGpuMemoryTypeId());
        Optional<InterfaceType> gpuInterface = interfaceTypeService.findById(gpuRequest.getGpuInterfaceId());


        manufacturer.ifPresent(gpu.get()::setManufacturerId);
        gpu.get().setModel(gpuRequest.getModel());
        gpuChipset.ifPresent(gpu.get()::setGpuChipsetId);
        gpuMemoryType.ifPresent(gpu.get()::setGpuMemoryTypeId);
        gpuInterface.ifPresent(gpu.get()::setInterfaceTypeId);
        gpu.get().setBaseClock(gpuRequest.getBaseClock());
        gpu.get().setBoostClock(gpuRequest.getBoostClock());
        gpu.get().setFrameSync(gpuRequest.getFrameSync());
        gpu.get().setMemoryGB(gpuRequest.getMemoryGB());
        gpu.get().setTdp(gpuRequest.getTdp());
        gpu.get().setPortsDvi(gpuRequest.getPortsDvi());
        gpu.get().setPortsHdmi(gpuRequest.getPortsHdmi());
        gpu.get().setPortsMiniHdmi(gpuRequest.getPortsMiniHdmi());
        gpu.get().setPortsDisplayPort(gpuRequest.getPortsDisplayPort());
        gpu.get().setPortsMiniDisplayPort(gpuRequest.getPortsMiniDisplayPort());


        gpuRepository.save(gpu.get());


    }

    @Override
    public void deleteGpu(Gpu gpu) {

        gpuRepository.delete(gpu);

    }

    @Override
    public List<Gpu> findAll() {
        return gpuRepository.findAll();
    }

    @Override
    public Page<Gpu> findAll(Specification<Gpu> spec, Pageable pageRequest) {
        return gpuRepository.findAll(spec, pageRequest);
    }

    @Override
    public Optional<Gpu> findById(Long id) {
        return gpuRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return gpuRepository.existsById(id);
    }

    @Override
    public boolean existsByModel(String model) {
        return gpuRepository.existsByModel(model);
    }
}
