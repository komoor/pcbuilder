package pl.komoor.pcbuilder.controllers.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import pl.komoor.pcbuilder.models.users.User;
import pl.komoor.pcbuilder.payload.request.products.CpuRequest;
import pl.komoor.pcbuilder.payload.request.users.UserRequest;
import pl.komoor.pcbuilder.payload.response.basic.PageMeta;
import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.payload.response.users.UserDetailsResponse;
import pl.komoor.pcbuilder.payload.response.users.UsersListResponse;
import pl.komoor.pcbuilder.payload.response.basic.AppResponse;
import pl.komoor.pcbuilder.payload.response.basic.ErrorResponse;
import pl.komoor.pcbuilder.repository.users.UserRepository;
import pl.komoor.pcbuilder.services.dto.users.UserDetailsDTO;
import pl.komoor.pcbuilder.services.users.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")

public class UsersController {
    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    public AppResponse getAllUsers(@RequestParam(value = "page", defaultValue = "1") int page,
                             @RequestParam(value = "page_size", defaultValue = "10") int pageSize,
                             @RequestParam(value = "sort_by", defaultValue = "id") String sortBy,
                             HttpServletRequest request) {

        Pageable pageable = getPageable(page, pageSize, sortBy);
        Page<User> users = this.userService.findAll(pageable);
        List<UserDetailsDTO> usersDTO = buildUsersDTO(users);
        return new UsersListResponse(usersDTO, PageMeta.build(users, request.getRequestURI()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppResponse> getUserById(@PathVariable("id") String id) {

        if(isNumeric(id)) {
            Long idLong;
            idLong = Long.parseLong(id);
            Optional<User> user = this.userService.findById(idLong);
            Optional<UserDetailsDTO> usersDTO = user.map(UserDetailsDTO::build);
            return usersDTO.<ResponseEntity<AppResponse>>map(userDetails -> new ResponseEntity<>(new UserDetailsResponse(userDetails), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new ErrorResponse("User not found."), HttpStatus.NOT_FOUND));
        } else {
            Optional<User> user = this.userService.findByUsername(id);
            Optional<UserDetailsDTO> usersDTO = user.map(UserDetailsDTO::build);
            return usersDTO.<ResponseEntity<AppResponse>>map(userDetails -> new ResponseEntity<>(new UserDetailsResponse(userDetails), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new ErrorResponse("User not found."), HttpStatus.NOT_FOUND));
        }

    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequest userRequest) {

        if(!userService.existsById(id)){
            return new ResponseEntity<>(new ErrorResponse("Nie znaleziono użytkownika"), HttpStatus.NOT_FOUND);
        }

        userService.updateUser(userRequest, id);

        return ResponseEntity.ok(new SuccessResponse("Aktualizacja przebiegła pomyślnie"));

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<AppResponse> deleteUser(@PathVariable("id") Long id) {
        Optional<User> user = this.userService.findById(id);
        if (user.isPresent()) {
            userService.deleteUser(user.get());
            return ResponseEntity.ok(new SuccessResponse("Użytkownik został usunięty."));
        } else {
            return new ResponseEntity<>(new ErrorResponse("User not found."), HttpStatus.NOT_FOUND);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AppResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(new ErrorResponse(errors) {
        }, HttpStatus.BAD_REQUEST);
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

    private List<UserDetailsDTO> buildUsersDTO(Page<User> users) {
        List<UserDetailsDTO> usersDTO = users.getContent().stream().map(UserDetailsDTO::build).collect(Collectors.toList());
        return usersDTO;
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

}
