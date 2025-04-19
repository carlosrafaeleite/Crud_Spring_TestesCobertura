package br.com.testesUnitarios.demo.resources;

import br.com.testesUnitarios.demo.DTO.UsersDTO;
import br.com.testesUnitarios.demo.domain.Users;
import br.com.testesUnitarios.demo.implement.UserImplement;
import br.com.testesUnitarios.demo.repositories.UserRepositories;
import br.com.testesUnitarios.demo.services.UserServices;
import com.sun.source.tree.ModuleTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserResourcesTest {

    public static final long ID         = 1L;
    public static final String NOME     = "Antonella";
    public static final String EMAIL    = "Antonella@email";
    public static final String PASSWORD = "1234";
    public static final int INDEX = 0;
    public static final String EMAIL_JA_CADASTRADO = "email ja cadastrado";
    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";

    private Users users;
    private UsersDTO usersDTO;

    @InjectMocks
    private UserResources userResources;

    @Mock
    private UserImplement userImplement;

    @Mock
    private ModelMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        iniciaUsuarios();
    }

    @Test
    void findByIdResourcesSuccess() {
        Mockito.when(userImplement.findById(Mockito.anyLong())).thenReturn(users);
        Mockito.when(mapper.map(Mockito.any(), Mockito.any())).thenReturn(usersDTO);

        ResponseEntity<UsersDTO> response = userResources.findById(ID);

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(UsersDTO.class, response.getBody().getClass());

        Assertions.assertEquals(ID, response.getBody().getId());
        Assertions.assertEquals(NOME, response.getBody().getNome());
        Assertions.assertEquals(EMAIL, response.getBody().getEmail());
        Assertions.assertEquals(PASSWORD, response.getBody().getPassword());


    }

    @Test
    void findAllResourcesSuccessDTO() {
        Mockito.when(userImplement.findAll()).thenReturn(List.of(users));
        Mockito.when(mapper.map(Mockito.any(), Mockito.any())).thenReturn(usersDTO);
        ResponseEntity<List<UsersDTO>> response = userResources.findAll();

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(ArrayList.class, response.getBody().getClass());
        Assertions.assertEquals(UsersDTO.class, response.getBody().get(INDEX).getClass());

        Assertions.assertEquals(ID, response.getBody().get(INDEX).getId());
        Assertions.assertEquals(NOME, response.getBody().get(INDEX).getNome());
        Assertions.assertEquals(EMAIL, response.getBody().get(INDEX).getEmail());
        Assertions.assertEquals(PASSWORD, response.getBody().get(INDEX).getPassword());
    }

    @Test
    void createUserResourcesSuccess() {
        Mockito.when(userImplement.create(Mockito.any())).thenReturn(users);
        ResponseEntity<UsersDTO> response = userResources.createUser(usersDTO);

        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertNotNull(response.getHeaders().get("Location"));
    }

    @Test
    void updateResourcesSuccess() {
        Mockito.when(userImplement.update(usersDTO)).thenReturn(users);
        Mockito.when(mapper.map(Mockito.any(), Mockito.any())).thenReturn(usersDTO);
        ResponseEntity<UsersDTO> response = userResources.update(ID, usersDTO);

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());

        Assertions.assertEquals(ID, response.getBody().getId());
        Assertions.assertEquals(NOME, response.getBody().getNome());
        Assertions.assertEquals(EMAIL, response.getBody().getEmail());
        Assertions.assertEquals(PASSWORD, response.getBody().getPassword());
    }

    @Test
    void deleteResourcesSuccess() {
    Mockito.doNothing().when(userImplement).delete(Mockito.anyLong());
    ResponseEntity<UsersDTO> response = userResources.delete(ID);

    Assertions.assertNotNull(response);
    Assertions.assertEquals(ResponseEntity.class, response.getClass());
    Mockito.verify(userImplement, Mockito.times(1)).delete(Mockito.anyLong());
    Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }


    private void iniciaUsuarios(){
        users = new Users(ID, NOME, EMAIL, PASSWORD);
        usersDTO = new UsersDTO(ID, NOME, EMAIL, PASSWORD);
    }
}