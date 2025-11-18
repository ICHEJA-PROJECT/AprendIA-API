package com.icheha.aprendia_api.assets.assets.controllers;

import com.icheha.aprendia_api.assets.assets.data.dtos.request.CreateAssetDto;
import com.icheha.aprendia_api.assets.assets.data.dtos.response.CreateAssetResponseDto;
import com.icheha.aprendia_api.assets.assets.data.dtos.response.FindAssetDto;
import com.icheha.aprendia_api.assets.assets.domain.exceptions.AssetCreationException;
import com.icheha.aprendia_api.assets.assets.services.IAssetService;
import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/assets")
@Tag(name = "6.1. Assets", description = "Endpoints para la gestión de recursos (assets)")
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
    public ResponseEntity<BaseResponse<CreateAssetResponseDto>> createAsset(
        @RequestPart("file") MultipartFile file,
        @RequestPart("data") CreateAssetDto request
    ) {
        try {
            CreateAssetResponseDto response = assetService.createAndSaveAsset(file, request);
            BaseResponse<CreateAssetResponseDto> baseResponse = new BaseResponse<>(
                true,
                response,
                "Asset creado exitosamente",
                HttpStatus.CREATED
            );
            return baseResponse.buildResponseEntity();
        } catch (AssetCreationException e) {
            BaseResponse<CreateAssetResponseDto> baseResponse = new BaseResponse<>(
                false,
                null,
                e.getMessage(),
                HttpStatus.BAD_REQUEST
            );
            return baseResponse.buildResponseEntity();
        } catch (Exception e) {
            BaseResponse<CreateAssetResponseDto> baseResponse = new BaseResponse<>(
                false,
                null,
                "Ocurrió un error inesperado en el servidor.",
                HttpStatus.INTERNAL_SERVER_ERROR
            );
            return baseResponse.buildResponseEntity();
        }
    }

    @GetMapping
    @Operation(summary = "Obtener activos", description = "Retorna una lista de 100 activos buscados por tags")
    public ResponseEntity<BaseResponse<List<FindAssetDto>>> getAssetsByTags(@RequestParam("tagsIds") List<Long> tagsIds) {
        List<FindAssetDto> assets = assetService.findAssetByTagsIds(tagsIds);
        BaseResponse<List<FindAssetDto>> response = new BaseResponse<>(
                true, assets, "Activos obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }

    @GetMapping(path = "/")
    @Operation(summary = "Obtener activos", description = "Retorna una lista de todos activos buscados por una descripcion")
    public ResponseEntity<BaseResponse<List<FindAssetDto>>> getAssetsByDescription(@RequestParam("description") String description) {
        List<FindAssetDto> assets = assetService.findAssetByDescription(description);
        BaseResponse<List<FindAssetDto>> response = new BaseResponse<>(
                true, assets, "Activos obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener asset por ID", description = "Obtener un asset específico por su ID")
    @ApiResponse(responseCode = "200", description = "Asset obtenido exitosamente")
    @ApiResponse(responseCode = "404", description = "Asset no encontrado")
    public ResponseEntity<BaseResponse<FindAssetDto>> findById(
            @Parameter(description = "ID del asset") @PathVariable Long id) {
        return assetService.findById(id)
                .map(asset -> {
                    BaseResponse<FindAssetDto> response = new BaseResponse<>(
                            true, asset, "Asset obtenido exitosamente", HttpStatus.OK);
                    return response.buildResponseEntity();
                })
                .orElseGet(() -> {
                    BaseResponse<FindAssetDto> response = new BaseResponse<>(
                            false, null, "Asset no encontrado", HttpStatus.NOT_FOUND);
                    return response.buildResponseEntity();
                });
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar asset", description = "Actualizar un asset existente")
    @ApiResponse(responseCode = "200", description = "Asset actualizado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    @ApiResponse(responseCode = "404", description = "Asset no encontrado")
    public ResponseEntity<BaseResponse<FindAssetDto>> update(
            @Parameter(description = "ID del asset") @PathVariable Long id,
            @RequestBody com.icheha.aprendia_api.assets.assets.data.dtos.request.UpdateAssetDto updateAssetDto) {
        try {
            FindAssetDto updated = assetService.update(id, updateAssetDto);
            BaseResponse<FindAssetDto> response = new BaseResponse<>(
                    true, updated, "Asset actualizado exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<FindAssetDto> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        } catch (Exception e) {
            BaseResponse<FindAssetDto> response = new BaseResponse<>(
                    false, null, "Error al actualizar asset: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            return response.buildResponseEntity();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar asset", description = "Eliminar un asset del sistema")
    @ApiResponse(responseCode = "200", description = "Asset eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Asset no encontrado")
    public ResponseEntity<BaseResponse<Void>> delete(
            @Parameter(description = "ID del asset") @PathVariable Long id) {
        try {
            assetService.delete(id);
            BaseResponse<Void> response = new BaseResponse<>(
                    true, null, "Asset eliminado exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<Void> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        } catch (Exception e) {
            BaseResponse<Void> response = new BaseResponse<>(
                    false, null, "Error al eliminar asset: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            return response.buildResponseEntity();
        }
    }
}