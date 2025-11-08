package com.icheha.aprendia_api.auth.domain.services.impl;

import com.icheha.aprendia_api.auth.domain.entities.Persona;
import com.icheha.aprendia_api.auth.domain.exceptions.InvalidCredentialsException;
import com.icheha.aprendia_api.auth.domain.exceptions.UserNotFoundException;
import com.icheha.aprendia_api.auth.domain.repositories.IPersonaRepository;
import com.icheha.aprendia_api.auth.domain.services.IAuthDomainService;
import com.icheha.aprendia_api.auth.domain.valueobjects.Curp;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Implementación del servicio de dominio de autenticación
 * Contiene la lógica de negocio pura relacionada con autenticación
 */
@Service
public class AuthDomainServiceImpl implements IAuthDomainService {
    
    private final IPersonaRepository personaRepository;
    private final PasswordEncoder passwordEncoder;
    
    public AuthDomainServiceImpl(@Lazy IPersonaRepository personaRepository, PasswordEncoder passwordEncoder) {
        this.personaRepository = personaRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public Persona authenticateUser(Curp curp, String password) {
        if (curp == null) {
            throw new InvalidCredentialsException("CURP no puede ser nulo");
        }
        
        if (password == null || password.trim().isEmpty()) {
            throw new InvalidCredentialsException("Contraseña no puede ser nula o vacía");
        }
        
        Persona persona = findUserByCurp(curp);
        
        // Verificar que la persona tenga password
        if (!persona.tienePassword()) {
            throw new InvalidCredentialsException("La persona no tiene contraseña configurada");
        }
        
        // Verificar contraseña usando PasswordEncoder directamente
        if (!passwordEncoder.matches(password, persona.getPassword().getHashedValue())) {
            throw new InvalidCredentialsException("Contraseña incorrecta");
        }
        
        return persona;
    }
    
    @Override
    public Persona findUserByCurp(Curp curp) {
        if (curp == null) {
            throw new IllegalArgumentException("CURP no puede ser nulo");
        }
        
        return personaRepository.findByCurp(curp)
                .orElseThrow(() -> UserNotFoundException.byCurp(curp.getValue()));
    }
    
    @Override
    public Persona findUserById(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("ID de usuario no puede ser nulo");
        }
        
        return personaRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.byId(userId));
    }
    
    @Override
    public boolean userExists(Curp curp) {
        if (curp == null) {
            return false;
        }
        
        return personaRepository.existsByCurp(curp);
    }
}

