package com.icheha.aprendia_api.users.student.services.impl;

import com.icheha.aprendia_api.auth.data.repositories.UserRepository;
import com.icheha.aprendia_api.auth.domain.entities.Persona;
import com.icheha.aprendia_api.core.utils.JwtUtil;
import com.icheha.aprendia_api.users.person.domain.repositories.IPersonaRepository;
import com.icheha.aprendia_api.users.person.services.IImageUploadService;
import com.icheha.aprendia_api.preferences.impairments.data.entities.StudentImpairment;
import com.icheha.aprendia_api.preferences.impairments.data.repositories.StudentImpairmentRepository;
import com.icheha.aprendia_api.preferences.impairments.services.IStudentImpairmentService;
import com.icheha.aprendia_api.users.student.data.dtos.CreateParienteDto;
import com.icheha.aprendia_api.users.student.data.dtos.CreateStudentDto;
import com.icheha.aprendia_api.users.student.data.dtos.RegisterStudentResponseDto;
import com.icheha.aprendia_api.users.student.data.dtos.StudentResponseDto;
import com.icheha.aprendia_api.users.student.data.dtos.UpdateStudentDto;
import com.icheha.aprendia_api.users.student.domain.entities.Student;
import com.icheha.aprendia_api.users.student.domain.repositories.IEncryptDataRepository;
import com.icheha.aprendia_api.users.student.domain.repositories.IQRRepository;
import com.icheha.aprendia_api.users.student.domain.repositories.IStudentRepository;
import com.icheha.aprendia_api.users.student.domain.repositories.IRolParienteRepository;
import com.icheha.aprendia_api.users.student.services.IParienteService;
import com.icheha.aprendia_api.users.student.services.IStudentService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements IStudentService {
    
    private final IStudentRepository studentRepository;
    private final IParienteService parienteService;
    private final IRolParienteRepository rolParienteRepository;
    private final IPersonaRepository personaRepository;
    private final IQRRepository qrRepository;
    private final IEncryptDataRepository encryptDataRepository;
    private final IImageUploadService imageUploadService;
    private final IStudentImpairmentService studentImpairmentService;
    private final StudentImpairmentRepository studentImpairmentRepository;
    private final JwtUtil jwtUtil;
    private final com.icheha.aprendia_api.users.role.services.IRolePersonService rolePersonService;
    private final UserRepository userRepository;
    
    public StudentServiceImpl(IStudentRepository studentRepository,
                             IParienteService parienteService,
                             IRolParienteRepository rolParienteRepository,
                             @Qualifier("userPersonaRepositoryImpl") IPersonaRepository personaRepository,
                             IQRRepository qrRepository,
                             IEncryptDataRepository encryptDataRepository,
                             IImageUploadService imageUploadService,
                             IStudentImpairmentService studentImpairmentService,
                             StudentImpairmentRepository studentImpairmentRepository,
                             JwtUtil jwtUtil,
                             com.icheha.aprendia_api.users.role.services.IRolePersonService rolePersonService,
                             UserRepository userRepository) {
        this.studentRepository = studentRepository;
        this.parienteService = parienteService;
        this.rolParienteRepository = rolParienteRepository;
        this.personaRepository = personaRepository;
        this.qrRepository = qrRepository;
        this.encryptDataRepository = encryptDataRepository;
        this.imageUploadService = imageUploadService;
        this.studentImpairmentService = studentImpairmentService;
        this.studentImpairmentRepository = studentImpairmentRepository;
        this.jwtUtil = jwtUtil;
        this.rolePersonService = rolePersonService;
        this.userRepository = userRepository;
    }
    
    @Override
    @Transactional
    public RegisterStudentResponseDto create(CreateStudentDto createStudentDto) {
        return create(createStudentDto, null);
    }
    
    @Override
    @Transactional
    public RegisterStudentResponseDto create(CreateStudentDto createStudentDto, Long createdByUserId) {
        try {
            // Validar que la persona existe
            Persona persona = personaRepository.findById(createStudentDto.getPersonId())
                    .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada con ID: " + createStudentDto.getPersonId()));
            
            // Validar educador si se proporciona
            Persona teacher = null;
            if (createStudentDto.getTeacherId() != null) {
                teacher = personaRepository.findById(createStudentDto.getTeacherId())
                        .orElseThrow(() -> new IllegalArgumentException("Educador no encontrado con ID: " + createStudentDto.getTeacherId()));
                
                // Validar que el educador tenga rol de educador (rol ID 1)
                Long userId = userRepository.findByIdPersona(createStudentDto.getTeacherId())
                        .map(u -> u.getIdUser())
                        .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado para la persona con ID: " + createStudentDto.getTeacherId()));
                var teacherRoles = rolePersonService.findByUserId(userId);
                
                boolean hasTeacherRole = teacherRoles.stream()
                        .anyMatch(rp -> rp.getRoleId() != null && rp.getRoleId().equals(1L));
                if (!hasTeacherRole) {
                    String rolesFound = teacherRoles.isEmpty() ? "ninguno" : 
                        teacherRoles.stream()
                            .map(rp -> String.valueOf(rp.getRoleId()))
                            .collect(java.util.stream.Collectors.joining(", "));
                    throw new IllegalArgumentException("La persona seleccionada como educador no cuenta con ese rol. Roles encontrados: " + rolesFound);
                }
            }
            
            // Asignar rol de estudiante (rol ID 4) a la persona
            Long studentUserId = userRepository.findByIdPersona(createStudentDto.getPersonId())
                    .map(u -> u.getIdUser())
                    .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado para la persona con ID: " + createStudentDto.getPersonId()));
            com.icheha.aprendia_api.users.role.data.dtos.CreateRolePersonDto rolePersonDto = 
                    new com.icheha.aprendia_api.users.role.data.dtos.CreateRolePersonDto();
            rolePersonDto.setUserId(studentUserId);
            rolePersonDto.setRoleId(4L); // Rol de Estudiante
            rolePersonService.create(rolePersonDto);
            
            // Crear estudiante
            Student student = new Student.Builder()
                    .persona(persona)
                    .teacher(teacher)
                    .qrPath("pending")
                    .build();
            
            Student savedStudent = studentRepository.create(
                    student,
                    createStudentDto.getPersonId(),
                    createStudentDto.getTeacherId(),
                    "pending",
                    createdByUserId
            );
            
            // Crear relaciones de parientes (padre y madre)
            // Obtener IDs de roles de pariente dinámicamente
            Long padreRolId = rolParienteRepository.findByNombre("Padre")
                    .orElseThrow(() -> new RuntimeException("Rol de pariente 'Padre' no encontrado"))
                    .getId();
            Long madreRolId = rolParienteRepository.findByNombre("Madre")
                    .orElseThrow(() -> new RuntimeException("Rol de pariente 'Madre' no encontrado"))
                    .getId();
            
            // Crear relación padre
            CreateParienteDto createPadreDto = new CreateParienteDto();
            createPadreDto.setPersonaId(createStudentDto.getPersonId());
            createPadreDto.setParienteId(createStudentDto.getFatherPersonId());
            createPadreDto.setRolParienteId(padreRolId);
            try {
                parienteService.create(createPadreDto);
            } catch (Exception e) {
                // Si ya existe la relación, continuar
                System.out.println("Relación padre ya existe o error: " + e.getMessage());
            }
            
            // Crear relación madre
            CreateParienteDto createMadreDto = new CreateParienteDto();
            createMadreDto.setPersonaId(createStudentDto.getPersonId());
            createMadreDto.setParienteId(createStudentDto.getMotherPersonId());
            createMadreDto.setRolParienteId(madreRolId);
            try {
                parienteService.create(createMadreDto);
            } catch (Exception e) {
                // Si ya existe la relación, continuar
                System.out.println("Relación madre ya existe o error: " + e.getMessage());
            }
            
            // Crear discapacidades si se proporcionan
            if (createStudentDto.getImpairments() != null && !createStudentDto.getImpairments().isEmpty()) {
                for (Long impairmentId : createStudentDto.getImpairments()) {
                    StudentImpairment studentImpairment = new StudentImpairment();
                    // Nota: StudentImpairment usa id_educando que es el ID del estudiante
                    studentImpairment.setStudentId(savedStudent.getId());
                    studentImpairment.setImpairmentId(impairmentId);
                    studentImpairmentRepository.save(studentImpairment);
                }
            }
            
            // Obtener preferencias del estudiante (usa personId, no studentId)
            var studentPreferences = studentImpairmentService.getStudentPreferencesWithDetails(
                    savedStudent.getPersona().getIdPersona().intValue());
            
            // Generar token JWT con la misma estructura que el original
            // El payload debe incluir: studentId, personId, name, y preferencias
            String disabilityName = "Sin discapacidad";
            Long disabilityId = 0L;
            Long learningPathId = 2L;
            
            if (studentPreferences != null) {
                if (studentPreferences.getImpairments() != null && !studentPreferences.getImpairments().isEmpty()) {
                    disabilityName = studentPreferences.getImpairments().get(0).getName();
                    disabilityId = studentPreferences.getImpairments().get(0).getId();
                }
                if (studentPreferences.getLearningPath() != null) {
                    learningPathId = studentPreferences.getLearningPath().getId();
                }
            }
            
            // Generar token con la estructura del original
            String token = generateStudentToken(
                    savedStudent.getId(),
                    savedStudent.getPersona().getIdPersona(),
                    savedStudent.getPersona().getPrimerNombre(),
                    disabilityName,
                    disabilityId,
                    learningPathId
            );
            
            // Encriptar token
            String encryptedToken = encryptDataRepository.encrypt(token);
            
            // Generar QR
            String qrImageBase64 = qrRepository.generateQR(encryptedToken);
            
            // Convertir base64 a bytes y subir a Cloudinary
            String base64Data = qrImageBase64.substring(qrImageBase64.indexOf(",") + 1);
            byte[] qrImageBytes = Base64.getDecoder().decode(base64Data);
            
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss"));
            String fileName = savedStudent.getId() + "-qr-" + timestamp + ".png";
            
            String qrImageUrl = imageUploadService.uploadImage(qrImageBytes, fileName, "qr-images");
            
            // Actualizar ruta del QR
            studentRepository.updateQrPath(savedStudent.getId(), qrImageUrl);
            
            return new RegisterStudentResponseDto(qrImageUrl, encryptedToken);
            
        } catch (Exception e) {
            throw new RuntimeException("Error al crear estudiante: " + e.getMessage(), e);
        }
    }
    
    private String generateStudentToken(Long studentId, Long personId, String name, 
                                       String disabilityName, Long disabilityId, Long learningPathId) {
        // Generar token con la misma estructura que el original
        // El payload original incluye: studentId, personId, name, disabilityName, disabilityId, learningPathId
        try {
            // Crear un mapa con los claims como en el original
            Map<String, Object> claims = new HashMap<>();
            claims.put("studentId", studentId);
            claims.put("personId", personId);
            claims.put("name", name);
            claims.put("disabilityName", disabilityName);
            claims.put("disabilityId", disabilityId);
            claims.put("learningPathId", learningPathId);
            
            // Generar token usando JwtUtil con los claims directamente
            // JwtUtil debe soportar Map<String, Object> o necesitamos crear un método específico
            return jwtUtil.generateTokenFromClaims(claims);
        } catch (Exception e) {
            throw new RuntimeException("Error al generar token: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<StudentResponseDto> findByTeacher(Long teacherId) {
        return studentRepository.findByTeacher(teacherId).stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<StudentResponseDto> findByCurp(String curp) {
        return studentRepository.findByCurp(curp).stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<StudentResponseDto> findByName(String name) {
        String[] parts = name.split(" ", 2);
        String firstName = parts.length > 0 ? parts[0] : "";
        String paternalSurname = parts.length > 1 ? parts[1] : "";
        
        return studentRepository.findByName(firstName, paternalSurname).stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<StudentResponseDto> findById(Long id) {
        return studentRepository.findById(id)
                .map(this::toResponseDto);
    }
    
    @Override
    public List<String> findUniqueNames() {
        return studentRepository.findUniqueNames();
    }
    
    @Override
    @Transactional
    public List<StudentResponseDto> findAll() {
        return studentRepository.findAll().stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public StudentResponseDto update(Long id, UpdateStudentDto updateStudentDto) {
        try {
            // Validar que el estudiante existe
            Student existingStudent = studentRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado con ID: " + id));
            
            // Validar educador si se proporciona
            Long teacherId = updateStudentDto.getTeacherId();
            if (teacherId != null) {
                Persona teacher = personaRepository.findById(teacherId)
                        .orElseThrow(() -> new IllegalArgumentException("Educador no encontrado con ID: " + teacherId));
                
                // Validar que el educador tenga rol de educador (rol ID 1)
                Long userId = userRepository.findByIdPersona(teacherId)
                        .map(u -> u.getIdUser())
                        .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado para la persona con ID: " + teacherId));
                var teacherRoles = rolePersonService.findByUserId(userId);
                boolean hasTeacherRole = teacherRoles.stream()
                        .anyMatch(rp -> rp.getRoleId() != null && rp.getRoleId().equals(1L));
                if (!hasTeacherRole) {
                    throw new IllegalArgumentException("La persona seleccionada como educador no cuenta con ese rol");
                }
            }
            
            // Actualizar estudiante
            Student updatedStudent = studentRepository.update(
                    id,
                    teacherId,
                    updateStudentDto.getQrPath()
            );
            
            // Actualizar relaciones de parientes si se proporcionan
            if (updateStudentDto.getFatherPersonId() != null || updateStudentDto.getMotherPersonId() != null) {
                Long personaId = existingStudent.getPersona().getIdPersona();
                
                // Obtener roles de pariente dinámicamente
                Long padreRolId = rolParienteRepository.findByNombre("Padre")
                        .orElseThrow(() -> new RuntimeException("Rol de pariente 'Padre' no encontrado"))
                        .getId();
                Long madreRolId = rolParienteRepository.findByNombre("Madre")
                        .orElseThrow(() -> new RuntimeException("Rol de pariente 'Madre' no encontrado"))
                        .getId();
                
                // Actualizar relación padre si se proporciona
                if (updateStudentDto.getFatherPersonId() != null) {
                    // Buscar relación existente de padre
                    var parientesPadre = parienteService.findByPersonaIdAndRolNombre(personaId, "Padre");
                    if (!parientesPadre.isEmpty()) {
                        // Eliminar relación anterior
                        parienteService.delete(parientesPadre.get(0).getId());
                    }
                    
                    // Crear nueva relación
                    CreateParienteDto createPadreDto = new CreateParienteDto();
                    createPadreDto.setPersonaId(personaId);
                    createPadreDto.setParienteId(updateStudentDto.getFatherPersonId());
                    createPadreDto.setRolParienteId(padreRolId);
                    try {
                        parienteService.create(createPadreDto);
                    } catch (Exception e) {
                        System.out.println("Error al actualizar relación padre: " + e.getMessage());
                    }
                }
                
                // Actualizar relación madre si se proporciona
                if (updateStudentDto.getMotherPersonId() != null) {
                    // Buscar relación existente de madre
                    var parientesMadre = parienteService.findByPersonaIdAndRolNombre(personaId, "Madre");
                    if (!parientesMadre.isEmpty()) {
                        // Eliminar relación anterior
                        parienteService.delete(parientesMadre.get(0).getId());
                    }
                    
                    // Crear nueva relación
                    CreateParienteDto createMadreDto = new CreateParienteDto();
                    createMadreDto.setPersonaId(personaId);
                    createMadreDto.setParienteId(updateStudentDto.getMotherPersonId());
                    createMadreDto.setRolParienteId(madreRolId);
                    try {
                        parienteService.create(createMadreDto);
                    } catch (Exception e) {
                        System.out.println("Error al actualizar relación madre: " + e.getMessage());
                    }
                }
            }
            
            // Actualizar discapacidades si se proporcionan
            if (updateStudentDto.getImpairments() != null) {
                // Eliminar discapacidades existentes
                List<StudentImpairment> existingImpairments = 
                        studentImpairmentRepository.findByStudentId(updatedStudent.getId());
                studentImpairmentRepository.deleteAll(existingImpairments);
                
                // Crear nuevas discapacidades
                if (!updateStudentDto.getImpairments().isEmpty()) {
                    for (Long impairmentId : updateStudentDto.getImpairments()) {
                        StudentImpairment studentImpairment = new StudentImpairment();
                        studentImpairment.setStudentId(updatedStudent.getId());
                        studentImpairment.setImpairmentId(impairmentId);
                        studentImpairmentRepository.save(studentImpairment);
                    }
                }
            }
            
            return toResponseDto(updatedStudent);
            
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar estudiante: " + e.getMessage(), e);
        }
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        try {
            // Validar que el estudiante existe
            Student student = studentRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado con ID: " + id));
            
            // Eliminar discapacidades asociadas
            List<StudentImpairment> impairments = studentImpairmentRepository.findByStudentId(id);
            if (!impairments.isEmpty()) {
                studentImpairmentRepository.deleteAll(impairments);
            }
            
            // Eliminar estudiante
            studentRepository.delete(id);
            
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar estudiante: " + e.getMessage(), e);
        }
    }
    
    private StudentResponseDto toResponseDto(Student student) {
        StudentResponseDto dto = new StudentResponseDto();
        dto.setId(student.getId());
        dto.setPersonId(student.getPersona() != null ? student.getPersona().getIdPersona() : null);
        dto.setTeacherId(student.getTeacher() != null ? student.getTeacher().getIdPersona() : null);
        dto.setQrPath(student.getQrPath());
        return dto;
    }
}

