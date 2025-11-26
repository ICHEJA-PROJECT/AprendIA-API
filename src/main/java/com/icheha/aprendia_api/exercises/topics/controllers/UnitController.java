package com.icheha.aprendia_api.exercises.topics.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.exercises.topics.data.dtos.request.CreateUnitDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.request.UpdateUnitDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.UnitResponseDto;
import com.icheha.aprendia_api.exercises.topics.services.IUnitService;
import com.icheha.aprendia_api.users.person.services.IImageUploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/units")
@Tag(name = "4.16. Units", description = "API para gestión de unidades")
public class UnitController {

    private final IUnitService unitService;
    private final IImageUploadService imageUploadService;

    @Autowired
    public UnitController(IUnitService unitService, IImageUploadService imageUploadService) {
        this.unitService = unitService;
        this.imageUploadService = imageUploadService;
    }

    @PostMapping
    @Operation(summary = "Crear unidad", description = "Crear nueva unidad asociada a un cuadernillo")
    @ApiResponse(responseCode = "201", description = "Unidad creada exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    @ApiResponse(responseCode = "404", description = "Cuadernillo no encontrado")
    public ResponseEntity<BaseResponse<UnitResponseDto>> createUnit(
            @Valid @RequestBody CreateUnitDto createUnitDto) {
        try {
            UnitResponseDto unit = unitService.createUnit(createUnitDto);
            BaseResponse<UnitResponseDto> response = new BaseResponse<>(
                    true, unit, "Unidad creada exitosamente", HttpStatus.CREATED);
            return response.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<UnitResponseDto> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }

    @GetMapping
    @Operation(summary = "Obtener todas las unidades", description = "Obtener todas las unidades")
    public ResponseEntity<BaseResponse<List<UnitResponseDto>>> getAllUnits() {
        List<UnitResponseDto> units = unitService.getAllUnits();
        BaseResponse<List<UnitResponseDto>> response = new BaseResponse<>(
                true, units, "Unidades obtenidas exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener unidad por ID", description = "Obtener una unidad específica por su ID")
    public ResponseEntity<BaseResponse<UnitResponseDto>> findById(
            @Parameter(description = "ID de la unidad") @PathVariable Long id) {
        return unitService.findById(id)
                .map(unit -> {
                    BaseResponse<UnitResponseDto> response = new BaseResponse<>(
                            true, unit, "Unidad obtenida exitosamente", HttpStatus.OK);
                    return response.buildResponseEntity();
                })
                .orElseGet(() -> {
                    BaseResponse<UnitResponseDto> response = new BaseResponse<>(
                            false, null, "Unidad no encontrada", HttpStatus.NOT_FOUND);
                    return response.buildResponseEntity();
                });
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar unidad", description = "Actualizar una unidad existente")
    @ApiResponse(responseCode = "200", description = "Unidad actualizada exitosamente")
    @ApiResponse(responseCode = "404", description = "Unidad no encontrada")
    public ResponseEntity<BaseResponse<UnitResponseDto>> update(
            @Parameter(description = "ID de la unidad") @PathVariable Long id,
            @Valid @RequestBody UpdateUnitDto updateUnitDto) {
        try {
            UnitResponseDto updated = unitService.update(id, updateUnitDto);
            BaseResponse<UnitResponseDto> response = new BaseResponse<>(
                    true, updated, "Unidad actualizada exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<UnitResponseDto> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar unidad", description = "Eliminar una unidad del sistema")
    @ApiResponse(responseCode = "200", description = "Unidad eliminada exitosamente")
    @ApiResponse(responseCode = "404", description = "Unidad no encontrada")
    public ResponseEntity<BaseResponse<Void>> delete(
            @Parameter(description = "ID de la unidad") @PathVariable Long id) {
        try {
            unitService.delete(id);
            BaseResponse<Void> response = new BaseResponse<>(
                    true, null, "Unidad eliminada exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<Void> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
    
    @GetMapping("/cuadernillo/{cuadernilloId}")
    @Operation(summary = "Obtener unidades por cuadernillo", description = "Obtiene todas las unidades asociadas a un cuadernillo específico")
    @ApiResponse(responseCode = "200", description = "Unidades obtenidas exitosamente")
    public ResponseEntity<BaseResponse<List<UnitResponseDto>>> getUnitsByCuadernillo(
            @Parameter(description = "ID del cuadernillo", required = true)
            @PathVariable Long cuadernilloId) {
        List<UnitResponseDto> units = unitService.getUnitsByCuadernillo(cuadernilloId);
        BaseResponse<List<UnitResponseDto>> response = new BaseResponse<>(
                true, units, "Unidades obtenidas exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @PostMapping(value = "/{id}/upload-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Subir imagen de unidad", description = "Sube una imagen para una unidad usando Cloudinary")
    @ApiResponse(responseCode = "200", description = "Imagen subida exitosamente")
    @ApiResponse(responseCode = "404", description = "Unidad no encontrada")
    public ResponseEntity<BaseResponse<String>> uploadImage(
            @Parameter(description = "ID de la unidad") @PathVariable Long id,
            @Parameter(description = "Archivo de imagen") @RequestPart("file") MultipartFile file) {
        try {
            String originalFilename = file.getOriginalFilename();
            String extension = (originalFilename != null && originalFilename.contains(".")) 
                    ? originalFilename.substring(originalFilename.lastIndexOf(".")) 
                    : ".jpg";
            String fileName = "unit-" + id + "-" + 
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss")) + extension;
            String imageUrl = imageUploadService.uploadImage(file.getBytes(), fileName, "units-images");
            
            // Actualizar la unidad con la URL de la imagen
            UpdateUnitDto updateDto = new UpdateUnitDto();
            updateDto.setUrlImagen(imageUrl);
            unitService.update(id, updateDto);
            
            BaseResponse<String> response = new BaseResponse<>(
                    true, imageUrl, "Imagen subida exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (Exception e) {
            BaseResponse<String> response = new BaseResponse<>(
                    false, null, "Error al subir imagen: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            return response.buildResponseEntity();
        }
    }
}