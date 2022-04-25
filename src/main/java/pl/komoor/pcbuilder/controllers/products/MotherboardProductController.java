package pl.komoor.pcbuilder.controllers.products;

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
import pl.komoor.pcbuilder.models.products.Motherboard;
import pl.komoor.pcbuilder.payload.request.products.MotherboardRequest;
import pl.komoor.pcbuilder.payload.response.basic.AppResponse;
import pl.komoor.pcbuilder.payload.response.basic.ErrorResponse;
import pl.komoor.pcbuilder.payload.response.basic.PageMeta;
import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.payload.response.products.motherboard.MotherboardListResponse;
import pl.komoor.pcbuilder.payload.response.products.motherboard.MotherboardResponse;
import pl.komoor.pcbuilder.services.dto.products.MotherboardDTO;
import pl.komoor.pcbuilder.services.products.motherboard.MotherboardService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/products")
public class MotherboardProductController {

    @Autowired
    MotherboardService motherboardService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/motherboard")
    public ResponseEntity<AppResponse> addMotherboard(@Valid @RequestBody MotherboardRequest motherboardRequest) {


        if (motherboardService.existsByModel(motherboardRequest.getModel())) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponse("Wybrany model już istnieje w bazie"));
        }

        motherboardService.saveMotherboard(motherboardRequest);

        return ResponseEntity.ok(new SuccessResponse("Pomyślnie dodano produkt " + motherboardRequest.getModel()));
    }

    @GetMapping("/motherboard")
    public ResponseEntity<AppResponse> getAllMotherboards(
            @Join(path = "manufacturerId", alias="mId")
            @Join(path = "cpuSocketId", alias="csId")
            @Join(path = "motherboardFormFactorId", alias="mffId")
            @Join(path = "motherboardChipsetId", alias="mcId")
            @Join(path = "memoryTypeId", alias="mtId")
            @Join(path = "motherboardEthernetId", alias="meId")
            @Join(path = "productId", alias="prodId")
            @And({
                    @Spec(path = "prodId.id", params = "prodID", paramSeparator = ',', spec = In.class),
                    @Spec(path = "mId.id", params = "mID", paramSeparator = ',', spec = In.class),
                    @Spec(path = "csId.id", params = "csID", paramSeparator = ',', spec = In.class),
                    @Spec(path = "mffId.id", params = "mffID", paramSeparator = ',', spec = In.class),
                    @Spec(path = "mcId.id", params = "mcID", paramSeparator = ',', spec = In.class),
                    @Spec(path = "mtId.id", params = "mtID", paramSeparator = ',', spec = In.class),
                    @Spec(path = "meId.id", params = "meID", paramSeparator = ',', spec = In.class),
                    @Spec(path = "memoryMax", params = "memoryMaxGT", paramSeparator = ',', spec = GreaterThanOrEqual.class),
                    @Spec(path = "memoryMax", params = "memoryMaxLT", paramSeparator = ',', spec = LessThanOrEqual.class),
                    @Spec(path = "memorySlots", params = "memorySlotsGT", paramSeparator = ',', spec = GreaterThanOrEqual.class),
                    @Spec(path = "memorySlots", params = "memorySlotsLT", paramSeparator = ',', spec = LessThanOrEqual.class),
                    @Spec(path = "m2Slots", params = "m2SlotsGT", paramSeparator = ',', spec = GreaterThanOrEqual.class),
                    @Spec(path = "m2Slots", params = "m2SlotsLT", paramSeparator = ',', spec = LessThanOrEqual.class),
                    @Spec(path = "pcieX16Slots", params = "pcieX16SlotsGT", paramSeparator = ',', spec = GreaterThanOrEqual.class),
                    @Spec(path = "pcieX16Slots", params = "pcieX16SlotsLT", paramSeparator = ',', spec = LessThanOrEqual.class),
                    @Spec(path = "pcieX8Slots", params = "pcieX8SlotsGT", paramSeparator = ',', spec = GreaterThanOrEqual.class),
                    @Spec(path = "pcieX8Slots", params = "pcieX8SlotsLT", paramSeparator = ',', spec = LessThanOrEqual.class),
                    @Spec(path = "pcieX4Slots", params = "pcieX4SlotsGT", paramSeparator = ',', spec = GreaterThanOrEqual.class),
                    @Spec(path = "pcieX4Slots", params = "pcieX4SlotsLT", paramSeparator = ',', spec = LessThanOrEqual.class),
                    @Spec(path = "pcieX1Slots", params = "pcieX1SlotsGT", paramSeparator = ',', spec = GreaterThanOrEqual.class),
                    @Spec(path = "pcieX1Slots", params = "pcieX1SlotsLT", paramSeparator = ',', spec = LessThanOrEqual.class),
                    @Spec(path = "pciSlots", params = "pciSlotsGT", paramSeparator = ',', spec = GreaterThanOrEqual.class),
                    @Spec(path = "pciSlots", params = "pciSlotsLT", paramSeparator = ',', spec = LessThanOrEqual.class),
                    @Spec(path = "sata3GbPorts", params = "sata3GbPortsGT", paramSeparator = ',', spec = GreaterThanOrEqual.class),
                    @Spec(path = "sata3GbPorts", params = "sata3GbPortsLT", paramSeparator = ',', spec = LessThanOrEqual.class),
                    @Spec(path = "sata6GbPorts", params = "sata6GbPortsGT", paramSeparator = ',', spec = GreaterThanOrEqual.class),
                    @Spec(path = "sata6GbPorts", params = "sata6GbPortsLT", paramSeparator = ',', spec = LessThanOrEqual.class),
                    @Spec(path = "sataExpressPorts", params = "sataExpressPortsGT", paramSeparator = ',', spec = GreaterThanOrEqual.class),
                    @Spec(path = "sataExpressPorts", params = "sataExpressPortsLT", paramSeparator = ',', spec = LessThanOrEqual.class),
                    @Spec(path = "usb20Headers", params = "usb20HeadersGT", paramSeparator = ',', spec = GreaterThanOrEqual.class),
                    @Spec(path = "usb20Headers", params = "usb20HeadersLT", paramSeparator = ',', spec = LessThanOrEqual.class),
                    @Spec(path = "usb32gen1Headers", params = "usb32gen1HeadersGT", paramSeparator = ',', spec = GreaterThanOrEqual.class),
                    @Spec(path = "usb32gen1Headers", params = "usb32gen1HeadersLT", paramSeparator = ',', spec = LessThanOrEqual.class),
                    @Spec(path = "usb32gen2Headers", params = "usb32gen2HeadersGT", paramSeparator = ',', spec = GreaterThanOrEqual.class),
                    @Spec(path = "usb32gen2Headers", params = "usb32gen2HeadersLT", paramSeparator = ',', spec = LessThanOrEqual.class),
            }) Specification<Motherboard> spec,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "page_size", defaultValue = "10") int pageSize,
            @RequestParam(value = "sort_by", defaultValue = "id") String sortBy,
            HttpServletRequest request) {

        Pageable pageable = getPageable(page, pageSize, sortBy);
        Page<Motherboard> motherboards = motherboardService.findAll(spec, pageable);

        List<MotherboardDTO> motherboardDTOList = motherboards.stream().map(MotherboardDTO::build).collect(Collectors.toList());

        return new ResponseEntity<>(new MotherboardListResponse(motherboardDTOList, PageMeta.build(motherboards, request.getRequestURI())), HttpStatus.OK);
    }

    @GetMapping("/motherboard/{id}")
    public ResponseEntity<AppResponse> getMotherboardById(@PathVariable Long id,
                                                     HttpServletRequest request) {

        Optional<Motherboard> motherboard = motherboardService.findById(id);


        if (motherboard.isPresent()) {

            Optional<MotherboardDTO> motherboardDTO = motherboard.map(MotherboardDTO::build);

            return new ResponseEntity<>(new MotherboardResponse(motherboardDTO.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ErrorResponse("Nie znaleziono żądanej płyty głownej."), HttpStatus.NOT_FOUND);
        }

    }


    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/motherboard/{id}")
    public ResponseEntity<?> updateMotherboard(@PathVariable Long id, @Valid @RequestBody MotherboardRequest motherboardRequest) {

        if(!motherboardService.existsById(id)){
            return new ResponseEntity<>(new ErrorResponse("Nie znaleziono żądanej płyty głownej"), HttpStatus.NOT_FOUND);
        }

        motherboardService.updateMotherboard(motherboardRequest, id);

        return ResponseEntity.ok(new SuccessResponse("Aktualizacja przebiegła pomyślnie"));

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/motherboard/{id}")
    public ResponseEntity<AppResponse> deleteMotherboard(@PathVariable Long id) {

        Optional<Motherboard> motherboard = motherboardService.findById(id);

        if (motherboard.isPresent()) {
            motherboardService.deleteMotherboard(motherboard.get());
            return ResponseEntity.ok(new SuccessResponse("Płyta główna pomyślnie usunięta."));
        } else {
            return new ResponseEntity<>(new ErrorResponse("Nie znaleziono żądanej płyty głownej"), HttpStatus.NOT_FOUND);
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
