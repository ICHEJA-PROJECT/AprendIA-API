package com.icheha.aprendia_api.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    
    @Value("${jwt.secret:aprendia-secret-key-2024}")
    private String secret;
    
    @Value("${jwt.expiration:86400000}") // 24 horas en milisegundos
    private Long expiration;
    
    @Value("${jwt.header:Authorization}")
    private String header;
    
    @Value("${jwt.prefix:Bearer }")
    private String prefix;
    
    public String getSecret() {
        return secret;
    }
    
    public Long getExpiration() {
        return expiration;
    }
    
    public String getHeader() {
        return header;
    }
    
    public String getPrefix() {
        return prefix;
    }
}
