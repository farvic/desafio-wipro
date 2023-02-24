package com.farvic.desafiowipro.errors;

import com.farvic.desafiowipro.dtos.AddressDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class CustomControllerAdvice {
        String invalidRequest = "Requisição inválida. Por favor, insira um cep no formato 01001-000 ou  01001000.";
        @ExceptionHandler(MethodArgumentNotValidException.class) // exception handled
        public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
                MethodArgumentNotValidException e) {
                return new ResponseEntity<>(
                        new ErrorResponse(
                                invalidRequest),
                        HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(HttpMessageNotReadableException.class) // exception handled
        public ResponseEntity<ErrorResponse> handleHttpExceptions(
                HttpMessageNotReadableException e) {
                return new ResponseEntity<>(
                        new ErrorResponse(
                                invalidRequest),
                        HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(RuntimeException.class) // exception handled
        public ResponseEntity<ErrorResponse> handleAddressNotFoundExceptions(
                AddressNotFoundException e) {

                HttpStatus status = e.getStatus();

                return new ResponseEntity<>(
                        new ErrorResponse(
                                e.getError()),
                        HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(Exception.class) // exception handled
        public ResponseEntity<ErrorResponse> handleExceptions(
                        Exception e) {
                return new ResponseEntity<>(
                                new ErrorResponse(
                                                e.getMessage()),
                                HttpStatus.BAD_REQUEST);
        }
}
