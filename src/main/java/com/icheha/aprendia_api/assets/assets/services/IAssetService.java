package com.icheha.aprendia_api.assets.assets.services;

import com.icheha.aprendia_api.assets.assets.data.dtos.request.CreateAssetDto;
import com.icheha.aprendia_api.assets.assets.data.dtos.response.AssetResponseDto;

public interface IAssetService {
    AssetResponseDto createAsset(CreateAssetDto createAssetDto);
}
