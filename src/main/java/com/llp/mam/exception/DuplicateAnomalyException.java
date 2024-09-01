package com.llp.mam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class DuplicateAnomalyException extends AmmException {
    public DuplicateAnomalyException(Long s) {
        super(1L);
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("anomaly already registered");
        pb.setDetail("There is already an anomaly registered with this description for this equipment.");
        return pb;
    }
}
