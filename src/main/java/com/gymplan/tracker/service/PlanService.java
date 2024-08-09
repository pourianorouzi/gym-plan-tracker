package com.gymplan.tracker.service;

import com.gymplan.tracker.dto.PlanDTO;

import java.util.List;

public interface PlanService {

    PlanDTO createPlan(PlanDTO planDTO);

    PlanDTO getPlanById(Long id);

    List<PlanDTO> getAllPlans();

    PlanDTO updatePlan(Long id, PlanDTO planDTO);

    void deletePlan(Long id);

    List<PlanDTO> getExercisesByUserId(Long userId);
}
