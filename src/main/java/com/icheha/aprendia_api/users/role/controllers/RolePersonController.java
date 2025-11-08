package com.icheha.aprendia_api.users.role.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.users.role.data.dtos.CreateManyRolePersonDto;
import com.icheha.aprendia_api.users.role.data.dtos.CreateRolePersonDto;
import com.icheha.aprendia_api.users.role.data.dtos.RolePersonResponseDto;
import com.icheha.aprendia_api.users.role.services.IRolePersonService;
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
@RequestMapping("/role-persons")
@Tag(name = "2.03. Role Persons", description = "API para gestión de relaciones persona-rol")
public class RolePersonController {
    
    private final IRolePersonService rolePersonService;
    
    @Autowired
    public RolePersonController(IRolePersonService rolePersonService) {
        this.rolePersonService = rolePersonService;
    }
    
    @PostMapping
    @Operation(summary = "Asignar rol a persona", description = "Asigna un rol a una persona")
    public ResponseEntity<BaseResponse<RolePersonResponseDto>> create(@Valid @RequestBody CreateRolePersonDto createRolePersonDto) {
        RolePersonResponseDto response = rolePersonService.create(createRolePersonDto);
        BaseResponse<RolePersonResponseDto> baseResponse = new BaseResponse<>(
                true, response, "Rol asignado exitosamente", HttpStatus.CREATED);
        return baseResponse.buildResponseEntity();
    }
    
    @PostMapping("/many")
    @Operation(summary = "Asignar múltiples roles a persona", description = "Asigna múltiples roles a una persona")
    public ResponseEntity<BaseResponse<List<RolePersonResponseDto>>> createMany(@Valid @RequestBody CreateManyRolePersonDto createManyRolePersonDto) {
        List<RolePersonResponseDto> response = rolePersonService.createMany(createManyRolePersonDto);
        BaseResponse<List<RolePersonResponseDto>> baseResponse = new BaseResponse<>(
                true, response, "Roles asignados exitosamente", HttpStatus.CREATED);
        return baseResponse.buildResponseEntity();
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener relación persona-rol por ID", description = "Obtiene una relación persona-rol por su ID")
    public ResponseEntity<BaseResponse<RolePersonResponseDto>> findById(
            @Parameter(description = "ID de la relación", required = true) @PathVariable Long id) {
        Optional<RolePersonResponseDto> rolePerson = rolePersonService.findById(id);
        if (rolePerson.isPresent()) {
            BaseResponse<RolePersonResponseDto> response = new BaseResponse<>(
                    true, rolePerson.get(), "Relación obtenida exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } else {
            BaseResponse<RolePersonResponseDto> response = new BaseResponse<>(
                    false, null, "Relación no encontrada", HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
    
    @GetMapping("/person/{personId}")
    @Operation(summary = "Obtener roles de una persona", description = "Obtiene todos los roles asignados a una persona")
    public ResponseEntity<BaseResponse<List<RolePersonResponseDto>>> findByPersonId(
            @Parameter(description = "ID de la persona", required = true) @PathVariable Long personId) {
        List<RolePersonResponseDto> roles = rolePersonService.findByPersonId(personId);
        BaseResponse<List<RolePersonResponseDto>> response = new BaseResponse<>(
                true, roles, "Roles obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
}

