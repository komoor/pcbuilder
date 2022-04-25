package pl.komoor.pcbuilder.services.impl.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.komoor.pcbuilder.models.enums.ERole;
import pl.komoor.pcbuilder.models.products.Cpu;
import pl.komoor.pcbuilder.models.products.Product;
import pl.komoor.pcbuilder.models.tools.FileToDatabase;
import pl.komoor.pcbuilder.models.users.Role;
import pl.komoor.pcbuilder.models.users.User;
import pl.komoor.pcbuilder.payload.request.users.UserRequest;
import pl.komoor.pcbuilder.repository.users.RoleRepository;
import pl.komoor.pcbuilder.repository.users.UserRepository;
import pl.komoor.pcbuilder.services.dto.users.UserDetailsDTO;
import pl.komoor.pcbuilder.services.tools.FileToDatabaseService;
import pl.komoor.pcbuilder.services.users.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    FileToDatabaseService fileToDatabaseService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Użytkownik nie został odnaleziony w systemie: " + username));
        return UserDetailsDTO.build(user);
    }

    @Override
    public void saveUser(User user) {

        userRepository.save(user);

    }

    @Override
    public void updateUser(UserRequest userRequest, Long id) {

        Optional<User> user = userRepository.findById(id);

        if(userRequest.getAvatarId().isEmpty()) {
            user.get().setFileToDatabase(null);
        } else {

            Optional<FileToDatabase> fileToDatabase = fileToDatabaseService.findById(userRequest.getAvatarId());

            if(fileToDatabase.isPresent()) {

                fileToDatabase.ifPresent(user.get()::setFileToDatabase);
            } else
            {
                user.get().setFileToDatabase(null);
            }
        }


        Set<String> strRoles = userRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "ROLE_ADMIN":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "ROLE_MODERATOR":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.get().setRoles(roles);
        user.get().setEmail(userRequest.getEmail());
        user.get().setUsername(userRequest.getUsername());


        userRepository.save(user.get());

    }


    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Page<User> findAll(Pageable pageRequest) {
        return userRepository.findAll(pageRequest);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }


}
