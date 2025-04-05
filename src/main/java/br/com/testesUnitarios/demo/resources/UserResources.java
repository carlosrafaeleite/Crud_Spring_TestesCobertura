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

    public static final String ID = "{id}";

    @Autowired
    private UserServices userServices;

    @Autowired
    private ModelMapper mapper;

    @GetMapping(value = "/" + ID)
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
                         .fromCurrentRequest().path("/" + ID)
                              .buildAndExpand(userServices.create(obj).getId()).toUri()
            ).build();
    }

    @PutMapping(value = "/alterar/" + ID)
    public ResponseEntity<UsersDTO> update(@PathVariable Long id, @RequestBody UsersDTO obj){
        findById(id);
        obj.setId(id);
        return  ResponseEntity.ok().body(mapper.map(userServices.update(obj), UsersDTO.class));
    }

    @DeleteMapping(value = "/excluir/" + ID)
    public ResponseEntity<UsersDTO>delete(@PathVariable Long id){
        userServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}
