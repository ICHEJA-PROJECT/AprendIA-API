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
    
    /**
     * Genera un token JWT desde un mapa de claims
     * Usado principalmente para tokens de estudiantes con estructura personalizada
     */
    public String generateTokenFromClaims(java.util.Map<String, Object> claims) {
        SecretKey key = Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes(StandardCharsets.UTF_8));
        
        var builder = Jwts.builder();
        
        // Agregar todos los claims del mapa
        for (java.util.Map.Entry<String, Object> entry : claims.entrySet()) {
            builder.claim(entry.getKey(), entry.getValue());
        }
        
        // Establecer subject si existe studentId o personId
        Object subject = claims.get("studentId");
        if (subject == null) {
            subject = claims.get("personId");
        }
        if (subject != null) {
            builder.setSubject(subject.toString());
        }
        
        return builder
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
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            // Token expirado pero formato válido
            return true; // Retornar true para que isTokenExpired lo maneje
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
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            // Token expirado
            return true;
        } catch (Exception e) {
            // Si hay otro error, asumir que no está expirado (el problema es otro)
            return false;
        }
    }
    
    public TokenPayloadDto extractPayload(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes(StandardCharsets.UTF_8));
            Claims claims;
            
            try {
                // Intentar parsear el token normalmente
                claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            } catch (io.jsonwebtoken.ExpiredJwtException e) {
                // Si el token está expirado, aún podemos extraer los claims
                claims = e.getClaims();
            } catch (io.jsonwebtoken.security.SignatureException | io.jsonwebtoken.MalformedJwtException e) {
                // Si hay un problema con la firma o formato, no podemos extraer el payload
                return null;
            }
            
            // Intentar obtener idPersona, si no existe intentar personId
            Long idPersona = claims.get("idPersona", Long.class);
            if (idPersona == null) {
                Object personIdObj = claims.get("personId");
                if (personIdObj != null) {
                    idPersona = personIdObj instanceof Long ? (Long) personIdObj : Long.valueOf(personIdObj.toString());
                }
            }
            
            return TokenPayloadDto.builder()
                    .idPersona(idPersona)
                    .username(claims.get("username", String.class))
                    .nombre(claims.get("nombre", String.class))
                    .roleName(claims.get("roleName", String.class))
                    .disabilityName(claims.get("disabilityName", String.class))
                    .disabilityId(claims.get("disabilityId", Long.class))
                    .learningPathId(claims.get("learningPathId", Long.class))
                    .iat(claims.getIssuedAt() != null ? claims.getIssuedAt().getTime() : null)
                    .exp(claims.getExpiration() != null ? claims.getExpiration().getTime() : null)
                    .build();
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Extrae los claims directamente del token JWT sin convertirlos a TokenPayloadDto
     * Útil cuando se necesita acceder a claims personalizados
     */
    public Claims extractClaims(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes(StandardCharsets.UTF_8));
            return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        } catch (Exception e) {
            return null;
        }
    }
}
