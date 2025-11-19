-- =====================================================
-- Hacer el campo password nullable en la tabla usuario
-- Esto permite crear usuarios sin password inicial
-- =====================================================

ALTER TABLE usuario ALTER COLUMN password DROP NOT NULL;

