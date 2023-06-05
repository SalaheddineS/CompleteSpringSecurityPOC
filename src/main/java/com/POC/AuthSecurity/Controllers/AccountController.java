package com.POC.AuthSecurity.Controllers;

import com.POC.AuthSecurity.Entities.RoleEntity;
import com.POC.AuthSecurity.Entities.UserEntity;
import com.POC.AuthSecurity.Security.JwtAuthDTO;
import com.POC.AuthSecurity.Services.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@ResponseBody
@RequestMapping("/api/account")
public class AccountController {
    private final AccountServiceImpl accountService;

    @Autowired
    public AccountController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/getAll")
    public List<UserEntity> getAll(){
        return accountService.findAllUsers();
    }

    @PostMapping("/getSingle")
    public UserEntity getSingle(@RequestBody String username){
        return accountService.findUserByUsername(username);
    }

    @PostMapping("/addUser")
    public UserEntity addUser(@RequestBody UserEntity user){
        return accountService.addUser(user);
    }

    @PostMapping("/addRole")
    public RoleEntity addRole(@RequestBody RoleEntity role){
        return accountService.addRole(role);
    }

    @PostMapping("/addRoleToUser")
    public String addRoleToUser(@RequestBody Map<String,String> values){
        try{
        return accountService.addRoleToUser(values.get("username"), values.get("name"));
}
        catch (Exception e){
            throw new RuntimeException("User or Role not found");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthDTO> login(@RequestBody Map<String,String> values){
        return accountService.login(values.get("username"), values.get("password"));
    }


}
