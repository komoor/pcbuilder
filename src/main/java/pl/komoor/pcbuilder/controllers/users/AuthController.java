package pl.komoor.pcbuilder.controllers.users;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.komoor.pcbuilder.models.enums.ERole;
import pl.komoor.pcbuilder.models.users.Role;
import pl.komoor.pcbuilder.models.users.User;
import pl.komoor.pcbuilder.payload.request.auth.ChangePasswordRequest;
import pl.komoor.pcbuilder.payload.request.auth.LoginRequest;
import pl.komoor.pcbuilder.payload.request.auth.SignupRequest;
import pl.komoor.pcbuilder.payload.response.basic.ErrorResponse;
import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.payload.response.users.JwtResponse;
import pl.komoor.pcbuilder.payload.response.basic.AppResponse;
import pl.komoor.pcbuilder.repository.users.RoleRepository;
import pl.komoor.pcbuilder.repository.users.UserRepository;
import pl.komoor.pcbuilder.security.jwt.JwtUtils;
import pl.komoor.pcbuilder.services.dto.users.UserDetailsDTO;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<? extends AppResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsDTO userDetails = (UserDetailsDTO) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt,
												 userDetails.getId(),
												 userDetails.getUsername(),
												 userDetails.getEmail(),
												 userDetails.getAvatarUrl(),
												 roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new ErrorResponse("Błąd: Nazwa użytkownika jest zajęta!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new ErrorResponse("Błąd: Adres mailowy jest zajęty!"));
		}

		User user = new User(signUpRequest.getUsername(),
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
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

		user.setRoles(roles);
		userRepository.save(user);


		return ResponseEntity.ok(new SuccessResponse("Użytkownik pomyślnie zarejestrowany"));
	}

	@PatchMapping("/changePassword")
	public ResponseEntity<?> changeUserPassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) {


		Optional<User> user = userRepository.findById(changePasswordRequest.getUserId());


		if(user.isPresent()) {

			String encodedPassword = encoder.encode(changePasswordRequest.getPassword());

			if(encoder.matches(changePasswordRequest.getPassword(), user.get().getPassword())) {

				return ResponseEntity.ok(new ErrorResponse("Nowe hasło jest takie samo jak poprzednie!"));
			} else {

				user.get().setPassword(encodedPassword);

				userRepository.save(user.get());

				return ResponseEntity.ok(new SuccessResponse("Pomyślnie zmieniono hasło użytkownika."));
			}

		} else {

			return ResponseEntity.ok(new ErrorResponse("Nie znaleziono użytkownika!"));
		}

	}

}
