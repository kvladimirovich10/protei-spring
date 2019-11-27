package com.protei.spring.repository;

import com.protei.spring.model.User;
import com.protei.spring.model.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserStatusRepository extends JpaRepository<UserStatus, Long> {
    Optional<UserStatus> findUserStatusById (Long id);
}
