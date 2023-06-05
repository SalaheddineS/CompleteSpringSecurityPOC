package com.POC.AuthSecurity.Repositories;

import com.POC.AuthSecurity.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    public UserEntity findByUsername(String username);
    public boolean existsByUsername(String username);
}
