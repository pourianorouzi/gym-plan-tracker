package com.gymplan.tracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlanDTO {

    private Long id;

    @NotNull(message = "User id can not be blank")
    private Long userId;

    @NotBlank(message = "Title can not be blank")
    @Size(min = 2, max = 25, message = "Title must be between 2 and 25 characters")
    private String title;

    @Size(max = 25, message = "Description must be less than 255 characters")
    private String description;

}
