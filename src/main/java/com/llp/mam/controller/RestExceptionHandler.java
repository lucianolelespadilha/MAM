package com.llp.mam.controller;


import com.llp.mam.exception.AmmException;
import com.llp.mam.exception.CustomHttpMessageNotReadableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    /**
     * Handles exceptions of type AmmException.
     * This method is triggered whenever an exception that extends AmmException is thrown in the application.
     * It converts the exception into a ProblemDetail object, which provides details about the error to the client.
     *
     * @param e the AmmException that was thrown
     * @return a ResponseEntity containing the ProblemDetail and an appropriate HTTP status code
     */

    @ExceptionHandler(AmmException.class)
    public ProblemDetail handleAmmException(AmmException e) {
        return e.toProblemDetail();
    }

    /**
     * Handles MethodArgumentNotValidException, which is thrown when validation on an argument annotated with @Valid fails.
     * This method extracts the validation errors and constructs a ProblemDetail object to inform the client about the invalid parameters.
     *
     * @param e the MethodArgumentNotValidException that was thrown
     * @return a ResponseEntity containing the ProblemDetail and an appropriate HTTP status code
     */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var fieldErrors = e.getFieldErrors().stream()
                .map(error -> new InvalidParam(error.getField(), error.getDefaultMessage())).toList();
        var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pb.setTitle("Invalid request parameters. Please check and try again.");
        pb.setProperty("invalidParams", fieldErrors);
        return pb;
    }

    /**
     * Handles HttpMessageNotReadableException, which is thrown when the request body is not readable (e.g., missing required parameters or containing invalid values).
     * This method constructs a ProblemDetail object to inform the client about the JSON parsing error.
     *
     * @param e the HttpMessageNotReadableException that was thrown
     * @return a ResponseEntity containing the ProblemDetail and an appropriate HTTP status code
     */

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
