-- =====================================================
-- ESTUDIANTES Y RELACIONES ESTUDIANTE-DISCAPACIDAD
-- IMPORTANTE: Este archivo debe ejecutarse DESPUÉS de:
-- - 01-basic-data.sql (roles, temas)
-- - 04-impairments.sql (discapacidades)
-- - 05-locations.sql (estados, municipios)
-- - 06-persons-initial.sql (personas iniciales)
-- - 07-persons-additional.sql (personas adicionales)
-- - 14-rol-pariente-data.sql (roles de pariente - DEBE ejecutarse ANTES)
-- NOTA: Las relaciones de parientes se crean en 16-migrate-progenitor-to-pariente.sql
-- =====================================================

-- Insertar estudiantes (educandos)
-- Nota: Los estudiantes usan las personas con ID 1, 4, 9, 10, 11
-- Las relaciones con parientes (padre/madre) se manejan en la tabla pariente
INSERT INTO educando (id_educando, id_persona, id_educador, qr_ruta)
VALUES
    (1, 1, 2, 'https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581287/ICHEJA/ICHEJA/qr_estudiante_1.png'),   -- Fernando (persona 1) con educador María (persona 2)
    (2, 4, 3, 'https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581287/ICHEJA/ICHEJA/qr_estudiante_2.png'),   -- Ana (persona 4) con educador Carlos (persona 3)
    (3, 9, 6, 'https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581287/ICHEJA/ICHEJA/qr_estudiante_3.png'),   -- Miguel (persona 9) con educador Laura (persona 6)
    (4, 10, 7, 'https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581287/ICHEJA/ICHEJA/qr_estudiante_4.png'),  -- Carmen (persona 10) con educador Juan (persona 7)
    (5, 11, 8, 'https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581287/ICHEJA/ICHEJA/qr_estudiante_5.png'); -- Diego (persona 11) con educador Patricia (persona 8)

-- =====================================================
-- RELACIONES ESTUDIANTE-DISCAPACIDAD
-- IMPORTANTE: Debe ir DESPUÉS de insertar los estudiantes
-- =====================================================
INSERT INTO educando_discapacidades (id_educando, id_discapacidad) 
VALUES 
    (1, 2),  -- Fernando: Discapacidad Auditiva
    (2, 3),  -- Ana: Discapacidad Visual
    (3, 4),  -- Miguel: Discapacidad Motriz
    (4, 5),  -- Carmen: Discapacidad Intelectual
    (5, 2),  -- Diego: Discapacidad Auditiva
    (2, 8),  -- Ana: También tiene Discapacidad de Lenguaje
    (3, 1);  -- Miguel: También tiene Ninguna (caso especial)

-- Relaciones estudiante-temas
INSERT INTO educando_temas (id, id_educando, id_tema)
VALUES (1, 1, 1), (2, 1, 2);

