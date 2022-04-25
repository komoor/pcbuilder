package pl.komoor.pcbuilder.controllers.productsDetails;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.komoor.pcbuilder.models.productDetails.Category;
import pl.komoor.pcbuilder.payload.response.basic.AppResponse;
import pl.komoor.pcbuilder.payload.response.basic.ErrorResponse;
import pl.komoor.pcbuilder.payload.response.productsDetails.CategoryListResponse;
import pl.komoor.pcbuilder.payload.response.productsDetails.CategoryResponse;
import pl.komoor.pcbuilder.services.dto.productsDetails.CategoryDTO;
import pl.komoor.pcbuilder.services.productsDetails.CategoryService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;


    @GetMapping("/")

    public ResponseEntity<AppResponse> getAllCategories(HttpServletRequest request) {

        List<CategoryDTO> categories = categoryService.findAll();

        return new ResponseEntity<>(new CategoryListResponse(categories), HttpStatus.OK);


    }

    @GetMapping("/{id}")
    public ResponseEntity<AppResponse> getCategoryById(@PathVariable Long id,
                                                       HttpServletRequest request) {

        Optional<Category> category = categoryService.findById(id);


        if (category.isPresent()) {

            Optional<CategoryDTO> categoryDTO = category.map(CategoryDTO::build);


            return new ResponseEntity<>(new CategoryResponse(categoryDTO.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ErrorResponse("Nie podano kategorii."), HttpStatus.NOT_FOUND);
        }

    }





}
