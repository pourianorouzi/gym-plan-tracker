package com.gymplan.tracker.repository;

import com.gymplan.tracker.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity save(UserEntity userEntity);

    Optional<UserEntity> findById(Long id);

    Optional<UserEntity> findByUsernameAndPassword(String username, String password);

    boolean existsById(Long id);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

}
