package com.llp.mam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class DepartmentNotFoundException extends AmmException{


    private Long detail;

    public DepartmentNotFoundException(Long detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {

        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("Department does not exist");
        pb.setDetail(String.valueOf(detail));

        return pb;
    }
}
