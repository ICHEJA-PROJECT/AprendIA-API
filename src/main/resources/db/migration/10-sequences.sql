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

-- Sincronizar secuencia de user (se agregará en migración 11-user-table.sql)
-- SELECT setval(pg_get_serial_sequence('"user"', 'id_user'), COALESCE((SELECT MAX(id_user) FROM "user"), 1), true);

-- =====================================================
-- SECUENCIAS DE RUTAS DE APRENDIZAJE Y CONTENIDO
-- Estas son críticas para evitar conflictos de IDs
-- =====================================================

-- Sincronizar secuencia de metodologia
SELECT setval(pg_get_serial_sequence('metodologia', 'id_metodologia'), COALESCE((SELECT MAX(id_metodologia) FROM metodologia), 1), true);

-- Sincronizar secuencia de perfil
SELECT setval(pg_get_serial_sequence('perfil', 'id_perfil'), COALESCE((SELECT MAX(id_perfil) FROM perfil), 1), true);

-- Sincronizar secuencia de ruta_aprendizaje (CRÍTICO - causa el bug)
SELECT setval(pg_get_serial_sequence('ruta_aprendizaje', 'id_ruta'), COALESCE((SELECT MAX(id_ruta) FROM ruta_aprendizaje), 1), true);

-- Sincronizar secuencia de contenido/cuadernillo (CRÍTICO - causa el bug)
SELECT setval(pg_get_serial_sequence('contenido', 'id_cuadernillo'), COALESCE((SELECT MAX(id_cuadernillo) FROM contenido), 1), true);

-- Sincronizar secuencia de unidad
SELECT setval(pg_get_serial_sequence('unidad', 'id_unidad'), COALESCE((SELECT MAX(id_unidad) FROM unidad), 1), true);

-- Sincronizar secuencia de tema
SELECT setval(pg_get_serial_sequence('tema', 'id_tema'), COALESCE((SELECT MAX(id_tema) FROM tema), 1), true);

-- Sincronizar secuencia de tipo_layouts
SELECT setval(pg_get_serial_sequence('tipo_layouts', 'id_tipo_layout'), COALESCE((SELECT MAX(id_tipo_layout) FROM tipo_layouts), 1), true);

-- Sincronizar secuencia de layout
SELECT setval(pg_get_serial_sequence('layout', 'id_layout'), COALESCE((SELECT MAX(id_layout) FROM layout), 1), true);

-- Sincronizar secuencia de recurso
SELECT setval(pg_get_serial_sequence('recurso', 'id_recurso'), COALESCE((SELECT MAX(id_recurso) FROM recurso), 1), true);

-- Sincronizar secuencia de reactivo
SELECT setval(pg_get_serial_sequence('reactivo', 'id_reactivo'), COALESCE((SELECT MAX(id_reactivo) FROM reactivo), 1), true);

-- Sincronizar secuencia de tipo_instruccion_media
SELECT setval(pg_get_serial_sequence('tipo_instruccion_media', 'id_tipo_media'), COALESCE((SELECT MAX(id_tipo_media) FROM tipo_instruccion_media), 1), true);

-- Sincronizar secuencia de habilidad
SELECT setval(pg_get_serial_sequence('habilidad', 'id_agenda'), COALESCE((SELECT MAX(id_agenda) FROM habilidad), 1), true);

