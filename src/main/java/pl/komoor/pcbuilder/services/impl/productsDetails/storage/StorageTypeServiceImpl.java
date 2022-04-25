package pl.komoor.pcbuilder.services.impl.productsDetails.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.komoor.pcbuilder.models.productDetails.storage.StorageType;
import pl.komoor.pcbuilder.repository.productsDetails.storage.StorageTypeRepository;
import pl.komoor.pcbuilder.services.productsDetails.storage.StorageTypeService;

import java.util.List;
import java.util.Optional;

@Service
public class StorageTypeServiceImpl implements StorageTypeService {

    @Autowired
    StorageTypeRepository storageTypeRepository;

    @Override
    public List<StorageType> findAll() {
        return storageTypeRepository.findAll();
    }

    @Override
    public Optional<StorageType> findById(Long id) {
        return storageTypeRepository.findById(id);
    }
}
