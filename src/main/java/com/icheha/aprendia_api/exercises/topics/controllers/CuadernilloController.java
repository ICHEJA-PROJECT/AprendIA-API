package com.icheha.aprendia_api.exercises.topics.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.exercises.topics.data.dtos.request.CreateCuadernilloDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.request.UpdateCuadernilloDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.CuadernilloResponseDto;
import com.icheha.aprendia_api.exercises.topics.services.ICuadernilloService;
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
@RequestMapping("/cuadernillos")
@Tag(name = "4.03. Cuadernillos", description = "API para gestión de cuadernillos")
public class CuadernilloController {
    
    private final ICuadernilloService cuadernilloService;
    
    @Autowired
    public CuadernilloController(ICuadernilloService cuadernilloService) {
        this.cuadernilloService = cuadernilloService;
    }
    
    @PostMapping
    @Operation(
        summary = "Crear cuadernillo",
        description = "Crea un nuevo cuadernillo asociado a una ruta de aprendizaje"
    )
    @ApiResponse(responseCode = "201", description = "Cuadernillo creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    @ApiResponse(responseCode = "404", description = "Ruta de aprendizaje no encontrada")
    public ResponseEntity<BaseResponse<CuadernilloResponseDto>> create(
            @Valid @RequestBody CreateCuadernilloDto createCuadernilloDto) {
        try {
            CuadernilloResponseDto cuadernillo = cuadernilloService.create(createCuadernilloDto);
            BaseResponse<CuadernilloResponseDto> response = new BaseResponse<>(
                    true, cuadernillo, "Cuadernillo creado exitosamente", HttpStatus.CREATED);
            return response.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<CuadernilloResponseDto> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
    
    @GetMapping
    @Operation(
        summary = "Obtener todos los cuadernillos",
        description = "Obtiene una lista de todos los cuadernillos del sistema"
    )
    @ApiResponse(responseCode = "200", description = "Cuadernillos obtenidos exitosamente")
    public ResponseEntity<BaseResponse<List<CuadernilloResponseDto>>> findAll() {
        List<CuadernilloResponseDto> cuadernillos = cuadernilloService.findAll();
        BaseResponse<List<CuadernilloResponseDto>> response = new BaseResponse<>(
                true, cuadernillos, "Cuadernillos obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/{id}")
    @Operation(
        summary = "Obtener cuadernillo por ID",
        description = "Obtiene un cuadernillo específico por su ID"
    )
    @ApiResponse(responseCode = "200", description = "Cuadernillo obtenido exitosamente")
    @ApiResponse(responseCode = "404", description = "Cuadernillo no encontrado")
    public ResponseEntity<BaseResponse<CuadernilloResponseDto>> findById(
            @Parameter(description = "ID del cuadernillo", required = true)
            @PathVariable Long id) {
        Optional<CuadernilloResponseDto> cuadernillo = cuadernilloService.findById(id);
        if (cuadernillo.isPresent()) {
            BaseResponse<CuadernilloResponseDto> response = new BaseResponse<>(
                    true, cuadernillo.get(), "Cuadernillo obtenido exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } else {
            BaseResponse<CuadernilloResponseDto> response = new BaseResponse<>(
                    false, null, "Cuadernillo no encontrado", HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
    
    @GetMapping("/ruta-aprendizaje/{idRutaAprendizaje}")
    @Operation(
        summary = "Obtener cuadernillos por ruta de aprendizaje",
        description = "Obtiene todos los cuadernillos asociados a una ruta de aprendizaje específica"
    )
    @ApiResponse(responseCode = "200", description = "Cuadernillos obtenidos exitosamente")
    public ResponseEntity<BaseResponse<List<CuadernilloResponseDto>>> findByRutaAprendizaje(
            @Parameter(description = "ID de la ruta de aprendizaje", required = true)
            @PathVariable Long idRutaAprendizaje) {
        List<CuadernilloResponseDto> cuadernillos = cuadernilloService.findByRutaAprendizaje(idRutaAprendizaje);
        BaseResponse<List<CuadernilloResponseDto>> response = new BaseResponse<>(
                true, cuadernillos, "Cuadernillos obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/search")
    @Operation(
        summary = "Buscar cuadernillos por nombre",
        description = "Busca cuadernillos que contengan el nombre especificado"
    )
    @ApiResponse(responseCode = "200", description = "Cuadernillos obtenidos exitosamente")
    public ResponseEntity<BaseResponse<List<CuadernilloResponseDto>>> findByNombreContaining(
            @Parameter(description = "Nombre a buscar", required = true)
            @RequestParam String nombre) {
        List<CuadernilloResponseDto> cuadernillos = cuadernilloService.findByNombreContaining(nombre);
        BaseResponse<List<CuadernilloResponseDto>> response = new BaseResponse<>(
                true, cuadernillos, "Cuadernillos obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @PutMapping("/{id}")
    @Operation(
        summary = "Actualizar cuadernillo",
        description = "Actualiza un cuadernillo existente"
    )
    @ApiResponse(responseCode = "200", description = "Cuadernillo actualizado exitosamente")
    @ApiResponse(responseCode = "404", description = "Cuadernillo no encontrado")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<BaseResponse<CuadernilloResponseDto>> update(
            @Parameter(description = "ID del cuadernillo", required = true)
            @PathVariable Long id,
            @Valid @RequestBody UpdateCuadernilloDto updateCuadernilloDto) {
        try {
            CuadernilloResponseDto cuadernillo = cuadernilloService.update(id, updateCuadernilloDto);
            BaseResponse<CuadernilloResponseDto> response = new BaseResponse<>(
                    true, cuadernillo, "Cuadernillo actualizado exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<CuadernilloResponseDto> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar cuadernillo",
        description = "Elimina un cuadernillo del sistema"
    )
    @ApiResponse(responseCode = "200", description = "Cuadernillo eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Cuadernillo no encontrado")
    public ResponseEntity<BaseResponse<Void>> delete(
            @Parameter(description = "ID del cuadernillo", required = true)
            @PathVariable Long id) {
        try {
            cuadernilloService.delete(id);
            BaseResponse<Void> response = new BaseResponse<>(
                    true, null, "Cuadernillo eliminado exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            BaseResponse<Void> response = new BaseResponse<>(
                    false, null, e.getMessage(), HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
}

