package com.icheha.aprendia_api.users.role.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.users.role.data.dtos.CreateRoleDto;
import com.icheha.aprendia_api.users.role.data.dtos.RoleResponseDto;
import com.icheha.aprendia_api.users.role.data.dtos.UpdateRoleDto;
import com.icheha.aprendia_api.users.role.services.IRoleService;
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
@RequestMapping("/roles")
@Tag(name = "2.02. Roles", description = "API para gestión de roles")
public class RoleController {
    
    private final IRoleService roleService;
    
    @Autowired
    public RoleController(IRoleService roleService) {
        this.roleService = roleService;
    }
    
    @PostMapping
    @Operation(summary = "Crear rol", description = "Crea un nuevo rol en el sistema")
    public ResponseEntity<BaseResponse<RoleResponseDto>> create(@Valid @RequestBody CreateRoleDto createRoleDto) {
        RoleResponseDto response = roleService.create(createRoleDto);
        BaseResponse<RoleResponseDto> baseResponse = new BaseResponse<>(
                true, response, "Rol creado exitosamente", HttpStatus.CREATED);
        return baseResponse.buildResponseEntity();
    }
    
    @GetMapping
    @Operation(summary = "Listar roles", description = "Obtiene todos los roles del sistema")
    public ResponseEntity<BaseResponse<List<RoleResponseDto>>> findAll() {
        List<RoleResponseDto> roles = roleService.findAll();
        BaseResponse<List<RoleResponseDto>> response = new BaseResponse<>(
                true, roles, "Roles obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener rol por ID", description = "Obtiene un rol por su ID")
    public ResponseEntity<BaseResponse<RoleResponseDto>> findById(
            @Parameter(description = "ID del rol", required = true) @PathVariable Long id) {
        Optional<RoleResponseDto> role = roleService.findById(id);
        if (role.isPresent()) {
            BaseResponse<RoleResponseDto> response = new BaseResponse<>(
                    true, role.get(), "Rol obtenido exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } else {
            BaseResponse<RoleResponseDto> response = new BaseResponse<>(
                    false, null, "Rol no encontrado", HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar rol", description = "Actualiza un rol existente")
    public ResponseEntity<BaseResponse<RoleResponseDto>> update(
            @Parameter(description = "ID del rol", required = true) @PathVariable Long id,
            @Valid @RequestBody UpdateRoleDto updateRoleDto) {
        RoleResponseDto role = roleService.update(id, updateRoleDto);
        BaseResponse<RoleResponseDto> response = new BaseResponse<>(
                true, role, "Rol actualizado exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar rol", description = "Elimina un rol del sistema")
    public ResponseEntity<BaseResponse<Void>> deleteById(
            @Parameter(description = "ID del rol", required = true) @PathVariable Long id) {
        roleService.deleteById(id);
        BaseResponse<Void> response = new BaseResponse<>(
                true, null, "Rol eliminado exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
}

