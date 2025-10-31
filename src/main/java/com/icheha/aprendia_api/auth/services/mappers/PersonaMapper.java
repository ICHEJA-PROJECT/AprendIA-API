package com.icheha.aprendia_api.auth.services.mappers;

import com.icheha.aprendia_api.auth.data.entities.PersonaEntity;
import com.icheha.aprendia_api.auth.domain.entities.Persona;
import com.icheha.aprendia_api.auth.domain.valueobjects.Curp;
import com.icheha.aprendia_api.auth.domain.valueobjects.Password;
import org.springframework.stereotype.Component;

@Component
public class PersonaMapper {
    
    public Persona toDomain(PersonaEntity entity) {
        if (entity == null) return null;
        
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
                .build();
    }
    
    public PersonaEntity toEntity(Persona domain) {
        if (domain == null) return null;
        
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
        return entity;
    }
}
