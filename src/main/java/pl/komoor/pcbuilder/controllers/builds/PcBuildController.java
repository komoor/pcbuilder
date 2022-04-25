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
import pl.komoor.pcbuilder.models.builds.PcBuild;
import pl.komoor.pcbuilder.models.builds.PcBuildReview;
import pl.komoor.pcbuilder.models.products.Cpu;
import pl.komoor.pcbuilder.models.products.CpuCooler;
import pl.komoor.pcbuilder.models.users.User;
import pl.komoor.pcbuilder.payload.request.builds.PcBuildRequest;
import pl.komoor.pcbuilder.payload.response.basic.AppResponse;
import pl.komoor.pcbuilder.payload.response.basic.ErrorResponse;
import pl.komoor.pcbuilder.payload.response.basic.PageMeta;
import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.payload.response.builds.PcBuildFilterResponse;
import pl.komoor.pcbuilder.payload.response.builds.PcBuildListResponse;
import pl.komoor.pcbuilder.payload.response.builds.PcBuildResponse;
import pl.komoor.pcbuilder.payload.response.products.cpuCooler.CpuCoolerResponse;
import pl.komoor.pcbuilder.repository.users.UserRepository;
import pl.komoor.pcbuilder.services.builds.PcBuildService;
import pl.komoor.pcbuilder.services.dto.builds.PcBuildDTO;
import pl.komoor.pcbuilder.services.dto.builds.PcBuildFilterDTO;
import pl.komoor.pcbuilder.services.dto.products.CpuCoolerDTO;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/pcbuild")
public class PcBuildController {

    @Autowired
    PcBuildService pcBuildService;

    @Autowired
    UserRepository userRepository;

    @PreAuthorize("hasRole('USER') OR hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<AppResponse> addPcBuild(@Valid @RequestBody PcBuildRequest pcBuildRequest) {

        pcBuildService.savePcBuild(pcBuildRequest);

        return ResponseEntity.ok(new SuccessResponse("Pomyślnie dodano zestaw " + pcBuildRequest.getBuildName()));
    }


    @GetMapping("/")
    public ResponseEntity<AppResponse> getAllPcBuilds(
            @Join(path = "cpuId", alias="cpu")
            @Join(path = "cpuCoolerId", alias="cpuCooler")
            @Join(path = "motherboardId", alias="motherboard")
            @Join(path = "gpuId", alias="gpu")
            @Join(path = "psuId", alias="psu")
            @Join(path = "memory", alias="memory")
            @Join(path = "storage", alias="storage")
            @Join(path = "caseId", alias="case")
            @And({
                    @Spec(path = "cpu.id", params = "cpu", paramSeparator = ',', spec = In.class),
                    @Spec(path = "cpuCooler.id", params = "cpuCooler", paramSeparator = ',', spec = In.class),
                    @Spec(path = "motherboard.id", params = "motherboard", paramSeparator = ',', spec = In.class),
                    @Spec(path = "gpu.id", params = "gpu", paramSeparator = ',', spec = In.class),
                    @Spec(path = "psu.id", params = "psu", paramSeparator = ',', spec = In.class),
                    @Spec(path = "memory.id", params = "memory", paramSeparator = ',', spec = In.class),
                    @Spec(path = "storage.id", params = "storage", paramSeparator = ',', spec = In.class),
                    @Spec(path = "case.id", params = "case", paramSeparator = ',', spec = In.class)
            }) Specification<PcBuild> spec,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "page_size", defaultValue = "8") int pageSize,
            @RequestParam(value = "sort_by", defaultValue = "id") String sortBy,
            HttpServletRequest request) {

        Pageable pageable = getPageable(page, pageSize, sortBy);
        Page<PcBuild> pcBuilds = pcBuildService.findAll(spec, pageable);

        List<PcBuildDTO> pcBuildDTOList = pcBuilds.stream().map(PcBuildDTO::build).collect(Collectors.toList());

            return new ResponseEntity<>(new PcBuildListResponse(pcBuildDTOList, PageMeta.build(pcBuilds, request.getRequestURI())), HttpStatus.OK);

    }


    @GetMapping("/{id}")
    public ResponseEntity<AppResponse> getPcBuildById(@PathVariable Long id,
                                                        HttpServletRequest request) {

        Optional<PcBuild> pcBuild = pcBuildService.findById(id);

        if (pcBuild.isPresent()) {

            Optional<PcBuildDTO> pcBuildDTO = pcBuild.map(PcBuildDTO::build);
            return new ResponseEntity<>(new PcBuildResponse(pcBuildDTO.get()), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(new ErrorResponse("Nie znaleziono żądanego zestawu."), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/user/{id}")
    public ResponseEntity<AppResponse> getAllPcBuildsByUser(
            @PathVariable Long id,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "page_size", defaultValue = "8") int pageSize,
            @RequestParam(value = "sort_by", defaultValue = "id") String sortBy,
            HttpServletRequest request) {

        Pageable pageable = getPageable(page, pageSize, sortBy);
        User user = userRepository.findById(id).get();
        Page<PcBuild> pcBuilds = pcBuildService.findByUserId(user, pageable);

        List<PcBuildDTO> pcBuildDTOList = pcBuilds.stream().map(PcBuildDTO::build).collect(Collectors.toList());

        return new ResponseEntity<>(new PcBuildListResponse(pcBuildDTOList, PageMeta.build(pcBuilds, request.getRequestURI())), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<AppResponse> getFilter(HttpServletRequest request) {

        PcBuildFilterDTO pcBuildFilterDTO = pcBuildService.filterList();

        return new ResponseEntity<>(new PcBuildFilterResponse(pcBuildFilterDTO), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER') OR hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<AppResponse> deletePcBuild(@PathVariable Long id) {

        Optional<PcBuild> pcBuild = pcBuildService.findById(id);

        if (pcBuild.isPresent()) {

            pcBuildService.deletePcBuild(pcBuild.get());
            return ResponseEntity.ok(new SuccessResponse("Pomyślnie usunięto zestaw"));
        } else {
            return new ResponseEntity<>(new ErrorResponse("Nie znaleziono zestawu do usunięcia."), HttpStatus.NOT_FOUND);
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
