package pl.komoor.pcbuilder.services.impl.productsDetails.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.komoor.pcbuilder.models.productDetails.storage.StorageFormFactor;
import pl.komoor.pcbuilder.repository.productsDetails.storage.StorageFormFactorRepository;
import pl.komoor.pcbuilder.services.productsDetails.storage.StorageFormFactorService;

import java.util.List;
import java.util.Optional;

@Service
public class StorageFormFactorServiceImpl implements StorageFormFactorService {

    @Autowired
    StorageFormFactorRepository storageFormFactorRepository;

    @Override
    public List<StorageFormFactor> findAll() {
        return storageFormFactorRepository.findAll();
    }

    @Override
    public Optional<StorageFormFactor> findById(Long id) {
        return storageFormFactorRepository.findById(id);
    }
}
