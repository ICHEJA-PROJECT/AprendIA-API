package com.icheha.aprendia_api.users.person.data.adapters;

import com.icheha.aprendia_api.auth.domain.enums.GenderEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Converter para convertir GenderEnum a un solo carácter en la base de datos
 * MASCULINO -> "M"
 * FEMENINO -> "F"
 * OTRO -> "O"
 */
@Converter(autoApply = false)
public class GenderEnumConverter implements AttributeConverter<GenderEnum, String> {
    
    @Override
    public String convertToDatabaseColumn(GenderEnum gender) {
        if (gender == null) {
            return null;
        }
        
        switch (gender) {
            case MASCULINO:
                return "M";
            case FEMENINO:
                return "F";
            case OTRO:
                return "O";
            default:
                throw new IllegalArgumentException("Valor de género no válido: " + gender);
        }
    }
    
    @Override
    public GenderEnum convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.trim().isEmpty()) {
            return null;
        }
        
        String trimmed = dbData.trim().toUpperCase();
        switch (trimmed) {
            case "M":
                return GenderEnum.MASCULINO;
            case "F":
                return GenderEnum.FEMENINO;
            case "O":
                return GenderEnum.OTRO;
            default:
                // Intentar parsear como valor completo del enum por compatibilidad
                try {
                    return GenderEnum.fromValue(trimmed);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Valor de género no válido en la base de datos: " + dbData);
                }
        }
    }
}





