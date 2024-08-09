package com.gymplan.tracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

    @Positive(message = "Sets must be a one or more")
    private Integer sets;

    @Positive(message = "Reps must be a one or more")
    private Integer reps;

}
