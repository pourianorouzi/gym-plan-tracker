package com.gymplan.tracker.service.impl;

import com.gymplan.tracker.converter.UserConverter;
import com.gymplan.tracker.dto.UserDTO;
import com.gymplan.tracker.entity.UserEntity;
import com.gymplan.tracker.exception.BusinessError;
import com.gymplan.tracker.exception.BusinessException;
import com.gymplan.tracker.repository.UserRepository;
import com.gymplan.tracker.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserConverter userConverter;

    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        UserEntity userEntity = userConverter.convertDTOtoEntity(userDTO);

        // Checking business logic errors
        List<BusinessError> businessErrors = new ArrayList<>();
        if (Boolean.TRUE.equals(userRepository.existsByEmail(userEntity.getEmail()))) {
            BusinessError businessError = new BusinessError();
            businessError.setCode("Duplicate Email");
            businessError.setMessage("Email '" + userEntity.getEmail() + "' already registered");
            businessErrors.add(businessError);
        }
        if (Boolean.TRUE.equals(userRepository.existsByUsername(userEntity.getUsername()))) {
            BusinessError businessError = new BusinessError();
            businessError.setCode("Duplicate Username");
            businessError.setMessage("Username '" + userEntity.getUsername() + "' already registered");
            businessErrors.add(businessError);
        }

        if (businessErrors.isEmpty()) {
            userEntity = userRepository.save(userEntity);
            return userConverter.convertEntityToDTO(userEntity);
        } else {
            throw new BusinessException(businessErrors);
        }
    }

}
