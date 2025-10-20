package com.icheha.aprendia_api.assets.assets.services;

import com.icheha.aprendia_api.assets.assets.data.dtos.request.CreateAssetDto;
import com.icheha.aprendia_api.assets.assets.data.dtos.response.CreateAssetResponseDto;
import com.icheha.aprendia_api.assets.assets.data.dtos.response.FindAssetDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IAssetService {
    CreateAssetResponseDto createAndSaveAsset(MultipartFile file, CreateAssetDto request);
    List<FindAssetDto> findAssetByTagsIds(List<Long> tagsIds);
    List<FindAssetDto> findAssetByDescription(String description);
}