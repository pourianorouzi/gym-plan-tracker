package com.gymplan.tracker.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationError {

    private String field;
    private String message;

}
