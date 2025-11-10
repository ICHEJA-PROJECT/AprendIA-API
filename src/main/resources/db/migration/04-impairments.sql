-- =====================================================
-- DATOS PARA DISCAPACIDADES (Impairments)
-- =====================================================

INSERT INTO discapacidad (id_discapacidad, nombre) 
VALUES 
    (1, 'Ninguna'),
    (2, 'Auditiva'),
    (3, 'Visual'),
    (4, 'Motriz'),
    (5, 'Intelectual'),
    (6, 'Psicosocial'),
    (7, 'Múltiple'),
    (8, 'Lenguaje');

INSERT INTO ruta_aprendizaje_discapacidades (id_ruta_aprendizaje, id_discapacidad)
VALUES 
    (1, 2),
    (2, 1),
    (3, 1),
    (3, 2);

INSERT INTO recurso_discapacidades (id_recurso, id_discapacidad)
VALUES (1, 2), (2, 2), (3, 2), (4, 2);

INSERT INTO reactivo_discapacidades (id_reactivo, id_discapacidad)
VALUES (1, 1), (1, 2), (2, 1), (2, 2), (3, 1), (3, 2), (4, 2), (5, 2), (6, 2), (7, 2), (8, 2), (9, 2), (10, 2);

