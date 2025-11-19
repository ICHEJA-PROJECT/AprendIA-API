package com.icheha.aprendia_api.auth.data.repositories.impl;

import com.icheha.aprendia_api.auth.data.entities.UserEntity;
import com.icheha.aprendia_api.auth.data.entities.UserRolEntity;
import com.icheha.aprendia_api.auth.data.mappers.UserRolMapper;
import com.icheha.aprendia_api.auth.data.repositories.UserRolRepository;
import com.icheha.aprendia_api.auth.data.repositories.UserRepository;
import com.icheha.aprendia_api.auth.domain.entities.PersonaRol;
import com.icheha.aprendia_api.auth.domain.repositories.IRolRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class RolRepositoryImpl implements IRolRepository {
    
    @Autowired
    private UserRolRepository userRolRepository;
    
    @Autowired
    @Qualifier("userJpaRepository")
    private UserRepository userRepository;
    
    @Autowired
    private UserRolMapper userRolMapper;
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<PersonaRol> findByPersonaId(Long personaId) {
        if (personaId == null) {
            return Optional.empty();
        }
        
        // Usar EntityManager directamente para obtener UserEntity y evitar problemas de casting
        jakarta.persistence.TypedQuery<UserEntity> userQuery = entityManager.createQuery(
            "SELECT u FROM UserEntity u WHERE u.idPersona = :idPersona", UserEntity.class);
        userQuery.setParameter("idPersona", personaId);
        userQuery.setMaxResults(1);
        
        try {
            UserEntity userEntity = userQuery.getSingleResult();
            if (userEntity != null && userEntity.getIdUser() != null) {
                // Obtener roles usando el repositorio
                return userRolRepository.findAllByUserId(userEntity.getIdUser()).stream()
                        .map(userRolMapper::toDomain)
                        .findFirst();
            }
        } catch (jakarta.persistence.NoResultException e) {
            // No hay usuario para esta persona
        }
        
        return Optional.empty();
    }
    
    @Override
    public Optional<PersonaRol> findById(Long id) {
        return userRolRepository.findById(id)
                .map(userRolMapper::toDomain);
    }
    
    @Override
    public PersonaRol save(PersonaRol personaRol) {
        UserRolEntity entity = userRolMapper.toEntity(personaRol);
        // Necesitamos establecer la relación con UserEntity
        if (personaRol.getIdPersona() != null) {
            userRepository.findById(personaRol.getIdPersona())
                    .ifPresent(entity::setUser);
        }
        UserRolEntity savedEntity = userRolRepository.save(entity);
        return userRolMapper.toDomain(savedEntity);
    }
    
    @Override
    public void deleteById(Long id) {
        userRolRepository.deleteById(id);
    }
    
    @Override
    public void deleteByPersonaId(Long personaId) {
        if (personaId == null) {
            return;
        }
        
        // Usar EntityManager directamente para obtener UserEntity y evitar problemas de casting
        jakarta.persistence.TypedQuery<UserEntity> userQuery = entityManager.createQuery(
            "SELECT u FROM UserEntity u WHERE u.idPersona = :idPersona", UserEntity.class);
        userQuery.setParameter("idPersona", personaId);
        userQuery.setMaxResults(1);
        
        try {
            UserEntity userEntity = userQuery.getSingleResult();
            if (userEntity != null && userEntity.getIdUser() != null) {
                userRolRepository.deleteByUserId(userEntity.getIdUser());
            }
        } catch (jakarta.persistence.NoResultException e) {
            // No hay usuario para esta persona
        }
    }
}
