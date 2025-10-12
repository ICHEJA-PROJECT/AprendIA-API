package com.icheha.aprendia_api.assets.assets.services.impl;

import com.icheha.aprendia_api.assets.assets.data.dtos.request.CreateAssetDto;
import com.icheha.aprendia_api.assets.assets.data.dtos.response.AssetResponseDto;
import com.icheha.aprendia_api.assets.assets.domain.entities.Asset;
import com.icheha.aprendia_api.assets.assets.domain.repositories.IAssetRepository;
import com.icheha.aprendia_api.assets.assets.services.IAssetService;
import com.icheha.aprendia_api.assets.assets.services.mappers.AssetMapper;
import org.springframework.stereotype.Service;

@Service
public class AssetServiceImpl implements IAssetService {

    private final IAssetRepository assetRepository;
    private final AssetMapper assetMapper;

    public AssetServiceImpl(IAssetRepository assetRepository, @org.springframework.beans.factory.annotation.Qualifier("assetServiceMapper") AssetMapper assetMapper) {
        this.assetRepository = assetRepository;
        this.assetMapper = assetMapper;
    }

    @Override
    public AssetResponseDto createAsset(CreateAssetDto createAssetDto) {
        // 1. DTO -> Objeto de Dominio
        Asset asset = assetMapper.toDomain(createAssetDto);

        // 2. Guardar el objeto de dominio (la capa de datos se encarga de la conversión a Entidad)
        Asset savedAsset = assetRepository.save(asset);

        // 3. Objeto de Dominio -> DTO de Respuesta
        return assetMapper.toResponseDto(savedAsset);
    }
}
