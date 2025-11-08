package com.icheha.aprendia_api.users.person.data.mappers;

import com.icheha.aprendia_api.auth.domain.entities.Persona;
import com.icheha.aprendia_api.auth.domain.enums.GenderEnum;
import com.icheha.aprendia_api.auth.domain.valueobjects.Curp;
import com.icheha.aprendia_api.users.person.data.entities.DomicilioEntity;
import com.icheha.aprendia_api.users.person.data.entities.PersonaEntity;
import com.icheha.aprendia_api.users.person.data.entities.RoadTypeEntity;
import com.icheha.aprendia_api.users.person.data.entities.SettlementEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component("userPersonaMapper")
public class PersonaMapper {
    
    private final RoadTypeMapper roadTypeMapper;
    private final SettlementMapper settlementMapper;
    
    public PersonaMapper(RoadTypeMapper roadTypeMapper, SettlementMapper settlementMapper) {
        this.roadTypeMapper = roadTypeMapper;
        this.settlementMapper = settlementMapper;
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
            password = entity.getPassword();
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
        
        // Password: obtener desde PersonaEntity si está disponible
        if (password != null && !password.trim().isEmpty()) {
            builder.password(new com.icheha.aprendia_api.auth.domain.valueobjects.Password(password));
        } else {
            builder.password(null);
        }
        
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
        
        // Guardar password si está disponible en el dominio
        if (domain.getPassword() != null && domain.getPassword().getHashedValue() != null) {
            entity.setPassword(domain.getPassword().getHashedValue());
        }
        
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
        
        // Nota: Password ahora está en UserEntity, no en PersonaEntity
        // Nota: profileImagePath se guardará en otra entidad o se manejará por separado
        // Por ahora, si hay un campo en PersonaEntity para la imagen, se establecería aquí
        
        return entity;
    }
}

