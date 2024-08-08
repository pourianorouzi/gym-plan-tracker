package com.gymplan.tracker.controller;

import com.gymplan.tracker.dto.PlanDTO;
import com.gymplan.tracker.service.PlanService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
