package com.gymplan.tracker.controller;

import com.gymplan.tracker.dto.ExerciseDTO;
import com.gymplan.tracker.service.ExerciseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @PostMapping("")
    public ExerciseDTO createExercise(@Valid @RequestBody ExerciseDTO exerciseDTO) {
        return exerciseService.createExercise(exerciseDTO);
    }

    @GetMapping("/{id}")
    public ExerciseDTO getExerciseById(@PathVariable Long id) {
        return exerciseService.getExerciseById(id);
    }

    @GetMapping("")
    public List<ExerciseDTO> getAllExercises() {
        return exerciseService.getAllExercises();
    }

    @PutMapping("/{id}")
    public ExerciseDTO updateExercise(@PathVariable Long id, @Valid @RequestBody ExerciseDTO exerciseDTO) {
        return exerciseService.updateExercise(id, exerciseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long id) {
        exerciseService.deleteExercise(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/plans/{planId}")
    public List<ExerciseDTO> getExercisesByPlanId(@PathVariable Long planId) {
        return exerciseService.getExercisesByPlanId(planId);
    }

}
