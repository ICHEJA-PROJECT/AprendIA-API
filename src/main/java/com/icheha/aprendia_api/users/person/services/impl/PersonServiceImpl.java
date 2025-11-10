package com.icheha.aprendia_api.users.person.services.impl;

import com.icheha.aprendia_api.auth.domain.entities.Persona;
import com.icheha.aprendia_api.auth.domain.enums.GenderEnum;
import com.icheha.aprendia_api.auth.domain.valueobjects.Curp;
import com.icheha.aprendia_api.auth.domain.valueobjects.Password;
import com.icheha.aprendia_api.users.person.data.dtos.CreatePersonDto;
import com.icheha.aprendia_api.users.person.data.dtos.PersonResponseDto;
import com.icheha.aprendia_api.users.person.data.dtos.UpdatePersonDto;
import com.icheha.aprendia_api.users.person.data.entities.PersonaEntity;
import com.icheha.aprendia_api.users.person.data.mappers.PersonaMapper;
import com.icheha.aprendia_api.users.person.data.repositories.PersonaRepository;
import com.icheha.aprendia_api.users.person.domain.repositories.IHashDataRepository;
import com.icheha.aprendia_api.users.person.domain.repositories.IPersonaRepository;
import com.icheha.aprendia_api.users.person.services.IImageUploadService;
import com.icheha.aprendia_api.users.person.services.IPersonService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements IPersonService {
    
    private final IPersonaRepository personaRepository;
    private final PersonaRepository personaJpaRepository;
    private final IHashDataRepository hashDataRepository;
    private final IImageUploadService imageUploadService;
    private final PersonaMapper personaMapper;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    public PersonServiceImpl(@Qualifier("userPersonaRepositoryImpl") IPersonaRepository personaRepository,
                            @Qualifier("userPersonaRepository") PersonaRepository personaJpaRepository,
                            IHashDataRepository hashDataRepository,
                            IImageUploadService imageUploadService,
                            @Qualifier("userPersonaMapper") PersonaMapper personaMapper) {
        this.personaRepository = personaRepository;
        this.personaJpaRepository = personaJpaRepository;
        this.hashDataRepository = hashDataRepository;
        this.imageUploadService = imageUploadService;
        this.personaMapper = personaMapper;
    }
    
    @Override
    @Transactional
    public PersonResponseDto create(CreatePersonDto createPersonDto) {
        try {
            // Convertir base64 a bytes
            byte[] imageBytes = Base64.getDecoder().decode(createPersonDto.getProfileImagePath());
            
            // Generar nombre de archivo
            String fileName = createPersonDto.getCurp() + "-" + 
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss")) + ".jpg";
            
            // Subir imagen a Cloudinary
            String imageUrl = imageUploadService.uploadImage(imageBytes, fileName, "profiles-images");
            
            // Hashear contraseña
            String hashedPassword = hashDataRepository.hash(createPersonDto.getPassword());
            
            // Convertir fecha de nacimiento
            LocalDate birthdate = LocalDate.parse(createPersonDto.getBirthdate(), DATE_FORMATTER);
            
            // Convertir género (M/F a MASCULINO/FEMENINO)
            GenderEnum gender;
            String genderStr = createPersonDto.getGender().toUpperCase();
            if ("M".equals(genderStr)) {
                gender = GenderEnum.MASCULINO;
            } else if ("F".equals(genderStr)) {
                gender = GenderEnum.FEMENINO;
            } else {
                gender = GenderEnum.fromValue(genderStr);
            }
            
            // Validar CURP antes de crear el value object
            String curpValue = createPersonDto.getCurp();
            if (curpValue == null || curpValue.trim().isEmpty()) {
                throw new IllegalArgumentException("El CURP es obligatorio y no puede estar vacío");
            }
            
            // Validar que el CURP no exista ya en la base de datos
            Curp curp = new Curp(curpValue);
            if (personaRepository.findByCurp(curp).isPresent()) {
                throw new IllegalArgumentException("Ya existe una persona con el CURP: " + curpValue);
            }
            
            // Crear entidad de dominio Persona
            Persona persona = new Persona.Builder()
                    .primerNombre(createPersonDto.getFirstName())
                    .segundoNombre(createPersonDto.getMiddleName())
                    .apellidoPaterno(createPersonDto.getPaternalSurname())
                    .apellidoMaterno(createPersonDto.getMaternalSurname())
                    .curp(curp)
                    .numeroIne(createPersonDto.getIneNumber())
                    .fechaNacimiento(birthdate)
                    .genero(gender)
                    .vialidadNombre(createPersonDto.getRoadName())
                    .idVialidadTipo(createPersonDto.getRoadTypeId().intValue())
                    .asentamiento(null) // Se establecerá desde el settlement
                    .idAsentamientoTipo(null) // Se establecerá desde el settlement
                    .password(new Password(hashedPassword))
                    .build();
            
            // Guardar persona con los IDs de relaciones y la URL de la imagen
            Persona savedPersona = personaRepository.create(persona, 
                    createPersonDto.getRoadTypeId(), 
                    createPersonDto.getSettlementId(),
                    imageUrl);
            
            // La URL de la imagen ya está guardada en la BD durante el create
            // Usamos la URL que acabamos de subir y guardar
            return toResponseDto(savedPersona, imageUrl);
            
        } catch (Exception e) {
            throw new RuntimeException("Error al crear persona: " + e.getMessage(), e);
        }
    }
    
    @Override
    public Optional<PersonResponseDto> findById(Long id) {
        return personaRepository.findById(id)
                .map(persona -> {
                    // Obtener profileImagePath desde la entidad JPA
                    String profileImagePath = personaJpaRepository.findById(id)
                            .map(PersonaEntity::getProfileImagePath)
                            .orElse(null);
                    return toResponseDto(persona, profileImagePath);
                });
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<PersonResponseDto> findAll() {
        // Usar findAll() estándar de JpaRepository y mapear directamente
        // El mapper ya está configurado para no acceder a relaciones lazy
        List<Persona> personas = personaRepository.findAll();
        
        // Obtener profileImagePath usando consulta que no carga relaciones
        List<Long> personaIds = personas.stream()
                .map(Persona::getIdPersona)
                .filter(id -> id != null)
                .collect(Collectors.toList());
        
        if (personaIds.isEmpty()) {
            return java.util.Collections.emptyList();
        }
        
        Map<Long, String> imagePathsMap = personaJpaRepository.findProfileImagePathsByIds(personaIds).stream()
                .collect(Collectors.toMap(
                    row -> (Long) row[0],
                    row -> (String) row[1],
                    (existing, replacement) -> existing
                ));
        
        return personas.stream()
                .map(persona -> {
                    String profileImagePath = imagePathsMap.get(persona.getIdPersona());
                    return toResponseDto(persona, profileImagePath);
                })
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public PersonResponseDto update(Long id, UpdatePersonDto updatePersonDto) {
        Persona existingPersona = personaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + id));
        
        try {
            // Procesar imagen si se proporciona
            String imageUrl = null;
            if (updatePersonDto.getProfileImagePath() != null && !updatePersonDto.getProfileImagePath().isEmpty()) {
                byte[] imageBytes = Base64.getDecoder().decode(updatePersonDto.getProfileImagePath());
                String fileName = (updatePersonDto.getCurp() != null ? updatePersonDto.getCurp() : existingPersona.getCurpValue()) + "-" + 
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss")) + ".jpg";
                imageUrl = imageUploadService.uploadImage(imageBytes, fileName, "profiles-images");
            }
            
            // Hashear contraseña si se proporciona
            String hashedPassword = null;
            if (updatePersonDto.getPassword() != null && !updatePersonDto.getPassword().isEmpty()) {
                hashedPassword = hashDataRepository.hash(updatePersonDto.getPassword());
            }
            
            // Convertir fecha de nacimiento si se proporciona
            LocalDate birthdate = null;
            if (updatePersonDto.getBirthdate() != null && !updatePersonDto.getBirthdate().isEmpty()) {
                birthdate = LocalDate.parse(updatePersonDto.getBirthdate(), DATE_FORMATTER);
            } else {
                birthdate = existingPersona.getFechaNacimiento();
            }
            
            // Convertir género si se proporciona
            GenderEnum gender = existingPersona.getGenero();
            if (updatePersonDto.getGender() != null && !updatePersonDto.getGender().isEmpty()) {
                String genderStr = updatePersonDto.getGender().toUpperCase();
                if ("M".equals(genderStr)) {
                    gender = GenderEnum.MASCULINO;
                } else if ("F".equals(genderStr)) {
                    gender = GenderEnum.FEMENINO;
                } else {
                    gender = GenderEnum.fromValue(genderStr);
                }
            }
            
            // Validar CURP si se proporciona
            String curpValue = updatePersonDto.getCurp() != null ? updatePersonDto.getCurp() : existingPersona.getCurpValue();
            
            // Construir Persona actualizada
            Persona.Builder builder = new Persona.Builder()
                    .idPersona(existingPersona.getIdPersona())
                    .primerNombre(updatePersonDto.getFirstName() != null ? updatePersonDto.getFirstName() : existingPersona.getPrimerNombre())
                    .segundoNombre(updatePersonDto.getMiddleName() != null ? updatePersonDto.getMiddleName() : existingPersona.getSegundoNombre())
                    .apellidoPaterno(updatePersonDto.getPaternalSurname() != null ? updatePersonDto.getPaternalSurname() : existingPersona.getApellidoPaterno())
                    .apellidoMaterno(updatePersonDto.getMaternalSurname() != null ? updatePersonDto.getMaternalSurname() : existingPersona.getApellidoMaterno())
                    .numeroIne(updatePersonDto.getIneNumber() != null ? updatePersonDto.getIneNumber() : existingPersona.getNumeroIne())
                    .fechaNacimiento(birthdate)
                    .genero(gender)
                    .vialidadNombre(updatePersonDto.getRoadName() != null ? updatePersonDto.getRoadName() : existingPersona.getVialidadNombre())
                    .idVialidadTipo(updatePersonDto.getRoadTypeId() != null ? updatePersonDto.getRoadTypeId().intValue() : existingPersona.getIdVialidadTipo())
                    .asentamiento(existingPersona.getAsentamiento())
                    .idAsentamientoTipo(existingPersona.getIdAsentamientoTipo());
            
            if (curpValue != null && !curpValue.trim().isEmpty()) {
                builder.curp(new Curp(curpValue));
            } else {
                builder.curp(existingPersona.getCurp());
            }
            
            if (hashedPassword != null) {
                builder.password(new Password(hashedPassword));
            } else {
                builder.password(existingPersona.getPassword());
            }
            
            Persona updatedPersona = builder.build();
            
            // Actualizar persona
            Persona savedPersona = personaRepository.update(updatedPersona, 
                    updatePersonDto.getRoadTypeId(), 
                    updatePersonDto.getSettlementId(),
                    imageUrl);
            
            // Obtener profileImagePath actualizado
            String finalImageUrl = imageUrl != null ? imageUrl : 
                    personaJpaRepository.findById(id)
                            .map(PersonaEntity::getProfileImagePath)
                            .orElse(null);
            
            return toResponseDto(savedPersona, finalImageUrl);
            
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar persona: " + e.getMessage(), e);
        }
    }
    
    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!personaRepository.findById(id).isPresent()) {
            throw new RuntimeException("Persona no encontrada con ID: " + id);
        }
        personaRepository.deleteById(id);
    }
    
    private PersonResponseDto toResponseDto(Persona persona) {
        return toResponseDto(persona, null);
    }
    
    private PersonResponseDto toResponseDto(Persona persona, String imageUrl) {
        PersonResponseDto dto = new PersonResponseDto();
        dto.setIdPersona(persona.getIdPersona());
        dto.setPrimerNombre(persona.getPrimerNombre());
        dto.setSegundoNombre(persona.getSegundoNombre());
        dto.setApellidoPaterno(persona.getApellidoPaterno());
        dto.setApellidoMaterno(persona.getApellidoMaterno());
        dto.setCurp(persona.getCurpValue()); // Usar método seguro que maneja null
        dto.setNumeroIne(persona.getNumeroIne());
        dto.setFechaNacimiento(persona.getFechaNacimiento());
        dto.setGenero(persona.getGenero().getValue());
        dto.setVialidadNombre(persona.getVialidadNombre());
        dto.setProfileImagePath(imageUrl);
        return dto;
    }
}

