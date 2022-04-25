package pl.komoor.pcbuilder.services.impl.products.motherboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import pl.komoor.pcbuilder.models.productDetails.Category;
import pl.komoor.pcbuilder.models.productDetails.Manufacturer;
import pl.komoor.pcbuilder.models.productDetails.cpu.CpuSocket;
import pl.komoor.pcbuilder.models.productDetails.memory.MemoryType;
import pl.komoor.pcbuilder.models.productDetails.motherboard.MotherboardChipset;
import pl.komoor.pcbuilder.models.productDetails.motherboard.MotherboardEthernet;
import pl.komoor.pcbuilder.models.productDetails.motherboard.MotherboardFormFactor;
import pl.komoor.pcbuilder.models.products.Motherboard;
import pl.komoor.pcbuilder.models.products.Product;
import pl.komoor.pcbuilder.models.tools.FileToDatabase;
import pl.komoor.pcbuilder.payload.request.products.MotherboardRequest;
import pl.komoor.pcbuilder.repository.products.MotherboardRepository;
import pl.komoor.pcbuilder.services.products.product.ProductService;
import pl.komoor.pcbuilder.services.products.motherboard.MotherboardService;
import pl.komoor.pcbuilder.services.productsDetails.CategoryService;
import pl.komoor.pcbuilder.services.productsDetails.ManufacturerService;
import pl.komoor.pcbuilder.services.productsDetails.cpu.CpuSocketService;
import pl.komoor.pcbuilder.services.productsDetails.memory.MemoryTypeService;
import pl.komoor.pcbuilder.services.productsDetails.motherboard.MotherboardChipsetService;
import pl.komoor.pcbuilder.services.productsDetails.motherboard.MotherboardEthernetService;
import pl.komoor.pcbuilder.services.productsDetails.motherboard.MotherboardFormFactorService;
import pl.komoor.pcbuilder.services.tools.FileToDatabaseService;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MotherboardServiceImpl implements MotherboardService {

    @Autowired
    ManufacturerService manufacturerService;

    @Autowired
    FileToDatabaseService fileToDatabaseService;

    @Autowired
    ProductService productService;

    @Autowired
    CpuSocketService cpuSocketService;

    @Autowired
    MotherboardFormFactorService motherboardFormFactorService;

    @Autowired
    MotherboardChipsetService motherboardChipsetService;

    @Autowired
    MotherboardEthernetService motherboardEthernetService;

    @Autowired
    MemoryTypeService memoryTypeService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    MotherboardRepository motherboardRepository;


    @Override
    @Transactional
    public void saveMotherboard(MotherboardRequest motherboardRequest) {



        Motherboard motherboard = new Motherboard();
        Product product = new Product();

        Optional<Category> category = categoryService.findByCategoryName("motherboard");
        product.setCategory(category.get());


        if(motherboardRequest.getImageId().isEmpty()) {

            product.setFileToDatabase(null);

        } else {

            Optional<FileToDatabase> fileToDatabase = fileToDatabaseService.findById(motherboardRequest.getImageId());

            if(fileToDatabase.isPresent()) {
                fileToDatabase.ifPresent(product::setFileToDatabase);
            } else
            {
                product.setFileToDatabase(null);
            }
        }

        productService.saveProduct(product);

        Optional<Manufacturer> manufacturer = Optional.ofNullable(manufacturerService.findById(motherboardRequest.getManufacturerId()).orElseThrow(() -> new RuntimeException("1 not found.")));
        Optional<CpuSocket> cpuSocket = Optional.ofNullable(cpuSocketService.findById(motherboardRequest.getCpuSocketId()).orElseThrow(() -> new RuntimeException("2 not found.")));
        Optional<MotherboardFormFactor> motherboardFormFactor = Optional.ofNullable(motherboardFormFactorService.findById(motherboardRequest.getMotherboardFormFactorId()).orElseThrow(() -> new RuntimeException("3 not found.")));
        Optional<MotherboardChipset> motherboardChipset = Optional.ofNullable(motherboardChipsetService.findById(motherboardRequest.getMotherboardChipsetId()).orElseThrow(() -> new RuntimeException("4 not found.")));
        Optional<MotherboardEthernet> motherboardEthernet = Optional.ofNullable(motherboardEthernetService.findById(motherboardRequest.getMotherboardEthernetId()).orElseThrow(() -> new RuntimeException("5 not found.")));


        motherboard.setProductId(product);
        motherboard.setManufacturerId(manufacturer.get());
        motherboard.setModel(motherboardRequest.getModel());
        motherboard.setCpuSocketId(cpuSocket.get());
        motherboard.setMotherboardFormFactorId(motherboardFormFactor.get());
        motherboard.setMotherboardChipsetId(motherboardChipset.get());
        motherboard.setMemoryMax(motherboardRequest.getMemoryMax());
        motherboard.setMemorySlots(motherboardRequest.getMemorySlots());
        motherboard.setM2Slots(motherboardRequest.getM2Slots());
        motherboard.setPcieX16Slots(motherboardRequest.getPcieX16Slots());
        motherboard.setPcieX8Slots(motherboardRequest.getPcieX8Slots());
        motherboard.setPcieX4Slots(motherboardRequest.getPcieX4Slots());
        motherboard.setPcieX1Slots(motherboardRequest.getPcieX1Slots());
        motherboard.setPciSlots(motherboardRequest.getPciSlots());
        motherboard.setSata3GbPorts(motherboardRequest.getSata3GbPorts());
        motherboard.setSata6GbPorts(motherboardRequest.getSata6GbPorts());
        motherboard.setSataExpressPorts(motherboardRequest.getSataExpressPorts());
        motherboard.setUsb20Headers(motherboardRequest.getUsb20Headers());
        motherboard.setUsb32gen1Headers(motherboardRequest.getUsb32gen1Headers());
        motherboard.setUsb32gen2Headers(motherboardRequest.getUsb32gen2Headers());
        motherboard.setMotherboardEthernetId(motherboardEthernet.get());

        //Dodawanie MemoryType

        Set<String> memoryTypeString = motherboardRequest.getMemoryTypeId();

        Set<MemoryType> memoryTypes = new HashSet<>();

        memoryTypeString.forEach(memoryTypeItem -> {
            MemoryType memoryType = memoryTypeService.findById(Long.valueOf(memoryTypeItem))
                    .orElseThrow(() -> new RuntimeException("MemoryType not found."));

            memoryTypes.add(memoryType);
        });

        motherboard.setMemoryTypeId(memoryTypes);

        motherboardRepository.save(motherboard);

    }

    @Override
    @Transactional
    public void updateMotherboard(MotherboardRequest motherboardRequest, Long id) {

        Optional<Motherboard> motherboard = findById(id);
        Optional<Product> product = productService.findById(motherboard.get().getProductId().getId());


        if(motherboardRequest.getImageId().isEmpty()) {

            product.get().setFileToDatabase(null);

        } else {

            Optional<FileToDatabase> fileToDatabase = fileToDatabaseService.findById(motherboardRequest.getImageId());

            if(fileToDatabase.isPresent()) {
                fileToDatabase.ifPresent(product.get()::setFileToDatabase);
            } else
            {
                product.get().setFileToDatabase(null);
            }
        }

        Optional<Manufacturer> manufacturer = manufacturerService.findById(motherboardRequest.getManufacturerId());
        Optional<CpuSocket> cpuSocket = cpuSocketService.findById(motherboardRequest.getCpuSocketId());
        Optional<MotherboardFormFactor> motherboardFormFactor = motherboardFormFactorService.findById(motherboardRequest.getMotherboardFormFactorId());
        Optional<MotherboardChipset> motherboardChipset = motherboardChipsetService.findById(motherboardRequest.getMotherboardChipsetId());
        Optional<MotherboardEthernet> motherboardEthernet = motherboardEthernetService.findById(motherboardRequest.getMotherboardEthernetId());

        manufacturer.ifPresent(motherboard.get()::setManufacturerId);
        motherboard.get().setModel(motherboardRequest.getModel());

        motherboard.get().setCpuSocketId(cpuSocket.get());
        motherboard.get().setMotherboardFormFactorId(motherboardFormFactor.get());
        motherboard.get().setMotherboardChipsetId(motherboardChipset.get());
        motherboard.get().setMemoryMax(motherboardRequest.getMemoryMax());
        motherboard.get().setMemorySlots(motherboardRequest.getMemorySlots());
        motherboard.get().setM2Slots(motherboardRequest.getM2Slots());
        motherboard.get().setPcieX16Slots(motherboardRequest.getPcieX16Slots());
        motherboard.get().setPcieX8Slots(motherboardRequest.getPcieX8Slots());
        motherboard.get().setPcieX4Slots(motherboardRequest.getPcieX4Slots());
        motherboard.get().setPcieX1Slots(motherboardRequest.getPcieX1Slots());
        motherboard.get().setPciSlots(motherboardRequest.getPciSlots());
        motherboard.get().setSata3GbPorts(motherboardRequest.getSata3GbPorts());
        motherboard.get().setSata6GbPorts(motherboardRequest.getSata6GbPorts());
        motherboard.get().setSataExpressPorts(motherboardRequest.getSataExpressPorts());
        motherboard.get().setUsb20Headers(motherboardRequest.getUsb20Headers());
        motherboard.get().setUsb32gen1Headers(motherboardRequest.getUsb32gen1Headers());
        motherboard.get().setUsb32gen2Headers(motherboardRequest.getUsb32gen2Headers());
        motherboard.get().setMotherboardEthernetId(motherboardEthernet.get());

        //Dodawanie MemoryType

        Set<String> memoryTypeString = motherboardRequest.getMemoryTypeId();

        Set<MemoryType> memoryTypes = new HashSet<>();

        memoryTypeString.forEach(memoryTypeItem -> {
            MemoryType memoryType = memoryTypeService.findById(Long.valueOf(memoryTypeItem))
                    .orElseThrow(() -> new RuntimeException("MemoryType not found."));

            memoryTypes.add(memoryType);
        });

        motherboard.get().setMemoryTypeId(memoryTypes);

        motherboardRepository.save(motherboard.get());


    }

    @Override
    public void deleteMotherboard(Motherboard motherboard) {
        motherboardRepository.delete(motherboard);
    }

    @Override
    public List<Motherboard> findAll() {
        return motherboardRepository.findAll();
    }

    @Override
    public Page<Motherboard> findAll(Specification spec, Pageable pageRequest) {
        return motherboardRepository.findAll(spec, pageRequest);
    }

    @Override
    public Optional<Motherboard> findById(Long id) {
        return motherboardRepository.findById(id);
    }

    @Override
    public Optional<Motherboard> findByProductId(Product product) {
        return motherboardRepository.findByProductId(product);
    }

    @Override
    public boolean existsById(Long id) {
        return motherboardRepository.existsById(id);
    }

    @Override
    public boolean existsByModel(String model) {
        return motherboardRepository.existsByModel(model);
    }
}
