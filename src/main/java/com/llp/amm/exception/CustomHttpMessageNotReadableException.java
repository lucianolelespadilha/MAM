package com.llp.amm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class CustomHttpMessageNotReadableException extends AmmException {

    public CustomHttpMessageNotReadableException(){
        super("The request body is missing required parameters or contains invalid values. Please check your JSON and try again.");
    }

    @Override
    public ProblemDetail toProblemDetail() {
    var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

    pb.setTitle("Json parse erro.");
    pb.setDetail(getMessage());

    return pb;
    }
}
