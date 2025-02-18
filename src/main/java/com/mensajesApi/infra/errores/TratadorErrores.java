package com.mensajesApi.infra.errores;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mensajesApi.domain.mensaje.ValidationException;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratadorErrores {

	@ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404(){

        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity tratarErrores( ValidationException e ){
        return ResponseEntity.badRequest().body(e.getMessage());
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErroresValidacion( MethodArgumentNotValidException e ){
        return ResponseEntity.badRequest().body(e.getAllErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity tratarErroresValidacion( SQLIntegrityConstraintViolationException e ){
        return ResponseEntity.badRequest().body("Usuario no encontrado");
    }
    
}
