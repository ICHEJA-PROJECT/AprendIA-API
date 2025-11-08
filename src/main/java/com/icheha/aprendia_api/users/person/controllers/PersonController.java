package com.icheha.aprendia_api.users.person.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.users.person.data.dtos.CreatePersonDto;
import com.icheha.aprendia_api.users.person.data.dtos.PersonResponseDto;
import com.icheha.aprendia_api.users.person.data.dtos.UpdatePersonDto;
import com.icheha.aprendia_api.users.person.services.IPersonService;
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
@RequestMapping("/persons")
@Tag(name = "2.01. Persons", description = "API para gestión de personas")
public class PersonController {
    
    private final IPersonService personService;
    
    @Autowired
    public PersonController(IPersonService personService) {
        this.personService = personService;
    }
    
    @PostMapping
    @Operation(summary = "Crear persona", description = "Crea una nueva persona en el sistema")
    public ResponseEntity<BaseResponse<PersonResponseDto>> create(
            @Valid @RequestBody CreatePersonDto createPersonDto) {
        PersonResponseDto person = personService.create(createPersonDto);
        BaseResponse<PersonResponseDto> response = new BaseResponse<>(
                true, person, "Persona creada exitosamente", HttpStatus.CREATED);
        return response.buildResponseEntity();
    }
    
    @GetMapping
    @Operation(summary = "Listar personas", description = "Obtiene todas las personas del sistema")
    public ResponseEntity<BaseResponse<List<PersonResponseDto>>> findAll() {
        List<PersonResponseDto> persons = personService.findAll();
        BaseResponse<List<PersonResponseDto>> response = new BaseResponse<>(
                true, persons, "Personas obtenidas exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener persona por ID", description = "Obtiene los datos de una persona por su ID")
    public ResponseEntity<BaseResponse<PersonResponseDto>> findById(
            @Parameter(description = "ID de la persona", required = true)
            @PathVariable Long id) {
        Optional<PersonResponseDto> person = personService.findById(id);
        if (person.isPresent()) {
            BaseResponse<PersonResponseDto> response = new BaseResponse<>(
                    true, person.get(), "Persona obtenida exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } else {
            BaseResponse<PersonResponseDto> response = new BaseResponse<>(
                    false, null, "Persona no encontrada", HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar persona", description = "Actualiza los datos de una persona existente")
    public ResponseEntity<BaseResponse<PersonResponseDto>> update(
            @Parameter(description = "ID de la persona", required = true)
            @PathVariable Long id,
            @Valid @RequestBody UpdatePersonDto updatePersonDto) {
        PersonResponseDto person = personService.update(id, updatePersonDto);
        BaseResponse<PersonResponseDto> response = new BaseResponse<>(
                true, person, "Persona actualizada exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar persona", description = "Elimina una persona del sistema")
    public ResponseEntity<BaseResponse<Void>> deleteById(
            @Parameter(description = "ID de la persona", required = true)
            @PathVariable Long id) {
        personService.deleteById(id);
        BaseResponse<Void> response = new BaseResponse<>(
                true, null, "Persona eliminada exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
}

