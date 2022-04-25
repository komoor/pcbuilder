package pl.komoor.pcbuilder.services.builds;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import pl.komoor.pcbuilder.models.builds.PcBuild;
import pl.komoor.pcbuilder.models.users.User;
import pl.komoor.pcbuilder.payload.request.builds.PcBuildRequest;
import pl.komoor.pcbuilder.services.dto.builds.PcBuildFilterDTO;

import java.util.List;
import java.util.Optional;

public interface PcBuildService {

    public void savePcBuild(PcBuildRequest pcBuildRequest);

    Page<PcBuild> findAll(Specification spec, Pageable pageRequest);

    Page<PcBuild> findByUserId(User id, Pageable pageRequest);

    List<PcBuild> findByUserId(User user);

    Optional<PcBuild> findById(Long id);

    boolean existsById(Long id);

    public void deletePcBuild(PcBuild pcBuild);

    PcBuildFilterDTO filterList();


}
