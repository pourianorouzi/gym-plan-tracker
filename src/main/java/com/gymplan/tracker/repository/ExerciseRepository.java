package com.gymplan.tracker.repository;

import com.gymplan.tracker.entity.ExerciseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseRepository extends CrudRepository<ExerciseEntity, Long> {

    ExerciseEntity save(ExerciseEntity exerciseEntity);

    void deleteById(Long id);

    Optional<ExerciseEntity> findById(Long id);

    List<ExerciseEntity> findAllByPlanId(Long id);

}
