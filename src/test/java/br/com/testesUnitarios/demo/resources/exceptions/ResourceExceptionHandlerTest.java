package br.com.testesUnitarios.demo.resources.exceptions;

import br.com.testesUnitarios.demo.services.exceptions.DataIntegrityViolationException;
import br.com.testesUnitarios.demo.services.exceptions.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResourceExceptionHandlerTest {

    @InjectMocks
    private ResourceExceptionHandler resourceExceptionHandler;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void objectNotFoundReturnEntity() {
        ResponseEntity<StandarError> response = resourceExceptionHandler
                .objectNotFound(new ObjectNotFoundException("Objeto não encontrado"), new MockHttpServletRequest());

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(StandarError.class, response.getBody().getClass());
        Assertions.assertEquals("Objeto não encontrado", response.getBody().getError());
        Assertions.assertEquals(404, response.getBody().getStatus());
    }

    @Test
    void dataIntegrityViolationReturnEntity() {

        ResponseEntity<StandarError> response = resourceExceptionHandler
                .dataIntegrityViolation(new DataIntegrityViolationException("email ja cadastrado"), new MockHttpServletRequest());

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(StandarError.class, response.getBody().getClass());
        Assertions.assertEquals("email ja cadastrado", response.getBody().getError());
        Assertions.assertEquals(400, response.getBody().getStatus());
        Assertions.assertNotEquals("/user/2", response.getBody().getPath());
        Assertions.assertNotEquals(LocalDateTime.now(), response.getBody().getTimestamp());
    }
}