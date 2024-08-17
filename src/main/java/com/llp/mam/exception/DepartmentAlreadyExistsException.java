package com.llp.mam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class DepartmentAlreadyExistsException extends AmmException{

    public DepartmentAlreadyExistsException(String name) {
        super(2L);
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("Department Already Exists");


        return pb;
    }


}
