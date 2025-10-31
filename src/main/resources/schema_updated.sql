-- AprendIA API - Esquema de Base de Datos Actualizado
-- Esquema que coincide con las entidades JPA

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
-- TABLAS DE UNIDADES Y TEMAS
-- ==============================================

-- Tabla de unidades
CREATE TABLE IF NOT EXISTS unidad (
    id_unidad SERIAL PRIMARY KEY,
    nombre VARCHAR(64) NOT NULL,
    descripcion TEXT NOT NULL
);

-- Tabla de temas
CREATE TABLE IF NOT EXISTS tema (
    id_tema SERIAL PRIMARY KEY,
    nombre VARCHAR(64) NOT NULL,
    id_unidad INTEGER NOT NULL REFERENCES unidad(id_unidad) ON DELETE CASCADE
);

-- Tabla de secuencias de temas
CREATE TABLE IF NOT EXISTS secuencia_temas (
    id_secuencia_temas SERIAL PRIMARY KEY,
    id_tema INTEGER NOT NULL REFERENCES tema(id_tema) ON DELETE CASCADE,
    id_tema_siguiente INTEGER NOT NULL REFERENCES tema(id_tema) ON DELETE CASCADE
);

-- ==============================================
-- TABLAS DE LAYOUTS
-- ==============================================

-- Tabla de tipos de layout
CREATE TABLE IF NOT EXISTS tipo_layouts (
    id_tipo_layout SERIAL PRIMARY KEY,
    nombre VARCHAR(64) NOT NULL
);

-- Tabla de layouts
CREATE TABLE IF NOT EXISTS layout (
    id_layout SERIAL PRIMARY KEY,
    nombre VARCHAR(64) NOT NULL,
    atributos JSONB NOT NULL,
    id_tipo_layout INTEGER NOT NULL REFERENCES tipo_layouts(id_tipo_layout) ON DELETE CASCADE
);

-- ==============================================
-- TABLAS DE RECURSOS
-- ==============================================

-- Tabla de recursos
CREATE TABLE IF NOT EXISTS recurso (
    id_recurso SERIAL PRIMARY KEY,
    titulo VARCHAR(64) NOT NULL,
    contenido JSONB NOT NULL,
    id_layout INTEGER NOT NULL REFERENCES layout(id_layout) ON DELETE CASCADE
);

-- Tabla de relación tema-recurso
CREATE TABLE IF NOT EXISTS tema_recursos (
    id_tema INTEGER NOT NULL REFERENCES tema(id_tema) ON DELETE CASCADE,
    id_recurso INTEGER NOT NULL REFERENCES recurso(id_recurso) ON DELETE CASCADE,
    PRIMARY KEY (id_tema, id_recurso)
);

-- ==============================================
-- TABLAS DE PLANTILLAS Y EJERCICIOS
-- ==============================================

-- Tabla de plantillas (templates)
CREATE TABLE IF NOT EXISTS plantilla (
    id_plantilla SERIAL PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    instrucciones TEXT NOT NULL,
    tiempo_sugerido VARCHAR(10) NOT NULL,
    id_layout INTEGER NOT NULL REFERENCES layout(id_layout) ON DELETE CASCADE,
    id_tema INTEGER NOT NULL REFERENCES tema(id_tema) ON DELETE CASCADE
);

-- Tabla de ejercicios
CREATE TABLE IF NOT EXISTS ejercicio (
    id_ejercicio SERIAL PRIMARY KEY,
    contexto JSONB NOT NULL,
    id_plantilla INTEGER NOT NULL REFERENCES plantilla(id_plantilla) ON DELETE CASCADE
);

-- ==============================================
-- TABLAS DE HABILIDADES
-- ==============================================

-- Tabla de habilidades
CREATE TABLE IF NOT EXISTS habilidad (
    id_habilidad SERIAL PRIMARY KEY,
    nombre VARCHAR(64) NOT NULL
);

-- Tabla de relación plantilla-habilidad
CREATE TABLE IF NOT EXISTS reactivo_habilidades (
    id_reactivo INTEGER NOT NULL REFERENCES plantilla(id_plantilla) ON DELETE CASCADE,
    id_habilidad INTEGER NOT NULL REFERENCES habilidad(id_habilidad) ON DELETE CASCADE,
    porcentaje FLOAT NOT NULL,
    bandera BOOLEAN NOT NULL,
    PRIMARY KEY (id_reactivo, id_habilidad)
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

-- Tabla de ocupaciones
CREATE TABLE IF NOT EXISTS ocupacion (
    id_ocupacion SERIAL PRIMARY KEY,
    nombre VARCHAR(32) NOT NULL
);

-- Tabla de relación estudiante-ocupación
CREATE TABLE IF NOT EXISTS estudiante_ocupacion (
    id_estudiante INTEGER NOT NULL REFERENCES persona(id_persona) ON DELETE CASCADE,
    id_ocupacion INTEGER NOT NULL REFERENCES ocupacion(id_ocupacion) ON DELETE CASCADE,
    PRIMARY KEY (id_estudiante, id_ocupacion)
);

-- Tabla de regiones
CREATE TABLE IF NOT EXISTS region (
    id_region SERIAL PRIMARY KEY,
    nombre VARCHAR(64) NOT NULL
);

-- Tabla de relación estudiante-región
CREATE TABLE IF NOT EXISTS estudiante_region (
    id_estudiante INTEGER NOT NULL REFERENCES persona(id_persona) ON DELETE CASCADE,
    id_region INTEGER NOT NULL REFERENCES region(id_region) ON DELETE CASCADE,
    PRIMARY KEY (id_estudiante, id_region)
);

-- Tabla de palabras
CREATE TABLE IF NOT EXISTS palabra (
    id_palabra SERIAL PRIMARY KEY,
    palabra VARCHAR(64) NOT NULL
);

-- Tabla de significados de palabras
CREATE TABLE IF NOT EXISTS palabra_significado (
    id_palabra_significado SERIAL PRIMARY KEY,
    id_palabra INTEGER NOT NULL REFERENCES palabra(id_palabra) ON DELETE CASCADE,
    significado VARCHAR(255) NOT NULL
);

-- Tabla de relación palabra-ocupación
CREATE TABLE IF NOT EXISTS palabra_ocupacion (
    id_palabra INTEGER NOT NULL REFERENCES palabra(id_palabra) ON DELETE CASCADE,
    id_ocupacion INTEGER NOT NULL REFERENCES ocupacion(id_ocupacion) ON DELETE CASCADE,
    PRIMARY KEY (id_palabra, id_ocupacion)
);

-- Tabla de relación palabra-región
CREATE TABLE IF NOT EXISTS palabra_region (
    id_palabra INTEGER NOT NULL REFERENCES palabra(id_palabra) ON DELETE CASCADE,
    id_region INTEGER NOT NULL REFERENCES region(id_region) ON DELETE CASCADE,
    PRIMARY KEY (id_palabra, id_region)
);

-- ==============================================
-- TABLAS DE REGISTROS
-- ==============================================

-- Tabla de ejercicios de alumnos
CREATE TABLE IF NOT EXISTS educando_ejercicios (
    id_educando_ejercicio SERIAL PRIMARY KEY,
    id_educando INTEGER NOT NULL REFERENCES persona(id_persona) ON DELETE CASCADE,
    id_ejercicio INTEGER NOT NULL REFERENCES ejercicio(id_ejercicio) ON DELETE CASCADE,
    puntuacion DECIMAL(5,2),
    tiempo_resolucion INTEGER,
    fecha_realizacion TIMESTAMP,
    completado BOOLEAN NOT NULL DEFAULT FALSE,
    intentos INTEGER NOT NULL DEFAULT 0
);

-- Tabla de habilidades de alumnos
CREATE TABLE IF NOT EXISTS educando_ejercicio_habilidades (
    id SERIAL PRIMARY KEY,
    id_educando_ejercicio INTEGER NOT NULL REFERENCES educando_ejercicios(id_educando_ejercicio) ON DELETE CASCADE,
    id_habilidad INTEGER NOT NULL REFERENCES habilidad(id_habilidad) ON DELETE CASCADE,
    puntaje DECIMAL(5,2) NOT NULL
);

-- ==============================================
-- ÍNDICES PARA OPTIMIZACIÓN
-- ==============================================

-- Índices para autenticación
CREATE INDEX IF NOT EXISTS idx_persona_curp ON persona(curp);
CREATE INDEX IF NOT EXISTS idx_persona_rol_persona ON persona_rol(id_persona);
CREATE INDEX IF NOT EXISTS idx_persona_rol_rol ON persona_rol(id_rol);

-- Índices para ejercicios
CREATE INDEX IF NOT EXISTS idx_ejercicio_plantilla ON ejercicio(id_plantilla);
CREATE INDEX IF NOT EXISTS idx_reactivo_habilidades_reactivo ON reactivo_habilidades(id_reactivo);
CREATE INDEX IF NOT EXISTS idx_reactivo_habilidades_habilidad ON reactivo_habilidades(id_habilidad);

-- Índices para registros
CREATE INDEX IF NOT EXISTS idx_educando_ejercicios_educando ON educando_ejercicios(id_educando);
CREATE INDEX IF NOT EXISTS idx_educando_ejercicios_ejercicio ON educando_ejercicios(id_ejercicio);
CREATE INDEX IF NOT EXISTS idx_educando_ejercicio_habilidades_educando_ejercicio ON educando_ejercicio_habilidades(id_educando_ejercicio);
CREATE INDEX IF NOT EXISTS idx_educando_ejercicio_habilidades_habilidad ON educando_ejercicio_habilidades(id_habilidad);

-- ==============================================
-- DATOS INICIALES
-- ==============================================

-- Insertar roles básicos
INSERT INTO rol (nombre, descripcion) VALUES 
('ADMINISTRADOR', 'Administrador del sistema'),
('PROFESOR', 'Profesor/Instructor'),
('ESTUDIANTE', 'Estudiante/Alumno')
ON CONFLICT (nombre) DO NOTHING;

-- Insertar tipos de layout básicos
INSERT INTO tipo_layouts (nombre) VALUES 
('recurso'), 
('reactivo')
ON CONFLICT DO NOTHING;

-- Insertar habilidades básicas
INSERT INTO habilidad (nombre) VALUES 
('Grafomotricidad'), 
('Memoria'), 
('Asociación'), 
('Identificación'), 
('Comprensión')
ON CONFLICT DO NOTHING;

-- Insertar discapacidades básicas
INSERT INTO discapacidad (nombre) VALUES 
('Ninguna'), 
('Auditiva'),
('Visual'),
('Motora'),
('Cognitiva'),
('Multiple')
ON CONFLICT DO NOTHING;
