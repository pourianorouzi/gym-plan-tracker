package com.gymplan.tracker.service;

import com.gymplan.tracker.dto.UserDTO;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    UserDTO findUserById(Long id);

    UserDTO findUserByUsernameAndPassword(String username, String password);

}
