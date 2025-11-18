package com.icheha.aprendia_api.users.person.data.mappers;

import com.icheha.aprendia_api.auth.domain.entities.Persona;
import com.icheha.aprendia_api.auth.domain.entities.PersonaRol;
import com.icheha.aprendia_api.auth.domain.enums.GenderEnum;
import com.icheha.aprendia_api.auth.domain.valueobjects.Curp;
import com.icheha.aprendia_api.auth.data.entities.UserEntity;
import com.icheha.aprendia_api.auth.data.mappers.UserRolMapper;
import com.icheha.aprendia_api.auth.data.repositories.UserRepository;
import com.icheha.aprendia_api.users.person.data.entities.DomicilioEntity;
import com.icheha.aprendia_api.users.person.data.entities.PersonaEntity;
import com.icheha.aprendia_api.users.person.data.entities.RoadTypeEntity;
import com.icheha.aprendia_api.users.person.data.entities.SettlementEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component("userPersonaMapper")
public class PersonaMapper {
    
    private final RoadTypeMapper roadTypeMapper;
    private final SettlementMapper settlementMapper;
    private final UserRepository userRepository;
    private final UserRolMapper userRolMapper;
    
    @Autowired
    public PersonaMapper(RoadTypeMapper roadTypeMapper, SettlementMapper settlementMapper, UserRepository userRepository, UserRolMapper userRolMapper) {
        this.roadTypeMapper = roadTypeMapper;
        this.settlementMapper = settlementMapper;
        this.userRepository = userRepository;
        this.userRolMapper = userRolMapper;
    }
    
    public Persona toDomain(PersonaEntity entity) {
        if (entity == null) return null;
        
        // Obtener datos de dirección desde DomicilioEntity si existe
        // IMPORTANTE: No acceder a getDomicilio() directamente para evitar StackOverflowError
        // en relaciones bidireccionales. Usar try-catch para manejar proxies lazy.
        String codigoPostal = null;
        String estado = null;
        String municipio = null;
        String localidad = null;
        String asentamiento = null;
        Integer idVialidadTipo = null;
        Integer idAsentamientoTipo = null;
        String vialidadNombre = null;
        
        // NO acceder a getDomicilio() en findAll() para evitar StackOverflowError
        // El domicilio solo se cargará cuando sea necesario (findById con relaciones)
        // Para findAll(), dejamos todos los campos de dirección como null
        
        // Construir Persona con valores reales cuando estén disponibles
        // Acceder a los campos de forma segura para evitar StackOverflowError
        Long idPersona = null;
        String primerNombre = null;
        String segundoNombre = null;
        String primerApellido = null;
        String segundoApellido = null;
        String numeroIne = null;
        LocalDate fechaNacimiento = null;
        GenderEnum genero = null;
        String curp = null;
        String password = null;
        
        try {
            idPersona = entity.getIdPersona();
            primerNombre = entity.getPrimerNombre();
            segundoNombre = entity.getSegundoNombre();
            primerApellido = entity.getPrimerApellido();
            segundoApellido = entity.getSegundoApellido();
            numeroIne = entity.getNumeroIne();
            fechaNacimiento = entity.getFechaNacimiento();
            genero = entity.getGenero();
            curp = entity.getCurp();
            
            // Obtener password desde UserEntity
            // IMPORTANTE: No acceder a entity.getUser() directamente para evitar StackOverflowError
            // en relaciones bidireccionales. Usar consulta directa al repositorio.
            try {
                if (idPersona != null) {
                    UserEntity userEntity = userRepository.findByIdPersona(idPersona).orElse(null);
                    if (userEntity != null) {
                        password = userEntity.getPassword();
                    }
                }
            } catch (Exception e) {
                // Si hay error al acceder a UserEntity, dejar password como null
            }
        } catch (Exception e) {
            // Si hay error al acceder a los campos, usar valores por defecto
        }
        
        Persona.Builder builder = new Persona.Builder()
                .idPersona(idPersona)
                .primerNombre(primerNombre != null ? primerNombre : "")
                .segundoNombre(segundoNombre != null ? segundoNombre : "")
                .apellidoPaterno(primerApellido != null ? primerApellido : "")
                .apellidoMaterno(segundoApellido != null ? segundoApellido : "")
                .numeroIne(numeroIne != null ? numeroIne : "")
                .fechaNacimiento(fechaNacimiento)
                .genero(genero)
                .codigoPostal(codigoPostal)
                .estado(estado)
                .municipio(municipio)
                .localidad(localidad)
                .vialidadNombre(vialidadNombre)
                .idVialidadTipo(idVialidadTipo)
                .asentamiento(asentamiento)
                .idAsentamientoTipo(idAsentamientoTipo);
        
        // CURP: usar valor real si está disponible
        if (curp != null && !curp.trim().isEmpty()) {
            try {
                builder.curp(new Curp(curp));
            } catch (IllegalArgumentException e) {
                // Si el CURP no es válido, dejarlo como null
                builder.curp(null);
            }
        } else {
            builder.curp(null);
        }
        
        // Password y Roles: obtener desde UserEntity si está disponible
        List<PersonaRol> personaRoles = new ArrayList<>();
        if (password != null && !password.trim().isEmpty()) {
            builder.password(new com.icheha.aprendia_api.auth.domain.valueobjects.Password(password));
        } else {
            builder.password(null);
        }
        
        // Obtener roles desde UserEntity
        // IMPORTANTE: No acceder a entity.getUser() directamente para evitar StackOverflowError
        // en relaciones bidireccionales. Usar consulta directa al repositorio.
        try {
            if (idPersona != null) {
                UserEntity userEntity = userRepository.findByIdPersona(idPersona).orElse(null);
                if (userEntity != null && userEntity.getIdUser() != null) {
                    try {
                        var userWithRoles = userRepository.findByIdWithRoles(userEntity.getIdUser());
                        if (userWithRoles.isPresent() && userWithRoles.get().getUserRoles() != null) {
                            personaRoles = userWithRoles.get().getUserRoles().stream()
                                    .map(userRolMapper::toDomain)
                                    .filter(pr -> pr != null)
                                    .collect(java.util.stream.Collectors.toList());
                        }
                    } catch (Exception e) {
                        // Si hay error, dejar la lista vacía
                    }
                }
            }
        } catch (Exception e) {
            // Si hay error al acceder a UserEntity, dejar la lista vacía
        }
        
        builder.personaRoles(personaRoles);
        return builder.build();
    }
    
    public PersonaEntity toEntity(Persona domain, RoadTypeEntity roadType, SettlementEntity settlement) {
        return toEntity(domain, roadType, settlement, null);
    }
    
    public PersonaEntity toEntity(Persona domain, RoadTypeEntity roadType, SettlementEntity settlement, String profileImagePath) {
        if (domain == null) return null;
        
        PersonaEntity entity = new PersonaEntity();
        entity.setIdPersona(domain.getIdPersona());
        entity.setPrimerNombre(domain.getPrimerNombre());
        entity.setSegundoNombre(domain.getSegundoNombre());
        entity.setPrimerApellido(domain.getApellidoPaterno());
        entity.setSegundoApellido(domain.getApellidoMaterno());
        entity.setCurp(domain.getCurp() != null ? domain.getCurp().getValue() : null);
        entity.setNumeroIne(domain.getNumeroIne());
        entity.setFechaNacimiento(domain.getFechaNacimiento());
        entity.setGenero(domain.getGenero());
        entity.setEmail(null); // TODO: Agregar email al dominio si es necesario
        entity.setTelefono(null); // TODO: Agregar telefono al dominio si es necesario
        entity.setProfileImagePath(profileImagePath); // Guardar la URL de la imagen de perfil
        
        // Nota: Password se maneja en UserEntity, no aquí
        // El UserEntity debe ser creado/actualizado por separado
        
        // Crear DomicilioEntity con los datos de dirección
        // El idPersona se establecerá después de guardar la persona
        if (domain.getVialidadNombre() != null || settlement != null) {
            DomicilioEntity domicilio = new DomicilioEntity();
            domicilio.setCalle(domain.getVialidadNombre()); // vialidadNombre se guarda en calle
            domicilio.setColonia(settlement != null ? settlement.getNombre() : null); // asentamiento se guarda en colonia
            domicilio.setIdMunicipio(settlement != null && settlement.getMunicipio() != null 
                ? settlement.getMunicipio().getId() : null);
            domicilio.setIdEstado(settlement != null && settlement.getMunicipio() != null 
                && settlement.getMunicipio().getEstado() != null 
                ? settlement.getMunicipio().getEstado().getId() : null);
            domicilio.setCp(settlement != null && settlement.getZipcode() != null 
                ? settlement.getZipcode().getCodigo() : null);
            domicilio.setLocalidad(settlement != null && settlement.getCiudad() != null 
                ? settlement.getCiudad().getNombre() : null);
            
            // Establecer la relación bidireccional
            entity.setDomicilio(domicilio);
            domicilio.setPersona(entity);
        }
        
        // Nota: Password está en UserEntity, debe manejarse por separado
        // Nota: profileImagePath se guardará en otra entidad o se manejará por separado
        // Por ahora, si hay un campo en PersonaEntity para la imagen, se establecería aquí
        
        return entity;
    }
}

