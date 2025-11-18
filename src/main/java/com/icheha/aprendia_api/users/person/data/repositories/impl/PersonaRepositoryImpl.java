package com.icheha.aprendia_api.users.person.data.repositories.impl;

import com.icheha.aprendia_api.auth.domain.entities.Persona;
import com.icheha.aprendia_api.auth.domain.valueobjects.Curp;
import com.icheha.aprendia_api.auth.data.entities.UserEntity;
import com.icheha.aprendia_api.auth.data.repositories.UserRepository;
import com.icheha.aprendia_api.users.person.data.entities.DomicilioEntity;
import com.icheha.aprendia_api.users.person.data.entities.PersonaEntity;
import com.icheha.aprendia_api.users.person.data.entities.RoadTypeEntity;
import com.icheha.aprendia_api.users.person.data.entities.SettlementEntity;
import com.icheha.aprendia_api.users.person.data.mappers.PersonaMapper;
import com.icheha.aprendia_api.users.person.data.repositories.PersonaRepository;
import com.icheha.aprendia_api.users.person.data.repositories.RoadTypeRepository;
import com.icheha.aprendia_api.users.person.data.repositories.SettlementRepository;
import com.icheha.aprendia_api.users.person.domain.repositories.IPersonaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository("userPersonaRepositoryImpl")
public class PersonaRepositoryImpl implements IPersonaRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    private final PersonaRepository personaRepository;
    private final RoadTypeRepository roadTypeRepository;
    private final SettlementRepository settlementRepository;
    private final PersonaMapper personaMapper;
    private final UserRepository userRepository;
    
    public PersonaRepositoryImpl(@Lazy @Qualifier("userPersonaRepository") PersonaRepository personaRepository,
                                @Lazy RoadTypeRepository roadTypeRepository,
                                @Lazy SettlementRepository settlementRepository,
                                @Qualifier("userPersonaMapper") PersonaMapper personaMapper,
                                @Lazy UserRepository userRepository) {
        this.personaRepository = personaRepository;
        this.roadTypeRepository = roadTypeRepository;
        this.settlementRepository = settlementRepository;
        this.personaMapper = personaMapper;
        this.userRepository = userRepository;
    }
    
    @Override
    public Persona create(Persona persona, Long roadTypeId, Long settlementId, String profileImagePath) {
        if (persona == null) {
            throw new IllegalArgumentException("Persona no puede ser nula");
        }
        if (roadTypeId == null) {
            throw new IllegalArgumentException("ID del tipo de vialidad no puede ser nulo");
        }
        if (settlementId == null) {
            throw new IllegalArgumentException("ID del asentamiento no puede ser nulo");
        }
        
        RoadTypeEntity roadType = roadTypeRepository.findById(roadTypeId)
                .orElseThrow(() -> new IllegalArgumentException("Tipo de vialidad no encontrado con ID: " + roadTypeId));
        
        SettlementEntity settlement = settlementRepository.findById(settlementId)
                .orElseThrow(() -> new IllegalArgumentException("Asentamiento no encontrado con ID: " + settlementId));
        
        PersonaEntity entity = personaMapper.toEntity(persona, roadType, settlement, profileImagePath);
        
        // Temporalmente remover el domicilio para evitar que se guarde con cascade
        DomicilioEntity domicilio = entity.getDomicilio();
        entity.setDomicilio(null);
        
        // Guardar primero la persona para obtener el ID
        PersonaEntity savedEntity = personaRepository.save(entity);
        
        // Establecer idPersona en el domicilio y guardarlo manualmente
        if (domicilio != null) {
            domicilio.setIdPersona(savedEntity.getIdPersona());
            domicilio.setPersona(savedEntity);
            savedEntity.setDomicilio(domicilio);
            // Guardar nuevamente para persistir el domicilio con el idPersona correcto
            savedEntity = personaRepository.save(savedEntity);
        }
        
        // Crear UserEntity si la persona tiene password
        if (persona.getPassword() != null && persona.getPassword().getHashedValue() != null) {
            String username = persona.getCurp() != null ? persona.getCurp().getValue() : "user_" + savedEntity.getIdPersona();
            UserEntity userEntity = new UserEntity();
            userEntity.setIdPersona(savedEntity.getIdPersona());
            userEntity.setUsername(username);
            userEntity.setPassword(persona.getPassword().getHashedValue());
            userEntity.setIsActive(true);
            userEntity.setPersona(savedEntity);
            userRepository.save(userEntity);
        }
        
        return personaMapper.toDomain(savedEntity);
    }
    
    @Override
    public Optional<Persona> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return personaRepository.findByIdWithRelations(id)
                .map(personaMapper::toDomain);
    }
    
    @Override
    public Optional<Persona> findByCurp(Curp curp) {
        if (curp == null) {
            return Optional.empty();
        }
        return personaRepository.findByCurp(curp.getValue())
                .map(personaMapper::toDomain);
    }
    
    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<Persona> findAll() {
        // Usar EntityManager directamente para evitar recursión con el proxy de Spring
        // El mapper ya está configurado para no acceder a relaciones lazy
        jakarta.persistence.TypedQuery<PersonaEntity> query = entityManager.createQuery(
            "SELECT p FROM UserPersonaEntity p", PersonaEntity.class);
        return query.getResultList().stream()
                .map(personaMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public Persona update(Persona persona, Long roadTypeId, Long settlementId, String profileImagePath) {
        if (persona == null || persona.getIdPersona() == null) {
            throw new IllegalArgumentException("Persona y su ID no pueden ser nulos");
        }
        
        PersonaEntity existingEntity = personaRepository.findById(persona.getIdPersona())
                .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada con ID: " + persona.getIdPersona()));
        
        RoadTypeEntity roadType = null;
        if (roadTypeId != null) {
            roadType = roadTypeRepository.findById(roadTypeId)
                    .orElseThrow(() -> new IllegalArgumentException("Tipo de vialidad no encontrado con ID: " + roadTypeId));
        }
        // Si no se proporciona roadTypeId, el mapper manejará esto correctamente
        
        SettlementEntity settlement = null;
        if (settlementId != null) {
            settlement = settlementRepository.findById(settlementId)
                    .orElseThrow(() -> new IllegalArgumentException("Asentamiento no encontrado con ID: " + settlementId));
        } else {
            // Mantener el asentamiento existente si no se proporciona uno nuevo
            // No podemos buscar fácilmente el settlement desde el domicilio, así que lo dejamos null
            // El mapper manejará esto correctamente
        }
        
        // Actualizar la entidad con los nuevos datos
        PersonaEntity updatedEntity = personaMapper.toEntity(persona, roadType, settlement, profileImagePath);
        updatedEntity.setIdPersona(existingEntity.getIdPersona());
        
        // Mantener profileImagePath si no se proporciona uno nuevo
        if (profileImagePath == null && existingEntity.getProfileImagePath() != null) {
            updatedEntity.setProfileImagePath(existingEntity.getProfileImagePath());
        }
        
        PersonaEntity savedEntity = personaRepository.save(updatedEntity);
        
        // Establecer idPersona en DomicilioEntity después de guardar
        if (savedEntity.getDomicilio() != null) {
            savedEntity.getDomicilio().setIdPersona(savedEntity.getIdPersona());
            personaRepository.save(savedEntity);
        }
        
        // Crear o actualizar UserEntity si la persona tiene password
        if (persona.getPassword() != null && persona.getPassword().getHashedValue() != null) {
            UserEntity userEntity = userRepository.findByIdPersona(savedEntity.getIdPersona()).orElse(null);
            
            if (userEntity == null) {
                // Crear nuevo UserEntity
                String username = persona.getCurp() != null ? persona.getCurp().getValue() : "user_" + savedEntity.getIdPersona();
                userEntity = new UserEntity();
                userEntity.setIdPersona(savedEntity.getIdPersona());
                userEntity.setUsername(username);
                userEntity.setPassword(persona.getPassword().getHashedValue());
                userEntity.setIsActive(true);
                userEntity.setPersona(savedEntity);
            } else {
                // Actualizar password existente
                userEntity.setPassword(persona.getPassword().getHashedValue());
            }
            
            userRepository.save(userEntity);
        }
        
        return personaMapper.toDomain(savedEntity);
    }
    
    @Override
    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        if (!personaRepository.existsById(id)) {
            throw new IllegalArgumentException("Persona no encontrada con ID: " + id);
        }
        personaRepository.deleteById(id);
    }
}

