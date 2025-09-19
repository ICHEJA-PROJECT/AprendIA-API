-- =====================================================
-- MIGRACIÓN DE BASE DE DATOS - SERVICIO DE EJERCICIOS
-- =====================================================
-- Script SQL para crear todas las tablas del sistema
-- Arquitectura: Hexagonal con TypeORM
-- Base de datos: PostgreSQL

-- =====================================================
-- 1. TABLAS MAESTRAS (Sin dependencias)
-- =====================================================

-- Tabla: Tipo de Layouts
CREATE TABLE tipo_layouts (
    id_tipo_layout SERIAL PRIMARY KEY,
    nombre VARCHAR(64) NOT NULL
);

-- Tabla: Habilidades
CREATE TABLE habilidad (
    id_habilidad SERIAL PRIMARY KEY,
    nombre VARCHAR(64) NOT NULL
);

-- Tabla: Tipo de Instrucción Media
CREATE TABLE tipo_instruccion_media (
    id_tipo_media SERIAL PRIMARY KEY,
    nombre VARCHAR(32) NOT NULL
);

-- Tabla: Ruta de Aprendizaje
CREATE TABLE ruta_aprendizaje (
    id_ruta_aprendizaje SERIAL PRIMARY KEY,
    nombre VARCHAR(64) NOT NULL
);

-- =====================================================
-- 2. TABLAS PRINCIPALES (Con dependencias básicas)
-- =====================================================

-- Tabla: Layouts
CREATE TABLE layout (
    id_layout SERIAL PRIMARY KEY,
    nombre VARCHAR(64) NOT NULL,
    atributos JSONB NOT NULL,
    id_tipo_layout INTEGER NOT NULL,
    FOREIGN KEY (id_tipo_layout) REFERENCES tipo_layouts(id_tipo_layout)
);

-- Tabla: Temas
CREATE TABLE tema (
    id_tema SERIAL PRIMARY KEY,
    nombre VARCHAR(64) NOT NULL
);

-- =====================================================
-- 3. TABLAS DE RECURSOS
-- =====================================================

-- Tabla: Recursos
CREATE TABLE recurso (
    id_recurso SERIAL PRIMARY KEY,
    titulo VARCHAR(64) NOT NULL,
    contenido JSONB NOT NULL,
    id_layout INTEGER NOT NULL,
    FOREIGN KEY (id_layout) REFERENCES layout(id_layout)
);

-- Tabla de relación: Tema - Recursos (Many-to-Many)
CREATE TABLE tema_recursos (
    id_tema INTEGER NOT NULL,
    id_recurso INTEGER NOT NULL,
    PRIMARY KEY (id_tema, id_recurso),
    FOREIGN KEY (id_tema) REFERENCES tema(id_tema),
    FOREIGN KEY (id_recurso) REFERENCES recurso(id_recurso)
);

-- =====================================================
-- 4. TABLAS DE TEMPLATES
-- =====================================================

-- Tabla: Templates (Reactivos)
CREATE TABLE reactivo (
    id_reactivo SERIAL PRIMARY KEY,
    titulo VARCHAR(64) NOT NULL,
    instrucciones TEXT NOT NULL,
    tiempo_sugerido TIME WITHOUT TIME ZONE NOT NULL,
    id_tema INTEGER NOT NULL,
    id_layout INTEGER NOT NULL,
    FOREIGN KEY (id_tema) REFERENCES tema(id_tema),
    FOREIGN KEY (id_layout) REFERENCES layout(id_layout)
);

-- Tabla de relación: Template - Habilidades
CREATE TABLE reactivo_habilidades (
    id_reactivo INTEGER NOT NULL,
    id_habilidad INTEGER NOT NULL,
    porcentaje FLOAT NOT NULL,
    bandera BOOLEAN NOT NULL,
    PRIMARY KEY (id_reactivo, id_habilidad),
    FOREIGN KEY (id_reactivo) REFERENCES reactivo(id_reactivo),
    FOREIGN KEY (id_habilidad) REFERENCES habilidad(id_habilidad)
);

-- Tabla de relación: Template - Instrucción Media
CREATE TABLE reactivo_instruccion_media (
    id_reactivo INTEGER NOT NULL,
    id_tipo_media INTEGER NOT NULL,
    ruta_media VARCHAR(254) NOT NULL,
    PRIMARY KEY (id_reactivo, id_tipo_media),
    FOREIGN KEY (id_reactivo) REFERENCES reactivo(id_reactivo),
    FOREIGN KEY (id_tipo_media) REFERENCES tipo_instruccion_media(id_tipo_media)
);

-- =====================================================
-- 5. TABLAS DE EJERCICIOS
-- =====================================================

-- Tabla: Ejercicios
CREATE TABLE ejercicio (
    id_ejercicio SERIAL PRIMARY KEY,
    contexto JSONB NOT NULL,
    id_reactivo INTEGER NOT NULL,
    FOREIGN KEY (id_reactivo) REFERENCES reactivo(id_reactivo)
);

-- =====================================================
-- 6. TABLAS DE SECUENCIAS
-- =====================================================

-- Tabla: Secuencia de Temas
CREATE TABLE secuencia_temas (
    id_tema INTEGER NOT NULL,
    id_tema_siguiente INTEGER NOT NULL,
    id_ruta_aprendizaje INTEGER NOT NULL,
    PRIMARY KEY (id_tema, id_tema_siguiente),
    FOREIGN KEY (id_tema) REFERENCES tema(id_tema),
    FOREIGN KEY (id_tema_siguiente) REFERENCES tema(id_tema),
    FOREIGN KEY (id_ruta_aprendizaje) REFERENCES ruta_aprendizaje(id_ruta_aprendizaje)
);

-- =====================================================
-- 7. ÍNDICES PARA OPTIMIZACIÓN
-- =====================================================

-- Índices en campos de búsqueda frecuente
CREATE INDEX idx_layout_tipo ON layout(id_tipo_layout);
CREATE INDEX idx_recurso_layout ON recurso(id_layout);
CREATE INDEX idx_reactivo_tema ON reactivo(id_tema);
CREATE INDEX idx_reactivo_layout ON reactivo(id_layout);
CREATE INDEX idx_ejercicio_reactivo ON ejercicio(id_reactivo);
CREATE INDEX idx_secuencia_tema ON secuencia_temas(id_tema);
CREATE INDEX idx_secuencia_siguiente ON secuencia_temas(id_tema_siguiente);
CREATE INDEX idx_secuencia_ruta ON secuencia_temas(id_ruta_aprendizaje);

-- Índices en campos JSONB para consultas eficientes
CREATE INDEX idx_layout_atributos ON layout USING GIN (atributos);
CREATE INDEX idx_recurso_contenido ON recurso USING GIN (contenido);
CREATE INDEX idx_ejercicio_contexto ON ejercicio USING GIN (contexto);

-- =====================================================
-- 8. COMENTARIOS EN TABLAS Y COLUMNAS
-- =====================================================

COMMENT ON TABLE tipo_layouts IS 'Catálogo de tipos de layouts disponibles';
COMMENT ON TABLE layout IS 'Layouts para presentación de recursos y templates';
COMMENT ON TABLE habilidad IS 'Habilidades que se pueden evaluar en los ejercicios';
COMMENT ON TABLE tipo_instruccion_media IS 'Tipos de medios de instrucción (imagen, video, audio, etc.)';
COMMENT ON TABLE ruta_aprendizaje IS 'Rutas de aprendizaje estructuradas';
COMMENT ON TABLE tema IS 'Temas o materias de estudio';
COMMENT ON TABLE recurso IS 'Recursos educativos con contenido';
COMMENT ON TABLE reactivo IS 'Templates de ejercicios reutilizables';
COMMENT ON TABLE ejercicio IS 'Ejercicios específicos basados en templates';
COMMENT ON TABLE secuencia_temas IS 'Secuencia de temas en rutas de aprendizaje';

-- Comentarios en columnas importantes
COMMENT ON COLUMN layout.atributos IS 'Atributos de configuración del layout en formato JSON';
COMMENT ON COLUMN recurso.contenido IS 'Contenido del recurso en formato JSON';
COMMENT ON COLUMN ejercicio.contexto IS 'Contexto específico del ejercicio en formato JSON';
COMMENT ON COLUMN reactivo_habilidades.porcentaje IS 'Porcentaje de la habilidad en el template';
COMMENT ON COLUMN reactivo_habilidades.bandera IS 'Indicador booleano de la habilidad';

-- =====================================================
-- 9. DATOS INICIALES (OPCIONAL)
-- =====================================================

-- Insertar tipos de layout básicos
INSERT INTO tipo_layouts (nombre) VALUES 
('Pregunta Simple'),
('Pregunta Múltiple'),
('Verdadero/Falso'),
('Completar Espacios'),
('Emparejar'),
('Ordenar Secuencias');

-- Insertar tipos de media básicos
INSERT INTO tipo_instruccion_media (nombre) VALUES 
('Imagen'),
('Video'),
('Audio'),
('Documento'),
('Animación'),
('Interactivo');

-- Insertar habilidades básicas
INSERT INTO habilidad (nombre) VALUES 
('Comprensión Lectora'),
('Análisis Crítico'),
('Resolución de Problemas'),
('Memorización'),
('Aplicación Práctica'),
('Síntesis'),
('Evaluación');

-- =====================================================
-- FIN DE LA MIGRACIÓN
-- =====================================================

-- Verificar que todas las tablas se crearon correctamente
SELECT 
    schemaname,
    tablename,
    tableowner
FROM pg_tables 
WHERE schemaname = 'public' 
ORDER BY tablename;
