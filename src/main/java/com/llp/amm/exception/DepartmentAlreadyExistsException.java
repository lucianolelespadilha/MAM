package com.llp.amm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class DepartmentAlreadyExistsException extends AmmException{

    public DepartmentAlreadyExistsException(String name) {
        super("Department Already Exists");
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("Department Already Exists");


        return pb;
    }


}
