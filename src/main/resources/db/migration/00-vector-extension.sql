-- =====================================================
-- EXTENSIÓN VECTOR DE POSTGRESQL
-- IMPORTANTE: Este script debe ejecutarse ANTES de que Hibernate
-- cree las tablas que usan el tipo vector
-- =====================================================

-- Crear la extensión vector si no existe
-- Esta extensión permite almacenar y buscar vectores de embeddings
-- Debe ejecutarse ANTES de crear cualquier tabla con tipo vector
CREATE EXTENSION IF NOT EXISTS vector;

