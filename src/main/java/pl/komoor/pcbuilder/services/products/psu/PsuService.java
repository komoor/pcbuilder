package pl.komoor.pcbuilder.services.products.psu;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import pl.komoor.pcbuilder.models.products.Psu;
import pl.komoor.pcbuilder.payload.request.products.PsuRequest;

import java.util.List;
import java.util.Optional;

public interface PsuService {


    public void savePsu(PsuRequest psuRequest);

    public void updatePsu(PsuRequest psuRequest, Long id);

    public void deletePsu(Psu psu);

    List<Psu> findAll();

    Page<Psu> findAll(Specification spec, Pageable pageRequest);

    Optional<Psu> findById(Long id);

    boolean existsById(Long id);

    boolean existsByModel(String model);

}
