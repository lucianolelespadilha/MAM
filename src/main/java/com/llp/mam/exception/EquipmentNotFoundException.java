package com.llp.mam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class EquipmentNotFoundException extends AmmException{

    private Long detail;

    public EquipmentNotFoundException(Long detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
         var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("Equipment does not exist");
        pb.setDetail(String.valueOf(detail));
        return pb;
    }
}
