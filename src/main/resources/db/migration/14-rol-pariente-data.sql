-- =====================================================
-- DATOS: ROLES DE PARIENTE
-- IMPORTANTE: Este archivo solo inserta datos
-- Las tablas se crean automáticamente con ddl-auto: create-drop
-- Este archivo debe ejecutarse DESPUÉS de:
-- - 11-user-table.sql (usuarios)
-- =====================================================

-- Insertar roles básicos de pariente
-- Nota: Las tablas se crean automáticamente por JPA/Hibernate
INSERT INTO rol_pariente (id_rol_pariente, nombre)
VALUES
    (1, 'Padre'),
    (2, 'Madre'),
    (3, 'Tutor'),
    (4, 'Abuelo'),
    (5, 'Abuela'),
    (6, 'Hermano'),
    (7, 'Hermana')
ON CONFLICT (id_rol_pariente) DO NOTHING;

