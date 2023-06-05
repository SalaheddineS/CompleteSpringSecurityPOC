package com.POC.AuthSecurity.Repositories;

import com.POC.AuthSecurity.Entities.UniversityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepository extends JpaRepository<UniversityEntity,Integer> {
}
