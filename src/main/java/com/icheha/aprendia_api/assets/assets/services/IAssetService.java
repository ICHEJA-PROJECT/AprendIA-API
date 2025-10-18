package com.icheha.aprendia_api.assets.assets.services;

import com.icheha.aprendia_api.assets.assets.data.dtos.request.CreateAssetDto;
import com.icheha.aprendia_api.assets.assets.data.dtos.response.AssetResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface IAssetService {
    AssetResponseDto createAndSaveAsset(MultipartFile file, CreateAssetDto request);
}