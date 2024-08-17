package com.llp.mam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class EquipmentAlreadyExistsException extends AmmException {
    public EquipmentAlreadyExistsException(Long s) {
        super(s);
    }



    @Override
    public ProblemDetail toProblemDetail() {

        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("Equipmente Already Exists");

        return pb;
    }
}
