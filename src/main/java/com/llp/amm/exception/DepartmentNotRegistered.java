package com.llp.amm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;


public class DepartmentNotRegistered extends AmmException{


    public DepartmentNotRegistered() {
        super("Department not registered");
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("Department not registered");
        pb.setDetail("This department is not registered");
        return pb;
    }
}
