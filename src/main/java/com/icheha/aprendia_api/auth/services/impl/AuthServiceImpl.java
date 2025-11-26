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
import com.icheha.aprendia_api.auth.domain.repositories.IPersonaRepository;
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
import org.springframework.transaction.annotation.Transactional;

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
    private com.icheha.aprendia_api.users.student.domain.repositories.IEncryptDataRepository encryptDataRepository;
    
    @Autowired
    private TokenPayloadMapper tokenPayloadMapper;
    
    @Autowired
    private com.icheha.aprendia_api.users.student.domain.repositories.IStudentRepository studentRepository;
    
    @Autowired
    private com.icheha.aprendia_api.preferences.impairments.services.IStudentImpairmentService studentImpairmentService;
    
    @Autowired
    private com.icheha.aprendia_api.users.student.services.IParienteService parienteService;
    
    @Autowired
    private IPersonaRepository personaRepository;
    
    @Transactional(readOnly = true)
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
        } catch (UserRoleNotFoundException e) {
            logger.warn("User role not found for CURP: {} - {}", loginDto.getCurp(), e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Login with credentials failed", e);
            throw new RuntimeException(LOGIN_FAILED, e);
        }
    }
    
    public LoginResponseDto loginWithQR(LoginQrDto loginDto) {
        try {
            logger.debug("Attempting login with QR token");
            
            String token = loginDto.getToken();
            
            // Validar que el token no esté vacío o nulo
            if (token == null || token.trim().isEmpty()) {
                logger.warn("QR token is null or empty");
                throw InvalidTokenException.qrToken();
            }
            
            logger.debug("Token length: {}, starts with: {}", token.length(), token.length() > 10 ? token.substring(0, 10) : token);
            
            String jwtToken = null;
            boolean isEncrypted = false;
            
            // Detectar si el token es un JWT directo (tiene puntos y 3 partes)
            String[] tokenParts = token.split("\\.");
            if (tokenParts.length == 3) {
                // Es un JWT directo, usarlo sin desencriptar
                logger.debug("Token appears to be a JWT (has 3 parts), using directly");
                jwtToken = token;
            } else {
                // Intentar desencriptar el token usando el mismo método que se usó para encriptar (EncryptDataRepository)
                logger.debug("Token does not appear to be a JWT, attempting decryption with EncryptDataRepository");
                try {
                    jwtToken = encryptDataRepository.decrypt(token);
                    isEncrypted = true;
                    logger.debug("QR token decrypted successfully, length: {}", jwtToken.length());
                    
                    // Verificar que el token desencriptado tenga formato JWT
                    String[] decryptedParts = jwtToken.split("\\.");
                    if (decryptedParts.length != 3) {
                        logger.warn("Decrypted token does not have JWT format (expected 3 parts, got {})", decryptedParts.length);
                        throw InvalidTokenException.qrToken();
                    }
                } catch (Exception decryptException) {
                    logger.warn("Failed to decrypt token with EncryptDataRepository: {}", decryptException.getMessage());
                    logger.debug("Decryption exception type: {}", decryptException.getClass().getSimpleName());
                    
                    // Si falla con EncryptDataRepository, intentar con EncryptionUtil (por compatibilidad)
                    try {
                        logger.debug("Attempting decryption with EncryptionUtil as fallback");
                        jwtToken = encryptionUtil.decrypt(token);
                        isEncrypted = true;
                        logger.debug("QR token decrypted successfully with EncryptionUtil, length: {}", jwtToken.length());
                        
                        // Verificar que el token desencriptado tenga formato JWT
                        String[] decryptedParts = jwtToken.split("\\.");
                        if (decryptedParts.length != 3) {
                            logger.warn("Decrypted token does not have JWT format (expected 3 parts, got {})", decryptedParts.length);
                            throw InvalidTokenException.qrToken();
                        }
                    } catch (Exception fallbackException) {
                        logger.warn("Failed to decrypt token with EncryptionUtil fallback: {}", fallbackException.getMessage());
                        // Si falla la desencriptación, intentar usar el token como JWT directo
                        logger.debug("Attempting to use token as JWT directly");
                        jwtToken = token;
                    }
                }
            }
            
            // Intentar extraer el payload primero para verificar el formato
            TokenPayloadDto payload = jwtUtil.extractPayload(jwtToken);
            if (payload == null) {
                logger.warn("Failed to extract payload from JWT token - invalid format or signature");
                // Intentar validar el token para obtener más información del error
                boolean isValid = jwtUtil.validateToken(jwtToken);
                logger.warn("JWT token validation result: {}", isValid);
                throw InvalidTokenException.qrToken();
            }
            
            // Validar el token (firma y expiración)
            if (!jwtUtil.validateToken(jwtToken)) {
                logger.warn("JWT token validation failed - invalid signature or expired");
                // Verificar si está expirado
                boolean isExpired = jwtUtil.isTokenExpired(jwtToken);
                if (isExpired) {
                    logger.warn("JWT token is expired");
                }
                throw InvalidTokenException.qrToken();
            }
            
            logger.debug("QR token decoded successfully, idPersona: {}", payload.getIdPersona());
            
            // El payload puede tener idPersona o personId dependiendo de cómo se generó el token
            Long personId = payload.getIdPersona();
            if (personId == null) {
                // Intentar obtener personId desde los claims del JWT directamente
                try {
                    io.jsonwebtoken.Claims claims = jwtUtil.extractClaims(jwtToken);
                    if (claims != null) {
                        Object personIdObj = claims.get("personId");
                        if (personIdObj != null) {
                            personId = personIdObj instanceof Long ? (Long) personIdObj : Long.valueOf(personIdObj.toString());
                            logger.debug("Extracted personId from claims: {}", personId);
                        }
                    }
                } catch (Exception e) {
                    logger.warn("Could not extract personId from claims: {}", e.getMessage());
                }
            }
            
            if (personId == null) {
                logger.warn("Could not determine personId from token payload");
                throw InvalidTokenException.qrToken();
            }
            
            Persona persona = authDomainService.findUserById(personId);
            
            // Obtener studentId buscando por personId
            Long studentId = null;
            var studentOpt = studentRepository.findByPersonId(persona.getIdPersona());
            studentId = studentOpt.map(com.icheha.aprendia_api.users.student.domain.entities.Student::getId).orElse(null);
            
            LoginResponseDto loginResponse = generateLoginResponse(persona, studentId);
            
            logger.info("User {} logged in successfully with QR (token was {}).", 
                persona.getCurp().getValue(), isEncrypted ? "encrypted" : "JWT direct");
            
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
            
            // Primero intentar extraer el payload (esto puede funcionar incluso si está expirado)
            TokenPayloadDto payload = jwtUtil.extractPayload(token);
            
            if (payload == null) {
                // No se pudo extraer el payload, el token tiene formato inválido o firma incorrecta
                logger.warn("Could not extract payload from token - invalid format or signature");
                return new ValidateTokenResponseDto(
                    false,
                    false,
                    null,
                    INVALID_TOKEN_FORMAT
                );
            }
            
            // Verificar si el token está expirado
            boolean isExpired = jwtUtil.isTokenExpired(token);
            if (isExpired) {
                logger.warn("Token is expired for user {}", payload.getUsername());
                return new ValidateTokenResponseDto(
                    false,
                    true,
                    payload,
                    TOKEN_EXPIRED
                );
            }
            
            // Validar la firma del token
            if (!jwtUtil.validateToken(token)) {
                logger.warn("Token signature validation failed");
                return new ValidateTokenResponseDto(
                    false,
                    false,
                    payload,
                    INVALID_TOKEN_FORMAT
                );
            }
            
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
                TOKEN_VALIDATION_FAILED + ": " + e.getMessage()
            );
        }
    }
    
    private LoginResponseDto generateLoginResponse(Persona persona) {
        return generateLoginResponse(persona, null);
    }
    
    private LoginResponseDto generateLoginResponse(Persona persona, Long studentId) {
        PersonaRol personaRol = getUserRole(persona.getIdPersona());
        
        // Validar que el rol esté presente
        if (personaRol.getRol() == null) {
            logger.error("Rol is null for PersonaRol with idPersona: {}", persona.getIdPersona());
            throw new RuntimeException("Rol no encontrado para el usuario");
        }
        
        String disabilityName = null;
        Long disabilityId = null;
        Long learningPathId = null;
        
        // Si es estudiante, obtener información de discapacidad desde la BD
        // NOTA: Siempre se obtienen las impairments actuales desde la BD, ignorando las del token QR
        // Si el estudiante tiene múltiples impairments, solo se usa la primera para el token de respuesta
        // (por limitaciones del TokenPayloadDto que solo soporta una impairment)
        if (studentId != null) {
            try {
                var impairmentDetails = studentImpairmentService.getStudentPreferencesWithDetails(persona.getIdPersona().intValue());
                if (impairmentDetails != null && !impairmentDetails.getImpairments().isEmpty()) {
                    int totalImpairments = impairmentDetails.getImpairments().size();
                    // Solo se usa la primera impairment para el token de respuesta
                    // Si hay múltiples, se registra en el log para referencia
                    var firstImpairment = impairmentDetails.getImpairments().get(0);
                    disabilityName = firstImpairment.getName();
                    disabilityId = firstImpairment.getId();
                    
                    if (totalImpairments > 1) {
                        logger.debug("Estudiante {} tiene {} impairment(s), usando solo la primera para el token: {}", 
                            studentId, totalImpairments, disabilityName);
                    }
                    
                    if (impairmentDetails.getLearningPath() != null) {
                        learningPathId = impairmentDetails.getLearningPath().getId();
                    }
                }
            } catch (Exception e) {
                logger.warn("Error obteniendo información de discapacidad para estudiante {}: {}", studentId, e.getMessage());
            }
        }
        
        // Obtener información de parientes (mamá y papá)
        com.icheha.aprendia_api.auth.data.dtos.response.ParienteInfoDto padreInfo = null;
        com.icheha.aprendia_api.auth.data.dtos.response.ParienteInfoDto madreInfo = null;
        
        try {
            // Buscar pariente con rol "Padre"
            var parientesPadre = parienteService.findByPersonaIdAndRolNombre(persona.getIdPersona(), "Padre");
            if (!parientesPadre.isEmpty()) {
                var padreDto = parientesPadre.get(0);
                // Obtener información completa del pariente desde el repositorio
                java.util.Optional<Persona> padrePersonaOpt = personaRepository.findById(padreDto.getParienteId());
                if (padrePersonaOpt.isPresent()) {
                    padreInfo = tokenPayloadMapper.toParienteInfoDto(padrePersonaOpt.get());
                } else {
                    // Si no se encuentra, crear DTO con existe = false
                    padreInfo = com.icheha.aprendia_api.auth.data.dtos.response.ParienteInfoDto.builder()
                            .existe(false)
                            .build();
                }
            } else {
                // No existe padre, crear DTO con existe = false
                padreInfo = com.icheha.aprendia_api.auth.data.dtos.response.ParienteInfoDto.builder()
                        .existe(false)
                        .build();
            }
            
            // Buscar pariente con rol "Madre"
            var parientesMadre = parienteService.findByPersonaIdAndRolNombre(persona.getIdPersona(), "Madre");
            if (!parientesMadre.isEmpty()) {
                var madreDto = parientesMadre.get(0);
                // Obtener información completa del pariente desde el repositorio
                java.util.Optional<Persona> madrePersonaOpt = personaRepository.findById(madreDto.getParienteId());
                if (madrePersonaOpt.isPresent()) {
                    madreInfo = tokenPayloadMapper.toParienteInfoDto(madrePersonaOpt.get());
                } else {
                    // Si no se encuentra, crear DTO con existe = false
                    madreInfo = com.icheha.aprendia_api.auth.data.dtos.response.ParienteInfoDto.builder()
                            .existe(false)
                            .build();
                }
            } else {
                // No existe madre, crear DTO con existe = false
                madreInfo = com.icheha.aprendia_api.auth.data.dtos.response.ParienteInfoDto.builder()
                        .existe(false)
                        .build();
            }
        } catch (Exception e) {
            logger.warn("Error obteniendo información de parientes para persona {}: {}", persona.getIdPersona(), e.getMessage());
            // En caso de error, crear DTOs con existe = false
            padreInfo = com.icheha.aprendia_api.auth.data.dtos.response.ParienteInfoDto.builder()
                    .existe(false)
                    .build();
            madreInfo = com.icheha.aprendia_api.auth.data.dtos.response.ParienteInfoDto.builder()
                    .existe(false)
                    .build();
        }
        
        TokenPayloadDto payload = tokenPayloadMapper.toDto(persona, 
                personaRol.getRol().getNombre(), 
                disabilityName,
                disabilityId,
                learningPathId,
                studentId,
                padreInfo,
                madreInfo
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
