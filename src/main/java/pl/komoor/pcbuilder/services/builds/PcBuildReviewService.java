package pl.komoor.pcbuilder.services.builds;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.komoor.pcbuilder.models.builds.PcBuildReview;
import pl.komoor.pcbuilder.payload.request.builds.PcBuildReviewRequest;
import pl.komoor.pcbuilder.services.dto.builds.PcBuildReviewStatsDTO;
import pl.komoor.pcbuilder.services.dto.productReview.ProductReviewStatsDTO;

import java.util.Optional;

public interface PcBuildReviewService {

    public void savePcBuildReview(PcBuildReviewRequest pcBuildReviewRequest);

    public void updatePcBuildReview(PcBuildReviewRequest pcBuildReviewRequest, Long id);

    public void deletePcBuildReview(PcBuildReview pcBuildReview);

    public Page<PcBuildReview> findAllPcBuildReviews(Pageable pageable);

    public Page<PcBuildReview> findAllPcBuildReviewsByPcBuildId(Long pcBuildId, Pageable pageable);

    public Page<PcBuildReview> findAllPcBuildReviewsByUserId(Long userId, Pageable pageable);

    public PcBuildReviewStatsDTO getPcBuildReviewStatsByPcBuildId(Long pcBuildId);

    Optional<PcBuildReview> findById(Long id);

    boolean existsPcBuildReview(Long userId, Long pcBuildId);

}
