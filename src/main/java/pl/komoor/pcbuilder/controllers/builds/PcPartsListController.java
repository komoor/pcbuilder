package pl.komoor.pcbuilder.controllers.builds;

import net.kaczmarzyk.spring.data.jpa.domain.GreaterThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.In;
import net.kaczmarzyk.spring.data.jpa.domain.LessThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.komoor.pcbuilder.models.builds.PcPartsList;
import pl.komoor.pcbuilder.models.products.Case;
import pl.komoor.pcbuilder.models.users.User;
import pl.komoor.pcbuilder.payload.request.builds.PcPartsListRequest;
import pl.komoor.pcbuilder.payload.request.products.CaseRequest;
import pl.komoor.pcbuilder.payload.response.basic.AppResponse;
import pl.komoor.pcbuilder.payload.response.basic.ErrorResponse;
import pl.komoor.pcbuilder.payload.response.basic.PageMeta;
import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.payload.response.builds.PcPartsListListResponse;
import pl.komoor.pcbuilder.payload.response.builds.PcPartsListResponse;
import pl.komoor.pcbuilder.payload.response.products.cases.CaseListResponse;
import pl.komoor.pcbuilder.payload.response.products.cases.CaseResponse;
import pl.komoor.pcbuilder.repository.users.UserRepository;
import pl.komoor.pcbuilder.services.builds.PcPartsListService;
import pl.komoor.pcbuilder.services.dto.builds.PcPartsListDTO;
import pl.komoor.pcbuilder.services.dto.products.CaseDTO;
import pl.komoor.pcbuilder.services.users.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/pcpartslist")
public class PcPartsListController {

    @Autowired
    PcPartsListService pcPartsListService;

    @Autowired
    UserRepository userRepository;

    @PreAuthorize("hasRole('USER') OR hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<AppResponse> addPcPartsList(@Valid @RequestBody PcPartsListRequest pcPartsListRequest) {

        pcPartsListService.savePcPartsList(pcPartsListRequest);

        return ResponseEntity.ok(new SuccessResponse("Pomyślnie dodano listę " + pcPartsListRequest.getListName()));
    }

    @GetMapping("/")
    public ResponseEntity<AppResponse> getAllPcPartsList(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "page_size", defaultValue = "10") int pageSize,
            @RequestParam(value = "sort_by", defaultValue = "id") String sortBy,
            HttpServletRequest request) {

        Pageable pageable = getPageable(page, pageSize, sortBy);
        Page<PcPartsList> pcPartsLists = pcPartsListService.findAll(pageable);

        List<PcPartsListDTO> pcPartsListDTOList = pcPartsLists.stream().map(PcPartsListDTO::build).collect(Collectors.toList());

        return new ResponseEntity<>(new PcPartsListListResponse(pcPartsListDTOList, PageMeta.build(pcPartsLists, request.getRequestURI())), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<AppResponse> getPcPartsListById(@PathVariable Long id,
                                                   HttpServletRequest request) {

        Optional<PcPartsList> pcPartsList = pcPartsListService.findById(id);


        if (pcPartsList.isPresent()) {

            Optional<PcPartsListDTO> pcPartsListDTO = pcPartsList.map(PcPartsListDTO::build);

            return new ResponseEntity<>(new PcPartsListResponse(pcPartsListDTO.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ErrorResponse("Nie znaleziono listy."), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/user/{id}")
    public ResponseEntity<AppResponse> getAllPcPartsListByUser(
            @PathVariable Long id,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "page_size", defaultValue = "10") int pageSize,
            @RequestParam(value = "sort_by", defaultValue = "id") String sortBy,
            HttpServletRequest request) {

        Pageable pageable = getPageable(page, pageSize, sortBy);
        User user = userRepository.findById(id).get();
        Page<PcPartsList> pcPartsLists = pcPartsListService.findByUserId(user, pageable);

        List<PcPartsListDTO> pcPartsListDTOList = pcPartsLists.stream().map(PcPartsListDTO::build).collect(Collectors.toList());

        return new ResponseEntity<>(new PcPartsListListResponse(pcPartsListDTOList, PageMeta.build(pcPartsLists, request.getRequestURI())), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER') OR hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<AppResponse> deletePcPartsList(@PathVariable Long id) {

        Optional<PcPartsList> pcPartsList = pcPartsListService.findById(id);

        if (pcPartsList.isPresent()) {

            pcPartsListService.deletePcPartsList(pcPartsList.get());
            return ResponseEntity.ok(new SuccessResponse("Lista pomyślnie usunięta."));
        } else {
            return new ResponseEntity<>(new ErrorResponse("Nie znaleziono listy do usunięcia."), HttpStatus.NOT_FOUND);
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
