package pl.komoor.pcbuilder.repository.users;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.komoor.pcbuilder.models.enums.ERole;
import pl.komoor.pcbuilder.models.users.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
