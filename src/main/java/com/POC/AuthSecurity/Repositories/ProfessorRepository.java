package com.POC.AuthSecurity.Repositories;

import com.POC.AuthSecurity.Entities.ProfessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<ProfessorEntity,Integer> {
}
