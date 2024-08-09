package com.gymplan.tracker.converter;

import com.gymplan.tracker.dto.ExerciseDTO;
import com.gymplan.tracker.dto.PlanDTO;
import com.gymplan.tracker.entity.ExerciseEntity;
import com.gymplan.tracker.service.PlanService;
import org.springframework.stereotype.Component;

@Component
public class ExerciseConverter {

    private final PlanService planService;
    private final PlanConverter planConverter;

    public ExerciseConverter(PlanService planService, PlanConverter planConverter) {
        this.planService = planService;
        this.planConverter = planConverter;
    }

    public ExerciseEntity convertDTOtoEntity(ExerciseDTO exerciseDTO) {
        ExerciseEntity exerciseEntity = new ExerciseEntity();

        exerciseEntity.setId(exerciseDTO.getId());
        if (exerciseDTO.getPlanId() > 0) {
            PlanDTO planDTO = planService.getPlanById(exerciseDTO.getPlanId());
            exerciseEntity.setPlan(
                    planConverter.convertDTOtoEntity(planDTO)
            );
        }
        exerciseEntity.setTitle(exerciseDTO.getTitle());
        exerciseEntity.setSets(exerciseDTO.getSets());
        exerciseEntity.setReps(exerciseDTO.getReps());

        return exerciseEntity;
    }

    public ExerciseDTO convertEntityToDTO(ExerciseEntity exerciseEntity) {
        ExerciseDTO exerciseDTO = new ExerciseDTO();

        exerciseDTO.setId(exerciseEntity.getId());
        exerciseDTO.setPlanId(exerciseEntity.getPlan().getId());
        exerciseDTO.setTitle(exerciseEntity.getTitle());
        exerciseDTO.setSets(exerciseEntity.getSets());
        exerciseDTO.setReps(exerciseEntity.getReps());

        return exerciseDTO;
    }

}
