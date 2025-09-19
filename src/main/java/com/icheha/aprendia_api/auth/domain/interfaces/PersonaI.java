package com.icheha.aprendia_api.auth.domain.interfaces;

import com.icheha.aprendia_api.auth.domain.enums.GenderEnum;
import java.time.LocalDate;
import java.util.List;

/**
 * Interface de dominio para Persona
 * Representa la información personal de un usuario del sistema
 */
public interface PersonaI {
    
    Long getIdPersona();
    void setIdPersona(Long idPersona);
    
    String getPrimerNombre();
    void setPrimerNombre(String primerNombre);
    
    String getSegundoNombre();
    void setSegundoNombre(String segundoNombre);
    
    String getApellidoPaterno();
    void setApellidoPaterno(String apellidoPaterno);
    
    String getApellidoMaterno();
    void setApellidoMaterno(String apellidoMaterno);
    
    String getCurp();
    void setCurp(String curp);
    
    String getNumeroIne();
    void setNumeroIne(String numeroIne);
    
    LocalDate getFechaNacimiento();
    void setFechaNacimiento(LocalDate fechaNacimiento);
    
    GenderEnum getGenero();
    void setGenero(GenderEnum genero);
    
    String getCodigoPostal();
    void setCodigoPostal(String codigoPostal);
    
    String getEstado();
    void setEstado(String estado);
    
    String getMunicipio();
    void setMunicipio(String municipio);
    
    String getLocalidad();
    void setLocalidad(String localidad);
    
    String getVialidadNombre();
    void setVialidadNombre(String vialidadNombre);
    
    Integer getIdVialidadTipo();
    void setIdVialidadTipo(Integer idVialidadTipo);
    
    String getAsentamiento();
    void setAsentamiento(String asentamiento);
    
    Integer getIdAsentamientoTipo();
    void setIdAsentamientoTipo(Integer idAsentamientoTipo);
    
    String getPassword();
    void setPassword(String password);
    
    List<? extends PersonaRolI> getPersonaRoles();
    void setPersonaRoles(List<? extends PersonaRolI> personaRoles);
}
