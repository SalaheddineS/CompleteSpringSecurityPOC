package com.POC.AuthSecurity.Services;

import com.POC.AuthSecurity.Entities.UniversityEntity;
import java.util.List;

public interface IUniversityService {
    List<UniversityEntity> getAll();
    UniversityEntity getSingleUniversity(int id);
    UniversityEntity addUniversity(UniversityEntity universityEntity);
    String removeUniversity(int id);
    UniversityEntity updateUniversity(UniversityEntity universityEntity);

}
