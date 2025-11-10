package com.icheha.aprendia_api.users.user.data.repositories;

import com.icheha.aprendia_api.users.user.data.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}

