package com.icheha.aprendia_api.assets.assets.controllers;

import com.icheha.aprendia_api.assets.assets.data.dtos.request.CreateAssetDto;
import com.icheha.aprendia_api.assets.assets.data.dtos.response.AssetResponseDto;
import com.icheha.aprendia_api.assets.assets.domain.exceptions.AssetCreationException;
import com.icheha.aprendia_api.assets.assets.services.IAssetService;
import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/assets")
@Tag(name = "Assets", description = "Endpoints para la gestión de recursos (assets)")
public class AssetController {

    private final IAssetService assetService;

    @Autowired
    public AssetController(IAssetService assetService) {
        this.assetService = assetService;
    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
        summary = "Crear un nuevo asset",
        description = "Sube una imagen, genera un vector a partir de la descripción y guarda el asset en la base de datos."
    )
    @ApiResponse(responseCode = "201", description = "Asset creado exitosamente")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    public ResponseEntity<BaseResponse<AssetResponseDto>> createAsset(
        @RequestPart("file") MultipartFile file,
        @RequestPart("data") CreateAssetDto request
    ) {
        try {
            AssetResponseDto response = assetService.createAndSaveAsset(file, request);
            BaseResponse<AssetResponseDto> baseResponse = new BaseResponse<>(
                true,
                response,
                "Asset creado exitosamente",
                HttpStatus.CREATED
            );
            return baseResponse.buildResponseEntity();
        } catch (AssetCreationException e) {
            BaseResponse<AssetResponseDto> baseResponse = new BaseResponse<>(
                false,
                null,
                e.getMessage(),
                HttpStatus.BAD_REQUEST
            );
            return baseResponse.buildResponseEntity();
        } catch (Exception e) {
            BaseResponse<AssetResponseDto> baseResponse = new BaseResponse<>(
                false,
                null,
                "Ocurrió un error inesperado en el servidor.",
                HttpStatus.INTERNAL_SERVER_ERROR
            );
            return baseResponse.buildResponseEntity();
        }
    }
}