package com.icheha.aprendia_api.auth.data.repositories;

import com.icheha.aprendia_api.auth.data.entities.UserRolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRolRepository extends JpaRepository<UserRolEntity, Long> {
    
    @Query("SELECT ur FROM UserRolEntity ur JOIN FETCH ur.rol WHERE ur.user.idUser = :userId")
    Optional<UserRolEntity> findByUserId(@Param("userId") Long userId);
    
    @Query("SELECT ur FROM UserRolEntity ur JOIN FETCH ur.rol WHERE ur.idUser = :userId")
    List<UserRolEntity> findAllByUserId(@Param("userId") Long userId);
    
    @Modifying
    @Query("DELETE FROM UserRolEntity ur WHERE ur.user.idUser = :userId")
    void deleteByUserId(@Param("userId") Long userId);
    
    @Query("SELECT ur FROM UserRolEntity ur JOIN FETCH ur.rol JOIN FETCH ur.user WHERE ur.idUser = :userId")
    List<UserRolEntity> findAllByUserIdWithRelations(@Param("userId") Long userId);
}




