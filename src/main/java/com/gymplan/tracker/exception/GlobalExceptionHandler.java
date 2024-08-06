package com.gymplan.tracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationError>> handleValidationErrors(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<ObjectError> objectErrors = methodArgumentNotValidException.getBindingResult().getAllErrors();

        List<ValidationError> validationErrors = new ArrayList<>();
        for (ObjectError objectError : objectErrors) {
            ValidationError validationError = new ValidationError();
            validationError.setField(
                    ((FieldError) objectError).getField()
            );
            validationError.setMessage(
                    objectError.getDefaultMessage()
            );
            validationErrors.add(validationError);
        }

        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<BusinessError>> handleBusinessErrors(BusinessException businessException) {
        List<BusinessError> businessErrors = businessException.getBusinessErrors();
        return new ResponseEntity<>(businessErrors, HttpStatus.BAD_REQUEST);
    }

}
