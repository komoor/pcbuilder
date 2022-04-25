package pl.komoor.pcbuilder.services.impl.products.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.komoor.pcbuilder.models.builds.PcBuild;
import pl.komoor.pcbuilder.models.products.Memory;
import pl.komoor.pcbuilder.models.products.Product;
import pl.komoor.pcbuilder.models.products.Storage;
import pl.komoor.pcbuilder.models.tools.FileToDatabase;
import pl.komoor.pcbuilder.models.users.User;
import pl.komoor.pcbuilder.repository.products.ProductRepository;
import pl.komoor.pcbuilder.services.builds.PcBuildService;
import pl.komoor.pcbuilder.services.products.product.ProductService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    PcBuildService pcBuildService;

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> findByImage(FileToDatabase file) {
        return productRepository.findByFileToDatabase(file);
    }

    @Override
    public Set<Product> getAllProductUsedInBuildsByUserId(User user) {



        List<PcBuild> pcBuilds = pcBuildService.findByUserId(user);


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

        return products;
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {

       return productRepository.findAll();

    }
}
