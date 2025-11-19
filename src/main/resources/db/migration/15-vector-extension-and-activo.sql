-- =====================================================
-- EXTENSIÓN VECTOR Y TABLA ACTIVO
-- IMPORTANTE: Esta migración crea la extensión vector de PostgreSQL
-- y la tabla activo con soporte para búsqueda vectorial
-- =====================================================

-- Crear la extensión vector si no existe
-- Esta extensión permite almacenar y buscar vectores de embeddings
CREATE EXTENSION IF NOT EXISTS vector;

-- =====================================================
-- TABLA: ACTIVO
-- Almacena activos (recursos) con embeddings vectoriales
-- =====================================================

-- Crear la tabla activo si no existe
CREATE TABLE IF NOT EXISTS activo (
    id_activo SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    url TEXT NOT NULL,
    descripcion TEXT NOT NULL,
    vector vector(1536) NOT NULL
);

-- Crear índices para mejorar el rendimiento de búsquedas vectoriales
-- Índice HNSW para búsqueda por similitud vectorial (más rápido para búsquedas aproximadas)
CREATE INDEX IF NOT EXISTS activo_vector_idx ON activo 
USING hnsw (vector vector_cosine_ops);

-- Índice adicional para búsquedas por nombre (opcional, pero útil)
CREATE INDEX IF NOT EXISTS activo_name_idx ON activo (name);

-- Comentarios en la tabla y columnas para documentación
COMMENT ON TABLE activo IS 'Tabla que almacena activos (recursos) con embeddings vectoriales para búsqueda semántica';
COMMENT ON COLUMN activo.id_activo IS 'Identificador único del activo';
COMMENT ON COLUMN activo.name IS 'Nombre del activo';
COMMENT ON COLUMN activo.url IS 'URL del recurso activo';
COMMENT ON COLUMN activo.descripcion IS 'Descripción del activo';
COMMENT ON COLUMN activo.vector IS 'Vector de embedding de 1536 dimensiones para búsqueda semántica';

