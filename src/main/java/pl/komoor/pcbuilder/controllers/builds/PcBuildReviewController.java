package pl.komoor.pcbuilder.controllers.builds;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.komoor.pcbuilder.models.builds.PcBuildReview;
import pl.komoor.pcbuilder.models.products.CpuCooler;
import pl.komoor.pcbuilder.payload.request.builds.PcBuildReviewCheckRequest;
import pl.komoor.pcbuilder.payload.request.builds.PcBuildReviewRequest;
import pl.komoor.pcbuilder.payload.response.basic.AppResponse;
import pl.komoor.pcbuilder.payload.response.basic.ErrorResponse;
import pl.komoor.pcbuilder.payload.response.basic.PageMeta;
import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.payload.response.builds.PcBuildReviewCheckResponse;
import pl.komoor.pcbuilder.payload.response.builds.PcBuildReviewListResponse;
import pl.komoor.pcbuilder.payload.response.builds.PcBuildReviewStatsResponse;
import pl.komoor.pcbuilder.payload.response.products.ProductReview.ProductReviewCheckResponse;
import pl.komoor.pcbuilder.services.builds.PcBuildReviewService;
import pl.komoor.pcbuilder.services.dto.builds.PcBuildReviewCheckDTO;
import pl.komoor.pcbuilder.services.dto.builds.PcBuildReviewDTO;
import pl.komoor.pcbuilder.services.dto.productReview.ProductReviewCheckDTO;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/pcBuildReview")
public class PcBuildReviewController {

    @Autowired
    PcBuildReviewService pcBuildReviewService;


    @PostMapping("/")
    public ResponseEntity<AppResponse> addPcBuildReview(@Valid @RequestBody PcBuildReviewRequest pcBuildReviewRequest) {

        pcBuildReviewService.savePcBuildReview(pcBuildReviewRequest);

        return ResponseEntity.ok(new SuccessResponse("Pomyślnie dodano komentarz"));
    }

    @PostMapping("/check")
    public ResponseEntity<AppResponse> checkIfPcBuildReviewExistByUserId(@Valid @RequestBody PcBuildReviewCheckRequest pcBuildReviewCheckRequest) {

        Boolean checkIfExists;

        checkIfExists = pcBuildReviewService.existsPcBuildReview(pcBuildReviewCheckRequest.getUserId(), pcBuildReviewCheckRequest.getPcBuildId());

        PcBuildReviewCheckDTO pcBuildReviewCheckDTO = new PcBuildReviewCheckDTO(checkIfExists);

        return new ResponseEntity<>(new PcBuildReviewCheckResponse(pcBuildReviewCheckDTO), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<AppResponse> getAllPcBuildReviews(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "page_size", defaultValue = "5") int pageSize,
            @RequestParam(value = "sort_by", defaultValue = "id") String sortBy,
            HttpServletRequest request) {

        Pageable pageable = getPageable(page, pageSize, sortBy);
        Page<PcBuildReview> pcBuildReviews = pcBuildReviewService.findAllPcBuildReviews(pageable);

        List<PcBuildReviewDTO> pcBuildReviewDTOList = pcBuildReviews.stream().map(PcBuildReviewDTO::build).collect(Collectors.toList());

        return new ResponseEntity<>(new PcBuildReviewListResponse(pcBuildReviewDTOList, PageMeta.build(pcBuildReviews, request.getRequestURI())), HttpStatus.OK);
    }


    @GetMapping("/pcBuildId/{pcBuildId}")
    public ResponseEntity<AppResponse> getAllPcBuildReviewsByPcBuildId(
            @PathVariable Long pcBuildId,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "page_size", defaultValue = "5") int pageSize,
            @RequestParam(value = "sort_by", defaultValue = "id") String sortBy,
            HttpServletRequest request) {

        Pageable pageable = getPageable(page, pageSize, sortBy);
        Page<PcBuildReview> pcBuildReviews = pcBuildReviewService.findAllPcBuildReviewsByPcBuildId(pcBuildId, pageable);

        List<PcBuildReviewDTO> pcBuildReviewDTOList = pcBuildReviews.stream().map(PcBuildReviewDTO::build).collect(Collectors.toList());

        return new ResponseEntity<>(new PcBuildReviewListResponse(pcBuildReviewDTOList, PageMeta.build(pcBuildReviews, request.getRequestURI())), HttpStatus.OK);
    }

    @GetMapping("/userId/{userId}")
    public ResponseEntity<AppResponse> getAllPcBuildReviewsByUserId(
            @PathVariable Long userId,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "page_size", defaultValue = "5") int pageSize,
            @RequestParam(value = "sort_by", defaultValue = "id") String sortBy,
            HttpServletRequest request) {

        Pageable pageable = getPageable(page, pageSize, sortBy);
        Page<PcBuildReview> pcBuildReviews = pcBuildReviewService.findAllPcBuildReviewsByUserId(userId, pageable);

        List<PcBuildReviewDTO> pcBuildReviewDTOList = pcBuildReviews.stream().map(PcBuildReviewDTO::build).collect(Collectors.toList());

        return new ResponseEntity<>(new PcBuildReviewListResponse(pcBuildReviewDTOList, PageMeta.build(pcBuildReviews, request.getRequestURI())), HttpStatus.OK);
    }

    @GetMapping("/pcBuildId/{pcBuildId}/stats")
    public ResponseEntity<AppResponse> getAllProductReviewsByProductIdStats(
            @PathVariable Long pcBuildId,
            HttpServletRequest request) {

        return new ResponseEntity<>(new PcBuildReviewStatsResponse(pcBuildReviewService.getPcBuildReviewStatsByPcBuildId(pcBuildId)), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER') OR hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<AppResponse> deletePcBuildReview(@PathVariable Long id) {

        Optional<PcBuildReview> pcBuildReview = pcBuildReviewService.findById(id);

        if (pcBuildReview.isPresent()) {

            pcBuildReviewService.deletePcBuildReview(pcBuildReview.get());
            return ResponseEntity.ok(new SuccessResponse("Pomyślnie usunięto komentarz"));
        } else {
            return new ResponseEntity<>(new ErrorResponse("Nie znaleziono komentarza do usunięcia."), HttpStatus.NOT_FOUND);
        }
    }

    private Pageable getPageable(int page, int pageSize, String sortBy) {
        Sort.Direction direction;

        if (page <= 0)
            page = 1;

        if (pageSize <= 0)
            pageSize = 5;

        if(sortBy.substring(0,1).equals("-")) {
            direction = Sort.Direction.DESC;
            sortBy = sortBy.substring(1);
        } else {
            direction = Sort.Direction.ASC;
        }

        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, direction, sortBy);
        return pageRequest;
    }

}
