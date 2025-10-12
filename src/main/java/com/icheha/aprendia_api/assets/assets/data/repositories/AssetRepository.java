package com.icheha.aprendia_api.assets.assets.data.repositories;

import com.icheha.aprendia_api.assets.assets.data.entities.AssetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<AssetEntity, Long> {
}
