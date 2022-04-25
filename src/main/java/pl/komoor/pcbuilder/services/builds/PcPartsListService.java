package pl.komoor.pcbuilder.services.builds;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.komoor.pcbuilder.models.builds.PcPartsList;
import pl.komoor.pcbuilder.models.users.User;
import pl.komoor.pcbuilder.payload.request.builds.PcPartsListRequest;

import java.util.List;
import java.util.Optional;

public interface PcPartsListService {

    public void savePcPartsList(PcPartsListRequest pcPartsListRequest);

    List<PcPartsList> findAll();

    Page<PcPartsList> findAll(Pageable pageRequest);

    Page<PcPartsList> findByUserId(User id, Pageable pageRequest);

    Optional<PcPartsList> findById(Long id);

    boolean existsById(Long id);

    public void deletePcPartsList(PcPartsList pcPartsList);


}
