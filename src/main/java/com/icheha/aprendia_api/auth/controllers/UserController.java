package com.icheha.aprendia_api.auth.controllers;

import com.icheha.aprendia_api.auth.data.dtos.CreateUserDto;
import com.icheha.aprendia_api.auth.data.dtos.UpdateUserDto;
import com.icheha.aprendia_api.auth.data.dtos.UserResponseDto;
import com.icheha.aprendia_api.auth.services.IUserService;
import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@Tag(name = "2.05. Users", description = "API para gestión de usuarios")
public class UserController {
    
    private final IUserService userService;
    
    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }
    
    @PostMapping
    @Operation(
        summary = "Crear usuario",
        description = "Crea un nuevo usuario en el sistema"
    )
    @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<BaseResponse<UserResponseDto>> create(
            @Valid @RequestBody CreateUserDto createUserDto) {
        UserResponseDto response = userService.create(createUserDto);
        BaseResponse<UserResponseDto> baseResponse = new BaseResponse<>(
                true, response, "Usuario creado exitosamente", HttpStatus.CREATED);
        return baseResponse.buildResponseEntity();
    }
    
    @GetMapping("/{id}")
    @Operation(
        summary = "Obtener usuario por ID",
        description = "Obtiene los datos de un usuario por su ID"
    )
    @ApiResponse(responseCode = "200", description = "Usuario encontrado")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    public ResponseEntity<BaseResponse<UserResponseDto>> findById(
            @Parameter(description = "ID del usuario", required = true)
            @PathVariable Long id) {
        Optional<UserResponseDto> user = userService.findById(id);
        if (user.isPresent()) {
            BaseResponse<UserResponseDto> response = new BaseResponse<>(
                    true, user.get(), "Usuario obtenido exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } else {
            BaseResponse<UserResponseDto> response = new BaseResponse<>(
                    false, null, "Usuario no encontrado", HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
    
    @GetMapping("/persona/{idPersona}")
    @Operation(
        summary = "Obtener usuario por ID de persona",
        description = "Obtiene los datos de un usuario por el ID de la persona asociada"
    )
    @ApiResponse(responseCode = "200", description = "Usuario encontrado")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    public ResponseEntity<BaseResponse<UserResponseDto>> findByIdPersona(
            @Parameter(description = "ID de la persona", required = true)
            @PathVariable Long idPersona) {
        Optional<UserResponseDto> user = userService.findByIdPersona(idPersona);
        if (user.isPresent()) {
            BaseResponse<UserResponseDto> response = new BaseResponse<>(
                    true, user.get(), "Usuario obtenido exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } else {
            BaseResponse<UserResponseDto> response = new BaseResponse<>(
                    false, null, "Usuario no encontrado", HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
    
    @GetMapping("/username/{username}")
    @Operation(
        summary = "Obtener usuario por username",
        description = "Obtiene los datos de un usuario por su username"
    )
    @ApiResponse(responseCode = "200", description = "Usuario encontrado")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    public ResponseEntity<BaseResponse<UserResponseDto>> findByUsername(
            @Parameter(description = "Username del usuario", required = true)
            @PathVariable String username) {
        Optional<UserResponseDto> user = userService.findByUsername(username);
        if (user.isPresent()) {
            BaseResponse<UserResponseDto> response = new BaseResponse<>(
                    true, user.get(), "Usuario obtenido exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } else {
            BaseResponse<UserResponseDto> response = new BaseResponse<>(
                    false, null, "Usuario no encontrado", HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
    
    @GetMapping
    @Operation(
        summary = "Obtener todos los usuarios",
        description = "Obtiene una lista con todos los usuarios del sistema"
    )
    @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida exitosamente")
    public ResponseEntity<BaseResponse<List<UserResponseDto>>> findAll() {
        List<UserResponseDto> users = userService.findAll();
        BaseResponse<List<UserResponseDto>> response = new BaseResponse<>(
                true, users, "Usuarios obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @PutMapping("/{id}")
    @Operation(
        summary = "Actualizar usuario",
        description = "Actualiza los datos de un usuario existente"
    )
    @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<BaseResponse<UserResponseDto>> update(
            @Parameter(description = "ID del usuario", required = true)
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserDto updateUserDto) {
        try {
            UserResponseDto response = userService.update(id, updateUserDto);
            BaseResponse<UserResponseDto> baseResponse = new BaseResponse<>(
                    true, response, "Usuario actualizado exitosamente", HttpStatus.OK);
            return baseResponse.buildResponseEntity();
        } catch (IllegalArgumentException e) {
            BaseResponse<UserResponseDto> baseResponse = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return baseResponse.buildResponseEntity();
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar usuario",
        description = "Elimina un usuario del sistema"
    )
    @ApiResponse(responseCode = "200", description = "Usuario eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    public ResponseEntity<BaseResponse<Void>> delete(
            @Parameter(description = "ID del usuario", required = true)
            @PathVariable Long id) {
        try {
            userService.delete(id);
            BaseResponse<Void> response = new BaseResponse<>(
                    true, null, "Usuario eliminado exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (IllegalArgumentException e) {
            BaseResponse<Void> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
}

