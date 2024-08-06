package com.gymplan.tracker.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessError {

    private String code;
    private String message;

}
