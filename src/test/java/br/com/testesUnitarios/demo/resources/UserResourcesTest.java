package br.com.testesUnitarios.demo.resources;

import br.com.testesUnitarios.demo.DTO.UsersDTO;
import br.com.testesUnitarios.demo.domain.Users;
import br.com.testesUnitarios.demo.implement.UserImplement;
import br.com.testesUnitarios.demo.repositories.UserRepositories;
import br.com.testesUnitarios.demo.services.UserServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void createUser() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void iniciaUsuarios(){
        users = new Users(ID, NOME, EMAIL, PASSWORD);
        usersDTO = new UsersDTO(ID, NOME, EMAIL, PASSWORD);
    }
}