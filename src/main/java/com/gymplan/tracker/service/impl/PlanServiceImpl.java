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
            businessError.setMessage("User id '" + planDTO.getUserId() + "' does not exist.");
            businessErrors.add(businessError);
        }

        if (businessErrors.isEmpty()) {
            PlanEntity planEntity = planConverter.convertDTOtoEntity(planDTO);
            planEntity = planRepository.save(planEntity);
            return planConverter.convertEntityToDTO(planEntity);
        } else {
            throw new BusinessException(businessErrors);
        }
    }
}
