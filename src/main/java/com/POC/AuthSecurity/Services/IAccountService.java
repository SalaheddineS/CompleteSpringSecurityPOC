package com.POC.AuthSecurity.Services;

import com.POC.AuthSecurity.Entities.RoleEntity;
import com.POC.AuthSecurity.Entities.UserEntity;
import com.POC.AuthSecurity.Security.JwtAuthDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IAccountService {
    public UserEntity addUser(UserEntity user);
    public RoleEntity addRole(RoleEntity role);
    public String addRoleToUser(String username, String roleName);
    public UserEntity findUserByUsername(String username);
    List<UserEntity> findAllUsers();
    ResponseEntity<JwtAuthDTO> login(String username, String password);
}
