package com.llp.amm.exception;

import jakarta.transaction.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class EquipmentNotFoundException extends AmmException{

    public EquipmentNotFoundException(Long s) {
        super(1L);
    }

    @Override
    public ProblemDetail toProblemDetail() {
         var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("Equipment does not exist");
        pb.setDetail("Equipment not registered");
        return pb;
    }
}
