package pl.komoor.pcbuilder.controllers.productsDetails;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.komoor.pcbuilder.models.productDetails.Category;
import pl.komoor.pcbuilder.models.productDetails.Manufacturer;
import pl.komoor.pcbuilder.payload.request.productsDetails.ManufacturerRequest;
import pl.komoor.pcbuilder.payload.response.basic.*;
import pl.komoor.pcbuilder.payload.response.productsDetails.ManufacturerListResponse;
import pl.komoor.pcbuilder.services.dto.productsDetails.ManufacturerDTO;
import pl.komoor.pcbuilder.services.productsDetails.CategoryService;
import pl.komoor.pcbuilder.services.productsDetails.ManufacturerService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/manufacturer")
public class ManufacturerController {

    @Autowired
    ManufacturerService manufacturerService;

    @Autowired
    CategoryService categoryService;


    @GetMapping("/")
    public ResponseEntity<AppResponse> getAll(@RequestParam(value = "categoryId", required = false) Long categoryId, HttpServletRequest request) {


        if(categoryId != null) {

            Optional<Category> category = categoryService.findById(categoryId);

            if(category.isPresent()) {

                List<Manufacturer> manufacturers = manufacturerService.findAllByManufacturerCategory(category.get());

                if(!manufacturers.isEmpty())
                    return new ResponseEntity<>(new ManufacturerListResponse(manufacturers), HttpStatus.OK);

                return new ResponseEntity<>(new ErrorResponse("Not found manufacturer by category: " + categoryId), HttpStatus.NOT_FOUND);

            }
            return new ResponseEntity<AppResponse>(new ErrorResponse("Manufacturer category not found."), HttpStatus.NOT_FOUND);
        }

        List<Manufacturer> manufacturers = manufacturerService.findAllManufacturer();

        return new ResponseEntity<>(new ManufacturerListResponse(manufacturers), HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<?> addManufacturer(@Valid @RequestBody ManufacturerRequest manufacturerRequest) {


        if (manufacturerService.existsByManufacturerName(manufacturerRequest.getManufacturerName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponse("Manufacturer already exists."));
        }


        Manufacturer manufacturer = new Manufacturer(manufacturerRequest.getManufacturerName());

        Set<String> categoriesStr = manufacturerRequest.getCategories();

        Set<Category> categories = new HashSet<>();

        if (categoriesStr == null) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Categories not found."));
        } else {
            categoriesStr.forEach(item -> {
                        Category category = categoryService.findByCategoryName(item)
                                .orElseThrow(() -> new RuntimeException("Category not found."));
                        categories.add(category);

            });
        }

        manufacturer.setManufacturerCategory(categories);
        manufacturerService.saveManufacturer(manufacturer);

        return ResponseEntity.ok(new SuccessResponse("Manufacturer added succesfully."));
    }




}
