package com.gymplan.tracker.converter;

import com.gymplan.tracker.dto.PlanDTO;
import com.gymplan.tracker.entity.PlanEntity;
import com.gymplan.tracker.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class PlanConverter {

    private final UserService userService;
    private final UserConverter userConverter;

    public PlanConverter(UserService userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    public PlanEntity convertDTOtoEntity(PlanDTO planDTO) {
        PlanEntity planEntity = new PlanEntity();

        planEntity.setId(planDTO.getId());

        if (planDTO.getUserId() != null) {
            planEntity.setUser(
                    userConverter.convertDTOtoEntity(
                            userService.findUserById(planDTO.getUserId())
                    )
            );
        }

        planEntity.setTitle(planDTO.getTitle());
        planEntity.setDescription(planDTO.getDescription());

        return planEntity;
    }

    public PlanDTO convertEntityToDTO(PlanEntity planEntity) {
        PlanDTO planDTO = new PlanDTO();

        planDTO.setId(planEntity.getId());
        planDTO.setUserId(
                planEntity.getUser().getId()
        );
        planDTO.setTitle(planEntity.getTitle());
        planDTO.setDescription(planEntity.getDescription());

        return planDTO;
    }

}
