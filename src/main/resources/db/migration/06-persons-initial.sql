-- =====================================================
-- PERSONAS INICIALES (ID 1-4) Y SUS ROLES
-- =====================================================

INSERT INTO persona (id_persona, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, curp, numero_ine, fecha_nacimiento, genero, password, created_at, update_at)
VALUES
    (1, 'Fernando', 'Emiliano', 'Flores', 'De la Riva', 'FORF040807HCSLVRA8', '2364956377', '2004-08-07', 'M', '$2a$10$UCfkwoD89wngZbtACKqVbueESnw4ORgQA/p6QnbjUE0nmQLHdRN8m', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 'María', 'Elena', 'Hernández', 'Martínez', 'HEMM900215MCHRRL02', '2345678901234', '1990-02-15', 'F', '$2a$10$UCfkwoD89wngZbtACKqVbueESnw4ORgQA/p6QnbjUE0nmQLHdRN8m', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3, 'Carlos', 'Alberto', 'Pérez', 'Sánchez', 'PESC880312HCHRRL03', '3456789012345', '1988-03-12', 'M', '$2a$10$UCfkwoD89wngZbtACKqVbueESnw4ORgQA/p6QnbjUE0nmQLHdRN8m', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (4, 'Ana', 'Patricia', 'Rodríguez', 'González', 'ROGA920420MCHRDN04', '4567890123456', '1992-04-20', 'F', '$2a$10$UCfkwoD89wngZbtACKqVbueESnw4ORgQA/p6QnbjUE0nmQLHdRN8m', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO persona_rol (id_persona_rol, id_persona, id_rol)
VALUES
    (1, 1, 4),  -- Fernando Flores es Estudiante
    (2, 2, 1),  -- María Elena es Educador
    (3, 3, 1),  -- Carlos Alberto es Educador
    (4, 4, 4);  -- Ana Patricia es Estudiante

