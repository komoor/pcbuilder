package pl.komoor.pcbuilder.services.impl.products.cpu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import pl.komoor.pcbuilder.models.productDetails.Category;
import pl.komoor.pcbuilder.models.productDetails.cpu.CpuSocket;
import pl.komoor.pcbuilder.models.productDetails.cpu.IntegratedGraphic;
import pl.komoor.pcbuilder.models.productDetails.Manufacturer;
import pl.komoor.pcbuilder.models.products.Cpu;
import pl.komoor.pcbuilder.models.products.Product;
import pl.komoor.pcbuilder.models.tools.FileToDatabase;
import pl.komoor.pcbuilder.payload.request.products.CpuRequest;
import pl.komoor.pcbuilder.repository.products.CpuRepository;
import pl.komoor.pcbuilder.repository.productsDetails.cpu.IntegratedGraphicRepository;
import pl.komoor.pcbuilder.services.dto.products.CpuDTO;
import pl.komoor.pcbuilder.services.products.product.ProductService;
import pl.komoor.pcbuilder.services.products.cpu.CpuService;
import pl.komoor.pcbuilder.services.productsDetails.CategoryService;
import pl.komoor.pcbuilder.services.productsDetails.cpu.CpuIntegratedGraphicService;
import pl.komoor.pcbuilder.services.productsDetails.cpu.CpuSocketService;
import pl.komoor.pcbuilder.services.productsDetails.ManufacturerService;
import pl.komoor.pcbuilder.services.tools.FileToDatabaseService;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CpuServiceImpl implements CpuService {


    @Autowired
    CpuRepository cpuRepository;

    @Autowired
    CpuIntegratedGraphicService cpuIntegratedGraphicService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @Autowired
    ManufacturerService manufacturerService;

    @Autowired
    CpuSocketService cpuSocketService;

    @Autowired
    FileToDatabaseService fileToDatabaseService;


    @Override
    @Transactional
    public void saveCpuRequest(CpuRequest cpuRequest) {

        Cpu cpu = new Cpu();
        Product product = new Product();

        Optional<Category> category = categoryService.findByCategoryName("cpu");
        product.setCategory(category.get());

        if(cpuRequest.getImageId().isEmpty()) {
            product.setFileToDatabase(null);
        } else {

            Optional<FileToDatabase> fileToDatabase = fileToDatabaseService.findById(cpuRequest.getImageId());

            if(fileToDatabase.isPresent()) {
                fileToDatabase.ifPresent(product::setFileToDatabase);
            } else
            {
                product.setFileToDatabase(null);
            }
        }

        productService.saveProduct(product);

        Optional<Manufacturer> manufacturer = manufacturerService.findById(cpuRequest.getManufacturerId());
        Optional<CpuSocket> cpuSocket = cpuSocketService.findById(cpuRequest.getSocketId());

        cpu.setProductId(product);
        cpu.setManufacturerId(manufacturer.get());
        cpu.setModel(cpuRequest.getModel());
        cpu.setSocketId(cpuSocket.get());
        cpu.setCores(cpuRequest.getCores());
        cpu.setBaseClock(cpuRequest.getBaseClock());
        cpu.setBoostClock(cpuRequest.getBoostClock());
        cpu.setTdp(cpuRequest.getTdp());
        if(cpuRequest.getIntegratedGraphicId() == null) {
            cpu.setIntegratedGraphic(null);
        } else {

            Optional<IntegratedGraphic> integratedGraphic = cpuIntegratedGraphicService.findById(cpuRequest.getIntegratedGraphicId());
            cpu.setIntegratedGraphic(integratedGraphic.get());
        }

        cpuRepository.save(cpu);

    }

    @Override
    @Transactional
    public void updateCpu(CpuRequest cpuRequest, Long id) {

        Optional<Cpu> cpu = cpuRepository.findById(id);
        Optional<Product> product = productService.findById(cpu.get().getProductId().getId());

        if(cpuRequest.getImageId().isEmpty()) {
            product.get().setFileToDatabase(null);
        } else {

            Optional<FileToDatabase> fileToDatabase = fileToDatabaseService.findById(cpuRequest.getImageId());

            if(fileToDatabase.isPresent()) {

                fileToDatabase.ifPresent(product.get()::setFileToDatabase);
            } else
            {
                product.get().setFileToDatabase(null);
            }
        }


        Optional<Manufacturer> manufacturer = manufacturerService.findById(cpuRequest.getManufacturerId());
        Optional<CpuSocket> cpuSocket = cpuSocketService.findById(cpuRequest.getSocketId());

        manufacturer.ifPresent(cpu.get()::setManufacturerId);
        cpu.get().setModel(cpuRequest.getModel());
        cpuSocket.ifPresent(cpu.get()::setSocketId);
        cpu.get().setCores(cpuRequest.getCores());
        cpu.get().setBaseClock(cpuRequest.getBaseClock());
        cpu.get().setBoostClock(cpuRequest.getBoostClock());
        cpu.get().setTdp(cpuRequest.getTdp());

        if(cpuRequest.getIntegratedGraphicId() == null) {
            cpu.get().setIntegratedGraphic(null);
        } else {

            Optional<IntegratedGraphic> integratedGraphic = cpuIntegratedGraphicService.findById(cpuRequest.getIntegratedGraphicId());

            if(integratedGraphic.isPresent()) {
                cpu.get().setIntegratedGraphic(integratedGraphic.get());
            } else {
                cpu.get().setIntegratedGraphic(null);
            }
        }

        cpuRepository.save(cpu.get());

    }

    @Override
    public void deleteCpu(Cpu cpu) {

        cpuRepository.delete(cpu);
    }

    @Override
    public Page<Cpu> findAllCpu(Specification spec, Pageable pageable) {
        return cpuRepository.findAll(spec, pageable);
    }


    @Override
    public boolean existsById(Long id) {

        return cpuRepository.existsById(id);

    }

    @Override
    public boolean existsByModel(String model) {
        return cpuRepository.existsByModel(model);
    }

    @Override
    public Optional<Cpu> findById(Long id) {

        Optional<Cpu> cpu = cpuRepository.findById(id);

        return cpu;


    }



}
