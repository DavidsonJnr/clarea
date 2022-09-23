package br.com.clarea.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.clarea.entity.User;

@Repository
public interface UserWriteRepository extends JpaRepository<User, Long>, UserWriteRepositoryCustom {

}
