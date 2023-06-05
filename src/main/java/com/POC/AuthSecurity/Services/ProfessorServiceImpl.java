package com.POC.AuthSecurity.Services;

import com.POC.AuthSecurity.Entities.ProfessorEntity;
import com.POC.AuthSecurity.Entities.UniversityEntity;
import com.POC.AuthSecurity.Repositories.ProfessorRepository;
import com.POC.AuthSecurity.Repositories.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorServiceImpl implements IProfessorService{
    private final ProfessorRepository professorRepository;
    private final UniversityRepository universityRepository;

    @Autowired
    public ProfessorServiceImpl(ProfessorRepository professorRepository, UniversityRepository universityRepository){
        this.professorRepository=professorRepository;
        this.universityRepository = universityRepository;
    }

    @Override
    public List<ProfessorEntity> getAll() {
        return professorRepository.findAll();
    }

    @Override
    public ProfessorEntity getSingleProfessor(int id) {
        return professorRepository.findById(id).get();
    }

    @Override
    public ProfessorEntity addProfessor(ProfessorEntity professorEntity) {
        try
        {
            professorRepository.save(professorEntity);
            return professorEntity;
        }
        catch(Exception e)
        {
            throw new RuntimeException("Error whilst Adding the Professor");
        }
    }

    @Override
    public String removeProfessor(int id) {
        try
        {
            professorRepository.deleteById(id);
            return "Successfuly Remove The Professor";
        }
        catch (Exception e)
        {
            throw new RuntimeException("Error whilst deleting the Professors");
        }
    }

    @Override
    public ProfessorEntity updateProfessor(ProfessorEntity professorEntity) {
        try
        {
            ProfessorEntity professor = professorRepository.findById(professorEntity.getId()).get();
            if( professorEntity.getFirstName() != null && !professorEntity.getFirstName().isEmpty()) professor.setFirstName(professorEntity.getFirstName());
            if(professorEntity.getLastName() != null && !professorEntity.getLastName().isEmpty()) professor.setLastName(professorEntity.getLastName());
            if(professorEntity.getUniversity() != null ) professor.setUniversity(professorEntity.getUniversity());
            return professorRepository.save(professor);
        }
        catch (Exception e)
        {
            throw new RuntimeException("Error whilst updating the Professor");
        }
    }

    @Override
    public String assignToUniversity(int idProf, int idUni) {
        try
        {
            ProfessorEntity professorEntity= professorRepository.findById(idProf).get();
            UniversityEntity UniversityEntity = universityRepository.findById(idUni).get();
            professorEntity.setUniversity(UniversityEntity);
            professorRepository.save(professorEntity);
            return "Assigned Professor to University Successfully";
        }
        catch (Exception e)
        {
            throw new RuntimeException("Error whilst assigning to University");
        }
    }
}
