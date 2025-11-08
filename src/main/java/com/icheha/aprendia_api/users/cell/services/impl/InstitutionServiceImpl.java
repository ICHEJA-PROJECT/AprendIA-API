package com.icheha.aprendia_api.users.cell.services.impl;

import com.icheha.aprendia_api.users.cell.data.dtos.CreateInstitutionDto;
import com.icheha.aprendia_api.users.cell.data.dtos.InstitutionResponseDto;
import com.icheha.aprendia_api.users.cell.domain.entities.Institution;
import com.icheha.aprendia_api.users.cell.domain.repositories.IInstitutionRepository;
import com.icheha.aprendia_api.users.cell.services.IInstitutionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InstitutionServiceImpl implements IInstitutionService {
    
    private final IInstitutionRepository institutionRepository;
    
    public InstitutionServiceImpl(IInstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }
    
    @Override
    @Transactional
    public InstitutionResponseDto create(CreateInstitutionDto createInstitutionDto) {
        Institution institution = new Institution.Builder()
                .rfc(createInstitutionDto.getRfc())
                .rct(createInstitutionDto.getRct())
                .name(createInstitutionDto.getName())
                .build();
        
        Institution saved = institutionRepository.create(institution);
        return toResponseDto(saved);
    }
    
    @Override
    public List<InstitutionResponseDto> findAll() {
        return institutionRepository.findAll().stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<InstitutionResponseDto> findById(Long id) {
        return institutionRepository.findById(id)
                .map(this::toResponseDto);
    }
    
    private InstitutionResponseDto toResponseDto(Institution institution) {
        InstitutionResponseDto dto = new InstitutionResponseDto();
        dto.setId(institution.getId());
        dto.setRfc(institution.getRfc());
        dto.setRct(institution.getRct());
        dto.setName(institution.getName());
        return dto;
    }
}

