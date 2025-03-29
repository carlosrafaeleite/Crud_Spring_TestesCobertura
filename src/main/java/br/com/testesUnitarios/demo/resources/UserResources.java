package br.com.testesUnitarios.demo.resources;

import br.com.testesUnitarios.demo.DTO.UsersDTO;
import br.com.testesUnitarios.demo.domain.Users;
import br.com.testesUnitarios.demo.repositories.UserRepositories;
import br.com.testesUnitarios.demo.services.UserServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/initio")
public class UserResources {

    @Autowired
    private UserServices userServices;

    @Autowired
    private ModelMapper mapper;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsersDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(mapper.map(userServices.findById(id), UsersDTO.class));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<UsersDTO>> findAll(){
        return ResponseEntity.ok().body(
                userServices.findAll().stream()
                        .map(x -> mapper.map(x, UsersDTO.class)).collect(Collectors.toList())
        );
    }

    @PostMapping(value = "/insertUser")
    public ResponseEntity<UsersDTO> createUser(@RequestBody UsersDTO obj){
            return ResponseEntity.created(
                    ServletUriComponentsBuilder
                         .fromCurrentRequest().path("/{id}")
                              .buildAndExpand(userServices.create(obj).getId()).toUri()
            ).build();
    }
}
