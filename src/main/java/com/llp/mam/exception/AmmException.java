package com.llp.mam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class AmmException extends RuntimeException {

    public AmmException(Long s) {
    }

    public ProblemDetail toProblemDetail() {

        var pb = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        pb.setTitle("AMM internal server error.");
        pb.setDetail(getMessage());

        return pb;

    }

}
