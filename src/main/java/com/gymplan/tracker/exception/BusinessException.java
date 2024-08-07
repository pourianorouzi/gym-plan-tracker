package com.gymplan.tracker.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class BusinessException extends RuntimeException {

    private final transient List<BusinessError> businessErrors;

    public BusinessException(List<BusinessError> businessErrors) {
        this.businessErrors = businessErrors;
    }
}
