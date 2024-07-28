package com.llp.amm.controller;


import com.llp.amm.exception.AmmException;
import com.llp.amm.exception.CustomHttpMessageNotReadableException;
import com.llp.amm.exception.DepartmentAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(AmmException.class)
    public ProblemDetail handleAmmException(AmmException e) {
        return e.toProblemDetail();

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var fieldErrors = e.getFieldErrors().stream()
                .map(error -> new InvalidParam(error.getField(), error.getDefaultMessage())).toList();
        var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pb.setTitle("Invalid request parameters. Please check and try again.");
        pb.setProperty("invalidParams", fieldErrors);
        return pb;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ProblemDetail> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        CustomHttpMessageNotReadableException customException = new CustomHttpMessageNotReadableException();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customException.toProblemDetail());

    }
    


    public record InvalidParam(
            String name,
            String reasom
    ) {
    }
}
