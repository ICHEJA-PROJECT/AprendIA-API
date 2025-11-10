package com.icheha.aprendia_api.assets.tags.controllers;

import com.icheha.aprendia_api.assets.tags.data.dtos.request.CreateTagDto;
import com.icheha.aprendia_api.assets.tags.data.dtos.request.UpdateTagDto;
import com.icheha.aprendia_api.assets.tags.data.dtos.response.TagResponseDto;
import com.icheha.aprendia_api.assets.tags.services.ITagService;
import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
@Tag(name = "6.2. Tags", description = "API para gestión de tags de activos")
public class TagController {
    private final ITagService tagService;

    @Autowired
    public TagController(ITagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping
    @Operation(summary = "Crear una nueva tag", description = "Crea una nueva tag para los activos")
    public ResponseEntity<BaseResponse<TagResponseDto>> createTag(
            @RequestBody CreateTagDto createTagDto) {
        TagResponseDto tag = tagService.createTag(createTagDto);
        BaseResponse<TagResponseDto> response = new BaseResponse<>(
                true, tag, "Tag creada exitosamente", HttpStatus.CREATED);
        return response.buildResponseEntity();
    }

    @GetMapping
    @Operation(summary = "Obtener todas las tags", description = "Retorna una lista de todas las tags registradas")
    public ResponseEntity<BaseResponse<List<TagResponseDto>>> getAllTags() {
        List<TagResponseDto> tags = tagService.getAllTags();
        BaseResponse<List<TagResponseDto>> response = new BaseResponse<>(
                true, tags, "Tags obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener tag por ID", description = "Obtiene una tag específica por su ID")
    public ResponseEntity<BaseResponse<TagResponseDto>> findById(
            @Parameter(description = "ID de la tag") @PathVariable Long id) {
        return tagService.findById(id)
                .map(tag -> {
                    BaseResponse<TagResponseDto> response = new BaseResponse<>(
                            true, tag, "Tag obtenida exitosamente", HttpStatus.OK);
                    return response.buildResponseEntity();
                })
                .orElseGet(() -> {
                    BaseResponse<TagResponseDto> response = new BaseResponse<>(
                            false, null, "Tag no encontrada", HttpStatus.NOT_FOUND);
                    return response.buildResponseEntity();
                });
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar tag", description = "Actualiza una tag existente")
    public ResponseEntity<BaseResponse<TagResponseDto>> update(
            @Parameter(description = "ID de la tag") @PathVariable Long id,
            @RequestBody UpdateTagDto updateTagDto) {
        TagResponseDto updated = tagService.update(id, updateTagDto);
        BaseResponse<TagResponseDto> response = new BaseResponse<>(
                true, updated, "Tag actualizada exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar tag", description = "Elimina una tag del sistema")
    public ResponseEntity<BaseResponse<Void>> delete(
            @Parameter(description = "ID de la tag") @PathVariable Long id) {
        tagService.delete(id);
        BaseResponse<Void> response = new BaseResponse<>(
                true, null, "Tag eliminada exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
}
