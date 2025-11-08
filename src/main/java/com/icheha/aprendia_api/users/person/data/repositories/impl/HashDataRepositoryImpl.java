package com.icheha.aprendia_api.users.person.data.repositories.impl;

import com.icheha.aprendia_api.users.person.domain.repositories.IHashDataRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class HashDataRepositoryImpl implements IHashDataRepository {
    
    private final BCryptPasswordEncoder passwordEncoder;
    private final int salts;
    
    public HashDataRepositoryImpl(@Lazy BCryptPasswordEncoder passwordEncoder,
                                  @Value("${app.security.bcrypt.salts:10}") int salts) {
        this.passwordEncoder = passwordEncoder;
        this.salts = salts;
    }
    
    @Override
    public String hash(String text) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("El texto a hashear no puede ser nulo o vacío");
        }
        return passwordEncoder.encode(text);
    }
}

