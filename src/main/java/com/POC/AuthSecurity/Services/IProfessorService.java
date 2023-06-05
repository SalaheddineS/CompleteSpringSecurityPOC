package com.POC.AuthSecurity.Services;

import com.POC.AuthSecurity.Entities.ProfessorEntity;

import java.util.ArrayList;
import java.util.List;

public interface IProfessorService {
    List<ProfessorEntity> getAll();
    ProfessorEntity getSingleProfessor(int id);
    ProfessorEntity addProfessor(ProfessorEntity professorEntity);
    String removeProfessor(int id);
    ProfessorEntity updateProfessor(ProfessorEntity professorEntity);
    String assignToUniversity(int idProf,int idUni);
}
