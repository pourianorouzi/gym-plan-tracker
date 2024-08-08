package com.gymplan.tracker.repository;

import com.gymplan.tracker.entity.PlanEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlanRepository extends CrudRepository<PlanEntity, Long> {

    PlanEntity save(PlanEntity planEntity);

    void deleteById(Long id);

    Optional<PlanEntity> findById(Long id);

    List<Optional<PlanEntity>> findAllByUserId(Long id);

}
