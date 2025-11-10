package com.icheha.aprendia_api.auth.services.impl;

import com.icheha.aprendia_api.auth.services.mappers.TokenPayloadMapper;
import com.icheha.aprendia_api.auth.data.dtos.request.LoginCredentialsDto;
import com.icheha.aprendia_api.auth.data.dtos.request.LoginQrDto;
import com.icheha.aprendia_api.auth.data.dtos.response.LoginResponseDto;
import com.icheha.aprendia_api.auth.data.dtos.response.ValidateTokenResponseDto;
import com.icheha.aprendia_api.auth.data.dtos.response.TokenPayloadDto;
import com.icheha.aprendia_api.auth.domain.entities.Persona;
import com.icheha.aprendia_api.auth.domain.entities.PersonaRol;
import com.icheha.aprendia_api.auth.domain.exceptions.InvalidCredentialsException;
import com.icheha.aprendia_api.auth.domain.exceptions.InvalidTokenException;
import com.icheha.aprendia_api.auth.domain.exceptions.UserNotFoundException;
import com.icheha.aprendia_api.auth.domain.exceptions.UserRoleNotFoundException;
import com.icheha.aprendia_api.auth.domain.repositories.IRolRepository;
import com.icheha.aprendia_api.auth.domain.services.IAuthDomainService;
import com.icheha.aprendia_api.auth.domain.valueobjects.Curp;
import com.icheha.aprendia_api.auth.services.IAuthService;
import com.icheha.aprendia_api.auth.services.utils.EncryptionUtil;
import com.icheha.aprendia_api.core.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements IAuthService {
    
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
    
    // Error messages
    private static final String LOGIN_FAILED = "Error en el proceso de login";
    private static final String INVALID_QR_TOKEN = "Token QR inválido";
    private static final String TOKEN_VALIDATION_FAILED = "Error en la validación del token";
    private static final String INVALID_TOKEN_FORMAT = "Formato de token inválido";
    private static final String TOKEN_EXPIRED = "Token expirado";
    private static final String TOKEN_VALID = "Token válido";
    
    @Autowired
    private IAuthDomainService authDomainService;
    
    @Autowired
    private IRolRepository rolRepository;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private EncryptionUtil encryptionUtil;
    
    @Autowired
    private TokenPayloadMapper tokenPayloadMapper;
    
    @Autowired
    private com.icheha.aprendia_api.users.student.domain.repositories.IStudentRepository studentRepository;
    
    @Autowired
    private com.icheha.aprendia_api.preferences.impairments.services.IStudentImpairmentService studentImpairmentService;
    
    public LoginResponseDto loginWithCredentials(LoginCredentialsDto loginDto) {
        try {
            logger.debug("Attempting login with credentials for CURP: {}", loginDto.getCurp());
            
            Curp curp;
            try {
                curp = new Curp(loginDto.getCurp());
            } catch (IllegalArgumentException e) {
                logger.warn("Invalid CURP format: {} - {}", loginDto.getCurp(), e.getMessage());
                throw new InvalidCredentialsException("Formato de CURP inválido: " + e.getMessage());
            }
            
            Persona persona = authDomainService.authenticateUser(curp, loginDto.getPassword());
            
            // Buscar si es estudiante para obtener información de discapacidad
            Long studentId = null;
            var studentOpt = studentRepository.findByPersonId(persona.getIdPersona());
            studentId = studentOpt.map(com.icheha.aprendia_api.users.student.domain.entities.Student::getId).orElse(null);
            
            LoginResponseDto loginResponse = generateLoginResponse(persona, studentId);
            
            logger.info("User {} logged in successfully with credentials.", persona.getCurp().getValue());
            
            return loginResponse;
        } catch (InvalidCredentialsException | UserNotFoundException e) {
            logger.warn("Login failed for CURP: {} - {}", loginDto.getCurp(), e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Login with credentials failed", e);
            throw new RuntimeException(LOGIN_FAILED, e);
        }
    }
    
    public LoginResponseDto loginWithQR(LoginQrDto loginDto) {
        try {
            logger.debug("Attempting login with QR token");
            
            String decryptedToken = encryptionUtil.decrypt(loginDto.getToken());
            logger.debug("QR token decrypted successfully");
            
            if (!jwtUtil.validateToken(decryptedToken)) {
                throw InvalidTokenException.qrToken();
            }
            
            TokenPayloadDto payload = jwtUtil.extractPayload(decryptedToken);
            logger.debug("QR token decoded successfully");
            
            Persona persona = authDomainService.findUserById(payload.getIdPersona());
            
            // Obtener studentId buscando por personId
            Long studentId = null;
            var studentOpt = studentRepository.findByPersonId(persona.getIdPersona());
            studentId = studentOpt.map(com.icheha.aprendia_api.users.student.domain.entities.Student::getId).orElse(null);
            
            LoginResponseDto loginResponse = generateLoginResponse(persona, studentId);
            
            logger.info("User {} logged in successfully with QR.", persona.getCurp().getValue());
            
            return loginResponse;
        } catch (InvalidTokenException | UserNotFoundException e) {
            logger.warn("QR login failed - {}", e.getMessage());
            throw e;
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
                TokenPayloadDto expiredPayload = jwtUtil.extractPayload(token);
                return new ValidateTokenResponseDto(
                    false,
                    true,
                    expiredPayload,
                    TOKEN_EXPIRED
                );
            }
            
            TokenPayloadDto payload = jwtUtil.extractPayload(token);
            logger.info("Token validated successfully for user {}", payload.getUsername());
            
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
    
    private LoginResponseDto generateLoginResponse(Persona persona) {
        return generateLoginResponse(persona, null);
    }
    
    private LoginResponseDto generateLoginResponse(Persona persona, Long studentId) {
        PersonaRol personaRol = getUserRole(persona.getIdPersona());
        
        String disabilityName = null;
        Long disabilityId = null;
        Long learningPathId = null;
        
        // Si es estudiante, obtener información de discapacidad
        if (studentId != null) {
            try {
                var impairmentDetails = studentImpairmentService.getStudentPreferencesWithDetails(persona.getIdPersona().intValue());
                if (impairmentDetails != null && !impairmentDetails.getImpairments().isEmpty()) {
                    var firstImpairment = impairmentDetails.getImpairments().get(0);
                    disabilityName = firstImpairment.getName();
                    disabilityId = firstImpairment.getId();
                    
                    if (impairmentDetails.getLearningPath() != null) {
                        learningPathId = impairmentDetails.getLearningPath().getId();
                    }
                }
            } catch (Exception e) {
                logger.warn("Error obteniendo información de discapacidad para estudiante {}: {}", studentId, e.getMessage());
            }
        }
        
        TokenPayloadDto payload = tokenPayloadMapper.toDto(persona, 
                personaRol.getRol().getNombre(), 
                disabilityName,
                disabilityId,
                learningPathId,
                studentId
        );
        String token = jwtUtil.generateToken(payload);
        
        return new LoginResponseDto(token, payload);
    }
    
    private PersonaRol getUserRole(Long personaId) {
        Optional<PersonaRol> personaRolOpt = rolRepository.findByPersonaId(personaId);
        if (personaRolOpt.isEmpty()) {
            throw UserRoleNotFoundException.forUser(personaId);
        }
        return personaRolOpt.get();
    }
    
}
