package com.ssafy.sandbox.social.repository;

import com.ssafy.sandbox.social.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    User save(User user);

    boolean existsById(Long id);
}