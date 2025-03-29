package br.com.testesUnitarios.demo.implement;


import br.com.testesUnitarios.demo.domain.Users;
import br.com.testesUnitarios.demo.services.exceptions.ObjectNotFoundException;
import br.com.testesUnitarios.demo.repositories.UserRepositories;
import br.com.testesUnitarios.demo.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserImplement implements UserServices {

    @Autowired
    UserRepositories userRepositories;

    @Override
    public Users findById(Long id) {
        Optional<Users> obj = userRepositories.findById(id);
        return obj.orElseThrow (()-> new ObjectNotFoundException("Objeto não encontrado"));
    }
};