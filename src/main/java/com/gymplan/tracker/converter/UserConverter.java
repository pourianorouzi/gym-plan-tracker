package com.gymplan.tracker.converter;

import com.gymplan.tracker.dto.UserDTO;
import com.gymplan.tracker.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserEntity convertDTOtoEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();

        userEntity.setId(userDTO.getId());
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setFullName(userDTO.getFullName());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPhoneNumber(userDTO.getPhoneNumber());

        return userEntity;
    }

    public UserDTO convertEntityToDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(userEntity.getId());
        userDTO.setUsername(userEntity.getUsername());
        userDTO.setFullName(userEntity.getFullName());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setPhoneNumber(userEntity.getPhoneNumber());

        return userDTO;
    }

}
