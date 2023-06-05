package com.POC.AuthSecurity.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "University")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UniversityEntity {
    @Id
    @GeneratedValue
    int id;
    @NotNull
    String name;
    @NotNull
    String address;
    @JsonManagedReference
    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL)
    List<ProfessorEntity> professorList = new ArrayList<>();

}
