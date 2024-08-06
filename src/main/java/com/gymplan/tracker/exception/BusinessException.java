package com.gymplan.tracker.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BusinessException extends RuntimeException {

    private final transient List<BusinessError> businessErrors;

    public BusinessException(List<BusinessError> businessErrors) {
        this.businessErrors = businessErrors;
    }
}
