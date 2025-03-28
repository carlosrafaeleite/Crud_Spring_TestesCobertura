package br.com.testesUnitarios.demo.services;

import br.com.testesUnitarios.demo.domain.Users;
import org.springframework.data.repository.Repository;

public interface UserServices extends Repository<Users, Integer> {
}
