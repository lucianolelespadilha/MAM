package com.llp.amm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class DepartmentNotFoundException extends AmmException{
    public DepartmentNotFoundException(Long s) {
        super(1L);
    }

    @Override
    public ProblemDetail toProblemDetail() {

        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("Department does not exist");
        pb.setDetail("Department not registered");
        return pb;
    }
}
