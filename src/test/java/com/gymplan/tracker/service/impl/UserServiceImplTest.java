package com.gymplan.tracker.service.impl;

import com.gymplan.tracker.converter.UserConverter;
import com.gymplan.tracker.dto.UserDTO;
import com.gymplan.tracker.entity.UserEntity;
import com.gymplan.tracker.exception.BusinessException;
import com.gymplan.tracker.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserConverter userConverter;

    @Test
    void createUser_givenValidUserDTO_shouldReturnCreatedUserDTO() {
        // Arrange input and expected output
        UserDTO inputUserDTO = new UserDTO();
        inputUserDTO.setUsername("testusername");
        inputUserDTO.setEmail("a@a.com");
        inputUserDTO.setFullName("John Smith");
        inputUserDTO.setPassword("123qwe");

        UserEntity convertedUserEntity = new UserEntity();
        convertedUserEntity.setUsername("testusername");
        convertedUserEntity.setEmail("a@a.com");
        convertedUserEntity.setFullName("John Smith");
        convertedUserEntity.setPassword("123qwe");

        UserEntity outputUserEntity = new UserEntity();
        outputUserEntity.setId(1L);
        outputUserEntity.setUsername("testusername");
        outputUserEntity.setEmail("a@a.com");
        outputUserEntity.setFullName("John Smith");
        outputUserEntity.setPassword("123qwe");

        UserDTO outputUserDTO = new UserDTO();
        outputUserDTO.setId(1L);
        outputUserDTO.setUsername("testusername");
        outputUserDTO.setEmail("a@a.com");
        outputUserDTO.setFullName("John Smith");
        outputUserDTO.setPassword("123qwe");

        // Arrange dependencies
        when(userConverter.convertDTOtoEntity(inputUserDTO)).thenReturn(convertedUserEntity);
        when(userRepository.existsByEmail(convertedUserEntity.getEmail())).thenReturn(false);
        when(userRepository.existsByUsername(convertedUserEntity.getUsername())).thenReturn(false);
        when(userRepository.save(convertedUserEntity)).thenReturn(outputUserEntity);
        when(userConverter.convertEntityToDTO(outputUserEntity)).thenReturn(outputUserDTO);

        // Act
        UserDTO createdUserDTO = userService.createUser(inputUserDTO);

        // Assert
        assertNotNull(createdUserDTO, "Method should not return not");
        assertEquals(1L, createdUserDTO.getId(), "The created user id must be 1");
        assertEquals("testusername", createdUserDTO.getUsername(), "The created username must be testusername");
    }

    @Test
    void createUser_givenInvalidUserDTO_shouldThrowBusinessException() {
        // Arrange input
        UserDTO inputUserDTO = new UserDTO();
        inputUserDTO.setUsername("testusername");
        inputUserDTO.setEmail("a@a.com");
        inputUserDTO.setFullName("John Smith");
        inputUserDTO.setPassword("123qwe");

        UserEntity convertedUserEntity = new UserEntity();
        convertedUserEntity.setUsername("testusername");
        convertedUserEntity.setEmail("a@a.com");
        convertedUserEntity.setFullName("John Smith");
        convertedUserEntity.setPassword("123qwe");

        // Arrange dependencies
        when(userConverter.convertDTOtoEntity(inputUserDTO)).thenReturn(convertedUserEntity);
        when(userRepository.existsByEmail(convertedUserEntity.getEmail())).thenReturn(true);
        when(userRepository.existsByUsername(convertedUserEntity.getUsername())).thenReturn(true);

        // Act
        BusinessException businessException = assertThrows(BusinessException.class, () -> {
            userService.createUser(inputUserDTO);
        });

        // Assert
        assertEquals(2,businessException.getBusinessErrors().size(), "There should be two BusinessErrors in the BusinessException");
    }

}