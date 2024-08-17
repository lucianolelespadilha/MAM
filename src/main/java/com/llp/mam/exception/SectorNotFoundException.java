package com.llp.mam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class SectorNotFoundException extends AmmException {
    public SectorNotFoundException(Long s) {
        super(2L);
    }

    @Override
    public ProblemDetail toProblemDetail() {

        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("Sector does not exist");
        pb.setDetail("Department not registered");
        return pb;
    }
}
