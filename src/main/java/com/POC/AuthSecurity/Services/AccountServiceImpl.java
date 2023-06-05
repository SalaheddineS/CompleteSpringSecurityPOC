package com.POC.AuthSecurity.Services;

import com.POC.AuthSecurity.Entities.RoleEntity;
import com.POC.AuthSecurity.Entities.UserEntity;
import com.POC.AuthSecurity.Repositories.RoleRepository;
import com.POC.AuthSecurity.Repositories.UserRepository;
import com.POC.AuthSecurity.Security.JwtAuthDTO;
import com.POC.AuthSecurity.Security.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @Transactional
public class AccountServiceImpl implements IAccountService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtGenerator jwtGenerator;


    BCryptPasswordEncoder bCryptPasswordEncoder (){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public AccountServiceImpl(UserRepository userRepository, RoleRepository roleRepository, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;

    }

    @Override
    public UserEntity addUser(UserEntity user) {
        if(userRepository.existsByUsername(user.getUsername())) throw new RuntimeException("User Already Exists");
        String hashPW = bCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashPW);
        return userRepository.save(user);
    }

    @Override
    public RoleEntity addRole(RoleEntity role) {
        return roleRepository.save(role);
    }

    @Override
    public String addRoleToUser(String username, String roleName) {
        try{
        UserEntity user = userRepository.findByUsername(username);
        RoleEntity role = roleRepository.findByName(roleName);
        if(user == null || role == null) throw new RuntimeException("User or Role not found");
        user.getRoles().add(role);
        return "Role added to user";
        }
        catch (Exception e){
            throw new RuntimeException("User or Role not found");
        }
    }

    @Override
    public UserEntity findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public ResponseEntity<JwtAuthDTO> login(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtGenerator.generateToken(authentication.getName());
        return new ResponseEntity<> (new JwtAuthDTO(jwt), org.springframework.http.HttpStatus.OK);

    }
    // @Override
    //public String login(String username, String password) {
      //  Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
       // SecurityContextHolder.getContext().setAuthentication(authentication);
        //return "Logged in";
    //}
}
