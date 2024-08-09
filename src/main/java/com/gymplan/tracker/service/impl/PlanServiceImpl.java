package com.gymplan.tracker.service.impl;

import com.gymplan.tracker.converter.PlanConverter;
import com.gymplan.tracker.dto.PlanDTO;
import com.gymplan.tracker.entity.PlanEntity;
import com.gymplan.tracker.exception.BusinessError;
import com.gymplan.tracker.exception.BusinessException;
import com.gymplan.tracker.repository.PlanRepository;
import com.gymplan.tracker.repository.UserRepository;
import com.gymplan.tracker.service.PlanService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;
    private final PlanConverter planConverter;
    private final UserRepository userRepository;

    public PlanServiceImpl(PlanRepository planRepository, PlanConverter planConverter, UserRepository userRepository) {
        this.planRepository = planRepository;
        this.planConverter = planConverter;
        this.userRepository = userRepository;
    }

    @Override
    public PlanDTO createPlan(PlanDTO planDTO) {
        List<BusinessError> businessErrors = new ArrayList<>();
        if (!userRepository.existsById(planDTO.getUserId())) {
            BusinessError businessError = new BusinessError();
            businessError.setCode("USER DOESN'T EXIST");
            businessError.setMessage("User id '" + planDTO.getUserId() + "' doesn't exist.");
            businessErrors.add(businessError);
        }

        if (businessErrors.isEmpty()) {
            PlanEntity planEntity = planConverter.convertDTOtoEntity(planDTO);
            // Set id to null to make sure another plan is not updated by mistake
            planEntity.setId(null);
            planEntity = planRepository.save(planEntity);
            return planConverter.convertEntityToDTO(planEntity);
        } else {
            throw new BusinessException(businessErrors);
        }
    }

    @Override
    public PlanDTO getPlanById(Long id) {
        Optional<PlanEntity> planEntityOptional = planRepository.findById(id);

        if (planEntityOptional.isPresent()) {
            return planConverter.convertEntityToDTO(
                    planEntityOptional.get()
            );
        } else {
            List<BusinessError> businessErrors = new ArrayList<>();
            BusinessError businessError = new BusinessError();
            businessError.setCode("PLAN DOESN'T EXIST");
            businessError.setMessage("Plan id '" + id + "' does not exist.");
            businessErrors.add(businessError);
            throw new BusinessException(businessErrors);
        }
    }

    @Override
    public List<PlanDTO> getAllPlans() {
        List<PlanDTO> planDTOs = new ArrayList<>();
        for (PlanEntity planEntity : planRepository.findAll()) {
            PlanDTO planDTO = planConverter.convertEntityToDTO(planEntity);
            planDTOs.add(planDTO);
        }
        return planDTOs;
    }

    @Override
    public PlanDTO updatePlan(Long id, PlanDTO planDTO) {
        List<BusinessError> businessErrors = new ArrayList<>();
        if (planDTO.getId() != null && !planDTO.getId().equals(id)) {
            BusinessError businessError = new BusinessError();
            businessError.setCode("PLAN IDS DON'T MATCH");
            businessError.setMessage("Plan id in the body does not match the plan id in the URI");
            businessErrors.add(businessError);
        }
        if (!userRepository.existsById(planDTO.getUserId())) {
            BusinessError businessError = new BusinessError();
            businessError.setCode("USER DOESN'T EXIST");
            businessError.setMessage("User id '" + planDTO.getUserId() + "' does not exist.");
            businessErrors.add(businessError);
        }

        if (businessErrors.isEmpty()) {
            PlanEntity planEntity = planConverter.convertDTOtoEntity(planDTO);
            // Set entity id to uri id to make sure the id is set in the entity
            planEntity.setId(id);
            planEntity = planRepository.save(planEntity);
            return planConverter.convertEntityToDTO(planEntity);
        } else {
            throw new BusinessException(businessErrors);
        }
    }

    @Override
    public void deletePlan(Long id) {
        planRepository.deleteById(id);
    }

    @Override
    public List<PlanDTO> getExercisesByUserId(Long userId) {
        List<BusinessError> businessErrors = new ArrayList<>();
        if (!userRepository.existsById(userId)) {
            BusinessError businessError = new BusinessError();
            businessError.setCode("USER DOESN'T EXIST");
            businessError.setMessage("User id '" + userId + "' does not exist.");
            businessErrors.add(businessError);
        }

        if (businessErrors.isEmpty()) {
            List<PlanDTO> planDTOs = new ArrayList<>();
            for (PlanEntity planEntity : planRepository.findAllByUserId(userId)) {
                PlanDTO planDTO = planConverter.convertEntityToDTO(planEntity);
                planDTOs.add(planDTO);
            }
            return planDTOs;
        } else {
            throw new BusinessException(businessErrors);
        }
    }
}
