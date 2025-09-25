package com.icheha.aprendia_api.auth.services.utils;

import com.icheha.aprendia_api.auth.data.dtos.response.TokenPayloadDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${jwt.secret:aprendia-secret-key-for-jwt-token-generation-2024}")
    private String secret;

    @Value("${jwt.expiration:3600}")
    private Long expiration;

    private SecretKey getSigningKey() {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(TokenPayloadDto payload) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id_persona", payload.getIdPersona());
        claims.put("nombre", payload.getNombre());
        claims.put("role_name", payload.getRoleName());
        claims.put("disability_name", payload.getDisabilityName());
        claims.put("disability_id", payload.getDisabilityId());
        claims.put("learning_path_id", payload.getLearningPathId());

        return createToken(claims, payload.getIdPersona().toString());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration * 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public TokenPayloadDto extractTokenPayload(String token) {
        Claims claims = extractAllClaims(token);
        
        TokenPayloadDto payload = new TokenPayloadDto();
        payload.setIdPersona(claims.get("id_persona", Long.class));
        payload.setNombre(claims.get("nombre", String.class));
        payload.setRoleName(claims.get("role_name", String.class));
        payload.setDisabilityName(claims.get("disability_name", String.class));
        payload.setDisabilityId(claims.get("disability_id", Long.class));
        payload.setLearningPathId(claims.get("learning_path_id", Long.class));
        payload.setIat(claims.getIssuedAt().getTime() / 1000);
        payload.setExp(claims.getExpiration().getTime() / 1000);
        
        return payload;
    }

    public Boolean validateToken(String token) {
        try {
            extractAllClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public Boolean validateToken(String token, org.springframework.security.core.userdetails.UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public Boolean isTokenExpired(String token) {
        try {
            Claims claims = extractAllClaims(token);
            return claims.getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return true;
        }
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUsernameFromToken(String token) {
        return extractAllClaims(token).getSubject();
    }
}
