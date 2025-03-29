package br.com.testesUnitarios.demo.repositories;

import br.com.testesUnitarios.demo.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositories extends JpaRepository<Users, Long>  {
}
