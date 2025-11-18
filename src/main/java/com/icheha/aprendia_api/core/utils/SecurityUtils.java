package com.icheha.aprendia_api.core.utils;

import com.icheha.aprendia_api.auth.data.dtos.response.TokenPayloadDto;
import com.icheha.aprendia_api.core.config.JwtConfig;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Utilidad para obtener información del usuario autenticado
 */
@Component
public class SecurityUtils {
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private JwtConfig jwtConfig;
    
    /**
     * Obtiene el ID de la persona del usuario autenticado desde el token JWT
     * @param request HttpServletRequest para extraer el token
     * @return ID de la persona o null si no se puede obtener (no hay token, token inválido, etc.)
     *         Este método nunca lanza excepciones, siempre retorna null si no puede obtener el usuario
     */
    public Long getCurrentUserId(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        
        try {
            String token = extractTokenFromRequest(request);
            if (token == null || token.trim().isEmpty()) {
                return null;
            }
            
            TokenPayloadDto payload = jwtUtil.extractPayload(token);
            if (payload == null || payload.getIdPersona() == null) {
                return null;
            }
            
            return payload.getIdPersona();
        } catch (Exception e) {
            // Silenciosamente retornar null si hay cualquier error
            // Esto permite que la aplicación continúe funcionando sin JWT
            return null;
        }
    }
    
    /**
     * Obtiene el ID de la persona del usuario autenticado desde SecurityContext
     * @param request HttpServletRequest para extraer el token si SecurityContext no tiene la info
     * @return ID de la persona o null si no se puede obtener
     */
    public Long getCurrentUserIdFromContext(HttpServletRequest request) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated()) {
                // Intentar obtener desde el token en el request
                return getCurrentUserId(request);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Extrae el token JWT del header Authorization
     * @param request HttpServletRequest
     * @return Token JWT sin el prefijo "Bearer " o null si no existe
     */
    private String extractTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader(jwtConfig.getHeader());
        if (header == null || !header.startsWith(jwtConfig.getPrefix())) {
            return null;
        }
        return header.replace(jwtConfig.getPrefix(), "").trim();
    }
    
    /**
     * Obtiene el payload completo del usuario autenticado
     * @param request HttpServletRequest para extraer el token
     * @return TokenPayloadDto o null si no se puede obtener
     */
    public TokenPayloadDto getCurrentUserPayload(HttpServletRequest request) {
        try {
            String token = extractTokenFromRequest(request);
            if (token == null) {
                return null;
            }
            return jwtUtil.extractPayload(token);
        } catch (Exception e) {
            return null;
        }
    }
}

