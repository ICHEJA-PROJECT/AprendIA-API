package com.icheha.aprendia_api.core.utils;

import com.icheha.aprendia_api.auth.data.dtos.response.TokenPayloadDto;
import com.icheha.aprendia_api.core.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {
    
    @Autowired
    private JwtConfig jwtConfig;
    
    public String generateToken(TokenPayloadDto payload) {
        SecretKey key = Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes(StandardCharsets.UTF_8));
        
        return Jwts.builder()
                .setSubject(payload.getUsername())
                .claim("idPersona", payload.getIdPersona())
                .claim("username", payload.getUsername())
                .claim("nombre", payload.getNombre())
                .claim("roleName", payload.getRoleName())
                .claim("disabilityName", payload.getDisabilityName())
                .claim("disabilityId", payload.getDisabilityId())
                .claim("learningPathId", payload.getLearningPathId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpiration()))
                .signWith(key)
                .compact();
    }
    
    public boolean validateToken(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes(StandardCharsets.UTF_8));
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isTokenExpired(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes(StandardCharsets.UTF_8));
            Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
            return claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return true;
        }
    }
    
    public TokenPayloadDto extractPayload(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes(StandardCharsets.UTF_8));
            Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
            
            return TokenPayloadDto.builder()
                    .idPersona(claims.get("idPersona", Long.class))
                    .username(claims.get("username", String.class))
                    .nombre(claims.get("nombre", String.class))
                    .roleName(claims.get("roleName", String.class))
                    .disabilityName(claims.get("disabilityName", String.class))
                    .disabilityId(claims.get("disabilityId", Long.class))
                    .learningPathId(claims.get("learningPathId", Long.class))
                    .iat(claims.getIssuedAt().getTime())
                    .exp(claims.getExpiration().getTime())
                    .build();
        } catch (Exception e) {
            return null;
        }
    }
}
