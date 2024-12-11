package com.llp.mam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class DuplicateAnomalyException extends AmmException {

    private Long detail;

    public DuplicateAnomalyException(Long detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("anomaly already registered");
        pb.setDetail(String.valueOf("Anomaly in the equipment with the id: " + detail + " is already registered."));
        return pb;
    }
}
