package com.icheha.aprendia_api.users.cell.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.users.cell.data.dtos.CreateInstitutionDto;
import com.icheha.aprendia_api.users.cell.data.dtos.InstitutionResponseDto;
import com.icheha.aprendia_api.users.cell.data.dtos.UpdateInstitutionDto;
import com.icheha.aprendia_api.users.cell.services.IInstitutionService;
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
@RequestMapping("/institutions")
@Tag(name = "2.06. Institutions", description = "API para gestión de instituciones educativas")
public class InstitutionController {
    
    private final IInstitutionService institutionService;
    
    @Autowired
    public InstitutionController(IInstitutionService institutionService) {
        this.institutionService = institutionService;
    }
    
    @PostMapping
    @Operation(summary = "Crear institución", description = "Crea una nueva institución educativa")
    public ResponseEntity<BaseResponse<InstitutionResponseDto>> create(@Valid @RequestBody CreateInstitutionDto createInstitutionDto) {
        InstitutionResponseDto response = institutionService.create(createInstitutionDto);
        BaseResponse<InstitutionResponseDto> baseResponse = new BaseResponse<>(
                true, response, "Institución creada exitosamente", HttpStatus.CREATED);
        return baseResponse.buildResponseEntity();
    }
    
    @GetMapping
    @Operation(summary = "Listar instituciones", description = "Obtiene todas las instituciones educativas")
    public ResponseEntity<BaseResponse<List<InstitutionResponseDto>>> findAll() {
        List<InstitutionResponseDto> institutions = institutionService.findAll();
        BaseResponse<List<InstitutionResponseDto>> response = new BaseResponse<>(
                true, institutions, "Instituciones obtenidas exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener institución por ID", description = "Obtiene una institución por su ID")
    public ResponseEntity<BaseResponse<InstitutionResponseDto>> findById(
            @Parameter(description = "ID de la institución", required = true) @PathVariable Long id) {
        Optional<InstitutionResponseDto> institution = institutionService.findById(id);
        if (institution.isPresent()) {
            BaseResponse<InstitutionResponseDto> response = new BaseResponse<>(
                    true, institution.get(), "Institución obtenida exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } else {
            BaseResponse<InstitutionResponseDto> response = new BaseResponse<>(
                    false, null, "Institución no encontrada", HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar institución", description = "Actualiza una institución existente")
    public ResponseEntity<BaseResponse<InstitutionResponseDto>> update(
            @Parameter(description = "ID de la institución", required = true) @PathVariable Long id,
            @Valid @RequestBody UpdateInstitutionDto updateInstitutionDto) {
        InstitutionResponseDto updated = institutionService.update(id, updateInstitutionDto);
        BaseResponse<InstitutionResponseDto> response = new BaseResponse<>(
                true, updated, "Institución actualizada exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar institución", description = "Elimina una institución del sistema")
    public ResponseEntity<BaseResponse<Void>> delete(
            @Parameter(description = "ID de la institución", required = true) @PathVariable Long id) {
        institutionService.delete(id);
        BaseResponse<Void> response = new BaseResponse<>(
                true, null, "Institución eliminada exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
}

