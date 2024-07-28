package com.llp.amm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class SectorAlreadyExistsException extends AmmException{

    public SectorAlreadyExistsException(String name) {
        super("Sector Already Exists");
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("Sector Already Exists");

        return pb;
    }
}
