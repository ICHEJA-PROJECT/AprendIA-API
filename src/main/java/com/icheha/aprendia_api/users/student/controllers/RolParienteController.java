package com.icheha.aprendia_api.users.student.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.users.student.data.dtos.CreateRolParienteDto;
import com.icheha.aprendia_api.users.student.data.dtos.RolParienteResponseDto;
import com.icheha.aprendia_api.users.student.data.dtos.UpdateRolParienteDto;
import com.icheha.aprendia_api.users.student.services.IRolParienteService;
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
@RequestMapping("/rol-parientes")
@Tag(name = "2.06. Rol Parientes", description = "API para gestión de roles de parientes")
public class RolParienteController {
    
    private final IRolParienteService rolParienteService;
    
    @Autowired
    public RolParienteController(IRolParienteService rolParienteService) {
        this.rolParienteService = rolParienteService;
    }
    
    @PostMapping
    @Operation(summary = "Crear rol de pariente", description = "Crea un nuevo rol de pariente en el sistema")
    public ResponseEntity<BaseResponse<RolParienteResponseDto>> create(
            @Valid @RequestBody CreateRolParienteDto createRolParienteDto) {
        RolParienteResponseDto response = rolParienteService.create(createRolParienteDto);
        BaseResponse<RolParienteResponseDto> baseResponse = new BaseResponse<>(
                true, response, "Rol de pariente creado exitosamente", HttpStatus.CREATED);
        return baseResponse.buildResponseEntity();
    }
    
    @GetMapping
    @Operation(summary = "Listar roles de parientes", description = "Obtiene todos los roles de parientes del sistema")
    public ResponseEntity<BaseResponse<List<RolParienteResponseDto>>> findAll() {
        List<RolParienteResponseDto> roles = rolParienteService.findAll();
        BaseResponse<List<RolParienteResponseDto>> response = new BaseResponse<>(
                true, roles, "Roles de parientes obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener rol de pariente por ID", description = "Obtiene los datos de un rol de pariente por su ID")
    public ResponseEntity<BaseResponse<RolParienteResponseDto>> findById(
            @Parameter(description = "ID del rol de pariente", required = true)
            @PathVariable Long id) {
        Optional<RolParienteResponseDto> rol = rolParienteService.findById(id);
        if (rol.isPresent()) {
            BaseResponse<RolParienteResponseDto> response = new BaseResponse<>(
                    true, rol.get(), "Rol de pariente obtenido exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } else {
            BaseResponse<RolParienteResponseDto> response = new BaseResponse<>(
                    false, null, "Rol de pariente no encontrado", HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar rol de pariente", description = "Actualiza los datos de un rol de pariente existente")
    public ResponseEntity<BaseResponse<RolParienteResponseDto>> update(
            @Parameter(description = "ID del rol de pariente", required = true)
            @PathVariable Long id,
            @Valid @RequestBody UpdateRolParienteDto updateRolParienteDto) {
        RolParienteResponseDto updated = rolParienteService.update(id, updateRolParienteDto);
        BaseResponse<RolParienteResponseDto> response = new BaseResponse<>(
                true, updated, "Rol de pariente actualizado exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar rol de pariente", description = "Elimina un rol de pariente del sistema")
    public ResponseEntity<BaseResponse<Void>> delete(
            @Parameter(description = "ID del rol de pariente", required = true)
            @PathVariable Long id) {
        rolParienteService.delete(id);
        BaseResponse<Void> response = new BaseResponse<>(
                true, null, "Rol de pariente eliminado exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
}

