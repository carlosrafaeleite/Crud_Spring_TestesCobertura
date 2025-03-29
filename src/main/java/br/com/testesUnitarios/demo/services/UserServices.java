package br.com.testesUnitarios.demo.services;

import br.com.testesUnitarios.demo.domain.Users;

import java.util.List;

public interface UserServices {

    Users findById(Long id);
    List<Users> findAll();

}
