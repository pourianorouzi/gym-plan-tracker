package com.gymplan.tracker.controller;

import com.gymplan.tracker.dto.PlanDTO;
import com.gymplan.tracker.service.PlanService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/plans")
public class PlanController {

    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @PostMapping("")
    public PlanDTO createPlan(@Valid @RequestBody PlanDTO planDTO) {
        return planService.createPlan(planDTO);
    }

    @GetMapping("/{id}")
    public PlanDTO getPlanById(@PathVariable Long id) {
        return planService.getPlanById(id);
    }

    @GetMapping("")
    public List<PlanDTO> getAllPlans() {
        return planService.getAllPlans();
    }

    @PutMapping("/{id}")
    public PlanDTO updatePlan(@PathVariable Long id, @Valid @RequestBody PlanDTO planDTO) {
        return planService.updatePlan(id, planDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlan(@PathVariable Long id) {
        planService.deletePlan(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users/{userId}")
    public List<PlanDTO> getPlansByUserId(@PathVariable Long userId) {
        return planService.getExercisesByUserId(userId);
    }

}
