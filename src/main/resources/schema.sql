-- AprendIA API - Esquema de Base de Datos Consolidado
-- Migración de microservicios NestJS a Spring Boot

-- ==============================================
-- TABLAS DE AUTENTICACIÓN
-- ==============================================

-- Tabla de roles
CREATE TABLE IF NOT EXISTS rol (
    id_rol SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    descripcion VARCHAR(255)
);

-- Tabla de personas (usuarios)
CREATE TABLE IF NOT EXISTS persona (
    id_persona SERIAL PRIMARY KEY,
    primer_nombre VARCHAR(32) NOT NULL,
    segundo_nombre VARCHAR(32),
    apellido_paterno VARCHAR(32) NOT NULL,
    apellido_materno VARCHAR(32) NOT NULL,
    curp VARCHAR(18) NOT NULL UNIQUE,
    numero_ine VARCHAR(13) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    genero VARCHAR(20) NOT NULL CHECK (genero IN ('MASCULINO', 'FEMENINO', 'OTRO')),
    codigo_postal VARCHAR(5) NOT NULL,
    estado VARCHAR(100) NOT NULL,
    municipio VARCHAR(100) NOT NULL,
    localidad VARCHAR(100) NOT NULL,
    vialidad_nombre VARCHAR(100) NOT NULL,
    id_vialidad_tipo INTEGER NOT NULL,
    asentamiento VARCHAR(100) NOT NULL,
    id_asentamiento_tipo INTEGER NOT NULL,
    password VARCHAR(254) NOT NULL
);

-- Tabla de relación persona-rol
CREATE TABLE IF NOT EXISTS persona_rol (
    id_persona_rol SERIAL PRIMARY KEY,
    id_persona INTEGER NOT NULL REFERENCES persona(id_persona) ON DELETE CASCADE,
    id_rol INTEGER NOT NULL REFERENCES rol(id_rol) ON DELETE CASCADE
);

-- ==============================================
-- TABLAS DE EJERCICIOS
-- ==============================================

-- Tabla de habilidades
CREATE TABLE IF NOT EXISTS habilidad (
    id_habilidad SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(500),
    activo BOOLEAN NOT NULL DEFAULT TRUE
);

-- Tabla de tipos de instrucción media
CREATE TABLE IF NOT EXISTS tipo_instruccion_media (
    id_tipo_instruccion_media SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255),
    activo BOOLEAN NOT NULL DEFAULT TRUE
);

-- Tabla de plantillas (templates)
CREATE TABLE IF NOT EXISTS plantilla (
    id_plantilla SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(500),
    instrucciones TEXT,
    activo BOOLEAN NOT NULL DEFAULT TRUE
);

-- Tabla de ejercicios
CREATE TABLE IF NOT EXISTS ejercicio (
    id_ejercicio SERIAL PRIMARY KEY,
    contexto JSONB NOT NULL,
    id_reactivo INTEGER NOT NULL REFERENCES plantilla(id_plantilla) ON DELETE CASCADE
);

-- Tabla de relación plantilla-habilidad
CREATE TABLE IF NOT EXISTS plantilla_habilidad (
    id_plantilla_habilidad SERIAL PRIMARY KEY,
    id_plantilla INTEGER NOT NULL REFERENCES plantilla(id_plantilla) ON DELETE CASCADE,
    id_habilidad INTEGER NOT NULL REFERENCES habilidad(id_habilidad) ON DELETE CASCADE,
    porcentaje DECIMAL(5,2) NOT NULL
);

-- Tabla de relación plantilla-instrucción media
CREATE TABLE IF NOT EXISTS plantilla_instruccion_media (
    id_plantilla_instruccion_media SERIAL PRIMARY KEY,
    id_plantilla INTEGER NOT NULL REFERENCES plantilla(id_plantilla) ON DELETE CASCADE,
    id_tipo_instruccion_media INTEGER NOT NULL REFERENCES tipo_instruccion_media(id_tipo_instruccion_media) ON DELETE CASCADE,
    url VARCHAR(500) NOT NULL,
    descripcion VARCHAR(255)
);

-- ==============================================
-- TABLAS DE PREFERENCIAS
-- ==============================================

-- Tabla de discapacidades
CREATE TABLE IF NOT EXISTS discapacidad (
    id_discapacidad SERIAL PRIMARY KEY,
    nombre VARCHAR(32) NOT NULL
);

-- Tabla de relación estudiante-discapacidad
CREATE TABLE IF NOT EXISTS estudiante_discapacidad (
    id_estudiante_discapacidad SERIAL PRIMARY KEY,
    id_estudiante INTEGER NOT NULL REFERENCES persona(id_persona) ON DELETE CASCADE,
    id_discapacidad INTEGER NOT NULL REFERENCES discapacidad(id_discapacidad) ON DELETE CASCADE,
    descripcion VARCHAR(500)
);

-- Tabla de relación reactivo-discapacidad
CREATE TABLE IF NOT EXISTS reactivo_discapacidad (
    id_reactivo_discapacidad SERIAL PRIMARY KEY,
    id_reactivo INTEGER NOT NULL REFERENCES plantilla(id_plantilla) ON DELETE CASCADE,
    id_discapacidad INTEGER NOT NULL REFERENCES discapacidad(id_discapacidad) ON DELETE CASCADE
);

-- ==============================================
-- TABLAS DE REGISTROS
-- ==============================================

-- Tabla de ejercicios de alumnos
CREATE TABLE IF NOT EXISTS alumno_ejercicio (
    id_alumno_ejercicio SERIAL PRIMARY KEY,
    id_alumno INTEGER NOT NULL REFERENCES persona(id_persona) ON DELETE CASCADE,
    id_ejercicio INTEGER NOT NULL REFERENCES ejercicio(id_ejercicio) ON DELETE CASCADE,
    puntuacion DECIMAL(5,2),
    tiempo_resolucion INTEGER,
    fecha_realizacion TIMESTAMP,
    completado BOOLEAN NOT NULL DEFAULT FALSE,
    intentos INTEGER NOT NULL DEFAULT 0
);

-- Tabla de habilidades de alumnos
CREATE TABLE IF NOT EXISTS alumno_habilidad (
    id_alumno_habilidad SERIAL PRIMARY KEY,
    id_alumno INTEGER NOT NULL REFERENCES persona(id_persona) ON DELETE CASCADE,
    id_habilidad INTEGER NOT NULL REFERENCES habilidad(id_habilidad) ON DELETE CASCADE,
    puntuacion DECIMAL(5,2) NOT NULL,
    porcentaje DECIMAL(5,2) NOT NULL,
    nivel INTEGER NOT NULL
);

-- ==============================================
-- ÍNDICES PARA OPTIMIZACIÓN
-- ==============================================

-- Índices para autenticación
CREATE INDEX IF NOT EXISTS idx_persona_curp ON persona(curp);
CREATE INDEX IF NOT EXISTS idx_persona_rol_persona ON persona_rol(id_persona);
CREATE INDEX IF NOT EXISTS idx_persona_rol_rol ON persona_rol(id_rol);

-- Índices para ejercicios
CREATE INDEX IF NOT EXISTS idx_ejercicio_reactivo ON ejercicio(id_reactivo);
CREATE INDEX IF NOT EXISTS idx_plantilla_habilidad_plantilla ON plantilla_habilidad(id_plantilla);
CREATE INDEX IF NOT EXISTS idx_plantilla_habilidad_habilidad ON plantilla_habilidad(id_habilidad);

-- Índices para registros
CREATE INDEX IF NOT EXISTS idx_alumno_ejercicio_alumno ON alumno_ejercicio(id_alumno);
CREATE INDEX IF NOT EXISTS idx_alumno_ejercicio_ejercicio ON alumno_ejercicio(id_ejercicio);
CREATE INDEX IF NOT EXISTS idx_alumno_habilidad_alumno ON alumno_habilidad(id_alumno);
CREATE INDEX IF NOT EXISTS idx_alumno_habilidad_habilidad ON alumno_habilidad(id_habilidad);

-- ==============================================
-- DATOS INICIALES
-- ==============================================

-- Insertar roles básicos
INSERT INTO rol (nombre, descripcion) VALUES 
('ADMINISTRADOR', 'Administrador del sistema'),
('PROFESOR', 'Profesor/Instructor'),
('ESTUDIANTE', 'Estudiante/Alumno')
ON CONFLICT (nombre) DO NOTHING;

-- Insertar tipos de instrucción media
INSERT INTO tipo_instruccion_media (nombre, descripcion) VALUES 
('IMAGEN', 'Imagen estática'),
('VIDEO', 'Video'),
('AUDIO', 'Audio'),
('ANIMACION', 'Animación'),
('INTERACTIVO', 'Contenido interactivo')
ON CONFLICT DO NOTHING;

-- Insertar habilidades básicas
INSERT INTO habilidad (nombre, descripcion) VALUES 
('LECTURA', 'Habilidad de lectura'),
('ESCRITURA', 'Habilidad de escritura'),
('MATEMATICAS', 'Habilidad matemática'),
('LOGICA', 'Habilidad lógica'),
('MEMORIA', 'Habilidad de memoria')
ON CONFLICT DO NOTHING;

-- Insertar discapacidades básicas
INSERT INTO discapacidad (nombre) VALUES 
('VISUAL', 'Discapacidad visual'),
('AUDITIVA', 'Discapacidad auditiva'),
('MOTORA', 'Discapacidad motora'),
('COGNITIVA', 'Discapacidad cognitiva'),
('MULTIPLE', 'Discapacidad múltiple')
ON CONFLICT DO NOTHING;
