package com.icheha.aprendia_api.users.student.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.users.student.data.dtos.CreateParienteDto;
import com.icheha.aprendia_api.users.student.data.dtos.ParienteResponseDto;
import com.icheha.aprendia_api.users.student.data.dtos.UpdateParienteDto;
import com.icheha.aprendia_api.users.student.services.IParienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/parientes")
@Tag(name = "2.07. Parientes", description = "API para gestión de parientes")
public class ParienteController {
    
    private final IParienteService parienteService;
    
    @Autowired
    public ParienteController(IParienteService parienteService) {
        this.parienteService = parienteService;
    }
    
    @PostMapping
    @Operation(summary = "Crear pariente", description = "Crea una nueva relación de pariente en el sistema")
    public ResponseEntity<BaseResponse<ParienteResponseDto>> create(
            @Valid @RequestBody CreateParienteDto createParienteDto) {
        ParienteResponseDto response = parienteService.create(createParienteDto);
        BaseResponse<ParienteResponseDto> baseResponse = new BaseResponse<>(
                true, response, "Pariente creado exitosamente", HttpStatus.CREATED);
        return baseResponse.buildResponseEntity();
    }
    
    @GetMapping
    @Operation(summary = "Listar parientes", description = "Obtiene todas las relaciones de parientes del sistema")
    public ResponseEntity<BaseResponse<List<ParienteResponseDto>>> findAll() {
        List<ParienteResponseDto> parientes = parienteService.findAll();
        BaseResponse<List<ParienteResponseDto>> response = new BaseResponse<>(
                true, parientes, "Parientes obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener pariente por ID", description = "Obtiene los datos de un pariente por su ID")
    public ResponseEntity<BaseResponse<ParienteResponseDto>> findById(
            @Parameter(description = "ID del pariente", required = true)
            @PathVariable Long id) {
        Optional<ParienteResponseDto> pariente = parienteService.findById(id);
        if (pariente.isPresent()) {
            BaseResponse<ParienteResponseDto> response = new BaseResponse<>(
                    true, pariente.get(), "Pariente obtenido exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } else {
            BaseResponse<ParienteResponseDto> response = new BaseResponse<>(
                    false, null, "Pariente no encontrado", HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
    
    @GetMapping("/persona/{personaId}")
    @Operation(summary = "Buscar parientes por persona", description = "Obtiene todos los parientes de una persona (estudiante)")
    public ResponseEntity<BaseResponse<List<ParienteResponseDto>>> findByPersonaId(
            @Parameter(description = "ID de la persona", required = true)
            @PathVariable Long personaId) {
        List<ParienteResponseDto> parientes = parienteService.findByPersonaId(personaId);
        BaseResponse<List<ParienteResponseDto>> response = new BaseResponse<>(
                true, parientes, "Parientes obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/pariente/{parienteId}")
    @Operation(summary = "Buscar por ID de pariente", description = "Obtiene todas las relaciones donde una persona es pariente")
    public ResponseEntity<BaseResponse<List<ParienteResponseDto>>> findByParienteId(
            @Parameter(description = "ID del pariente", required = true)
            @PathVariable Long parienteId) {
        List<ParienteResponseDto> parientes = parienteService.findByParienteId(parienteId);
        BaseResponse<List<ParienteResponseDto>> response = new BaseResponse<>(
                true, parientes, "Parientes obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/persona/{personaId}/rol/{rolNombre}")
    @Operation(summary = "Buscar parientes por persona y rol", description = "Obtiene los parientes de una persona filtrados por rol")
    public ResponseEntity<BaseResponse<List<ParienteResponseDto>>> findByPersonaIdAndRolNombre(
            @Parameter(description = "ID de la persona", required = true)
            @PathVariable Long personaId,
            @Parameter(description = "Nombre del rol", required = true)
            @PathVariable String rolNombre) {
        List<ParienteResponseDto> parientes = parienteService.findByPersonaIdAndRolNombre(personaId, rolNombre);
        BaseResponse<List<ParienteResponseDto>> response = new BaseResponse<>(
                true, parientes, "Parientes obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar pariente", description = "Actualiza los datos de un pariente existente")
    public ResponseEntity<BaseResponse<ParienteResponseDto>> update(
            @Parameter(description = "ID del pariente", required = true)
            @PathVariable Long id,
            @Valid @RequestBody UpdateParienteDto updateParienteDto) {
        ParienteResponseDto updated = parienteService.update(id, updateParienteDto);
        BaseResponse<ParienteResponseDto> response = new BaseResponse<>(
                true, updated, "Pariente actualizado exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar pariente", description = "Elimina una relación de pariente del sistema")
    public ResponseEntity<BaseResponse<Void>> delete(
            @Parameter(description = "ID del pariente", required = true)
            @PathVariable Long id) {
        parienteService.delete(id);
        BaseResponse<Void> response = new BaseResponse<>(
                true, null, "Pariente eliminado exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
}

