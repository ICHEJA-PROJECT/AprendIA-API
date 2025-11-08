package com.icheha.aprendia_api.users.person.data.mappers;

import com.icheha.aprendia_api.users.person.data.entities.SettlementEntity;
import com.icheha.aprendia_api.users.person.domain.entities.Settlement;
import com.icheha.aprendia_api.users.person.domain.entities.Zipcode;
import com.icheha.aprendia_api.users.person.domain.entities.SettlementType;
import com.icheha.aprendia_api.users.person.domain.entities.Municipality;
import com.icheha.aprendia_api.users.person.domain.entities.Town;
import com.icheha.aprendia_api.users.person.domain.entities.State;
import org.springframework.stereotype.Component;

@Component
public class SettlementMapper {
    
    private final ZipcodeMapper zipcodeMapper;
    private final SettlementTypeMapper settlementTypeMapper;
    private final MunicipalityMapper municipalityMapper;
    private final TownMapper townMapper;
    private final StateMapper stateMapper;
    
    public SettlementMapper(ZipcodeMapper zipcodeMapper, 
                           SettlementTypeMapper settlementTypeMapper,
                           MunicipalityMapper municipalityMapper,
                           TownMapper townMapper,
                           StateMapper stateMapper) {
        this.zipcodeMapper = zipcodeMapper;
        this.settlementTypeMapper = settlementTypeMapper;
        this.municipalityMapper = municipalityMapper;
        this.townMapper = townMapper;
        this.stateMapper = stateMapper;
    }
    
    // Método helper para evitar dependencia circular en MunicipalityMapper
    public Settlement toDomainWithoutMunicipality(SettlementEntity entity) {
        if (entity == null) return null;
        
        return new Settlement.Builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .codigoPostal(zipcodeMapper.toDomain(entity.getZipcode()))
                .tipoAsentamiento(settlementTypeMapper.toDomain(entity.getSettlementType()))
                .municipio(null) // Se establecerá desde fuera
                .ciudad(townMapper.toDomain(entity.getCiudad()))
                .build();
    }
    
    public Settlement toDomain(SettlementEntity entity) {
        if (entity == null) return null;
        
        // Cargar relaciones de forma segura evitando ciclos infinitos
        // Acceder solo a campos básicos directamente sin usar mappers que puedan causar ciclos
        Municipality municipio = null;
        if (entity.getMunicipio() != null) {
            try {
                com.icheha.aprendia_api.users.person.data.entities.MunicipalityEntity municipioEntity = entity.getMunicipio();
                Long municipioId = municipioEntity.getId();
                String municipioNombre = municipioEntity.getNombre();
                
                // Cargar estado directamente sin usar mapper para evitar ciclos
                State estado = null;
                try {
                    com.icheha.aprendia_api.users.person.data.entities.StateEntity estadoEntity = municipioEntity.getEstado();
                    if (estadoEntity != null) {
                        estado = new State.Builder()
                                .id(estadoEntity.getId())
                                .nombre(estadoEntity.getNombre())
                                .build();
                    }
                } catch (Exception e) {
                    estado = null;
                }
                
                municipio = new Municipality.Builder()
                        .id(municipioId)
                        .nombre(municipioNombre)
                        .estado(estado)
                        .build();
            } catch (Exception e) {
                municipio = null;
            }
        }
        
        // Cargar ciudad sin municipio para evitar ciclo
        Town ciudad = null;
        if (entity.getCiudad() != null) {
            try {
                com.icheha.aprendia_api.users.person.data.entities.TownEntity ciudadEntity = entity.getCiudad();
                ciudad = new Town.Builder()
                        .id(ciudadEntity.getId())
                        .nombre(ciudadEntity.getNombre())
                        .municipio(null) // No cargar municipio para evitar ciclo
                        .build();
            } catch (Exception e) {
                ciudad = null;
            }
        }
        
        // Cargar zipcode y settlementType directamente sin usar mappers para evitar cualquier acceso a relaciones
        Zipcode codigoPostal = null;
        try {
            com.icheha.aprendia_api.users.person.data.entities.ZipcodeEntity zipcodeEntity = entity.getZipcode();
            if (zipcodeEntity != null) {
                codigoPostal = new Zipcode.Builder()
                        .id(zipcodeEntity.getId())
                        .codigo(zipcodeEntity.getCodigo())
                        .build();
            }
        } catch (Exception e) {
            codigoPostal = null;
        }
        
        SettlementType tipoAsentamiento = null;
        try {
            com.icheha.aprendia_api.users.person.data.entities.SettlementTypeEntity settlementTypeEntity = entity.getSettlementType();
            if (settlementTypeEntity != null) {
                tipoAsentamiento = new SettlementType.Builder()
                        .id(settlementTypeEntity.getId())
                        .nombre(settlementTypeEntity.getNombre())
                        .build();
            }
        } catch (Exception e) {
            tipoAsentamiento = null;
        }
        
        return new Settlement.Builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .codigoPostal(codigoPostal)
                .tipoAsentamiento(tipoAsentamiento)
                .municipio(municipio)
                .ciudad(ciudad)
                .build();
    }
    
    public SettlementEntity toEntity(Settlement domain) {
        if (domain == null) return null;
        
        SettlementEntity entity = new SettlementEntity();
        entity.setId(domain.getId());
        entity.setNombre(domain.getNombre());
        entity.setZipcode(zipcodeMapper.toEntity(domain.getCodigoPostal()));
        entity.setSettlementType(settlementTypeMapper.toEntity(domain.getTipoAsentamiento()));
        entity.setMunicipio(municipalityMapper.toEntity(domain.getMunicipio()));
        entity.setCiudad(townMapper.toEntity(domain.getCiudad()));
        return entity;
    }
}

