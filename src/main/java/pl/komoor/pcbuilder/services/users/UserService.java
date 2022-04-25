package pl.komoor.pcbuilder.services.users;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import pl.komoor.pcbuilder.models.products.Case;
import pl.komoor.pcbuilder.models.users.User;
import pl.komoor.pcbuilder.payload.request.products.CaseRequest;
import pl.komoor.pcbuilder.payload.request.users.UserRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public UserDetails loadUserByUsername(String username);

    public void saveUser(User user);

    public void updateUser(UserRequest userRequest, Long id);

    public void deleteUser(User user);

    List<User> findAll();

    Page<User> findAll(Pageable pageRequest);

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    boolean existsById(Long id);


}
