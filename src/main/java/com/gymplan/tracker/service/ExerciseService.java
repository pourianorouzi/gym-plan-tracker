package com.gymplan.tracker.service;

import com.gymplan.tracker.dto.ExerciseDTO;

import java.util.List;

public interface ExerciseService {

    ExerciseDTO createExercise(ExerciseDTO exerciseDTO);

    ExerciseDTO getExerciseById(Long id);

    List<ExerciseDTO> getAllExercises();

    ExerciseDTO updateExercise(Long id, ExerciseDTO exerciseDTO);

    void deleteExercise(Long id);

    List<ExerciseDTO> getExercisesByPlanId(Long planId);

}