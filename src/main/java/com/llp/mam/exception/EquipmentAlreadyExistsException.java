package com.llp.mam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class EquipmentAlreadyExistsException extends AmmException {

    private Long detail;

    public EquipmentAlreadyExistsException(Long detail) {
        this.detail = detail;
    }



    @Override
    public ProblemDetail toProblemDetail() {

        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("Equipmente Already Exists");
        pb.setDetail(String.valueOf(detail));

        return pb;
    }
}
