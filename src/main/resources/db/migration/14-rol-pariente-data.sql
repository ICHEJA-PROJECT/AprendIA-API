-- =====================================================
-- DATOS: ROLES DE PARIENTE
-- IMPORTANTE: Este archivo solo inserta datos
-- Las tablas se crean automáticamente con ddl-auto: create-drop
-- Este archivo debe ejecutarse DESPUÉS de:
-- - 11-user-table.sql (usuarios)
-- =====================================================

-- Insertar roles de pariente
-- Nota: Las tablas se crean automáticamente por JPA/Hibernate
INSERT INTO rol_pariente (id_rol_pariente, nombre)
VALUES
    (1, 'Padre'),
    (2, 'Madre'),
    (3, 'Tutor'),
    (4, 'Abuelo'),
    (5, 'Abuela'),
    (6, 'Hermano'),
    (7, 'Hermana'),
    (8, 'Ahijada(o)'),
    (9, 'Bisabuela(o)'),
    (10, 'Bisnieta(o)'),
    (11, 'Comadre'),
    (12, 'Compadre'),
    (13, 'Concuña(o)'),
    (14, 'Conocida(o)'),
    (15, 'Consuegra(o)'),
    (16, 'Cuñada(o)'),
    (17, 'Entenada(o)'),
    (18, 'Exesposa(o)'),
    (19, 'Hermanastra(o)'),
    (20, 'Hija(o)'),
    (21, 'Hijastra(o)'),
    (22, 'Madrastra'),
    (23, 'Madrina'),
    (24, 'Esposo'),
    (25, 'Esposa'),
    (26, 'Nieta(o)'),
    (27, 'Novia(o)'),
    (28, 'Nuera'),
    (29, 'Padrastro'),
    (30, 'Padrino'),
    (31, 'Prima(o)'),
    (32, 'Sobrina(o)'),
    (33, 'Suegra(o)'),
    (34, 'Tatarabuela(o)'),
    (35, 'Tataranieta(o)'),
    (36, 'Tia(o)'),
    (37, 'Yerno')
ON CONFLICT (id_rol_pariente) DO NOTHING;

