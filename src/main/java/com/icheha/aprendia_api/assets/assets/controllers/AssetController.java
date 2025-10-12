package com.icheha.aprendia_api.assets.assets.controllers;

import com.icheha.aprendia_api.assets.assets.data.dtos.request.CreateAssetDto;
import com.icheha.aprendia_api.assets.assets.data.dtos.response.AssetResponseDto;
import com.icheha.aprendia_api.assets.assets.services.IAssetService;
import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assets")
@Tag(name="Assets", description = "API para gestión de activos")
public class AssetController {
    private final IAssetService assetService;

    @Autowired
    public AssetController(IAssetService assetService) {
        this.assetService = assetService;
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo activo", description = "Crea un nuevo activo")
    public ResponseEntity<BaseResponse<AssetResponseDto>> createAsset(
            @RequestBody CreateAssetDto createAssetDto
            ) {
        AssetResponseDto asset = assetService.createAsset(createAssetDto);
        BaseResponse<AssetResponseDto> response = new BaseResponse<>(
                true, asset, "Activo creado exitosamente", HttpStatus.CREATED
        );
        return response.buildResponseEntity();
    }
}
