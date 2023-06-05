package com.POC.AuthSecurity.Controllers;

import com.POC.AuthSecurity.Entities.UniversityEntity;
import com.POC.AuthSecurity.Services.UniversityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ResponseBody
@RestController
@RequestMapping("/api/university")
public class UniversityController {
    private final UniversityServiceImpl universityService;
    @Autowired
    public UniversityController(UniversityServiceImpl universityService) {
        this.universityService = universityService;
    }

    @GetMapping("/getAll")
    public List<UniversityEntity> getAllUniversities() {
        return universityService.getAll();
    }

    @GetMapping("/getById/{id}")
    public UniversityEntity getSingleUni(@PathVariable int id){
        return universityService.getSingleUniversity(id);
    }

    @PostMapping("/addUniversity")
    public UniversityEntity addUniversity(@RequestBody UniversityEntity universityEntity){
        return universityService.addUniversity(universityEntity);
    }

    @DeleteMapping("/removeUniversity/{id}")
    public String removeUniversity(@PathVariable int id){
        return universityService.removeUniversity(id);
    }

    @PatchMapping("/updateUniversity")
    public UniversityEntity updateUniversity(@RequestBody UniversityEntity universityEntity){
        return universityService.updateUniversity(universityEntity);
    }
}
