package com.gymplan.tracker.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private Long id;

    @NotBlank(message = "Username can not be blank")
    private String username;

    @NotBlank(message = "Password can not be blank")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    private String password;

    @NotBlank(message = "Full name can not be blank")
    private String fullName;

    @NotBlank(message = "Email can not be blank")
    @Email(message = "Email format is not correct")
    private String email;

    @Pattern(regexp = "^\\+316[1-9]\\d{7}$", message = "Phone number must be in +316XXXXXXXX format")
    private String phoneNumber;

}
