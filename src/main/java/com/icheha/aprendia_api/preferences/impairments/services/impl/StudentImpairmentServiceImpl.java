package com.icheha.aprendia_api.preferences.impairments.services.impl;

import com.icheha.aprendia_api.preferences.impairments.data.dtos.request.CreateStudentImpairmentDto;
import com.icheha.aprendia_api.preferences.impairments.data.dtos.response.StudentImpairmentDetailsResponseDto;
import com.icheha.aprendia_api.preferences.impairments.data.dtos.response.StudentImpairmentResponseDto;
import com.icheha.aprendia_api.preferences.impairments.data.entities.StudentImpairment;
import com.icheha.aprendia_api.preferences.impairments.data.entities.Impairment;
import com.icheha.aprendia_api.preferences.impairments.data.mappers.StudentImpairmentMapper;
import com.icheha.aprendia_api.preferences.impairments.data.repositories.StudentImpairmentRepository;
import com.icheha.aprendia_api.preferences.impairments.data.repositories.ImpairmentRepository;
import com.icheha.aprendia_api.preferences.impairments.domain.repositories.IStudentImpairmentRepository;
import com.icheha.aprendia_api.preferences.impairments.services.IStudentImpairmentService;
import com.icheha.aprendia_api.users.student.domain.entities.Student;
import com.icheha.aprendia_api.users.student.domain.repositories.IStudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentImpairmentServiceImpl implements IStudentImpairmentService {
    
    private final IStudentImpairmentRepository studentImpairmentRepository;
    private final StudentImpairmentRepository studentImpairmentJpaRepository;
    private final IStudentRepository studentRepository;
    private final ImpairmentRepository impairmentRepository;
    private final StudentImpairmentMapper studentImpairmentMapper;
    
    public StudentImpairmentServiceImpl(IStudentImpairmentRepository studentImpairmentRepository,
                                       StudentImpairmentRepository studentImpairmentJpaRepository,
                                       IStudentRepository studentRepository,
                                       ImpairmentRepository impairmentRepository,
                                       StudentImpairmentMapper studentImpairmentMapper) {
        this.studentImpairmentRepository = studentImpairmentRepository;
        this.studentImpairmentJpaRepository = studentImpairmentJpaRepository;
        this.studentRepository = studentRepository;
        this.impairmentRepository = impairmentRepository;
        this.studentImpairmentMapper = studentImpairmentMapper;
    }
    
    @Override
    public StudentImpairmentDetailsResponseDto getStudentPreferencesWithDetails(Integer id) {
        Long personId = Long.valueOf(id);
        
        // Obtener estudiante por personId
        Optional<Student> studentOpt = 
                studentRepository.findByPersonId(personId);
        
        if (studentOpt.isEmpty()) {
            // Si no es estudiante, retornar null o respuesta vacía
            return null;
        }
        
        Student student = studentOpt.get();
        Long studentId = student.getId();
        
        // Obtener discapacidades del estudiante
        List<com.icheha.aprendia_api.preferences.impairments.domain.entities.StudentImpairment> studentImpairments = 
                studentImpairmentRepository.findByStudentIdWithImpairment(studentId);
        
        // Obtener nombre del estudiante
        String studentName = student.getPersona() != null ? 
                student.getPersona().getPrimerNombre() + " " + 
                student.getPersona().getApellidoPaterno() : "Estudiante " + studentId;
        
        // Mapear a DTO usando el mapper real
        StudentImpairmentDetailsResponseDto response = studentImpairmentMapper.toResponseDto(
                studentId, studentImpairments, studentName);
        
        return response;
    }
    
    @Override
    @Transactional
    public StudentImpairmentResponseDto create(CreateStudentImpairmentDto dto) {
        // Validar que el estudiante existe
        Optional<Student> studentOpt = studentRepository.findById(dto.getStudentId());
        if (studentOpt.isEmpty()) {
            throw new IllegalArgumentException("Estudiante no encontrado con ID: " + dto.getStudentId());
        }
        
        // Validar que la discapacidad existe
        Optional<Impairment> impairmentOpt = impairmentRepository.findById(dto.getImpairmentId());
        if (impairmentOpt.isEmpty()) {
            throw new IllegalArgumentException("Discapacidad no encontrada con ID: " + dto.getImpairmentId());
        }
        
        // Validar que la relación no existe ya
        if (studentImpairmentRepository.existsByStudentIdAndImpairmentId(dto.getStudentId(), dto.getImpairmentId())) {
            throw new IllegalArgumentException("La relación entre el estudiante " + dto.getStudentId() + 
                    " y la discapacidad " + dto.getImpairmentId() + " ya existe");
        }
        
        com.icheha.aprendia_api.preferences.impairments.domain.entities.StudentImpairment studentImpairment = 
                com.icheha.aprendia_api.preferences.impairments.domain.entities.StudentImpairment.builder()
                        .studentId(dto.getStudentId())
                        .impairmentId(dto.getImpairmentId())
                        .build();
        
        com.icheha.aprendia_api.preferences.impairments.domain.entities.StudentImpairment saved = 
                studentImpairmentRepository.save(studentImpairment);
        
        // Cargar la relación con Impairment para obtener el nombre usando repositorio JPA
        Optional<StudentImpairment> entityWithImpairmentOpt = 
                studentImpairmentJpaRepository.findByStudentIdAndImpairmentId(saved.getStudentId(), saved.getImpairmentId());
        
        StudentImpairmentResponseDto response = new StudentImpairmentResponseDto();
        response.setStudentId(saved.getStudentId());
        response.setImpairmentId(saved.getImpairmentId());
        response.setImpairmentName(entityWithImpairmentOpt.isPresent() && 
                entityWithImpairmentOpt.get().getImpairment() != null ? 
                entityWithImpairmentOpt.get().getImpairment().getName() : impairmentOpt.get().getName());
        return response;
    }
    
    @Override
    public List<StudentImpairmentResponseDto> findAll() {
        // Usar repositorio JPA directamente para obtener el nombre de la discapacidad
        List<StudentImpairment> entities = 
                studentImpairmentJpaRepository.findAllWithImpairment();
        return entities.stream()
                .map(entity -> {
                    StudentImpairmentResponseDto dto = new StudentImpairmentResponseDto();
                    dto.setStudentId(entity.getStudentId());
                    dto.setImpairmentId(entity.getImpairmentId());
                    dto.setImpairmentName(entity.getImpairment() != null ? entity.getImpairment().getName() : null);
                    return dto;
                })
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Long> findByImpairmentOnlyIds(Long impairmentId) {
        return studentImpairmentRepository.findStudentIdsByImpairmentId(impairmentId);
    }
    
    @Override
    public List<Long> findByStudentOnlyIds(Long studentId) {
        return studentImpairmentRepository.findImpairmentIdsByStudentId(studentId);
    }
    
    @Override
    public List<StudentImpairmentResponseDto> findByStudent(Long studentId) {
        // Usar repositorio JPA directamente para obtener el nombre de la discapacidad
        List<StudentImpairment> entities = 
                studentImpairmentJpaRepository.findByStudentIdWithImpairmentForList(studentId);
        return entities.stream()
                .map(entity -> {
                    StudentImpairmentResponseDto dto = new StudentImpairmentResponseDto();
                    dto.setStudentId(entity.getStudentId());
                    dto.setImpairmentId(entity.getImpairmentId());
                    dto.setImpairmentName(entity.getImpairment() != null ? entity.getImpairment().getName() : null);
                    return dto;
                })
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public StudentImpairmentResponseDto findById(Long studentId, Long impairmentId) {
        Optional<StudentImpairment> entityOpt = 
                studentImpairmentJpaRepository.findByStudentIdAndImpairmentId(studentId, impairmentId);
        
        if (entityOpt.isEmpty()) {
            throw new IllegalArgumentException("Relación estudiante-discapacidad no encontrada con studentId: " + 
                    studentId + " e impairmentId: " + impairmentId);
        }
        
        StudentImpairment entity = entityOpt.get();
        StudentImpairmentResponseDto dto = new StudentImpairmentResponseDto();
        dto.setStudentId(entity.getStudentId());
        dto.setImpairmentId(entity.getImpairmentId());
        dto.setImpairmentName(entity.getImpairment() != null ? entity.getImpairment().getName() : null);
        return dto;
    }
    
    @Override
    @Transactional
    public void delete(Long studentId, Long impairmentId) {
        // Validar que la relación existe
        if (!studentImpairmentRepository.existsByStudentIdAndImpairmentId(studentId, impairmentId)) {
            throw new IllegalArgumentException("Relación estudiante-discapacidad no encontrada con studentId: " + 
                    studentId + " e impairmentId: " + impairmentId);
        }
        
        studentImpairmentRepository.deleteByStudentIdAndImpairmentId(studentId, impairmentId);
    }
    
    @Override
    @Transactional
    public void deleteByStudentId(Long studentId) {
        // Validar que el estudiante existe
        Optional<Student> studentOpt = studentRepository.findById(studentId);
        if (studentOpt.isEmpty()) {
            throw new IllegalArgumentException("Estudiante no encontrado con ID: " + studentId);
        }
        
        studentImpairmentRepository.deleteByStudentId(studentId);
    }
}
