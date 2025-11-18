package com.icheha.aprendia_api.assets.assets.services.impl;

import com.icheha.aprendia_api.assets.assets.data.dtos.request.CreateAssetDto;
import com.icheha.aprendia_api.assets.assets.data.dtos.request.UpdateAssetDto;
import com.icheha.aprendia_api.assets.assets.data.dtos.response.CreateAssetResponseDto;
import com.icheha.aprendia_api.assets.assets.data.dtos.response.FindAssetDB;
import com.icheha.aprendia_api.assets.assets.data.dtos.response.FindAssetDto;
import com.icheha.aprendia_api.assets.assets.data.dtos.response.UploadResponseDto;
import com.icheha.aprendia_api.assets.assets.domain.entities.Asset;
import com.icheha.aprendia_api.assets.assets.domain.exceptions.AssetCreationException;
import com.icheha.aprendia_api.assets.assets.domain.repositories.IAssetRepository;
import com.icheha.aprendia_api.assets.assets.services.IAssetService;
import com.icheha.aprendia_api.assets.assets.services.IToServerService;
import com.icheha.aprendia_api.assets.assets.services.mappers.AssetMapper;
import com.icheha.aprendia_api.assets.assets.services.mappers.AssetTagsMapper;
import com.icheha.aprendia_api.assets.associated_tags.services.IAssociatedTagService;
import com.icheha.aprendia_api.assets.assets.services.IVectorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssetServiceImpl implements IAssetService {

    private final IAssetRepository assetRepository;
    private final IToServerService toServerService;
    private final IVectorService vectorService;
    private final IAssociatedTagService associatedTagService;
    private final AssetMapper assetMapper;
    private final AssetTagsMapper assetTagsMapper;

    public AssetServiceImpl(
            IAssetRepository assetRepository,
            IToServerService toServerService,
            IVectorService vectorService,
            IAssociatedTagService associatedTagService,
            @org.springframework.beans.factory.annotation.Qualifier("assetServiceMapper") AssetMapper assetMapper,
            @org.springframework.beans.factory.annotation.Qualifier("assetTagServiceMapper") AssetTagsMapper assetTagsMapper
            ) {
        this.assetRepository = assetRepository;
        this.toServerService = toServerService;
        this.vectorService = vectorService;
        this.associatedTagService = associatedTagService;
        this.assetMapper = assetMapper;
        this.assetTagsMapper = assetTagsMapper;
    }

    @Override
    @Transactional
    public CreateAssetResponseDto createAndSaveAsset(MultipartFile file, CreateAssetDto request) {
        UploadResponseDto imageUrl = null;
        try {
            // Subir la imagen a Cloudinary
            imageUrl = toServerService.upload(file, request.getName());
            System.out.println(imageUrl);
            if (imageUrl == null) {
                throw new RuntimeException("Error al subir la imagen a Cloudinary.");
            }

            // Generar el vector con el servicio de IA
            float[] vector = vectorService.generateVector(request.getDescription());
            if (vector == null) {
                throw new RuntimeException("Error al generar el vector con el modelo de IA.");
            }


            Asset newAsset = Asset.builder()
                    .name(request.getName())
                    .url(imageUrl.getSecureUrl())
                    .description(request.getDescription())
                    .vector(vector)
                    .build();

            Asset savedAsset = assetRepository.save(newAsset);

            // Asociar los Tags
            if (request.getTagIds() != null && !request.getTagIds().isEmpty()) {
                associatedTagService.associateTagsToAsset(savedAsset.getId(), request.getTagIds());
            }

            return assetMapper.toResponseDto(savedAsset);

        } catch (Exception e) {
            // Lógica de compensación: si algo falla, borrar el archivo subido
            if (imageUrl != null) {
                toServerService.delete(imageUrl.getPublicId());
            }

            throw new AssetCreationException("Fallo la creación del asset: " + e.getMessage(), e);
        }
    }

    @Override
    public List<FindAssetDto> findAssetByTagsIds(List<Long> tagsIds) {
        return assetRepository.findByTags(tagsIds).stream()
                .map(assetTagsMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<FindAssetDto> findAssetByDescription(String description) {
        float[] vector = vectorService.generateVector(description);
        if (vector == null) {
            throw new RuntimeException("Error al generar el vector con el modelo de IA.");
        }
        return assetRepository.findByDescription(vector).stream()
                .map(assetTagsMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<FindAssetDto> findById(Long id) {
        return assetRepository.findByIdWithTags(id)
                .map(assetTagsMapper::toResponseDto);
    }

    @Override
    @Transactional
    public FindAssetDto update(Long id, UpdateAssetDto updateAssetDto) {
        Asset existingAsset = assetRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Asset no encontrado con ID: " + id));

        Asset.Builder builder = Asset.builder()
                .id(existingAsset.getId())
                .name(updateAssetDto.getName() != null ? updateAssetDto.getName() : existingAsset.getName())
                .url(existingAsset.getUrl()) // URL no se actualiza
                .description(updateAssetDto.getDescription() != null ? updateAssetDto.getDescription() : existingAsset.getDescription())
                .vector(existingAsset.getVector()); // Vector no se actualiza

        // Si se actualiza la descripción, regenerar el vector
        if (updateAssetDto.getDescription() != null && !updateAssetDto.getDescription().equals(existingAsset.getDescription())) {
            float[] newVector = vectorService.generateVector(updateAssetDto.getDescription());
            if (newVector != null) {
                builder.vector(newVector);
            }
        }

        assetRepository.save(builder.build());

        // Actualizar tags si se proporcionan
        if (updateAssetDto.getTagIds() != null) {
            // Eliminar todos los tags existentes
            associatedTagService.removeAllTagsFromAsset(id);
            // Asociar los nuevos tags
            if (!updateAssetDto.getTagIds().isEmpty()) {
                associatedTagService.associateTagsToAsset(id, updateAssetDto.getTagIds());
            }
        }

        // Retornar el asset con sus tags
        return assetRepository.findByIdWithTags(id)
                .map(assetTagsMapper::toResponseDto)
                .orElseThrow(() -> new RuntimeException("Error al obtener el asset actualizado"));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        assetRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Asset no encontrado con ID: " + id));

        // Eliminar tags asociados
        associatedTagService.removeAllTagsFromAsset(id);

        // Eliminar el asset
        assetRepository.delete(id);
    }
}