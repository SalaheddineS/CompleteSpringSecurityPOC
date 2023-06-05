package com.POC.AuthSecurity.Controllers;

import com.POC.AuthSecurity.Entities.ProfessorEntity;
import com.POC.AuthSecurity.Services.ProfessorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ResponseBody
@RestController
@RequestMapping("/api/professor")
public class ProfessorController {
    private final ProfessorServiceImpl professorService;
    @Autowired
    public ProfessorController(ProfessorServiceImpl professorService) {
        this.professorService = professorService;
    }

    @GetMapping("/getAll")
    public List<ProfessorEntity> getAll(){
        return professorService.getAll();
    }

    @GetMapping("/getSingleProfessor/{id}")
    public ProfessorEntity getSingleProfessor(@PathVariable int id){
        return professorService.getSingleProfessor(id);
    }

    @PostMapping("/addProfessor")
    public ProfessorEntity addProfessor(@RequestBody ProfessorEntity professorEntity){
        return professorService.addProfessor(professorEntity);
    }

    @DeleteMapping("/removeProfessor/{id}")
    public String removeProfessor(@PathVariable int id){
        return professorService.removeProfessor(id);
    }

    @PatchMapping("/updateProfessor")
    public ProfessorEntity updateProfessor(@RequestBody ProfessorEntity professorEntity){
        return professorService.updateProfessor(professorEntity);
    }

    @PostMapping("/assignToUniversity/{professorId}/{universityId}")
    public String addProfessorToUniversity(@PathVariable int professorId, @PathVariable int universityId){
        return professorService.assignToUniversity(professorId, universityId);
    }
}
