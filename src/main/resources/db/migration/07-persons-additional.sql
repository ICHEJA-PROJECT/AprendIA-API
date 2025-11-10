-- =====================================================
-- PERSONAS ADICIONALES (ID 5-11) Y SUS ROLES
-- IMPORTANTE: Requiere que existan estados y municipios (05-locations.sql)
-- =====================================================

INSERT INTO persona (id_persona, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, curp, numero_ine, fecha_nacimiento, genero, password, created_at, update_at)
VALUES
    (5, 'Roberto', 'Antonio', 'Morales', 'Jiménez', 'MOJR750505HCHRRS05', '5678901234567', '1975-05-05', 'M', '$2a$10$wv963y/KNW.S/QZZQbGoAuaVjlT3twwfhW28orveib1uMpoMCQLse', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (6, 'Laura', 'Isabel', 'Torres', 'Vargas', 'TOVL820606MCHRRL06', '6789012345678', '1982-06-06', 'F', '$2a$10$wv963y/KNW.S/QZZQbGoAuaVjlT3twwfhW28orveib1uMpoMCQLse', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (7, 'Juan', 'Carlos', 'Ramírez', 'Díaz', 'RADJ880707HCHRRL07', '7890123456789', '1988-07-07', 'M', '$2a$10$wv963y/KNW.S/QZZQbGoAuaVjlT3twwfhW28orveib1uMpoMCQLse', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (8, 'Patricia', 'Sofía', 'Gómez', 'Castro', 'GOCP900808MCHRRL08', '8901234567890', '1990-08-08', 'F', '$2a$10$wv963y/KNW.S/QZZQbGoAuaVjlT3twwfhW28orveib1uMpoMCQLse', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (9, 'Miguel', 'Ángel', 'López', 'Ruiz', 'LORM920909HCHRRL09', '9012345678901', '1992-09-09', 'M', '$2a$10$wv963y/KNW.S/QZZQbGoAuaVjlT3twwfhW28orveib1uMpoMCQLse', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (10, 'Carmen', 'Rosa', 'Martínez', 'Ortega', 'MAOC851010MCHRRL10', '0123456789012', '1985-10-10', 'F', '$2a$10$wv963y/KNW.S/QZZQbGoAuaVjlT3twwfhW28orveib1uMpoMCQLse', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (11, 'Diego', 'Alejandro', 'López', 'Ruiz', 'LORD051111HCHRRL11', '1123456789012', '2005-11-11', 'M', '$2a$10$UCfkwoD89wngZbtACKqVbueESnw4ORgQA/p6QnbjUE0nmQLHdRN8m', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insertar domicilios para estas personas
INSERT INTO domicilio (id_persona, calle, colonia, localidad, id_municipio, id_estado, cp)
VALUES
    (1, 'Versalles', 'Centro', 'Tuxtla Gutiérrez', 1, 1, '29000'),
    (2, 'Insurgentes', 'Las Flores', 'Tuxtla Gutiérrez', 1, 1, '29010'),
    (3, 'Revolución', 'San José', 'Tuxtla Gutiérrez', 1, 1, '29020'),
    (4, 'Hidalgo', 'El Carmen', 'Tuxtla Gutiérrez', 1, 1, '29030'),
    (5, 'Central', 'Centro', 'Tuxtla Gutiérrez', 1, 1, '29000'),
    (6, 'Norte', 'Las Flores', 'Tuxtla Gutiérrez', 1, 1, '29010'),
    (7, 'Sur', 'San José', 'Tuxtla Gutiérrez', 1, 1, '29020'),
    (8, 'Oriente', 'El Carmen', 'Tuxtla Gutiérrez', 1, 1, '29030'),
    (9, 'Poniente', 'Centro', 'Tuxtla Gutiérrez', 1, 1, '29000'),
    (10, 'Norte', 'Las Flores', 'Tuxtla Gutiérrez', 1, 1, '29010'),
    (11, 'Poniente', 'Centro', 'Tuxtla Gutiérrez', 1, 1, '29000');

-- Asignaciones de roles adicionales
INSERT INTO persona_rol (id_persona_rol, id_persona, id_rol)
VALUES
    (5, 5, 3),  -- Roberto es Coordinador
    (6, 6, 1),  -- Laura es Educador
    (7, 7, 1),  -- Juan es Educador
    (8, 8, 1),  -- Patricia es Educador
    (9, 9, 4),  -- Miguel es Estudiante
    (10, 10, 4), -- Carmen es Estudiante
    (11, 11, 4); -- Diego es Estudiante

