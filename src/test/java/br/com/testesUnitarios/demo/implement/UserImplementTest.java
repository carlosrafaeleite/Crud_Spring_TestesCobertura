package br.com.testesUnitarios.demo.implement;

import br.com.testesUnitarios.demo.DTO.UsersDTO;
import br.com.testesUnitarios.demo.domain.Users;
import br.com.testesUnitarios.demo.repositories.UserRepositories;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class UserImplementTest {

    public static final long ID         = 1L;
    public static final String NOME     = "Antonella";
    public static final String EMAIL    = "Antonella@email";
    public static final String PASSWORD = "1234";

    @InjectMocks
    private UserImplement userImplement;

    @Mock
    private UserRepositories userRepositories;

    @Mock
    private ModelMapper mapper;

    private Users users;
    private UsersDTO usersDTO;
    private Optional<Users> optionalUsers;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        iniciaUsuarios();
    }

    @Test
    void whenFindByIdReturnUserInstance() {

        Mockito.when(userRepositories.findById(Mockito.anyLong())).thenReturn(optionalUsers);
        Users response = userImplement.findById(ID);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(Users.class, response.getClass());
        Assertions.assertEquals(ID, response.getId());
        Assertions.assertEquals(NOME, response.getNome());
        Assertions.assertEquals(EMAIL, response.getEmail());
        Assertions.assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findByEmail() {
    }

    private void iniciaUsuarios(){
        users = new Users(ID, NOME, EMAIL, PASSWORD);
        usersDTO = new UsersDTO(ID, NOME, EMAIL, PASSWORD);
        optionalUsers = Optional.of(new Users (ID, NOME, EMAIL, PASSWORD));
    }

}