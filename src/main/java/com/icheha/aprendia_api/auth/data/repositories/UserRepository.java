package com.icheha.aprendia_api.auth.data.repositories;

import com.icheha.aprendia_api.auth.data.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    
    Optional<UserEntity> findByIdPersona(Long idPersona);
    
    Optional<UserEntity> findByUsername(String username);
    
    @Query("SELECT u FROM UserEntity u " +
           "LEFT JOIN FETCH u.persona " +
           "WHERE u.idPersona = :idPersona")
    Optional<UserEntity> findByIdPersonaWithPersona(@Param("idPersona") Long idPersona);
    
    @Query("SELECT u FROM UserEntity u " +
           "LEFT JOIN FETCH u.persona " +
           "WHERE u.username = :username")
    Optional<UserEntity> findByUsernameWithPersona(@Param("username") String username);
    
    @Query("SELECT u FROM UserEntity u " +
           "LEFT JOIN FETCH u.userRoles ur " +
           "LEFT JOIN FETCH ur.rol " +
           "WHERE u.idUser = :userId")
    Optional<UserEntity> findByIdWithRoles(@Param("userId") Long userId);
    
    boolean existsByUsername(String username);
    
    boolean existsByIdPersona(Long idPersona);
}

