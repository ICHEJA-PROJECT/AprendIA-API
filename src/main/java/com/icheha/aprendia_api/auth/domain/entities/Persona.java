package com.icheha.aprendia_api.auth.domain.entities;

import com.icheha.aprendia_api.auth.domain.enums.GenderEnum;
import com.icheha.aprendia_api.auth.domain.valueobjects.Curp;
import com.icheha.aprendia_api.auth.domain.valueobjects.Password;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Entidad de dominio Persona
 * Representa la información personal de un usuario del sistema
 * Contiene la lógica de negocio relacionada con una persona
 */
public class Persona {
    
    private final Long idPersona;
    private final String primerNombre;
    private final String segundoNombre;
    private final String apellidoPaterno;
    private final String apellidoMaterno;
    private final Curp curp;
    private final String numeroIne;
    private final LocalDate fechaNacimiento;
    private final GenderEnum genero;
    private final String codigoPostal;
    private final String estado;
    private final String municipio;
    private final String localidad;
    private final String vialidadNombre;
    private final Integer idVialidadTipo;
    private final String asentamiento;
    private final Integer idAsentamientoTipo;
    private final String email;
    private final String telefono;
    private final Password password;
    private final List<PersonaRol> personaRoles;
    
    // Constructor privado para usar Builder
    private Persona(Builder builder) {
        this.idPersona = builder.idPersona;
        this.primerNombre = builder.primerNombre;
        this.segundoNombre = builder.segundoNombre;
        this.apellidoPaterno = builder.apellidoPaterno;
        this.apellidoMaterno = builder.apellidoMaterno;
        this.curp = builder.curp;
        this.numeroIne = builder.numeroIne;
        this.fechaNacimiento = builder.fechaNacimiento;
        this.genero = builder.genero;
        this.codigoPostal = builder.codigoPostal;
        this.estado = builder.estado;
        this.municipio = builder.municipio;
        this.localidad = builder.localidad;
        this.vialidadNombre = builder.vialidadNombre;
        this.idVialidadTipo = builder.idVialidadTipo;
        this.asentamiento = builder.asentamiento;
        this.idAsentamientoTipo = builder.idAsentamientoTipo;
        this.email = builder.email;
        this.telefono = builder.telefono;
        this.password = builder.password;
        this.personaRoles = builder.personaRoles;
    }
    
    // Getters
    public Long getIdPersona() { return idPersona; }
    public String getPrimerNombre() { return primerNombre; }
    public String getSegundoNombre() { return segundoNombre; }
    public String getApellidoPaterno() { return apellidoPaterno; }
    public String getApellidoMaterno() { return apellidoMaterno; }
    public Curp getCurp() { return curp; }
    public String getNumeroIne() { return numeroIne; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public GenderEnum getGenero() { return genero; }
    public String getCodigoPostal() { return codigoPostal; }
    public String getEstado() { return estado; }
    public String getMunicipio() { return municipio; }
    public String getLocalidad() { return localidad; }
    public String getVialidadNombre() { return vialidadNombre; }
    public Integer getIdVialidadTipo() { return idVialidadTipo; }
    public String getAsentamiento() { return asentamiento; }
    public Integer getIdAsentamientoTipo() { return idAsentamientoTipo; }
    public String getEmail() { return email; }
    public String getTelefono() { return telefono; }
    public Password getPassword() { return password; }
    public List<PersonaRol> getPersonaRoles() { return personaRoles; }
    
    /**
     * Obtiene el nombre completo formateado
     */
    public String getNombreCompleto() {
        StringBuilder nombre = new StringBuilder(primerNombre);
        if (segundoNombre != null && !segundoNombre.trim().isEmpty()) {
            nombre.append(" ").append(segundoNombre);
        }
        return nombre.toString();
    }
    
    /**
     * Obtiene el nombre completo con apellidos
     */
    public String getNombreCompletoConApellidos() {
        return getNombreCompleto() + " " + apellidoPaterno + " " + apellidoMaterno;
    }
    
    /**
     * Verifica si la persona tiene un rol específico
     */
    public boolean tieneRol(String nombreRol) {
        return personaRoles != null && 
               personaRoles.stream()
                   .anyMatch(pr -> pr.getRol().getNombre().equals(nombreRol));
    }
    
    /**
     * Obtiene el primer rol de la persona
     */
    public Rol getPrimerRol() {
        if (personaRoles == null || personaRoles.isEmpty()) {
            return null;
        }
        return personaRoles.get(0).getRol();
    }
    
    /**
     * Verifica si la contraseña proporcionada coincide
     */
    public boolean verificarPassword(String passwordPlana) {
        return password != null && password.verificar(passwordPlana);
    }
    
    /**
     * Obtiene el valor del CURP de forma segura
     * @return El valor del CURP o null si no está disponible
     */
    public String getCurpValue() {
        return curp != null ? curp.getValue() : null;
    }
    
    /**
     * Verifica si la persona tiene CURP
     */
    public boolean tieneCurp() {
        return curp != null;
    }
    
    /**
     * Verifica si la persona tiene password
     */
    public boolean tienePassword() {
        return password != null;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(idPersona, persona.idPersona) &&
               Objects.equals(curp, persona.curp);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(idPersona, curp);
    }
    
    @Override
    public String toString() {
        return "Persona{" +
                "idPersona=" + idPersona +
                ", curp=" + curp +
                ", nombreCompleto='" + getNombreCompletoConApellidos() + '\'' +
                '}';
    }
    
    // Builder pattern
    public static class Builder {
        private Long idPersona;
        private String primerNombre;
        private String segundoNombre;
        private String apellidoPaterno;
        private String apellidoMaterno;
        private Curp curp;
        private String numeroIne;
        private LocalDate fechaNacimiento;
        private GenderEnum genero;
        private String codigoPostal;
        private String estado;
        private String municipio;
        private String localidad;
        private String vialidadNombre;
        private Integer idVialidadTipo;
        private String asentamiento;
        private Integer idAsentamientoTipo;
        private String email;
        private String telefono;
        private Password password;
        private List<PersonaRol> personaRoles;
        
        public Builder idPersona(Long idPersona) {
            this.idPersona = idPersona;
            return this;
        }
        
        public Builder primerNombre(String primerNombre) {
            this.primerNombre = primerNombre;
            return this;
        }
        
        public Builder segundoNombre(String segundoNombre) {
            this.segundoNombre = segundoNombre;
            return this;
        }
        
        public Builder apellidoPaterno(String apellidoPaterno) {
            this.apellidoPaterno = apellidoPaterno;
            return this;
        }
        
        public Builder apellidoMaterno(String apellidoMaterno) {
            this.apellidoMaterno = apellidoMaterno;
            return this;
        }
        
        public Builder curp(Curp curp) {
            this.curp = curp;
            return this;
        }
        
        public Builder numeroIne(String numeroIne) {
            this.numeroIne = numeroIne;
            return this;
        }
        
        public Builder fechaNacimiento(LocalDate fechaNacimiento) {
            this.fechaNacimiento = fechaNacimiento;
            return this;
        }
        
        public Builder genero(GenderEnum genero) {
            this.genero = genero;
            return this;
        }
        
        public Builder codigoPostal(String codigoPostal) {
            this.codigoPostal = codigoPostal;
            return this;
        }
        
        public Builder estado(String estado) {
            this.estado = estado;
            return this;
        }
        
        public Builder municipio(String municipio) {
            this.municipio = municipio;
            return this;
        }
        
        public Builder localidad(String localidad) {
            this.localidad = localidad;
            return this;
        }
        
        public Builder vialidadNombre(String vialidadNombre) {
            this.vialidadNombre = vialidadNombre;
            return this;
        }
        
        public Builder idVialidadTipo(Integer idVialidadTipo) {
            this.idVialidadTipo = idVialidadTipo;
            return this;
        }
        
        public Builder asentamiento(String asentamiento) {
            this.asentamiento = asentamiento;
            return this;
        }
        
        public Builder idAsentamientoTipo(Integer idAsentamientoTipo) {
            this.idAsentamientoTipo = idAsentamientoTipo;
            return this;
        }
        
        public Builder email(String email) {
            this.email = email;
            return this;
        }
        
        public Builder telefono(String telefono) {
            this.telefono = telefono;
            return this;
        }
        
        public Builder password(Password password) {
            this.password = password;
            return this;
        }
        
        public Builder personaRoles(List<PersonaRol> personaRoles) {
            this.personaRoles = personaRoles;
            return this;
        }
        
        public Persona build() {
            return new Persona(this);
        }
    }
}

