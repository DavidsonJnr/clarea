package br.com.clarea.repository.role;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.clarea.entity.Role;
import br.com.clarea.entity.constants.ERole;

@Repository
public interface RoleReadRepository extends JpaRepository<Role, Long>, RoleReadRepositoryCustom {
	Optional<Role> findByRole(ERole role);
}
