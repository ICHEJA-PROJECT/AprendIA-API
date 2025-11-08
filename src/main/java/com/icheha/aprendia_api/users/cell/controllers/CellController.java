package com.icheha.aprendia_api.users.cell.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.users.cell.data.dtos.CellResponseDto;
import com.icheha.aprendia_api.users.cell.data.dtos.CreateCellDto;
import com.icheha.aprendia_api.users.cell.services.ICellService;
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
@RequestMapping("/cells")
@Tag(name = "2.05. Cells", description = "API para gestión de células educativas")
public class CellController {
    
    private final ICellService cellService;
    
    @Autowired
    public CellController(ICellService cellService) {
        this.cellService = cellService;
    }
    
    @PostMapping
    @Operation(summary = "Crear célula", description = "Crea una nueva célula educativa")
    public ResponseEntity<BaseResponse<CellResponseDto>> create(@Valid @RequestBody CreateCellDto createCellDto) {
        CellResponseDto response = cellService.create(createCellDto);
        BaseResponse<CellResponseDto> baseResponse = new BaseResponse<>(
                true, response, "Célula creada exitosamente", HttpStatus.CREATED);
        return baseResponse.buildResponseEntity();
    }
    
    @GetMapping
    @Operation(summary = "Listar células", description = "Obtiene todas las células educativas")
    public ResponseEntity<BaseResponse<List<CellResponseDto>>> findAll() {
        List<CellResponseDto> cells = cellService.findAll();
        BaseResponse<List<CellResponseDto>> response = new BaseResponse<>(
                true, cells, "Células obtenidas exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/institution/{institutionId}")
    @Operation(summary = "Buscar células por institución", description = "Obtiene todas las células de una institución")
    public ResponseEntity<BaseResponse<List<CellResponseDto>>> findByInstitution(
            @Parameter(description = "ID de la institución", required = true) @PathVariable Long institutionId) {
        List<CellResponseDto> cells = cellService.findByInstitution(institutionId);
        BaseResponse<List<CellResponseDto>> response = new BaseResponse<>(
                true, cells, "Células obtenidas exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/coordinator/{coordinatorId}")
    @Operation(summary = "Buscar células por coordinador", description = "Obtiene todas las células de un coordinador")
    public ResponseEntity<BaseResponse<List<CellResponseDto>>> findByCoordinator(
            @Parameter(description = "ID del coordinador", required = true) @PathVariable Long coordinatorId) {
        List<CellResponseDto> cells = cellService.findByCoordinator(coordinatorId);
        BaseResponse<List<CellResponseDto>> response = new BaseResponse<>(
                true, cells, "Células obtenidas exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener célula por ID", description = "Obtiene una célula por su ID")
    public ResponseEntity<BaseResponse<CellResponseDto>> findById(
            @Parameter(description = "ID de la célula", required = true) @PathVariable Long id) {
        Optional<CellResponseDto> cell = cellService.findById(id);
        if (cell.isPresent()) {
            BaseResponse<CellResponseDto> response = new BaseResponse<>(
                    true, cell.get(), "Célula obtenida exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } else {
            BaseResponse<CellResponseDto> response = new BaseResponse<>(
                    false, null, "Célula no encontrada", HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
}

