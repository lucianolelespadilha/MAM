package com.llp.mam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class SectorNotFoundException extends AmmException {
    private Long detail;

    public SectorNotFoundException(Long detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {

        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("Sector does not exist");
        pb.setDetail(String.valueOf(detail));
        return pb;
    }
}
