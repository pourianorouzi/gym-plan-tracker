package com.gymplan.tracker.converter;

import com.gymplan.tracker.dto.UserDTO;
import com.gymplan.tracker.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserConverterTest {

    @InjectMocks
    private UserConverter userConverter;

    @Test
    void convertDTOtoEntity_givenUserDTO_shouldReturnUserEntity() {
        // Arrange input
        UserDTO inputUserDTO = new UserDTO();
        inputUserDTO.setUsername("testusername");
        inputUserDTO.setEmail("a@a.com");
        inputUserDTO.setFullName("John Smith");
        inputUserDTO.setPassword("123qwe");

        // Act
        UserEntity userEntity = userConverter.convertDTOtoEntity(inputUserDTO);

        // Assert
        assertNotNull(userEntity, "UserEntity must not be null");
        assertNull(userEntity.getId(), "User id must be null");
        assertEquals("testusername", userEntity.getUsername(), "UserEntity username must be equal to the input UserDTO username");
        assertEquals("a@a.com", userEntity.getEmail(), "UserEntity email must be equal to the input UserDTO email");
        assertEquals("John Smith", userEntity.getFullName(), "UserEntity full name must be equal to the input UserDTO full name");
        assertEquals("123qwe", userEntity.getPassword(), "UserEntity password must be equal to the input UserDTO password");
    }

    @Test
    void convertEntityToDTO_givenUserEntity_shouldReturnUserDTO() {
        // Arrange input
        UserEntity inputUserEntity = new UserEntity();
        inputUserEntity.setId(1L);
        inputUserEntity.setUsername("testusername");
        inputUserEntity.setEmail("a@a.com");
        inputUserEntity.setFullName("John Smith");
        inputUserEntity.setPassword("123qwe");

        // Act
        UserDTO userDTO = userConverter.convertEntityToDTO(inputUserEntity);

        // Assert
        assertNotNull(userDTO, "UserDTO must not be null");
        assertEquals(1L, userDTO.getId(), "UserDTO id must be equal to the input UserEntity id");
        assertEquals("testusername", userDTO.getUsername(), "UserDTO username must be equal to the input UserEntity username");
        assertEquals("a@a.com", userDTO.getEmail(), "UserDTO email must be equal to the input UserEntity email");
        assertEquals("John Smith", userDTO.getFullName(), "UserDTO full name must be equal to the input UserEntity full name");
        assertEquals("123qwe", userDTO.getPassword(), "UserDTO password must be equal to the input UserEntity password");
    }
}