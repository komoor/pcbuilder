package pl.komoor.pcbuilder.repository.builds;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.komoor.pcbuilder.models.builds.PcPartsList;
import pl.komoor.pcbuilder.models.products.Case;
import pl.komoor.pcbuilder.models.products.CpuCooler;
import pl.komoor.pcbuilder.models.users.User;

import java.util.List;
import java.util.Optional;

public interface PcPartsListRepository extends JpaRepository<PcPartsList, Long> {

    List<PcPartsList> findAll();

    Page<PcPartsList> findAll(Pageable pageRequest);

    Page<PcPartsList> findByUserId(User id, Pageable pageRequest);

    Optional<PcPartsList> findById(Long id);

    boolean existsById(Long id);


}
