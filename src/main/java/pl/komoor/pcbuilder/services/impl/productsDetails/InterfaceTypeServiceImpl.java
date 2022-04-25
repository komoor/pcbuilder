package pl.komoor.pcbuilder.services.impl.productsDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.komoor.pcbuilder.models.productDetails.InterfaceType;
import pl.komoor.pcbuilder.repository.productsDetails.InterfaceTypeRepository;
import pl.komoor.pcbuilder.services.productsDetails.InterfaceTypeService;

import java.util.List;
import java.util.Optional;

@Service
public class InterfaceTypeServiceImpl implements InterfaceTypeService {

    @Autowired
    InterfaceTypeRepository interfaceTypeRepository;

    @Override
    public List<InterfaceType> findAll() {
        return interfaceTypeRepository.findAll();
    }

    @Override
    public Optional<InterfaceType> findById(Long id) {
        return interfaceTypeRepository.findById(id);
    }
}
