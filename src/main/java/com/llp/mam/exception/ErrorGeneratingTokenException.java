package com.llp.mam.exception;

import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class ErrorGeneratingTokenException extends AmmException{

    private String detail;

    public ErrorGeneratingTokenException(JWTCreationException detail) {
        this.detail = String.valueOf(detail);
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        pb.setTitle("Token Generation Failed");
        pb.setDetail("\"An error occurred while generating the authentication token. Please try again later");
        return pb;
    }
}
