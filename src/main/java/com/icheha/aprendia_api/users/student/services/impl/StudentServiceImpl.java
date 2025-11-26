package com.icheha.aprendia_api.users.student.services.impl;

import com.icheha.aprendia_api.auth.data.entities.UserEntity;
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
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    
    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    
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
    
    @PersistenceContext
    private EntityManager entityManager;
    
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
                             com.icheha.aprendia_api.users.role.services.IRolePersonService rolePersonService) {
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
            // IMPORTANTE: Un maestro debe ser un UserEntity con rol de educador, no solo una PersonaEntity
            Persona teacher = null;
            if (createStudentDto.getTeacherId() != null) {
                // Primero validar que existe un UserEntity para esta persona (un maestro es un usuario)
                UserEntity teacherUserEntity = findUserEntityByIdPersona(createStudentDto.getTeacherId());
                
                // Validar que el usuario esté activo
                if (!Boolean.TRUE.equals(teacherUserEntity.getIsActive())) {
                    throw new IllegalArgumentException("El usuario con ID: " + teacherUserEntity.getIdUser() + 
                        " no está activo y no puede ser asignado como educador");
                }
                
                // Validar que el educador tenga rol de educador (rol ID 1)
                Long userId = teacherUserEntity.getIdUser();
                var teacherRoles = rolePersonService.findByUserId(userId);
                
                boolean hasTeacherRole = teacherRoles.stream()
                        .anyMatch(rp -> rp.getRoleId() != null && rp.getRoleId().equals(1L));
                if (!hasTeacherRole) {
                    String rolesFound = teacherRoles.isEmpty() ? "ninguno" : 
                        teacherRoles.stream()
                            .map(rp -> String.valueOf(rp.getRoleId()))
                            .collect(java.util.stream.Collectors.joining(", "));
                    throw new IllegalArgumentException("El usuario con ID: " + userId + 
                        " no tiene el rol de educador (rol ID 1). Roles encontrados: " + rolesFound);
                }
                
                // Solo después de validar que es un UserEntity con rol de educador, obtener la PersonaEntity
                teacher = personaRepository.findById(createStudentDto.getTeacherId())
                        .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada para el educador con ID: " + createStudentDto.getTeacherId()));
            }
            
            // Asignar rol de estudiante (rol ID 4) a la persona
            // Usar EntityManager directamente para obtener UserEntity y evitar problemas de casting
            UserEntity studentUserEntity = findUserEntityByIdPersona(createStudentDto.getPersonId());
            Long studentUserId = studentUserEntity.getIdUser();
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
            // NOTA: Si el estudiante tiene múltiples impairments, solo se guarda la primera en el token QR
            // Al hacer login, se obtendrán TODAS las impairments actuales desde la BD, ignorando las del token
            String disabilityName = "Sin discapacidad";
            Long disabilityId = 0L;
            Long learningPathId = 2L;
            
            if (studentPreferences != null) {
                if (studentPreferences.getImpairments() != null && !studentPreferences.getImpairments().isEmpty()) {
                    // Solo se guarda la primera impairment en el token QR para mantener el tamaño del token pequeño
                    // Si el estudiante tiene múltiples impairments, todas se obtendrán desde la BD al hacer login
                    disabilityName = studentPreferences.getImpairments().get(0).getName();
                    disabilityId = studentPreferences.getImpairments().get(0).getId();
                    // NOTA: Si hay múltiples impairments, solo se guarda la primera en el token QR
                }
                if (studentPreferences.getLearningPath() != null) {
                    learningPathId = studentPreferences.getLearningPath().getId();
                }
            }
            
            // Obtener nombres de parientes (padre y madre)
            String fatherName = obtenerNombrePariente(savedStudent.getPersona().getIdPersona(), "Padre");
            String motherName = obtenerNombrePariente(savedStudent.getPersona().getIdPersona(), "Madre");
            
            // Generar token con la estructura del original incluyendo nombres de parientes
            String token = generateStudentToken(
                    savedStudent.getId(),
                    savedStudent.getPersona().getIdPersona(),
                    savedStudent.getPersona().getPrimerNombre(),
                    disabilityName,
                    disabilityId,
                    learningPathId,
                    fatherName,
                    motherName
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
    
    /**
     * Obtiene el nombre completo de un pariente (padre o madre) del estudiante
     * 
     * @param personaId ID de la persona (estudiante)
     * @param rolNombre Nombre del rol del pariente ("Padre" o "Madre")
     * @return Nombre completo del pariente, o null si no existe
     */
    private String obtenerNombrePariente(Long personaId, String rolNombre) {
        try {
            var parientes = parienteService.findByPersonaIdAndRolNombre(personaId, rolNombre);
            if (!parientes.isEmpty()) {
                var parienteDto = parientes.get(0);
                Optional<Persona> parientePersonaOpt = personaRepository.findById(parienteDto.getParienteId());
                if (parientePersonaOpt.isPresent()) {
                    Persona pariente = parientePersonaOpt.get();
                    return pariente.getNombreCompletoConApellidos();
                } else {
                    logger.warn("Pariente con ID {} no encontrado en la base de datos para persona {}", 
                            parienteDto.getParienteId(), personaId);
                    return null;
                }
            }
            return null;
        } catch (Exception e) {
            logger.warn("Error obteniendo nombre de pariente {} para persona {}: {}", 
                    rolNombre, personaId, e.getMessage());
            return null;
        }
    }
    
    /**
     * Genera un token JWT para el estudiante con toda su información incluyendo nombres de parientes
     * 
     * @param studentId ID del estudiante
     * @param personId ID de la persona
     * @param name Nombre del estudiante
     * @param disabilityName Nombre de la discapacidad
     * @param disabilityId ID de la discapacidad
     * @param learningPathId ID de la ruta de aprendizaje
     * @param fatherName Nombre completo del padre (puede ser null)
     * @param motherName Nombre completo de la madre (puede ser null)
     * @return Token JWT como string
     */
    private String generateStudentToken(Long studentId, Long personId, String name, 
                                       String disabilityName, Long disabilityId, Long learningPathId,
                                       String fatherName, String motherName) {
        try {
            // Crear un mapa con los claims básicos
            Map<String, Object> claims = new HashMap<>();
            claims.put("studentId", studentId);
            claims.put("personId", personId);
            claims.put("name", name);
            claims.put("disabilityName", disabilityName);
            claims.put("disabilityId", disabilityId);
            claims.put("learningPathId", learningPathId);
            
            // Agregar nombres de parientes como strings simples
            if (fatherName != null && !fatherName.trim().isEmpty()) {
                claims.put("fatherName", fatherName);
            }
            if (motherName != null && !motherName.trim().isEmpty()) {
                claims.put("motherName", motherName);
            }
            
            // Generar token usando JwtUtil con los claims
            return jwtUtil.generateTokenFromClaims(claims);
        } catch (Exception e) {
            logger.error("Error al generar token para estudiante {}: {}", studentId, e.getMessage(), e);
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
            // IMPORTANTE: Un maestro debe ser un UserEntity con rol de educador, no solo una PersonaEntity
            Long teacherId = updateStudentDto.getTeacherId();
            if (teacherId != null) {
                // Primero validar que existe un UserEntity para esta persona (un maestro es un usuario)
                UserEntity teacherUserEntity = findUserEntityByIdPersona(teacherId);
                
                // Validar que el usuario esté activo
                if (!Boolean.TRUE.equals(teacherUserEntity.getIsActive())) {
                    throw new IllegalArgumentException("El usuario con ID: " + teacherUserEntity.getIdUser() + 
                        " no está activo y no puede ser asignado como educador");
                }
                
                // Validar que el educador tenga rol de educador (rol ID 1)
                Long userId = teacherUserEntity.getIdUser();
                var teacherRoles = rolePersonService.findByUserId(userId);
                boolean hasTeacherRole = teacherRoles.stream()
                        .anyMatch(rp -> rp.getRoleId() != null && rp.getRoleId().equals(1L));
                if (!hasTeacherRole) {
                    String rolesFound = teacherRoles.isEmpty() ? "ninguno" : 
                        teacherRoles.stream()
                            .map(rp -> String.valueOf(rp.getRoleId()))
                            .collect(java.util.stream.Collectors.joining(", "));
                    throw new IllegalArgumentException("El usuario con ID: " + userId + 
                        " no tiene el rol de educador (rol ID 1). Roles encontrados: " + rolesFound);
                }
                
                // La PersonaEntity ya está validada implícitamente por findUserEntityByIdPersona
                // ya que un UserEntity solo puede existir si existe la PersonaEntity asociada
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
    
    /**
     * Método helper para obtener UserEntity por idPersona usando EntityManager directamente.
     * Esto evita problemas de casting entre User (dominio) y UserEntity (data).
     * Si hay múltiples resultados, prioriza el que esté activo y tenga roles asignados.
     */
    private UserEntity findUserEntityByIdPersona(Long idPersona) {
        if (idPersona == null) {
            throw new IllegalArgumentException("ID de persona no puede ser nulo");
        }
        
        // Buscar todos los usuarios con este idPersona
        jakarta.persistence.TypedQuery<UserEntity> query = entityManager.createQuery(
            "SELECT u FROM UserEntity u WHERE u.idPersona = :idPersona", UserEntity.class);
        query.setParameter("idPersona", idPersona);
        
        List<UserEntity> results = query.getResultList();
        if (results.isEmpty()) {
            throw new IllegalArgumentException("Usuario no encontrado para la persona con ID: " + idPersona);
        }
        
        // Si hay solo un resultado, devolverlo
        if (results.size() == 1) {
            return results.get(0);
        }
        
        // Si hay múltiples resultados, buscar el que tenga roles asignados y esté activo
        // Esto es útil para identificar correctamente a un maestro (que tiene rol de educador)
        for (UserEntity userEntity : results) {
            if (Boolean.TRUE.equals(userEntity.getIsActive())) {
                // Verificar si tiene roles asignados
                jakarta.persistence.TypedQuery<Long> rolesQuery = entityManager.createQuery(
                    "SELECT COUNT(ur) FROM UserRolEntity ur WHERE ur.idUser = :userId", Long.class);
                rolesQuery.setParameter("userId", userEntity.getIdUser());
                Long roleCount = rolesQuery.getSingleResult();
                
                if (roleCount != null && roleCount > 0) {
                    // Este usuario está activo y tiene roles, es probablemente el correcto
                    System.err.println("ADVERTENCIA: Se encontraron " + results.size() + 
                        " usuarios para la persona con ID: " + idPersona + 
                        ". Se usará el activo con roles (idUser: " + userEntity.getIdUser() + ")");
                    return userEntity;
                }
            }
        }
        
        // Si ninguno tiene roles, tomar el más reciente (mayor idUser) y activo
        UserEntity mostRecent = results.stream()
            .filter(u -> Boolean.TRUE.equals(u.getIsActive()))
            .max((u1, u2) -> Long.compare(
                u1.getIdUser() != null ? u1.getIdUser() : 0L,
                u2.getIdUser() != null ? u2.getIdUser() : 0L))
            .orElse(results.get(0));
        
        System.err.println("ADVERTENCIA: Se encontraron " + results.size() + 
            " usuarios para la persona con ID: " + idPersona + 
            ". Se usará el más reciente activo (idUser: " + mostRecent.getIdUser() + ")");
        
        return mostRecent;
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

