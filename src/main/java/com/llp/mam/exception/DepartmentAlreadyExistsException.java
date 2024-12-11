package com.llp.mam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class DepartmentAlreadyExistsException extends AmmException{

    private String detail;

    public DepartmentAlreadyExistsException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("Department Already Exists");
        pb.setDetail(detail);


        return pb;
    }


}
