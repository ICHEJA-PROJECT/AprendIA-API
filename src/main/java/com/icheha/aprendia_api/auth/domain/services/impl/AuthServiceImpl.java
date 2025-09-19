package com.icheha.aprendia_api.auth.domain.services.impl;

import com.icheha.aprendia_api.auth.data.dtos.request.LoginCredentialsDto;
import com.icheha.aprendia_api.auth.data.dtos.request.LoginQrDto;
import com.icheha.aprendia_api.auth.data.dtos.response.LoginResponseDto;
import com.icheha.aprendia_api.auth.data.dtos.response.ValidateTokenResponseDto;
import com.icheha.aprendia_api.auth.data.dtos.response.TokenPayloadDto;
import com.icheha.aprendia_api.auth.domain.entities.Persona;
import com.icheha.aprendia_api.auth.domain.entities.pivot.PersonaRol;
import com.icheha.aprendia_api.auth.domain.repositories.PersonaRepository;
import com.icheha.aprendia_api.auth.domain.repositories.RolRepository;
import com.icheha.aprendia_api.auth.domain.services.IAuthService;
import com.icheha.aprendia_api.auth.utils.EncryptionUtil;
import com.icheha.aprendia_api.auth.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements IAuthService {
    
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
    
    // Error messages
    private static final String INVALID_CREDENTIALS = "Invalid credentials";
    private static final String USER_NOT_FOUND = "User not found";
    private static final String USER_ROLE_NOT_FOUND = "User role not found";
    private static final String LOGIN_FAILED = "Login failed";
    private static final String INVALID_QR_TOKEN = "Invalid QR token";
    private static final String TOKEN_VALIDATION_FAILED = "Token validation failed";
    private static final String INVALID_TOKEN_FORMAT = "Invalid token format";
    private static final String TOKEN_EXPIRED = "Token is expired";
    private static final String TOKEN_VALID = "Token is valid";
    
    // Default values
    private static final String DEFAULT_DISABILITY_NAME = "Sin discapacidad";
    private static final Long DEFAULT_DISABILITY_ID = 0L;
    private static final Long DEFAULT_LEARNING_PATH_ID = 2L;
    
    @Autowired
    private PersonaRepository personaRepository;
    
    @Autowired
    private RolRepository rolRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private EncryptionUtil encryptionUtil;
    
    public LoginResponseDto loginWithCredentials(LoginCredentialsDto loginDto) {
        try {
            logger.debug("Attempting login with credentials for CURP: {}", loginDto.getCurp());
            
            Persona persona = validateCredentials(loginDto);
            LoginResponseDto loginResponse = generateLoginResponse(persona);
            
            logger.info("User {} logged in successfully with credentials.", persona.getCurp());
            
            return loginResponse;
        } catch (Exception e) {
            logger.error("Login with credentials failed", e);
            throw new RuntimeException(LOGIN_FAILED, e);
        }
    }
    
    public LoginResponseDto loginWithQR(LoginQrDto loginDto) {
        try {
            logger.debug("Attempting login with QR token");
            
            Persona persona = validateQRToken(loginDto.getToken());
            LoginResponseDto loginResponse = generateLoginResponse(persona);
            
            logger.info("User {} logged in successfully with QR.", persona.getCurp());
            
            return loginResponse;
        } catch (Exception e) {
            logger.error("Login with QR failed", e);
            throw new RuntimeException(INVALID_QR_TOKEN, e);
        }
    }
    
    public ValidateTokenResponseDto validateToken(String token) {
        try {
            logger.debug("Validating token");
            
            if (!jwtUtil.validateToken(token)) {
                return new ValidateTokenResponseDto(
                    false,
                    false,
                    null,
                    INVALID_TOKEN_FORMAT
                );
            }
            
            if (jwtUtil.isTokenExpired(token)) {
                TokenPayloadDto expiredPayload = jwtUtil.extractTokenPayload(token);
                return new ValidateTokenResponseDto(
                    false,
                    true,
                    expiredPayload,
                    TOKEN_EXPIRED
                );
            }
            
            TokenPayloadDto payload = jwtUtil.extractTokenPayload(token);
            logger.info("Token validated successfully for user {}", payload.getNombre());
            
            return new ValidateTokenResponseDto(
                true,
                false,
                payload,
                TOKEN_VALID
            );
        } catch (Exception e) {
            logger.error("Token validation failed", e);
            return new ValidateTokenResponseDto(
                false,
                false,
                null,
                TOKEN_VALIDATION_FAILED
            );
        }
    }
    
    private Persona validateCredentials(LoginCredentialsDto loginDto) {
        Optional<Persona> personaOpt = personaRepository.findByCurpWithRoles(loginDto.getCurp());
        
        if (personaOpt.isEmpty()) {
            throw new RuntimeException(INVALID_CREDENTIALS);
        }
        
        Persona persona = personaOpt.get();
        
        if (!passwordEncoder.matches(loginDto.getPassword(), persona.getPassword())) {
            throw new RuntimeException(INVALID_CREDENTIALS);
        }
        
        return persona;
    }
    
    private Persona validateQRToken(String encryptedToken) {
        String decryptedToken = encryptionUtil.decrypt(encryptedToken);
        logger.debug("QR token decrypted successfully");
        
        if (!jwtUtil.validateToken(decryptedToken)) {
            throw new RuntimeException(INVALID_QR_TOKEN);
        }
        
        TokenPayloadDto payload = jwtUtil.extractTokenPayload(decryptedToken);
        logger.debug("QR token decoded successfully");
        
        Optional<Persona> personaOpt = personaRepository.findById(payload.getIdPersona());
        if (personaOpt.isEmpty()) {
            throw new RuntimeException(USER_NOT_FOUND);
        }
        
        return personaOpt.get();
    }
    
    private LoginResponseDto generateLoginResponse(Persona persona) {
        PersonaRol personaRol = getUserRole(persona.getIdPersona());
        
        TokenPayloadDto payload = createTokenPayload(persona, personaRol);
        String token = jwtUtil.generateToken(payload);
        
        return new LoginResponseDto(token, payload);
    }
    
    private PersonaRol getUserRole(Long personaId) {
        Optional<PersonaRol> personaRolOpt = rolRepository.findByPersonaId(personaId);
        if (personaRolOpt.isEmpty()) {
            throw new RuntimeException(USER_ROLE_NOT_FOUND);
        }
        return personaRolOpt.get();
    }
    
    private TokenPayloadDto createTokenPayload(Persona persona, PersonaRol personaRol) {
        String fullName = formatPersonName(persona.getPrimerNombre(), persona.getSegundoNombre());
        
        TokenPayloadDto payload = new TokenPayloadDto();
        payload.setIdPersona(persona.getIdPersona());
        payload.setNombre(fullName);
        payload.setRoleName(personaRol.getRol().getNombre());
        payload.setDisabilityName(DEFAULT_DISABILITY_NAME);
        payload.setDisabilityId(DEFAULT_DISABILITY_ID);
        payload.setLearningPathId(DEFAULT_LEARNING_PATH_ID);
        
        return payload;
    }
    
    private String formatPersonName(String primerNombre, String segundoNombre) {
        return primerNombre + (segundoNombre != null && !segundoNombre.isEmpty() ? " " + segundoNombre : "");
    }
}
