package com.icheha.aprendia_api.auth.services.impl;

import com.icheha.aprendia_api.auth.data.dtos.request.LoginCredentialsDto;
import com.icheha.aprendia_api.auth.data.dtos.request.LoginQrDto;
import com.icheha.aprendia_api.auth.data.dtos.response.LoginResponseDto;
import com.icheha.aprendia_api.auth.data.dtos.response.ValidateTokenResponseDto;
import com.icheha.aprendia_api.auth.domain.entities.Persona;
import com.icheha.aprendia_api.auth.domain.entities.PersonaRol;
import com.icheha.aprendia_api.auth.domain.entities.Rol;
import com.icheha.aprendia_api.auth.domain.exceptions.InvalidCredentialsException;
import com.icheha.aprendia_api.auth.domain.exceptions.InvalidTokenException;
import com.icheha.aprendia_api.auth.domain.exceptions.UserNotFoundException;
import com.icheha.aprendia_api.auth.domain.services.IAuthDomainService;
import com.icheha.aprendia_api.auth.domain.valueobjects.Curp;
import com.icheha.aprendia_api.auth.services.mappers.TokenPayloadMapper;
import com.icheha.aprendia_api.auth.services.utils.EncryptionUtil;
import com.icheha.aprendia_api.core.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private IAuthDomainService authDomainService;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private EncryptionUtil encryptionUtil;

    @Mock
    private TokenPayloadMapper tokenPayloadMapper;

    @InjectMocks
    private AuthServiceImpl authService;

    private LoginCredentialsDto loginCredentialsDto;
    private LoginQrDto loginQrDto;
    private Persona persona;
    private PersonaRol personaRol;
    private Rol rol;

    @BeforeEach
    void setUp() {
        // Setup test data
        loginCredentialsDto = new LoginCredentialsDto();
        loginCredentialsDto.setCurp("TEST123456HDFRRL01");
        loginCredentialsDto.setPassword("password123");

        loginQrDto = new LoginQrDto();
        loginQrDto.setToken("encrypted_qr_token");

        // Mock entities - these are domain entities that may not have setters
        rol = mock(Rol.class);
        when(rol.getIdRol()).thenReturn(1L);
        when(rol.getNombre()).thenReturn("STUDENT");

        personaRol = mock(PersonaRol.class);
        when(personaRol.getIdPersonaRol()).thenReturn(1L);
        when(personaRol.getRol()).thenReturn(rol);

        persona = mock(Persona.class);
        when(persona.getIdPersona()).thenReturn(1L);
        when(persona.getCurp()).thenReturn(new Curp("TEST123456HDFRRL01"));
        when(persona.getPrimerNombre()).thenReturn("Test");
        when(persona.getApellidoPaterno()).thenReturn("User");
    }

    @Test
    void loginWithCredentials_Success() {
        // Given
        when(authDomainService.authenticateUser(any(Curp.class), eq("password123")))
                .thenReturn(persona);
        when(tokenPayloadMapper.toDto(any(Persona.class), anyString(), isNull(), isNull(), isNull()))
                .thenReturn(createTokenPayload());
        when(jwtUtil.generateToken(any())).thenReturn("jwt_token");

        // When
        LoginResponseDto result = authService.loginWithCredentials(loginCredentialsDto);

        // Then
        assertNotNull(result);
        assertEquals("jwt_token", result.getToken());
        assertNotNull(result.getUserInfo());
        verify(authDomainService).authenticateUser(any(Curp.class), eq("password123"));
        verify(jwtUtil).generateToken(any());
    }

    @Test
    void loginWithCredentials_InvalidCredentials() {
        // Given
        when(authDomainService.authenticateUser(any(Curp.class), eq("password123")))
                .thenThrow(new InvalidCredentialsException("Invalid credentials"));

        // When & Then
        assertThrows(InvalidCredentialsException.class, () -> 
                authService.loginWithCredentials(loginCredentialsDto));
        
        verify(authDomainService).authenticateUser(any(Curp.class), eq("password123"));
        verify(jwtUtil, never()).generateToken(any());
    }

    @Test
    void loginWithCredentials_UserNotFound() {
        // Given
        when(authDomainService.authenticateUser(any(Curp.class), eq("password123")))
                .thenThrow(new UserNotFoundException("User not found"));

        // When & Then
        assertThrows(UserNotFoundException.class, () -> 
                authService.loginWithCredentials(loginCredentialsDto));
        
        verify(authDomainService).authenticateUser(any(Curp.class), eq("password123"));
        verify(jwtUtil, never()).generateToken(any());
    }

    @Test
    void loginWithQR_Success() {
        // Given
        when(encryptionUtil.decrypt("encrypted_qr_token")).thenReturn("decrypted_jwt_token");
        when(jwtUtil.validateToken("decrypted_jwt_token")).thenReturn(true);
        when(jwtUtil.extractPayload("decrypted_jwt_token")).thenReturn(createTokenPayload());
        when(authDomainService.findUserById(1L)).thenReturn(persona);
        when(tokenPayloadMapper.toDto(any(Persona.class), anyString(), isNull(), isNull(), isNull()))
                .thenReturn(createTokenPayload());
        when(jwtUtil.generateToken(any())).thenReturn("new_jwt_token");

        // When
        LoginResponseDto result = authService.loginWithQR(loginQrDto);

        // Then
        assertNotNull(result);
        assertEquals("new_jwt_token", result.getToken());
        assertNotNull(result.getUserInfo());
        verify(encryptionUtil).decrypt("encrypted_qr_token");
        verify(jwtUtil).validateToken("decrypted_jwt_token");
        verify(authDomainService).findUserById(1L);
    }

    @Test
    void loginWithQR_InvalidToken() {
        // Given
        when(encryptionUtil.decrypt("encrypted_qr_token")).thenReturn("invalid_token");
        when(jwtUtil.validateToken("invalid_token")).thenReturn(false);

        // When & Then
        assertThrows(InvalidTokenException.class, () -> 
                authService.loginWithQR(loginQrDto));
        
        verify(encryptionUtil).decrypt("encrypted_qr_token");
        verify(jwtUtil).validateToken("invalid_token");
        verify(authDomainService, never()).findUserById(any());
    }

    @Test
    void validateToken_ValidToken() {
        // Given
        String token = "valid_jwt_token";
        when(jwtUtil.validateToken(token)).thenReturn(true);
        when(jwtUtil.isTokenExpired(token)).thenReturn(false);
        when(jwtUtil.extractPayload(token)).thenReturn(createTokenPayload());

        // When
        ValidateTokenResponseDto result = authService.validateToken(token);

        // Then
        assertNotNull(result);
        assertTrue(result.getIsValid());
        assertFalse(result.getIsExpired());
        assertNotNull(result.getPayload());
        assertEquals("Token válido", result.getMessage());
    }

    @Test
    void validateToken_ExpiredToken() {
        // Given
        String token = "expired_jwt_token";
        when(jwtUtil.validateToken(token)).thenReturn(true);
        when(jwtUtil.isTokenExpired(token)).thenReturn(true);
        when(jwtUtil.extractPayload(token)).thenReturn(createTokenPayload());

        // When
        ValidateTokenResponseDto result = authService.validateToken(token);

        // Then
        assertNotNull(result);
        assertFalse(result.getIsValid());
        assertTrue(result.getIsExpired());
        assertNotNull(result.getPayload());
        assertEquals("Token expirado", result.getMessage());
    }

    @Test
    void validateToken_InvalidToken() {
        // Given
        String token = "invalid_jwt_token";
        when(jwtUtil.validateToken(token)).thenReturn(false);

        // When
        ValidateTokenResponseDto result = authService.validateToken(token);

        // Then
        assertNotNull(result);
        assertFalse(result.getIsValid());
        assertFalse(result.getIsExpired());
        assertNull(result.getPayload());
        assertEquals("Formato de token inválido", result.getMessage());
    }

    private com.icheha.aprendia_api.auth.data.dtos.response.TokenPayloadDto createTokenPayload() {
        return com.icheha.aprendia_api.auth.data.dtos.response.TokenPayloadDto.builder()
                .idPersona(1L)
                .username("testuser")
                .nombre("Test User")
                .roleName("STUDENT")
                .disabilityName(null)
                .disabilityId(null)
                .learningPathId(null)
                .iat(System.currentTimeMillis())
                .exp(System.currentTimeMillis() + 3600000)
                .build();
    }
}
