package com.icheha.aprendia_api.auth.data.repositories.impl;

import com.icheha.aprendia_api.auth.data.entities.UserEntity;
import com.icheha.aprendia_api.auth.data.mappers.UserMapper;
import com.icheha.aprendia_api.auth.data.repositories.UserRepository;
import com.icheha.aprendia_api.auth.domain.entities.User;
import com.icheha.aprendia_api.auth.domain.repositories.IUserRepository;
import com.icheha.aprendia_api.users.person.data.repositories.PersonaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementación del repositorio de dominio para User
 */
@Repository("userDomainRepository")
public class UserRepositoryImpl implements IUserRepository {
    
    private final UserRepository userRepository;
    private final PersonaRepository personaRepository;
    private final UserMapper userMapper;
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public UserRepositoryImpl(@Lazy @Qualifier("userJpaRepository") UserRepository userRepository,
                             @Lazy @Qualifier("userPersonaRepository") PersonaRepository personaRepository,
                             UserMapper userMapper) {
        this.userRepository = userRepository;
        this.personaRepository = personaRepository;
        this.userMapper = userMapper;
    }
    
    @Override
    public User create(User user, Long idPersona, String hashedPassword) {
        if (user == null) {
            throw new IllegalArgumentException("User no puede ser nulo");
        }
        if (idPersona == null) {
            throw new IllegalArgumentException("ID de persona no puede ser nulo");
        }
        
        // Verificar que la persona existe
        personaRepository.findById(idPersona)
                .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada con ID: " + idPersona));
        
        // Verificar que no existe otro usuario con el mismo username
        if (user.getUsername() != null && userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Ya existe un usuario con el username: " + user.getUsername());
        }
        
        // Verificar que no existe otro usuario con el mismo idPersona
        if (userRepository.existsByIdPersona(idPersona)) {
            throw new IllegalArgumentException("Ya existe un usuario para la persona con ID: " + idPersona);
        }
        
        UserEntity entity = userMapper.toEntity(user);
        entity.setIdPersona(idPersona);
        
        // Establecer password hasheado si se proporciona
        if (hashedPassword != null && !hashedPassword.trim().isEmpty()) {
            entity.setPassword(hashedPassword);
        }
        
        // NO establecer las relaciones para evitar recursión infinita
        // La relación se manejará automáticamente por JPA cuando sea necesario
        entity.setPersona(null);
        entity.setUserRoles(null);
        
        // Usar EntityManager directamente para evitar recursión con el proxy de Spring
        entityManager.persist(entity);
        entityManager.flush();
        // No usar refresh() porque puede cargar relaciones y causar recursión
        
        // Crear una nueva entidad limpia con solo los datos básicos para evitar StackOverflowError
        // Esto previene que JPA intente serializar relaciones bidireccionales
        UserEntity cleanEntity = createCleanEntity(entity);
        
        return userMapper.toDomain(cleanEntity);
    }
    
    /**
     * Crea una entidad limpia sin relaciones para evitar StackOverflowError
     */
    private UserEntity createCleanEntity(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        UserEntity cleanEntity = new UserEntity();
        cleanEntity.setIdUser(entity.getIdUser());
        cleanEntity.setIdPersona(entity.getIdPersona());
        cleanEntity.setUsername(entity.getUsername());
        cleanEntity.setPassword(entity.getPassword());
        cleanEntity.setIsActive(entity.getIsActive());
        cleanEntity.setCreatedAt(entity.getCreatedAt());
        cleanEntity.setUpdatedAt(entity.getUpdatedAt());
        // NO establecer relaciones para evitar recursión
        return cleanEntity;
    }
    
    @Override
    public Optional<User> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        // Usar EntityManager directamente para evitar recursión con el proxy de Spring
        UserEntity entity = entityManager.find(UserEntity.class, id);
        if (entity == null) {
            return Optional.empty();
        }
        UserEntity cleanEntity = createCleanEntity(entity);
        return Optional.ofNullable(userMapper.toDomain(cleanEntity));
    }
    
    @Override
    public Optional<User> findByIdPersona(Long idPersona) {
        if (idPersona == null) {
            return Optional.empty();
        }
        // Usar EntityManager directamente para evitar recursión con el proxy de Spring
        jakarta.persistence.TypedQuery<UserEntity> query = entityManager.createQuery(
            "SELECT u FROM UserEntity u WHERE u.idPersona = :idPersona", UserEntity.class);
        query.setParameter("idPersona", idPersona);
        query.setMaxResults(1);
        try {
            UserEntity entity = query.getSingleResult();
            UserEntity cleanEntity = createCleanEntity(entity);
            return Optional.ofNullable(userMapper.toDomain(cleanEntity));
        } catch (jakarta.persistence.NoResultException e) {
            return Optional.empty();
        }
    }
    
    @Override
    public Optional<User> findByUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return Optional.empty();
        }
        // Usar EntityManager directamente para evitar recursión con el proxy de Spring
        jakarta.persistence.TypedQuery<UserEntity> query = entityManager.createQuery(
            "SELECT u FROM UserEntity u WHERE u.username = :username", UserEntity.class);
        query.setParameter("username", username);
        query.setMaxResults(1);
        try {
            UserEntity entity = query.getSingleResult();
            UserEntity cleanEntity = createCleanEntity(entity);
            return Optional.ofNullable(userMapper.toDomain(cleanEntity));
        } catch (jakarta.persistence.NoResultException e) {
            return Optional.empty();
        }
    }
    
    @Override
    public List<User> findAll() {
        // Usar EntityManager directamente para evitar recursión con el proxy de Spring
        jakarta.persistence.TypedQuery<UserEntity> query = entityManager.createQuery(
            "SELECT u FROM UserEntity u", UserEntity.class);
        return query.getResultList().stream()
                .map(this::createCleanEntity)
                .map(userMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public User update(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User no puede ser nulo");
        }
        if (user.getId() == null) {
            throw new IllegalArgumentException("ID de usuario no puede ser nulo para actualizar");
        }
        
        UserEntity entity = userRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + user.getId()));
        
        // Actualizar campos permitidos
        if (user.getUsername() != null && !user.getUsername().equals(entity.getUsername())) {
            // Verificar que el nuevo username no esté en uso por otro usuario
            if (userRepository.existsByUsername(user.getUsername())) {
                throw new IllegalArgumentException("Ya existe un usuario con el username: " + user.getUsername());
            }
            entity.setUsername(user.getUsername());
        }
        
        if (user.getIsActive() != null) {
            entity.setIsActive(user.getIsActive());
        }
        
        UserEntity updatedEntity = userRepository.save(entity);
        // Crear entidad limpia para evitar recursión
        UserEntity cleanEntity = createCleanEntity(updatedEntity);
        return userMapper.toDomain(cleanEntity);
    }
    
    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID de usuario no puede ser nulo");
        }
        
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuario no encontrado con ID: " + id);
        }
        
        userRepository.deleteById(id);
    }
    
    @Override
    public boolean existsByUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        // Usar EntityManager directamente para evitar recursión con el proxy de Spring
        jakarta.persistence.TypedQuery<Long> query = entityManager.createQuery(
            "SELECT COUNT(u) FROM UserEntity u WHERE u.username = :username", Long.class);
        query.setParameter("username", username);
        Long count = query.getSingleResult();
        return count != null && count > 0;
    }
    
    @Override
    public boolean existsByIdPersona(Long idPersona) {
        if (idPersona == null) {
            return false;
        }
        // Usar EntityManager directamente para evitar recursión con el proxy de Spring
        jakarta.persistence.TypedQuery<Long> query = entityManager.createQuery(
            "SELECT COUNT(u) FROM UserEntity u WHERE u.idPersona = :idPersona", Long.class);
        query.setParameter("idPersona", idPersona);
        Long count = query.getSingleResult();
        return count != null && count > 0;
    }
}

