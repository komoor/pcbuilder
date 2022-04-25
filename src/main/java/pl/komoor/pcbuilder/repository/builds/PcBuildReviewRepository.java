package pl.komoor.pcbuilder.repository.builds;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.komoor.pcbuilder.models.builds.PcBuild;
import pl.komoor.pcbuilder.models.builds.PcBuildReview;
import pl.komoor.pcbuilder.models.products.Product;
import pl.komoor.pcbuilder.models.users.User;

public interface PcBuildReviewRepository extends JpaRepository<PcBuildReview, Long> {

    Page<PcBuildReview> findAll(Pageable pageRequest);

    Page<PcBuildReview> findByPcBuildId(PcBuild pcBuild, Pageable pageRequest);

    Page<PcBuildReview> findByUserId(User user, Pageable pageRequest);

    Boolean existsByUserIdAndPcBuildId(User user, PcBuild pcBuild);

    @Query("SELECT COALESCE(ROUND(AVG(pr.rating),2),0) FROM PcBuildReview pr WHERE pr.pcBuildId = :pcBuildId")
    Double getRatingAvarage(@Param("pcBuildId") PcBuild pcBuildId);

    @Query("SELECT COALESCE(COUNT(pr.rating),0) FROM PcBuildReview pr WHERE pr.pcBuildId = :pcBuildId")
    Integer getRatingCount(@Param("pcBuildId") PcBuild pcBuildId);

}
