package pl.komoor.pcbuilder.services.impl.productsDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.komoor.pcbuilder.models.productDetails.Category;
import pl.komoor.pcbuilder.models.productDetails.Manufacturer;
import pl.komoor.pcbuilder.repository.productsDetails.ManufacturerRepository;
import pl.komoor.pcbuilder.services.productsDetails.ManufacturerService;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    @Autowired
    ManufacturerRepository manufacturerRepository;


    @Override
    public Optional<Manufacturer> findById(Long id) {
        return manufacturerRepository.findById(id);
    }

    @Override
    public void saveManufacturer(Manufacturer manufacturer) {

        manufacturerRepository.save(manufacturer);

    }

    @Override
    public List<Manufacturer> findAllManufacturer() {
        return manufacturerRepository.findAll();
    }

    @Override
    public List<Manufacturer> findAllByManufacturerCategory(Category category) {
        return manufacturerRepository.findAllByManufacturerCategory(category);
    }


    @Override
    public boolean existsByManufacturerName(String manufacturerName) {
        return manufacturerRepository.existsByManufacturerName(manufacturerName);

    }
}
