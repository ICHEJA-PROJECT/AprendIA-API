-- =====================================================
-- SINCRONIZAR SECUENCIAS DE POSTGRESQL
-- Esto es necesario porque se insertaron IDs manualmente
-- y las secuencias deben actualizarse para evitar conflictos
-- =====================================================

-- Sincronizar secuencia de persona
SELECT setval(pg_get_serial_sequence('persona', 'id_persona'), COALESCE((SELECT MAX(id_persona) FROM persona), 1), true);

-- Sincronizar secuencia de rol
SELECT setval(pg_get_serial_sequence('rol', 'id_rol'), COALESCE((SELECT MAX(id_rol) FROM rol), 1), true);

-- Sincronizar secuencia de persona_rol
SELECT setval(pg_get_serial_sequence('persona_rol', 'id_persona_rol'), COALESCE((SELECT MAX(id_persona_rol) FROM persona_rol), 1), true);

-- Sincronizar secuencia de educando
SELECT setval(pg_get_serial_sequence('educando', 'id_educando'), COALESCE((SELECT MAX(id_educando) FROM educando), 1), true);

-- Sincronizar secuencia de celula (si existe)
SELECT setval(pg_get_serial_sequence('celula', 'id_celula'), COALESCE((SELECT MAX(id_celula) FROM celula), 1), true);

-- Sincronizar secuencia de discapacidad
SELECT setval(pg_get_serial_sequence('discapacidad', 'id_discapacidad'), COALESCE((SELECT MAX(id_discapacidad) FROM discapacidad), 1), true);

