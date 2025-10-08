package com.icheha.aprendia_api.auth.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icheha.aprendia_api.auth.data.dtos.request.LoginCredentialsDto;
import com.icheha.aprendia_api.auth.data.dtos.request.LoginQrDto;
import com.icheha.aprendia_api.auth.data.dtos.request.ValidateTokenDto;
import com.icheha.aprendia_api.auth.data.dtos.response.LoginResponseDto;
import com.icheha.aprendia_api.auth.data.dtos.response.ValidateTokenResponseDto;
import com.icheha.aprendia_api.auth.services.IAuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IAuthService authService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void loginWithCredentials_Success() throws Exception {
        // Given
        LoginCredentialsDto loginDto = new LoginCredentialsDto();
        loginDto.setCurp("TEST123456HDFRRL01");
        loginDto.setPassword("password123");

        LoginResponseDto loginResponse = new LoginResponseDto();
        loginResponse.setToken("jwt_token");
        loginResponse.setUserInfo(createTokenPayload());

        when(authService.loginWithCredentials(any(LoginCredentialsDto.class))).thenReturn(loginResponse);

        // When & Then
        mockMvc.perform(post("/api/auth/login/credentials")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.token").value("jwt_token"))
                .andExpect(jsonPath("$.data.payload.idPersona").value(1))
                .andExpect(jsonPath("$.data.payload.username").value("testuser"))
                .andExpect(jsonPath("$.message").value("Login exitoso"));

        verify(authService).loginWithCredentials(any(LoginCredentialsDto.class));
    }

    @Test
    void loginWithCredentials_InvalidCredentials() throws Exception {
        // Given
        LoginCredentialsDto loginDto = new LoginCredentialsDto();
        loginDto.setCurp("TEST123456HDFRRL01");
        loginDto.setPassword("wrongpassword");

        when(authService.loginWithCredentials(any(LoginCredentialsDto.class)))
                .thenThrow(new RuntimeException("Invalid credentials"));

        // When & Then
        mockMvc.perform(post("/api/auth/login/credentials")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("Error interno del servidor"));

        verify(authService).loginWithCredentials(any(LoginCredentialsDto.class));
    }

    @Test
    void loginWithQR_Success() throws Exception {
        // Given
        LoginQrDto loginDto = new LoginQrDto();
        loginDto.setToken("encrypted_qr_token");

        LoginResponseDto loginResponse = new LoginResponseDto();
        loginResponse.setToken("jwt_token");
        loginResponse.setUserInfo(createTokenPayload());

        when(authService.loginWithQR(any(LoginQrDto.class))).thenReturn(loginResponse);

        // When & Then
        mockMvc.perform(post("/api/auth/login/qr")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.token").value("jwt_token"))
                .andExpect(jsonPath("$.data.payload.idPersona").value(1))
                .andExpect(jsonPath("$.data.payload.username").value("testuser"))
                .andExpect(jsonPath("$.message").value("Login con QR exitoso"));

        verify(authService).loginWithQR(any(LoginQrDto.class));
    }

    @Test
    void loginWithQR_InvalidToken() throws Exception {
        // Given
        LoginQrDto loginDto = new LoginQrDto();
        loginDto.setToken("invalid_token");

        when(authService.loginWithQR(any(LoginQrDto.class)))
                .thenThrow(new RuntimeException("Invalid token"));

        // When & Then
        mockMvc.perform(post("/api/auth/login/qr")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("Error interno del servidor"));

        verify(authService).loginWithQR(any(LoginQrDto.class));
    }

    @Test
    void validateToken_ValidToken() throws Exception {
        // Given
        ValidateTokenDto validateDto = new ValidateTokenDto();
        validateDto.setToken("valid_jwt_token");

        ValidateTokenResponseDto validateResponse = new ValidateTokenResponseDto();
        validateResponse.setIsValid(true);
        validateResponse.setIsExpired(false);
        validateResponse.setPayload(createTokenPayload());
        validateResponse.setMessage("Token válido");

        when(authService.validateToken(anyString())).thenReturn(validateResponse);

        // When & Then
        mockMvc.perform(post("/api/auth/validate-token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validateDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.valid").value(true))
                .andExpect(jsonPath("$.data.expired").value(false))
                .andExpect(jsonPath("$.data.payload.idPersona").value(1))
                .andExpect(jsonPath("$.data.message").value("Token válido"))
                .andExpect(jsonPath("$.message").value("Validación de token completada"));

        verify(authService).validateToken("valid_jwt_token");
    }

    @Test
    void validateToken_ExpiredToken() throws Exception {
        // Given
        ValidateTokenDto validateDto = new ValidateTokenDto();
        validateDto.setToken("expired_jwt_token");

        ValidateTokenResponseDto validateResponse = new ValidateTokenResponseDto();
        validateResponse.setIsValid(false);
        validateResponse.setIsExpired(true);
        validateResponse.setPayload(createTokenPayload());
        validateResponse.setMessage("Token expirado");

        when(authService.validateToken(anyString())).thenReturn(validateResponse);

        // When & Then
        mockMvc.perform(post("/api/auth/validate-token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validateDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.valid").value(false))
                .andExpect(jsonPath("$.data.expired").value(true))
                .andExpect(jsonPath("$.data.message").value("Token expirado"))
                .andExpect(jsonPath("$.message").value("Validación de token completada"));

        verify(authService).validateToken("expired_jwt_token");
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
