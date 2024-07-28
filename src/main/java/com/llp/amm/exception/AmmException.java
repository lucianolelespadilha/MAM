package com.llp.amm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.client.HttpServerErrorException;

public class AmmException extends RuntimeException {

    public AmmException(String s) {
    }

    public ProblemDetail toProblemDetail() {

        var pb = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        pb.setTitle("AMM internal server error.");
        pb.setDetail(getMessage());

        return pb;

    }

}
