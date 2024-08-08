package com.gymplan.tracker.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

// Todo: test with WebMVC
//    @Test
//    void handleValidationErrors_() {
//
//    }

    @Test
    void handleBusinessErrors_givenBusinessExceptionCaught_shouldReturnResponseEntity() {
        // Arrange input
        BusinessError businessError = new BusinessError();
        businessError.setCode("TEST CODE");
        businessError.setMessage("Test message");
        List<BusinessError> businessErrors = new ArrayList<>();
        businessErrors.add(businessError);
        BusinessException businessException = new BusinessException(businessErrors);

        // Act
        ResponseEntity<List<BusinessError>> response = globalExceptionHandler.handleBusinessErrors(businessException);

        // Assert
        assertNotNull(response, "Response must not be null");
        assertNotNull(response.getBody(), "Response body must not be null");
        assertEquals(1,response.getBody().size(),"There should be 1 BusinessError in the response body.");
        assertEquals("TEST CODE",response.getBody().get(0).getCode(),"The error code must match the input.");
    }
}