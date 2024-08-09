package com.gymplan.tracker.service.impl;

import com.gymplan.tracker.converter.ExerciseConverter;
import com.gymplan.tracker.dto.ExerciseDTO;
import com.gymplan.tracker.entity.ExerciseEntity;
import com.gymplan.tracker.exception.BusinessError;
import com.gymplan.tracker.exception.BusinessException;
import com.gymplan.tracker.repository.ExerciseRepository;
import com.gymplan.tracker.repository.PlanRepository;
import com.gymplan.tracker.service.ExerciseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final PlanRepository planRepository;
    private final ExerciseConverter exerciseConverter;
    private final ExerciseRepository exerciseRepository;

    public ExerciseServiceImpl(
            PlanRepository planRepository,
            ExerciseConverter exerciseConverter,
            ExerciseRepository exerciseRepository
    ) {
        this.planRepository = planRepository;
        this.exerciseConverter = exerciseConverter;
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public ExerciseDTO createExercise(ExerciseDTO exerciseDTO) {
        List<BusinessError> businessErrors = new ArrayList<>();
        if (!planRepository.existsById(exerciseDTO.getPlanId())) {
            BusinessError businessError = new BusinessError();
            businessError.setCode("PLAN DOESN'T EXIST");
            businessError.setMessage("Plan id '" + exerciseDTO.getPlanId() + "' doesn't exist.");
            businessErrors.add(businessError);
        }

        if (businessErrors.isEmpty()) {
            ExerciseEntity exerciseEntity = exerciseConverter.convertDTOtoEntity(exerciseDTO);
            // Set id to null to make sure another exercise is not updated by mistake
            exerciseEntity.setId(null);
            exerciseEntity = exerciseRepository.save(exerciseEntity);
            return exerciseConverter.convertEntityToDTO(exerciseEntity);
        } else {
            throw new BusinessException(businessErrors);
        }
    }

    @Override
    public ExerciseDTO getExerciseById(Long id) {
        Optional<ExerciseEntity> exerciseEntityOptional = exerciseRepository.findById(id);

        if (exerciseEntityOptional.isPresent()) {
            return exerciseConverter.convertEntityToDTO(
                    exerciseEntityOptional.get()
            );
        } else {
            List<BusinessError> businessErrors = new ArrayList<>();
            BusinessError businessError = new BusinessError();
            businessError.setCode("EXERCISE DOESN'T EXIST");
            businessError.setMessage("Exercise id '" + id + "' does not exist.");
            businessErrors.add(businessError);
            throw new BusinessException(businessErrors);
        }
    }

    @Override
    public List<ExerciseDTO> getAllExercises() {
        List<ExerciseDTO> exerciseDTOs = new ArrayList<>();
        for (ExerciseEntity exerciseEntity : exerciseRepository.findAll()) {
            ExerciseDTO exerciseDTO = exerciseConverter.convertEntityToDTO(exerciseEntity);
            exerciseDTOs.add(exerciseDTO);
        }
        return exerciseDTOs;
    }

    @Override
    public ExerciseDTO updateExercise(Long id, ExerciseDTO exerciseDTO) {
        List<BusinessError> businessErrors = new ArrayList<>();
        if (exerciseDTO.getId() != null && !exerciseDTO.getId().equals(id)) {
            BusinessError businessError = new BusinessError();
            businessError.setCode("EXERCISE IDS DON'T MATCH");
            businessError.setMessage("Exercise id in the body does not match the Exercise id in the URI");
            businessErrors.add(businessError);
        }
        if (!planRepository.existsById(exerciseDTO.getPlanId())) {
            BusinessError businessError = new BusinessError();
            businessError.setCode("PLAN DOESN'T EXIST");
            businessError.setMessage("Plan id '" + exerciseDTO.getPlanId() + "' does not exist.");
            businessErrors.add(businessError);
        }

        if (businessErrors.isEmpty()) {
            ExerciseEntity exerciseEntity = exerciseConverter.convertDTOtoEntity(exerciseDTO);
            // Set entity id to uri id to make sure the id is set in the entity
            exerciseEntity.setId(id);
            exerciseEntity = exerciseRepository.save(exerciseEntity);
            return exerciseConverter.convertEntityToDTO(exerciseEntity);
        } else {
            throw new BusinessException(businessErrors);
        }
    }

    @Override
    public void deleteExercise(Long id) {
        exerciseRepository.deleteById(id);
    }

    @Override
    public List<ExerciseDTO> getExercisesByPlanId(Long planId) {
        List<BusinessError> businessErrors = new ArrayList<>();
        if (!planRepository.existsById(planId)) {
            BusinessError businessError = new BusinessError();
            businessError.setCode("PLAN DOESN'T EXIST");
            businessError.setMessage("Plan id '" + planId + "' does not exist.");
            businessErrors.add(businessError);
        }

        if (businessErrors.isEmpty()) {
            List<ExerciseDTO> exerciseDTOs = new ArrayList<>();
            for (ExerciseEntity exerciseEntity : exerciseRepository.findAllByPlanId(planId)) {
                ExerciseDTO exerciseDTO = exerciseConverter.convertEntityToDTO(exerciseEntity);
                exerciseDTOs.add(exerciseDTO);
            }
            return exerciseDTOs;
        } else {
            throw new BusinessException(businessErrors);
        }
    }

}
