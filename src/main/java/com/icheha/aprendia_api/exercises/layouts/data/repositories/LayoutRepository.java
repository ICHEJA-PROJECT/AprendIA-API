package com.icheha.aprendia_api.exercises.layouts.data.repositories;

import com.icheha.aprendia_api.exercises.layouts.data.entities.LayoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LayoutRepository extends JpaRepository<LayoutEntity, Long> {
}
