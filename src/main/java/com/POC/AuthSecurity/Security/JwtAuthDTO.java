package com.POC.AuthSecurity.Security;

import lombok.Data;

@Data
public class JwtAuthDTO {
    private String token;
    private String tokenType="Bearer ";

    public JwtAuthDTO(String token) {
        this.token = token;
    }
}
