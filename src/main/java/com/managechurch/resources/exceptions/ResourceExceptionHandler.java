package com.managechurch.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.managechurch.dto.ResponseDTO;
import com.managechurch.services.exceptions.DataItegrityException;
import com.managechurch.services.exceptions.EntityNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseDTO> entityNotFound(EntityNotFoundException e, HttpServletRequest request) {

        ResponseDTO error = standardErrorAux(e, request, "Informação não encontrada");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataItegrityException.class)
    public ResponseEntity<ResponseDTO> entityNotFound(DataItegrityException e,
            HttpServletRequest request) {
        ResponseDTO error = standardErrorAux(e, request, "Erro ao salvar informação");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseDTO> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        ResponseDTO response = standardErrorAux(ex, null, "ERROR");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private ResponseDTO standardErrorAux(Exception e, HttpServletRequest request, String errorInformation) {
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setError(errorInformation);
        error.setMessage(e.getMessage());
        if (request != null) {
            error.setPath(request.getRequestURI());
        }
        return new ResponseDTO("ERROR", error);
    }

}
