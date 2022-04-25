package pl.komoor.pcbuilder.services.impl.builds;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.komoor.pcbuilder.models.builds.PcBuild;
import pl.komoor.pcbuilder.models.builds.PcBuildReview;
import pl.komoor.pcbuilder.models.products.Product;
import pl.komoor.pcbuilder.models.users.User;
import pl.komoor.pcbuilder.payload.request.builds.PcBuildReviewRequest;
import pl.komoor.pcbuilder.repository.builds.PcBuildReviewRepository;
import pl.komoor.pcbuilder.services.builds.PcBuildReviewService;
import pl.komoor.pcbuilder.services.builds.PcBuildService;
import pl.komoor.pcbuilder.services.dto.builds.PcBuildReviewStatsDTO;
import pl.komoor.pcbuilder.services.dto.productReview.ProductReviewStatsDTO;
import pl.komoor.pcbuilder.services.users.UserService;

import java.util.Optional;

@Service
public class PcBuildReviewServiceImpl implements PcBuildReviewService {

    @Autowired
    PcBuildReviewRepository pcBuildReviewRepository;

    @Autowired
    UserService userService;

    @Autowired
    PcBuildService pcBuildService;


    @Override
    public void savePcBuildReview(PcBuildReviewRequest pcBuildReviewRequest) {

        Optional<User> user = userService.findById(pcBuildReviewRequest.getUserId());
        Optional<PcBuild> pcBuild = pcBuildService.findById(pcBuildReviewRequest.getPcBuildId());

        PcBuildReview pcBuildReview = new PcBuildReview();

        pcBuildReview.setReview(pcBuildReviewRequest.getReview());
        pcBuildReview.setRating(pcBuildReviewRequest.getRating());
        pcBuildReview.setUserId(user.get());
        pcBuildReview.setPcBuildId(pcBuild.get());

        pcBuildReviewRepository.save(pcBuildReview);

    }

    @Override
    public void updatePcBuildReview(PcBuildReviewRequest pcBuildReviewRequest, Long id) {

    }

    @Override
    public void deletePcBuildReview(PcBuildReview pcBuildReview) {

        pcBuildReviewRepository.delete(pcBuildReview);

    }

    @Override
    public Page<PcBuildReview> findAllPcBuildReviews(Pageable pageable) {
        return pcBuildReviewRepository.findAll(pageable);
    }

    @Override
    public Page<PcBuildReview> findAllPcBuildReviewsByPcBuildId(Long pcBuildId, Pageable pageable) {

        Optional<PcBuild> pcBuild = pcBuildService.findById(pcBuildId);

        return pcBuildReviewRepository.findByPcBuildId(pcBuild.get(), pageable);


    }

    @Override
    public Page<PcBuildReview> findAllPcBuildReviewsByUserId(Long userId, Pageable pageable) {

        Optional<User> user = userService.findById(userId);

        return pcBuildReviewRepository.findByUserId(user.get(), pageable);
    }

    @Override
    public PcBuildReviewStatsDTO getPcBuildReviewStatsByPcBuildId(Long pcBuildId) {

        Optional<PcBuild> pcBuild = pcBuildService.findById(pcBuildId);

        PcBuildReviewStatsDTO pcBuildReviewStatsDTO = new PcBuildReviewStatsDTO();

        pcBuildReviewStatsDTO.setRatingAvarage(pcBuildReviewRepository.getRatingAvarage(pcBuild.get()));
        pcBuildReviewStatsDTO.setRatingCount(pcBuildReviewRepository.getRatingCount(pcBuild.get()));

        return pcBuildReviewStatsDTO;
    }

    @Override
    public Optional<PcBuildReview> findById(Long id) {
        return pcBuildReviewRepository.findById(id);
    }

    @Override
    public boolean existsPcBuildReview(Long userId, Long pcBuildId) {

        Optional<User> user = userService.findById(userId);
        Optional<PcBuild> pcBuild = pcBuildService.findById(pcBuildId);

        return pcBuildReviewRepository.existsByUserIdAndPcBuildId(user.get(), pcBuild.get());
    }
}
