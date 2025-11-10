package com.icheha.aprendia_api.users.student.controllers;

import com.icheha.aprendia_api.core.dtos.response.BaseResponse;
import com.icheha.aprendia_api.users.student.data.dtos.CreateStudentDto;
import com.icheha.aprendia_api.users.student.data.dtos.RegisterStudentResponseDto;
import com.icheha.aprendia_api.users.student.data.dtos.StudentResponseDto;
import com.icheha.aprendia_api.users.student.services.IStudentService;
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
@RequestMapping("/students")
@Tag(name = "2.04. Students", description = "API para gestión de estudiantes")
public class StudentController {
    
    private final IStudentService studentService;
    
    @Autowired
    public StudentController(IStudentService studentService) {
        this.studentService = studentService;
    }
    
    @PostMapping
    @Operation(summary = "Crear estudiante", description = "Crea un nuevo estudiante en el sistema y genera su código QR")
    public ResponseEntity<BaseResponse<RegisterStudentResponseDto>> create(
            @Valid @RequestBody CreateStudentDto createStudentDto) {
        RegisterStudentResponseDto response = studentService.create(createStudentDto);
        BaseResponse<RegisterStudentResponseDto> baseResponse = new BaseResponse<>(
                true, response, "Estudiante creado exitosamente", HttpStatus.CREATED);
        return baseResponse.buildResponseEntity();
    }
    
    @GetMapping("/teacher/{teacherId}")
    @Operation(summary = "Buscar estudiantes por educador", description = "Obtiene todos los estudiantes de un educador")
    public ResponseEntity<BaseResponse<List<StudentResponseDto>>> findByTeacher(
            @Parameter(description = "ID del educador", required = true)
            @PathVariable Long teacherId) {
        List<StudentResponseDto> students = studentService.findByTeacher(teacherId);
        BaseResponse<List<StudentResponseDto>> response = new BaseResponse<>(
                true, students, "Estudiantes obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/curp/{curp}")
    @Operation(summary = "Buscar estudiantes por CURP", description = "Busca estudiantes por CURP")
    public ResponseEntity<BaseResponse<List<StudentResponseDto>>> findByCurp(
            @Parameter(description = "CURP a buscar", required = true)
            @PathVariable String curp) {
        List<StudentResponseDto> students = studentService.findByCurp(curp);
        BaseResponse<List<StudentResponseDto>> response = new BaseResponse<>(
                true, students, "Estudiantes obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/name")
    @Operation(summary = "Buscar estudiantes por nombre", description = "Busca estudiantes por nombre y apellido paterno")
    public ResponseEntity<BaseResponse<List<StudentResponseDto>>> findByName(
            @Parameter(description = "Nombre completo (nombre apellido)", required = true)
            @RequestParam String name) {
        List<StudentResponseDto> students = studentService.findByName(name);
        BaseResponse<List<StudentResponseDto>> response = new BaseResponse<>(
                true, students, "Estudiantes obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener estudiante por ID", description = "Obtiene los datos de un estudiante por su ID")
    public ResponseEntity<BaseResponse<StudentResponseDto>> findById(
            @Parameter(description = "ID del estudiante", required = true)
            @PathVariable Long id) {
        Optional<StudentResponseDto> student = studentService.findById(id);
        if (student.isPresent()) {
            BaseResponse<StudentResponseDto> response = new BaseResponse<>(
                    true, student.get(), "Estudiante obtenido exitosamente", HttpStatus.OK);
            return response.buildResponseEntity();
        } else {
            BaseResponse<StudentResponseDto> response = new BaseResponse<>(
                    false, null, "Estudiante no encontrado", HttpStatus.NOT_FOUND);
            return response.buildResponseEntity();
        }
    }
    
    @GetMapping("/names/unique")
    @Operation(summary = "Obtener nombres únicos", description = "Obtiene una lista de nombres únicos de estudiantes")
    public ResponseEntity<BaseResponse<List<String>>> findUniqueNames() {
        List<String> names = studentService.findUniqueNames();
        BaseResponse<List<String>> response = new BaseResponse<>(
                true, names, "Nombres obtenidos exitosamente", HttpStatus.OK);
        return response.buildResponseEntity();
    }
}

