package com.icheha.aprendia_api.auth.services.mappers;

import com.icheha.aprendia_api.users.person.data.entities.PersonaEntity;
import com.icheha.aprendia_api.users.person.data.entities.DomicilioEntity;
import com.icheha.aprendia_api.auth.domain.entities.Persona;
import com.icheha.aprendia_api.auth.domain.valueobjects.Curp;
import org.springframework.stereotype.Component;

@Component
public class PersonaMapper {
    
    public Persona toDomain(PersonaEntity entity) {
        if (entity == null) return null;
        
        // Obtener datos de dirección desde DomicilioEntity si existe
        String codigoPostal = null;
        String estado = null;
        String municipio = null;
        String localidad = null;
        String asentamiento = null;
        Integer idVialidadTipo = null;
        Integer idAsentamientoTipo = null;
        String vialidadNombre = null;
        
        if (entity.getDomicilio() != null) {
            DomicilioEntity domicilio = entity.getDomicilio();
            codigoPostal = domicilio.getCp();
            estado = domicilio.getEstado() != null ? domicilio.getEstado().getNombre() : null;
            municipio = domicilio.getMunicipio() != null ? domicilio.getMunicipio().getNombre() : null;
            localidad = domicilio.getLocalidad();
            asentamiento = domicilio.getColonia();
            vialidadNombre = domicilio.getCalle();
        }
        
        // Construir Persona con valores reales cuando estén disponibles
        // CURP está en PersonaEntity, así que lo usamos si está disponible
        // Password está en UserEntity, así que se deja como null cuando no está disponible
        Persona.Builder builder = new Persona.Builder()
                .idPersona(entity.getIdPersona())
                .primerNombre(entity.getPrimerNombre())
                .segundoNombre(entity.getSegundoNombre())
                .apellidoPaterno(entity.getPrimerApellido())
                .apellidoMaterno(entity.getSegundoApellido() != null ? entity.getSegundoApellido() : "")
                .numeroIne(entity.getNumeroIne() != null ? entity.getNumeroIne() : "")
                .fechaNacimiento(entity.getFechaNacimiento())
                .genero(entity.getGenero())
                .codigoPostal(codigoPostal)
                .estado(estado)
                .municipio(municipio)
                .localidad(localidad)
                .vialidadNombre(vialidadNombre)
                .idVialidadTipo(idVialidadTipo)
                .asentamiento(asentamiento)
                .idAsentamientoTipo(idAsentamientoTipo);
        
        // CURP: usar valor real si está disponible en la entidad
        if (entity.getCurp() != null && !entity.getCurp().trim().isEmpty()) {
            try {
                builder.curp(new Curp(entity.getCurp()));
            } catch (IllegalArgumentException e) {
                // Si el CURP no es válido, dejarlo como null
                // Esto puede ocurrir si hay datos inconsistentes en la BD
                builder.curp(null);
            }
        } else {
            builder.curp(null);
        }
        
        // Password: obtener desde PersonaEntity si está disponible
        if (entity.getPassword() != null && !entity.getPassword().trim().isEmpty()) {
            builder.password(new com.icheha.aprendia_api.auth.domain.valueobjects.Password(entity.getPassword()));
        } else {
            builder.password(null);
        }
        
        return builder.build();
    }
    
    public PersonaEntity toEntity(Persona domain) {
        if (domain == null) return null;
        
        PersonaEntity entity = new PersonaEntity();
        entity.setIdPersona(domain.getIdPersona());
        entity.setPrimerNombre(domain.getPrimerNombre());
        entity.setSegundoNombre(domain.getSegundoNombre());
        entity.setPrimerApellido(domain.getApellidoPaterno());
        entity.setSegundoApellido(domain.getApellidoMaterno());
        entity.setCurp(domain.getCurpValue()); // Usar método seguro que maneja null
        entity.setNumeroIne(domain.getNumeroIne());
        entity.setFechaNacimiento(domain.getFechaNacimiento());
        entity.setGenero(domain.getGenero());
        entity.setEmail(null); // TODO: Agregar email al dominio si es necesario
        entity.setTelefono(null); // TODO: Agregar telefono al dominio si es necesario
        // Nota: Password ahora está en UserEntity, no en PersonaEntity
        // Nota: Las relaciones (domicilio) deben establecerse en el servicio/repositorio
        return entity;
    }
}
