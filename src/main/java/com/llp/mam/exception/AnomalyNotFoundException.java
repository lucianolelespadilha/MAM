package com.llp.mam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class AnomalyNotFoundException  extends AmmException{
    public AnomalyNotFoundException(Long s) {
        super(2L);
    }

    @Override
    public ProblemDetail toProblemDetail() {

        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("Anomaly does not exist");
        pb.setDetail("Anomaly not registered");

        return pb;
    }
}
