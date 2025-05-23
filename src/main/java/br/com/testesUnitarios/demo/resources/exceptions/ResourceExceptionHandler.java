package br.com.testesUnitarios.demo.resources.exceptions;

import br.com.testesUnitarios.demo.services.exceptions.DataIntegrityViolationException;
import br.com.testesUnitarios.demo.services.exceptions.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.zip.DataFormatException;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandarError>objectNotFound(ObjectNotFoundException ex, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new StandarError (LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
                        ex.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandarError>dataIntegrityViolation(DataIntegrityViolationException ex, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new StandarError (LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
                        ex.getMessage(), request.getRequestURI()));
    }
}
