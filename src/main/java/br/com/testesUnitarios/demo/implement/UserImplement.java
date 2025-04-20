package br.com.testesUnitarios.demo.implement;


import br.com.testesUnitarios.demo.DTO.UsersDTO;
import br.com.testesUnitarios.demo.domain.Users;
import br.com.testesUnitarios.demo.services.exceptions.DataIntegrityViolationException;
import br.com.testesUnitarios.demo.services.exceptions.ObjectNotFoundException;
import br.com.testesUnitarios.demo.repositories.UserRepositories;
import br.com.testesUnitarios.demo.services.UserServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserImplement implements UserServices {

    @Autowired
    UserRepositories userRepositories;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Users findById(Long id) {
        Optional<Users> obj = userRepositories.findById(id);
        return obj.orElseThrow (()-> new ObjectNotFoundException("Objeto não encontrado"));
    }

    @Override
    public List<Users> findAll() {
        return userRepositories.findAll();
    }

    @Override
    public Users create(UsersDTO obj) {
        findByEmail(obj);
        return userRepositories.save(mapper.map(obj, Users.class));
    }

    @Override
    public Users update(UsersDTO obj) {
        findByEmail(obj);
        return userRepositories.save(mapper.map(obj, Users.class));
    }

    @Override
    public void delete(Long id) {
        findById(id);
        userRepositories.deleteById(id);
    }

    public void findByEmail(UsersDTO obj){
        Optional<Users> users = userRepositories.findByEmail(obj.getEmail());
        if (users.isPresent() && !users.get().getId().equals(obj.getId())){
            throw new DataIntegrityViolationException("email ja cadastrado");
        }
    }
};