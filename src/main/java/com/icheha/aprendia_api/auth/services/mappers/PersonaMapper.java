package com.icheha.aprendia_api.auth.services.mappers;

import com.icheha.aprendia_api.auth.data.entities.PersonaEntity;
import com.icheha.aprendia_api.auth.data.entities.PersonaRolEntity;
import com.icheha.aprendia_api.auth.data.entities.RolEntity;
import com.icheha.aprendia_api.auth.domain.entities.Persona;
import com.icheha.aprendia_api.auth.domain.entities.PersonaRol;
import com.icheha.aprendia_api.auth.domain.entities.Rol;
import com.icheha.aprendia_api.auth.domain.valueobjects.Curp;
import com.icheha.aprendia_api.auth.domain.valueobjects.Password;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper para convertir entre entidades de dominio y entidades de datos
 * Implementa el patrón Mapper para desacoplar las capas
 */
@Component
public class PersonaMapper {
    
    public PersonaMapper() {
        // Constructor sin dependencias para evitar circularidad
    }
    
    /**
     * Convierte una entidad de datos a entidad de dominio
     */
    public Persona toDomain(PersonaEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return new Persona.Builder()
                .idPersona(entity.getIdPersona())
                .primerNombre(entity.getPrimerNombre())
                .segundoNombre(entity.getSegundoNombre())
                .apellidoPaterno(entity.getApellidoPaterno())
                .apellidoMaterno(entity.getApellidoMaterno())
                .curp(new Curp(entity.getCurp()))
                .numeroIne(entity.getNumeroIne())
                .fechaNacimiento(entity.getFechaNacimiento())
                .genero(entity.getGenero())
                .codigoPostal(entity.getCodigoPostal())
                .estado(entity.getEstado())
                .municipio(entity.getMunicipio())
                .localidad(entity.getLocalidad())
                .vialidadNombre(entity.getVialidadNombre())
                .idVialidadTipo(entity.getIdVialidadTipo())
                .asentamiento(entity.getAsentamiento())
                .idAsentamientoTipo(entity.getIdAsentamientoTipo())
                .password(new Password(entity.getPassword()))
                .personaRoles(mapPersonaRolesToDomain(entity.getPersonaRoles()))
                .build();
    }
    
    /**
     * Convierte una entidad de dominio a entidad de datos
     */
    public PersonaEntity toEntity(Persona domain) {
        if (domain == null) {
            return null;
        }
        
        PersonaEntity entity = new PersonaEntity();
        entity.setIdPersona(domain.getIdPersona());
        entity.setPrimerNombre(domain.getPrimerNombre());
        entity.setSegundoNombre(domain.getSegundoNombre());
        entity.setApellidoPaterno(domain.getApellidoPaterno());
        entity.setApellidoMaterno(domain.getApellidoMaterno());
        entity.setCurp(domain.getCurp().getValue());
        entity.setNumeroIne(domain.getNumeroIne());
        entity.setFechaNacimiento(domain.getFechaNacimiento());
        entity.setGenero(domain.getGenero());
        entity.setCodigoPostal(domain.getCodigoPostal());
        entity.setEstado(domain.getEstado());
        entity.setMunicipio(domain.getMunicipio());
        entity.setLocalidad(domain.getLocalidad());
        entity.setVialidadNombre(domain.getVialidadNombre());
        entity.setIdVialidadTipo(domain.getIdVialidadTipo());
        entity.setAsentamiento(domain.getAsentamiento());
        entity.setIdAsentamientoTipo(domain.getIdAsentamientoTipo());
        entity.setPassword(domain.getPassword().getHashedValue());
        entity.setPersonaRoles(mapPersonaRolesToEntity(domain.getPersonaRoles()));
        
        return entity;
    }
    
    /**
     * Mapea una lista de PersonaRol de dominio a entidad
     */
    private List<PersonaRolEntity> mapPersonaRolesToEntity(List<PersonaRol> personaRoles) {
        if (personaRoles == null) {
            return null;
        }
        
        return personaRoles.stream()
                .map(this::mapPersonaRolToEntity)
                .collect(Collectors.toList());
    }
    
    /**
     * Mapea una lista de PersonaRol de entidad a dominio
     */
    private List<PersonaRol> mapPersonaRolesToDomain(List<PersonaRolEntity> personaRoles) {
        if (personaRoles == null) {
            return null;
        }
        
        return personaRoles.stream()
                .map(this::mapPersonaRolToDomain)
                .collect(Collectors.toList());
    }
    
    /**
     * Mapea PersonaRol de dominio a entidad
     */
    private PersonaRolEntity mapPersonaRolToEntity(PersonaRol personaRol) {
        if (personaRol == null) {
            return null;
        }
        
        PersonaRolEntity entity = new PersonaRolEntity();
        entity.setIdPersonaRol(personaRol.getIdPersonaRol());
        entity.setIdPersona(personaRol.getIdPersona());
        entity.setIdRol(personaRol.getIdRol());
        
        if (personaRol.getRol() != null) {
            RolEntity rolEntity = new RolEntity();
            rolEntity.setIdRol(personaRol.getRol().getIdRol());
            rolEntity.setNombre(personaRol.getRol().getNombre());
            entity.setRol(rolEntity);
        }
        
        return entity;
    }
    
    /**
     * Mapea PersonaRol de entidad a dominio
     */
    private PersonaRol mapPersonaRolToDomain(PersonaRolEntity entity) {
        if (entity == null) {
            return null;
        }
        
        Rol rol = null;
        if (entity.getRol() != null) {
            rol = new Rol.Builder()
                    .idRol(entity.getRol().getIdRol())
                    .nombre(entity.getRol().getNombre())
                    .build();
        }
        
        return new PersonaRol.Builder()
                .idPersonaRol(entity.getIdPersonaRol())
                .idPersona(entity.getIdPersona())
                .idRol(entity.getIdRol())
                .rol(rol)
                .build();
    }
}

