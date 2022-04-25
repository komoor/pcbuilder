package pl.komoor.pcbuilder.services.impl.productsDetails.cases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.komoor.pcbuilder.models.productDetails.cases.CaseType;
import pl.komoor.pcbuilder.models.products.Case;
import pl.komoor.pcbuilder.repository.productsDetails.cases.CaseTypeRepository;
import pl.komoor.pcbuilder.services.productsDetails.cases.CaseTypeService;

import java.util.List;
import java.util.Optional;

@Service
public class CaseTypeServiceImpl implements CaseTypeService {

    @Autowired
    CaseTypeRepository caseTypeRepository;

    @Override
    public List<CaseType> findAll() {
        return caseTypeRepository.findAll();
    }

    @Override
    public Optional<CaseType> findById(Long id) {
        return caseTypeRepository.findById(id);
    }
}
