package com.example.desafio_picpay_transacao.config;

import com.example.desafio_picpay_transacao.infrastructure.exception.UnauthorizedTransactionException;
import com.example.desafio_picpay_transacao.infrastructure.exception.UserNotFoundException;
import com.example.desafio_picpay_transacao.infrastructure.exception.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleUserNotFoundException(UserNotFoundException exception){
        ErrorDTO dto = new ErrorDTO(HttpStatus.NOT_FOUND.value(), exception.getMessage());
        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDTO> handleIllegalArgumentException(IllegalArgumentException exception){
        ErrorDTO dto = new ErrorDTO(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleException(Exception exception){
        ErrorDTO dto = new ErrorDTO(HttpStatus.UNAUTHORIZED.value(), exception.getMessage());
        return new ResponseEntity<>(dto, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UnauthorizedTransactionException.class)
    public ResponseEntity<ErrorDTO> handleUnauthorizedTransactionException(Exception exception){
        ErrorDTO dto = new ErrorDTO(HttpStatus.UNAUTHORIZED.value(), exception.getMessage());
        return new ResponseEntity<>(dto, HttpStatus.UNAUTHORIZED);
    }


}
