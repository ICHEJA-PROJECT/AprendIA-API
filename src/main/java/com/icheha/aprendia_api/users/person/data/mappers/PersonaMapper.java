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
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    public PersonaMapper(RoadTypeMapper roadTypeMapper, SettlementMapper settlementMapper, @Qualifier("userJpaRepository") UserRepository userRepository, UserRolMapper userRolMapper) {
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
        String email = null;
        String telefono = null;
        
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
            email = entity.getEmail();
            telefono = entity.getTelefono();
            
            // Obtener password desde UserEntity usando EntityManager directamente
            // para evitar recursión con el proxy de Spring y relaciones bidireccionales
            try {
                if (idPersona != null) {
                    jakarta.persistence.TypedQuery<UserEntity> query = entityManager.createQuery(
                        "SELECT u FROM UserEntity u WHERE u.idPersona = :idPersona", UserEntity.class);
                    query.setParameter("idPersona", idPersona);
                    query.setMaxResults(1);
                    try {
                        UserEntity userEntity = query.getSingleResult();
                        if (userEntity != null) {
                            password = userEntity.getPassword();
                        }
                    } catch (jakarta.persistence.NoResultException e) {
                        // No hay usuario para esta persona
                        password = null;
                    }
                }
            } catch (Exception e) {
                // Si hay error al acceder a UserEntity, dejar password como null
                password = null;
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
                .idAsentamientoTipo(idAsentamientoTipo)
                .email(email)
                .telefono(telefono);
        
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
        
        // Obtener roles desde UserEntity usando EntityManager directamente
        // para evitar recursión con el proxy de Spring y relaciones bidireccionales
        try {
            if (idPersona != null) {
                // Obtener UserEntity usando EntityManager
                jakarta.persistence.TypedQuery<UserEntity> userQuery = entityManager.createQuery(
                    "SELECT u FROM UserEntity u WHERE u.idPersona = :idPersona", UserEntity.class);
                userQuery.setParameter("idPersona", idPersona);
                userQuery.setMaxResults(1);
                try {
                    UserEntity userEntity = userQuery.getSingleResult();
                    if (userEntity != null && userEntity.getIdUser() != null) {
                        // Obtener roles usando consulta directa con EntityManager
                        jakarta.persistence.TypedQuery<com.icheha.aprendia_api.auth.data.entities.UserRolEntity> rolesQuery = 
                            entityManager.createQuery(
                                "SELECT ur FROM UserRolEntity ur " +
                                "LEFT JOIN FETCH ur.rol " +
                                "WHERE ur.idUser = :userId", 
                                com.icheha.aprendia_api.auth.data.entities.UserRolEntity.class);
                        rolesQuery.setParameter("userId", userEntity.getIdUser());
                        List<com.icheha.aprendia_api.auth.data.entities.UserRolEntity> userRoles = rolesQuery.getResultList();
                        
                        if (userRoles != null && !userRoles.isEmpty()) {
                            personaRoles = userRoles.stream()
                                    .map(userRolMapper::toDomain)
                                    .filter(pr -> pr != null)
                                    .collect(java.util.stream.Collectors.toList());
                        }
                    }
                } catch (jakarta.persistence.NoResultException e) {
                    // No hay usuario para esta persona, dejar roles vacíos
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
        entity.setEmail(domain.getEmail());
        entity.setTelefono(domain.getTelefono());
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

