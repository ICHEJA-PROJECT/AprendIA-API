-- =====================================================
-- INSTITUCIONES Y CÉLULAS EDUCATIVAS
-- IMPORTANTE: Requiere personas adicionales (07-persons-additional.sql)
-- =====================================================

INSERT INTO institucion (id_institucion, rfc, rct, nombre)
VALUES
    (1, 'ICHEJA001234', 'RCT-2024-001', 'Instituto Chiapaneco de Educación para Jóvenes y Adultos - Sede Central'),
    (2, 'ICHEJA005678', 'RCT-2024-002', 'ICHEJA - Sede San Cristóbal'),
    (3, 'ICHEJA009012', 'RCT-2024-003', 'ICHEJA - Sede Tapachula'),
    (4, 'ICHEJA003456', 'RCT-2024-004', 'ICHEJA - Sede Palenque'),
    (5, 'ICHEJA007890', 'RCT-2024-005', 'ICHEJA - Sede Comitán')
ON CONFLICT (id_institucion) DO NOTHING;

INSERT INTO celula (id_celula, id_institucion, id_persona, fecha_inicio, fecha_final)
VALUES
    (1, 1, 5, '2024-01-15 08:00:00', '2024-12-20 18:00:00'),  -- Célula 1 en Institución 1, coordinada por Roberto (ID 5)
    (2, 1, 5, '2024-02-01 08:00:00', '2024-12-20 18:00:00'),  -- Célula 2 en Institución 1, coordinada por Roberto (ID 5)
    (3, 2, 5, '2024-03-01 08:00:00', '2024-12-20 18:00:00'),  -- Célula 3 en Institución 2, coordinada por Roberto (ID 5)
    (4, 3, 5, '2024-04-01 08:00:00', '2024-12-20 18:00:00'),  -- Célula 4 en Institución 3, coordinada por Roberto (ID 5)
    (5, 4, 5, '2024-05-01 08:00:00', '2024-12-20 18:00:00');  -- Célula 5 en Institución 4, coordinada por Roberto (ID 5)

INSERT INTO celula_educador (id_persona, id_celula)
VALUES
    (2, 1),  -- María Elena (Educador ID 2) asignada a Célula 1
    (3, 1),  -- Carlos Alberto (Educador ID 3) asignado a Célula 1
    (6, 2),  -- Laura Isabel (Educador ID 6) asignada a Célula 2
    (7, 2),  -- Juan Carlos (Educador ID 7) asignado a Célula 2
    (8, 3),  -- Patricia Sofía (Educador ID 8) asignada a Célula 3
    (2, 3),  -- María Elena también asignada a Célula 3
    (3, 4),  -- Carlos Alberto asignado a Célula 4
    (6, 4),  -- Laura Isabel también asignada a Célula 4
    (7, 5),  -- Juan Carlos asignado a Célula 5
    (8, 5);  -- Patricia Sofía también asignada a Célula 5

