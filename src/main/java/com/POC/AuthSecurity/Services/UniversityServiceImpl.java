package com.POC.AuthSecurity.Services;

import com.POC.AuthSecurity.Entities.UniversityEntity;
import com.POC.AuthSecurity.Repositories.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityServiceImpl implements IUniversityService{
    private final UniversityRepository universityRepository;
    @Autowired
    public UniversityServiceImpl(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    public List<UniversityEntity> getAll() {
        return universityRepository.findAll();
    }

    @Override
    public UniversityEntity getSingleUniversity(int id) {
        return universityRepository.findById(id).get();
    }

    @Override
    public UniversityEntity addUniversity(UniversityEntity universityEntity) {
        return universityRepository.save(universityEntity);
    }

    @Override
    public String removeUniversity(int id) {
        universityRepository.deleteById(id);
        return "University with id "+id+" is removed";
    }

    @Override
    public UniversityEntity updateUniversity(UniversityEntity universityEntity) {
        try
        {
            UniversityEntity university = universityRepository.findById(universityEntity.getId()).get();
            if( universityEntity.getName() != null && !universityEntity.getName().isEmpty()) university.setName("Updated Name");
            if( universityEntity.getAddress() != null && !universityEntity.getAddress().isEmpty()) university.setAddress("Updated Address");
            return universityRepository.save(universityEntity);
        }catch (Exception e){
            throw new RuntimeException("University with id "+ universityEntity.getId()+" not found");
        }
    }
}
