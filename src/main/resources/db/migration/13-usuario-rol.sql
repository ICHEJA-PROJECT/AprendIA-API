-- =====================================================
-- INSERTAR ROLES PARA USUARIOS
-- NOTA: La tabla "usuario_rol" se crea automáticamente por Hibernate/JPA
-- Esta migración inserta los roles para los usuarios existentes
-- =====================================================

-- Insertar roles para usuarios iniciales (ID 1-4)
-- Usuario ID 1 (Persona ID 1 - Fernando) -> Rol 4 (Estudiante)
-- Usuario ID 2 (Persona ID 2 - María) -> Rol 1 (Educador)
-- Usuario ID 3 (Persona ID 3 - Carlos) -> Rol 1 (Educador)
-- Usuario ID 4 (Persona ID 4 - Ana) -> Rol 4 (Estudiante)
INSERT INTO usuario_rol (id_user, id_rol)
SELECT u.id_user, r.id_rol
FROM usuario u
CROSS JOIN rol r
WHERE (u.id_persona = 1 AND r.id_rol = 4)  -- Fernando es Estudiante
   OR (u.id_persona = 2 AND r.id_rol = 1)  -- María es Educador
   OR (u.id_persona = 3 AND r.id_rol = 1)  -- Carlos es Educador
   OR (u.id_persona = 4 AND r.id_rol = 4); -- Ana es Estudiante

-- Insertar roles para usuarios adicionales (ID 5-11)
-- Usuario ID 5 (Persona ID 5 - Roberto) -> Rol 3 (Coordinador)
-- Usuario ID 6 (Persona ID 6 - Laura) -> Rol 1 (Educador)
-- Usuario ID 7 (Persona ID 7 - Juan) -> Rol 1 (Educador)
-- Usuario ID 8 (Persona ID 8 - Patricia) -> Rol 1 (Educador)
-- Usuario ID 9 (Persona ID 9 - Miguel) -> Rol 4 (Estudiante)
-- Usuario ID 10 (Persona ID 10 - Carmen) -> Rol 4 (Estudiante)
-- Usuario ID 11 (Persona ID 11 - Diego) -> Rol 4 (Estudiante)
INSERT INTO usuario_rol (id_user, id_rol)
SELECT u.id_user, r.id_rol
FROM usuario u
CROSS JOIN rol r
WHERE (u.id_persona = 5 AND r.id_rol = 3)   -- Roberto es Coordinador
   OR (u.id_persona = 6 AND r.id_rol = 1)   -- Laura es Educador
   OR (u.id_persona = 7 AND r.id_rol = 1)   -- Juan es Educador
   OR (u.id_persona = 8 AND r.id_rol = 1)   -- Patricia es Educador
   OR (u.id_persona = 9 AND r.id_rol = 4)   -- Miguel es Estudiante
   OR (u.id_persona = 10 AND r.id_rol = 4)  -- Carmen es Estudiante
   OR (u.id_persona = 11 AND r.id_rol = 4); -- Diego es Estudiante

-- Sincronizar secuencia de usuario_rol (si es necesario)
SELECT setval(pg_get_serial_sequence('usuario_rol', 'id_usuario_rol'), COALESCE((SELECT MAX(id_usuario_rol) FROM usuario_rol), 1), true);

