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
public class ExerciseDTO {

    private Long id;

    @NotNull
    private Long planId;

    @NotBlank
    private String title;

    @Size(min = 1, message = "Sets must be a one or more")
    private Integer sets;

    @Size(min = 1, message = "Reps must be a one or more")
    private Integer reps;

}
