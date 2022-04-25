package pl.komoor.pcbuilder.services.products.cases;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import pl.komoor.pcbuilder.models.products.Case;
import pl.komoor.pcbuilder.models.products.Cpu;
import pl.komoor.pcbuilder.payload.request.products.CaseRequest;
import pl.komoor.pcbuilder.payload.request.products.CpuRequest;

import java.util.List;
import java.util.Optional;

public interface CaseService {

    public void saveCase(CaseRequest caseRequest);

    public void updateCase(CaseRequest caseRequest, Long id);

    public void deleteCase(Case cases);

    List<Case> findAll();

    Page<Case> findAll(Specification spec, Pageable pageRequest);

    Optional<Case> findById(Long id);

    boolean existsById(Long id);

    boolean existsByModel(String model);

}
