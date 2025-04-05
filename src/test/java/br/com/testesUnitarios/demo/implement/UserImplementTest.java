package br.com.testesUnitarios.demo.implement;

import br.com.testesUnitarios.demo.DTO.UsersDTO;
import br.com.testesUnitarios.demo.domain.Users;
import br.com.testesUnitarios.demo.repositories.UserRepositories;
import br.com.testesUnitarios.demo.services.exceptions.DataIntegrityViolationException;
import br.com.testesUnitarios.demo.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.function.Try;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
class UserImplementTest {


    public static final long ID         = 1L;
    public static final String NOME     = "Antonella";
    public static final String EMAIL    = "Antonella@email";
    public static final String PASSWORD = "1234";
    public static final int INDEX = 0;

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
        //Mockito.when(userRepositories.findById(Mockito.anyLong())).thenReturn(optionalUsers);
        when(userRepositories.findById(anyLong())).thenReturn(optionalUsers);
        Users response = userImplement.findById(ID);

        //Assertions.assertNotNull(response);
        assertNotNull(response);
        assertEquals(Users.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenFindByIdReturnNotFound(){
        //Mockito.
        when(userRepositories.findById(Mockito.anyLong()))
                .thenThrow(new ObjectNotFoundException("Objeto não encontrado"));
        try{
            userImplement.findById(ID);
        }catch(Exception ex){
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Objeto não encontrado", ex.getMessage());
        }
    }

    @Test
    void whenFindAllReturnListUsers() {
        when(userRepositories.findAll()).thenReturn(List.of(users));
        List<Users> response = userImplement.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Users.class, response.get(INDEX).getClass());
        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NOME, response.get(INDEX).getNome());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
        assertEquals(PASSWORD, response.get(INDEX).getPassword());

    }

    @Test
    void whenCreateReturnUserSuccess() {
        when(userRepositories.save(any())).thenReturn(users);
        Users response = userImplement.create(usersDTO);

        assertNotNull(response);
        assertEquals(Users.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenCreateReturnUserDataViolationException() {
        when(userRepositories.findByEmail(anyString())).thenReturn(optionalUsers);
        try {
            optionalUsers.get().setId(2L);
            userImplement.create(usersDTO);
        } catch (Exception ex) {
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals("email ja cadastrado", ex.getMessage());
        }
    }

    @Test
    void whenUpdateReturnUserSuccess()  {
        when(userRepositories.save(any())).thenReturn(users);
        Users response = userImplement.update(usersDTO);

        assertNotNull(response);
        assertEquals(Users.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
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