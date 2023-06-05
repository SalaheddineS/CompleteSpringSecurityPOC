package com.POC.AuthSecurity.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class UserEntity {
    @Id @GeneratedValue
    int id;
    String username;
    String password;

    @ManyToMany(fetch = FetchType.EAGER)
    Collection<RoleEntity> roles;
}
