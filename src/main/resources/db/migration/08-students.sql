-- =====================================================
-- ESTUDIANTES Y RELACIONES ESTUDIANTE-DISCAPACIDAD
-- IMPORTANTE: Este archivo debe ejecutarse DESPUÉS de:
-- - 01-basic-data.sql (roles, temas)
-- - 04-impairments.sql (discapacidades)
-- - 05-locations.sql (estados, municipios)
-- - 06-persons-initial.sql (personas iniciales)
-- - 07-persons-additional.sql (personas adicionales)
-- =====================================================

-- Progenitores (padres y madres)
INSERT INTO progenitor (id_progenitor, curp, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido)
VALUES
    (1, 'GALF850101HCHRPS01', 'Fernando', 'José', 'García', 'López'),      -- Padre del estudiante 1
    (2, 'HEMM900215MCHRRL02', 'María', 'Elena', 'Hernández', 'Martínez'),  -- Madre del estudiante 1
    (3, 'PESC880312HCHRRL03', 'Carlos', 'Alberto', 'Pérez', 'Sánchez'),    -- Padre del estudiante 2
    (4, 'ROGA920420MCHRDN04', 'Ana', 'Patricia', 'Rodríguez', 'González'), -- Madre del estudiante 2
    (5, 'MOJR750505HCHRRS05', 'Roberto', 'Antonio', 'Morales', 'Jiménez'),  -- Padre del estudiante 3
    (6, 'TOVL820606MCHRRL06', 'Laura', 'Isabel', 'Torres', 'Vargas'),      -- Madre del estudiante 3
    (7, 'RADJ880707HCHRRL07', 'Juan', 'Carlos', 'Ramírez', 'Díaz'),        -- Padre del estudiante 4
    (8, 'GOCP900808MCHRRL08', 'Patricia', 'Sofía', 'Gómez', 'Castro'),     -- Madre del estudiante 4
    (9, 'LORM920909HCHRRL09', 'Miguel', 'Ángel', 'López', 'Ruiz'),         -- Padre del estudiante 5
    (10, 'MAOC851010MCHRRL10', 'Carmen', 'Rosa', 'Martínez', 'Ortega'),    -- Madre del estudiante 5
    (11, 'LOPR751212HCHRRL11', 'Pedro', 'Luis', 'López', 'Ruiz'),         -- Padre del estudiante 5 (adicional)
    (12, 'MART801212MCHRRL12', 'Rosa', 'María', 'Martínez', 'Ortega');     -- Madre del estudiante 5 (adicional)

-- Insertar estudiantes (educandos)
-- Nota: Los estudiantes usan las personas con ID 1, 4, 9, 10, 11
INSERT INTO educando (id_educando, id_persona, id_educador, qr_ruta, id_padre, id_madre)
VALUES
    (1, 1, 2, 'https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581287/ICHEJA/ICHEJA/qr_estudiante_1.png', 1, 2),   -- Fernando (persona 1) con educador María (persona 2)
    (2, 4, 3, 'https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581287/ICHEJA/ICHEJA/qr_estudiante_2.png', 3, 4),   -- Ana (persona 4) con educador Carlos (persona 3)
    (3, 9, 6, 'https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581287/ICHEJA/ICHEJA/qr_estudiante_3.png', 5, 6),   -- Miguel (persona 9) con educador Laura (persona 6)
    (4, 10, 7, 'https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581287/ICHEJA/ICHEJA/qr_estudiante_4.png', 7, 8),  -- Carmen (persona 10) con educador Juan (persona 7)
    (5, 11, 8, 'https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581287/ICHEJA/ICHEJA/qr_estudiante_5.png', 11, 12); -- Diego (persona 11) con educador Patricia (persona 8)

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

