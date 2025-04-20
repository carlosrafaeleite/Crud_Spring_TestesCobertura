package br.com.testesUnitarios.demo.services;

import br.com.testesUnitarios.demo.DTO.UsersDTO;
import br.com.testesUnitarios.demo.domain.Users;
import org.apache.catalina.User;

import java.util.List;

public interface UserServices {

    Users findById(Long id);
    List<Users> findAll();
    Users create(UsersDTO obj);
    Users update(UsersDTO obj);
    void delete(Long id);


}
