-- =====================================================
-- DATOS BÁSICOS: ROLES
-- NOTA: Los temas se insertan después de crear rutas de aprendizaje y cuadernillos
-- =====================================================

INSERT INTO rol (id_rol, nombre, descripcion)
VALUES
    (1, 'Educador', 'Rol para educadores que pueden crear y asignar ejercicios'),
    (2, 'Administrador', 'Rol para administradores del sistema'),
    (3, 'Coordinador', 'Rol para coordinadores que supervisan el proceso educativo'),
    (4, 'Estudiante', 'Rol para estudiantes que realizan ejercicios');

