package com.gymplan.tracker.controller;

import com.gymplan.tracker.dto.UserDTO;
import com.gymplan.tracker.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Test
    void register_givenValidUser_shouldReturnCreatedUser() {
        // Arrange input and expected output
        UserDTO inputUserDTO = new UserDTO();
        inputUserDTO.setUsername("testusername");
        inputUserDTO.setEmail("a@a.com");
        inputUserDTO.setFullName("John Smith");
        inputUserDTO.setPassword("123qwe");

        UserDTO outputUserDTO = new UserDTO();
        outputUserDTO.setId(1L);
        outputUserDTO.setUsername("testusername");
        outputUserDTO.setEmail("a@a.com");
        outputUserDTO.setFullName("John Smith");
        outputUserDTO.setPassword("123qwe");

        // Arrange Dependencies
        when(userService.createUser(inputUserDTO)).thenReturn(outputUserDTO);

        // Act
        ResponseEntity<UserDTO> response = userController.register(inputUserDTO);

        // Assert
        assertNotNull(response.getBody(), "Response body must not be null");
        assertEquals(1L, response.getBody().getId(), "User id must be 1");
        assertEquals(201, response.getStatusCode().value(), "Status must be Created 201 (CREATED)");
    }
}