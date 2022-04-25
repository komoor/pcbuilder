package pl.komoor.pcbuilder.controllers.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.komoor.pcbuilder.models.products.Product;
import pl.komoor.pcbuilder.models.tools.FileToDatabase;
import pl.komoor.pcbuilder.payload.response.basic.AppResponse;
import pl.komoor.pcbuilder.payload.response.basic.ErrorResponse;
import pl.komoor.pcbuilder.payload.response.basic.PageMeta;
import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.payload.response.tools.FileToDatabaseListResponse;
import pl.komoor.pcbuilder.payload.response.tools.FileToDatabaseResponse;
import pl.komoor.pcbuilder.services.dto.tools.FileToDatabaseDTO;
import pl.komoor.pcbuilder.services.products.product.ProductService;
import pl.komoor.pcbuilder.services.tools.FileToDatabaseService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/images")
public class FileToDatabaseController {

    @Autowired
    FileToDatabaseService fileToDatabaseService;

    @Autowired
    ProductService productService;


    @GetMapping("/")
    public AppResponse getAllImages(@RequestParam(value = "page", defaultValue = "1") int page,
                                   @RequestParam(value = "page_size", defaultValue = "10") int pageSize,
                                   @RequestParam(value = "sort_by", defaultValue = "id") String sortBy,
                                   HttpServletRequest request) {

        Pageable pageable = getPageable(page, pageSize, sortBy);

        Page<FileToDatabase> files = fileToDatabaseService.getAllFiles(pageable);

        List<FileToDatabaseDTO> fileToDatabaseDTOList = fileToDatabaseService.convertToDto(files);

        return new FileToDatabaseListResponse(fileToDatabaseDTOList, PageMeta.build(files, request.getRequestURI()));



    }

    @PreAuthorize("hasRole('USER') OR hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<AppResponse> deleteImage(@PathVariable String id) {

        Optional<FileToDatabase> file = fileToDatabaseService.findById(id);

        if (file.isPresent()) {

            Optional<Product> product = productService.findByImage(file.get());

            if(product.isPresent())
                product.get().setFileToDatabase(null);

            fileToDatabaseService.deleteFile(file.get());
            return ResponseEntity.ok(new SuccessResponse("Pomyślnie usunięto obraz."));
        } else {
            return new ResponseEntity<>(new ErrorResponse("Nie znaleziono obrazu do usuniecia."), HttpStatus.NOT_FOUND);
        }
    }


    @PreAuthorize("hasRole('USER') OR hasRole('ADMIN')")
    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        String message = "";


        try {

            if(file.isEmpty())
                return ResponseEntity.ok(new ErrorResponse("Nie wskazano pliku."));

            Optional<FileToDatabase> fileReturn = fileToDatabaseService.storeFile(file);

            Optional<FileToDatabaseDTO> fileDTO = fileReturn.map(FileToDatabaseDTO::build);

            return ResponseEntity.ok(new FileToDatabaseResponse(fileDTO.get()));

        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.ok(new ErrorResponse(message));

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable String id) {

        Optional<FileToDatabase> file = this.fileToDatabaseService.findById(id);

        FileToDatabase fileContent = file.get();

        return ResponseEntity.ok().body(fileContent.getData());


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
