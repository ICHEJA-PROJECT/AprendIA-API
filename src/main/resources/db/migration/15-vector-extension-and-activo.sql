-- =====================================================
-- ÍNDICES Y DOCUMENTACIÓN PARA TABLA ACTIVO
-- IMPORTANTE: La extensión vector se crea en 00-vector-extension.sql
-- La tabla activo se crea automáticamente por Hibernate (ddl-auto: create-drop)
-- Este script solo crea los índices y comentarios que Hibernate no crea automáticamente
-- =====================================================

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

