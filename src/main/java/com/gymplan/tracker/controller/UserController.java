package com.gymplan.tracker.controller;

import com.gymplan.tracker.dto.UserDTO;
import com.gymplan.tracker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody UserDTO userDTO) {
        userDTO = userService.createUser(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

}
