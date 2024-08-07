package com.gymplan.tracker.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BusinessExceptionTest {

    @Test
    void testBusinessExceptionWithErrors() {
        // Arrange input
        BusinessError businessError = new BusinessError();
        businessError.setCode("TEST CODE");
        businessError.setMessage("Test message");
        List<BusinessError> businessErrors = new ArrayList<>();
        businessErrors.add(businessError);

        // Act
        BusinessException businessException = new BusinessException(businessErrors);

        // Assert
        assertNotNull(businessException, "businessException object should not be null");
        assertNotNull(businessException.getBusinessErrors(), "businessErrors list should not be null");
        assertEquals(1, businessException.getBusinessErrors().size(), "There must be one businessError in the businessErrors list");
        assertEquals("TEST CODE", businessException.getBusinessErrors().get(0).getCode(), "Error code must match the input");
    }
}