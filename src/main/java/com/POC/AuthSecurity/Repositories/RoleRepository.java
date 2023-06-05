package com.POC.AuthSecurity.Repositories;

import com.POC.AuthSecurity.Entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    public RoleEntity findByName(String name);
}
