package br.com.testesUnitarios.demo.repositories;

import br.com.testesUnitarios.demo.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositories extends JpaRepository<Users, Long>  {
    Optional<Users>findByEmail(String email);
}
