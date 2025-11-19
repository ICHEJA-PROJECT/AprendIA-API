package com.icheha.aprendia_api.users.person.services.impl;

import com.icheha.aprendia_api.users.person.data.dtos.request.CreateSettlementDto;
import com.icheha.aprendia_api.users.person.data.dtos.request.UpdateSettlementDto;
import com.icheha.aprendia_api.users.person.data.dtos.response.SettlementResponseDto;
import com.icheha.aprendia_api.users.person.data.mappers.MunicipalityMapper;
import com.icheha.aprendia_api.users.person.data.mappers.SettlementMapper;
import com.icheha.aprendia_api.users.person.data.mappers.SettlementTypeMapper;
import com.icheha.aprendia_api.users.person.data.mappers.TownMapper;
import com.icheha.aprendia_api.users.person.data.mappers.ZipcodeMapper;
import com.icheha.aprendia_api.users.person.data.repositories.MunicipalityRepository;
import com.icheha.aprendia_api.users.person.data.repositories.SettlementTypeRepository;
import com.icheha.aprendia_api.users.person.data.repositories.TownRepository;
import com.icheha.aprendia_api.users.person.data.repositories.ZipcodeRepository;
import com.icheha.aprendia_api.users.person.domain.entities.Municipality;
import com.icheha.aprendia_api.users.person.domain.entities.Settlement;
import com.icheha.aprendia_api.users.person.domain.entities.SettlementType;
import com.icheha.aprendia_api.users.person.domain.entities.Town;
import com.icheha.aprendia_api.users.person.domain.entities.Zipcode;
import com.icheha.aprendia_api.users.person.domain.repositories.ISettlementRepository;
import com.icheha.aprendia_api.users.person.services.ISettlementService;
import com.icheha.aprendia_api.users.person.data.repositories.SettlementRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SettlementServiceImpl implements ISettlementService {
    
    private final ISettlementRepository settlementRepository;
    private final SettlementRepository settlementJpaRepository;
    private final SettlementMapper settlementMapper;
    private final ZipcodeRepository zipcodeRepository;
    private final SettlementTypeRepository settlementTypeRepository;
    private final MunicipalityRepository municipalityRepository;
    private final TownRepository townRepository;
    private final ZipcodeMapper zipcodeMapper;
    private final SettlementTypeMapper settlementTypeMapper;
    private final MunicipalityMapper municipalityMapper;
    private final TownMapper townMapper;
    
    public SettlementServiceImpl(ISettlementRepository settlementRepository,
                                SettlementRepository settlementJpaRepository,
                                SettlementMapper settlementMapper,
                                ZipcodeRepository zipcodeRepository,
                                SettlementTypeRepository settlementTypeRepository,
                                MunicipalityRepository municipalityRepository,
                                TownRepository townRepository,
                                ZipcodeMapper zipcodeMapper,
                                SettlementTypeMapper settlementTypeMapper,
                                MunicipalityMapper municipalityMapper,
                                TownMapper townMapper) {
        this.settlementRepository = settlementRepository;
        this.settlementJpaRepository = settlementJpaRepository;
        this.settlementMapper = settlementMapper;
        this.zipcodeRepository = zipcodeRepository;
        this.settlementTypeRepository = settlementTypeRepository;
        this.municipalityRepository = municipalityRepository;
        this.townRepository = townRepository;
        this.zipcodeMapper = zipcodeMapper;
        this.settlementTypeMapper = settlementTypeMapper;
        this.municipalityMapper = municipalityMapper;
        this.townMapper = townMapper;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<SettlementResponseDto> findByZipcode(String zipcode) {
        return settlementRepository.findByZipcode(zipcode).stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<SettlementResponseDto> findByMunicipalityAndTown(Long municipalityId, Long townId) {
        return settlementRepository.findByMunicipalityAndTown(municipalityId, townId).stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public SettlementResponseDto create(CreateSettlementDto createSettlementDto) {
        // Validar y obtener entidades relacionadas
        Zipcode zipcode = zipcodeMapper.toDomain(
            zipcodeRepository.findById(createSettlementDto.getZipcodeId())
                .orElseThrow(() -> new EntityNotFoundException("Código postal no encontrado con ID: " + createSettlementDto.getZipcodeId()))
        );
        
        SettlementType settlementType = settlementTypeMapper.toDomain(
            settlementTypeRepository.findById(createSettlementDto.getSettlementTypeId())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de asentamiento no encontrado con ID: " + createSettlementDto.getSettlementTypeId()))
        );
        
        Municipality municipality = municipalityMapper.toDomain(
            municipalityRepository.findById(createSettlementDto.getMunicipalityId())
                .orElseThrow(() -> new EntityNotFoundException("Municipio no encontrado con ID: " + createSettlementDto.getMunicipalityId()))
        );
        
        Town town = null;
        if (createSettlementDto.getTownId() != null) {
            town = townMapper.toDomain(
                townRepository.findById(createSettlementDto.getTownId())
                    .orElseThrow(() -> new EntityNotFoundException("Ciudad no encontrada con ID: " + createSettlementDto.getTownId()))
            );
        }
        
        Settlement settlement = new Settlement.Builder()
                .nombre(createSettlementDto.getNombre())
                .codigoPostal(zipcode)
                .tipoAsentamiento(settlementType)
                .municipio(municipality)
                .ciudad(town)
                .build();
        
        Settlement savedSettlement = settlementRepository.save(settlement);
        return toResponseDto(savedSettlement);
    }
    
    @Override
    @Transactional(readOnly = true)
    public SettlementResponseDto findById(Long id) {
        Settlement settlement = settlementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Asentamiento no encontrado con ID: " + id));
        return toResponseDto(settlement);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<SettlementResponseDto> findAll() {
        return settlementRepository.findAll().stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<SettlementResponseDto> findAll(Pageable pageable) {
        return settlementJpaRepository.findAll(pageable)
                .map(entity -> {
                    Settlement settlement = settlementMapper.toDomain(entity);
                    return toResponseDto(settlement);
                });
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<SettlementResponseDto> search(String search, Pageable pageable) {
        if (search == null || search.trim().isEmpty()) {
            return settlementJpaRepository.findAll(pageable)
                    .map(entity -> {
                        Settlement settlement = settlementMapper.toDomain(entity);
                        return toResponseDto(settlement);
                    });
        }
        return settlementJpaRepository.search(search.trim(), pageable)
                .map(entity -> {
                    Settlement settlement = settlementMapper.toDomain(entity);
                    return toResponseDto(settlement);
                });
    }
    
    @Override
    @Transactional
    public SettlementResponseDto update(Long id, UpdateSettlementDto updateSettlementDto) {
        Settlement existingSettlement = settlementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Asentamiento no encontrado con ID: " + id));
        
        Settlement.Builder builder = new Settlement.Builder()
                .id(existingSettlement.getId())
                .nombre(updateSettlementDto.getNombre() != null ? updateSettlementDto.getNombre() : existingSettlement.getNombre());
        
        // Actualizar código postal si se proporciona
        if (updateSettlementDto.getZipcodeId() != null) {
            Zipcode zipcode = zipcodeMapper.toDomain(
                zipcodeRepository.findById(updateSettlementDto.getZipcodeId())
                    .orElseThrow(() -> new EntityNotFoundException("Código postal no encontrado con ID: " + updateSettlementDto.getZipcodeId()))
            );
            builder.codigoPostal(zipcode);
        } else {
            builder.codigoPostal(existingSettlement.getCodigoPostal());
        }
        
        // Actualizar tipo de asentamiento si se proporciona
        if (updateSettlementDto.getSettlementTypeId() != null) {
            SettlementType settlementType = settlementTypeMapper.toDomain(
                settlementTypeRepository.findById(updateSettlementDto.getSettlementTypeId())
                    .orElseThrow(() -> new EntityNotFoundException("Tipo de asentamiento no encontrado con ID: " + updateSettlementDto.getSettlementTypeId()))
            );
            builder.tipoAsentamiento(settlementType);
        } else {
            builder.tipoAsentamiento(existingSettlement.getTipoAsentamiento());
        }
        
        // Actualizar municipio si se proporciona
        if (updateSettlementDto.getMunicipalityId() != null) {
            Municipality municipality = municipalityMapper.toDomain(
                municipalityRepository.findById(updateSettlementDto.getMunicipalityId())
                    .orElseThrow(() -> new EntityNotFoundException("Municipio no encontrado con ID: " + updateSettlementDto.getMunicipalityId()))
            );
            builder.municipio(municipality);
        } else {
            builder.municipio(existingSettlement.getMunicipio());
        }
        
        // Actualizar ciudad si se proporciona
        if (updateSettlementDto.getTownId() != null) {
            Town town = townMapper.toDomain(
                townRepository.findById(updateSettlementDto.getTownId())
                    .orElseThrow(() -> new EntityNotFoundException("Ciudad no encontrada con ID: " + updateSettlementDto.getTownId()))
            );
            builder.ciudad(town);
        } else {
            builder.ciudad(existingSettlement.getCiudad());
        }
        
        Settlement updatedSettlement = settlementRepository.save(builder.build());
        return toResponseDto(updatedSettlement);
    }
    
    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!settlementRepository.existsById(id)) {
            throw new EntityNotFoundException("Asentamiento no encontrado con ID: " + id);
        }
        settlementRepository.deleteById(id);
    }
    
    private SettlementResponseDto toResponseDto(Settlement settlement) {
        SettlementResponseDto dto = new SettlementResponseDto();
        dto.setId(settlement.getId());
        dto.setNombre(settlement.getNombre());
        
        if (settlement.getCodigoPostal() != null) {
            dto.setZipcodeId(settlement.getCodigoPostal().getId());
            dto.setZipcode(settlement.getCodigoPostal().getCodigo());
        }
        
        if (settlement.getTipoAsentamiento() != null) {
            dto.setSettlementTypeId(settlement.getTipoAsentamiento().getId());
            dto.setSettlementTypeName(settlement.getTipoAsentamiento().getNombre());
        }
        
        if (settlement.getMunicipio() != null) {
            dto.setMunicipalityId(settlement.getMunicipio().getId());
            dto.setMunicipalityName(settlement.getMunicipio().getNombre());
        }
        
        if (settlement.getCiudad() != null) {
            dto.setTownId(settlement.getCiudad().getId());
            dto.setTownName(settlement.getCiudad().getNombre());
        }
        
        return dto;
    }
}

