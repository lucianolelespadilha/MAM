package com.llp.amm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class UserNotFoundException extends AmmException{
    public UserNotFoundException(Long s) {
        super(3L);
    }

    @Override
    public ProblemDetail toProblemDetail() {

        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("User does not exist");

        return pb;
    }
}
