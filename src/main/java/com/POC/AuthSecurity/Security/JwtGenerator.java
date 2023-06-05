package com.POC.AuthSecurity.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtGenerator {

    public String generateToken(String username) {
        Date ExpireDate = new Date(System.currentTimeMillis() + SecurityConstants.JWT_EXPIRATION_TIME);
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(ExpireDate)
                .signWith(Keys.hmacShaKeyFor(SecurityConstants.JWT_SECRET.getBytes()), SignatureAlgorithm.HS512)
                .compact();
        return token;
    }

    public String getUsernameFromToken(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SecurityConstants.JWT_SECRET.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(SecurityConstants.JWT_SECRET.getBytes())).build().parseClaimsJws(token);
            return true;
        }
        catch (Exception e){
            throw new IllegalStateException("Token is not valid");
        }

    }
}
