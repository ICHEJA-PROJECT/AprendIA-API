package com.icheha.aprendia_api.auth.services.mappers;

import com.icheha.aprendia_api.users.person.data.entities.PersonaEntity;
import com.icheha.aprendia_api.users.person.data.entities.DomicilioEntity;
import com.icheha.aprendia_api.auth.data.entities.UserEntity;
import com.icheha.aprendia_api.auth.data.repositories.UserRepository;
import com.icheha.aprendia_api.auth.data.mappers.UserRolMapper;
import com.icheha.aprendia_api.auth.domain.entities.Persona;
import com.icheha.aprendia_api.auth.domain.entities.PersonaRol;
import com.icheha.aprendia_api.auth.domain.valueobjects.Curp;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonaMapper {
    
    private final UserRepository userRepository;
    private final UserRolMapper userRolMapper;
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    public PersonaMapper(@Qualifier("userJpaRepository") UserRepository userRepository, UserRolMapper userRolMapper) {
        this.userRepository = userRepository;
        this.userRolMapper = userRolMapper;
    }
    
    public Persona toDomain(PersonaEntity entity) {
        if (entity == null) return null;
        
        // Obtener datos de dirección desde DomicilioEntity si existe
        // IMPORTANTE: Manejar LazyInitializationException de forma segura
        String codigoPostal = null;
        String estado = null;
        String municipio = null;
        String localidad = null;
        String asentamiento = null;
        Integer idVialidadTipo = null;
        Integer idAsentamientoTipo = null;
        String vialidadNombre = null;
        
        try {
        if (entity.getDomicilio() != null) {
            DomicilioEntity domicilio = entity.getDomicilio();
            codigoPostal = domicilio.getCp();
            estado = domicilio.getEstado() != null ? domicilio.getEstado().getNombre() : null;
            municipio = domicilio.getMunicipio() != null ? domicilio.getMunicipio().getNombre() : null;
            localidad = domicilio.getLocalidad();
            asentamiento = domicilio.getColonia();
            vialidadNombre = domicilio.getCalle();
            }
        } catch (org.hibernate.LazyInitializationException e) {
            // Si la relación no está cargada, dejar los valores como null
            // Esto es normal cuando findByCurp no carga las relaciones
        } catch (Exception e) {
            // Manejar cualquier otra excepción de forma segura
        }
        
        // Construir Persona con valores reales cuando estén disponibles
        // CURP está en PersonaEntity, así que lo usamos si está disponible
        // Password está en UserEntity
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
        
        // Password y Roles: obtener desde UserEntity usando EntityManager directamente
        // para evitar recursión con el proxy de Spring y relaciones bidireccionales
        List<PersonaRol> personaRoles = new ArrayList<>();
        String password = null;
        try {
            if (entity.getIdPersona() != null) {
                // Obtener UserEntity usando EntityManager directamente
                jakarta.persistence.TypedQuery<UserEntity> userQuery = entityManager.createQuery(
                    "SELECT u FROM UserEntity u WHERE u.idPersona = :idPersona", UserEntity.class);
                userQuery.setParameter("idPersona", entity.getIdPersona());
                userQuery.setMaxResults(1);
                try {
                    UserEntity userEntity = userQuery.getSingleResult();
                    if (userEntity != null) {
                        // Obtener password
                        password = userEntity.getPassword();
                        
                        // Obtener roles usando consulta directa con EntityManager
                        if (userEntity.getIdUser() != null) {
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
                    }
                } catch (jakarta.persistence.NoResultException e) {
                    // No hay usuario para esta persona
                }
            }
        } catch (Exception e) {
            // Si hay error al acceder a UserEntity, dejar password como null
        }
        
        // Establecer password en el builder
        if (password != null && !password.trim().isEmpty()) {
            builder.password(new com.icheha.aprendia_api.auth.domain.valueobjects.Password(password));
        } else {
            builder.password(null);
        }
        
        builder.personaRoles(personaRoles);
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
        // Nota: Password está en UserEntity, no se maneja aquí
        // Nota: Las relaciones (domicilio, user) deben establecerse en el servicio/repositorio
        return entity;
    }
}
