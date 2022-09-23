package br.com.clarea.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.clarea.entity.User;

@Repository
public interface UserReadRepository extends JpaRepository<User, Long>, UserReadRepositoryCustom {
	Optional<User> findByEmail(String email);

	Boolean existsByEmail(String email);
}
