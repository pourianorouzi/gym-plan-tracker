package com.gymplan.tracker.repository;

import com.gymplan.tracker.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity save(UserEntity userEntity);

    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);

}
