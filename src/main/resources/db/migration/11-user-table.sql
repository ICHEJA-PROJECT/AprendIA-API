-- =====================================================
-- INSERTAR USUARIOS CON PASSWORD
-- NOTA: La tabla "usuario" se crea automáticamente por Hibernate/JPA
-- Esta migración inserta los usuarios para las personas existentes
-- =====================================================

-- Insertar usuarios para personas iniciales (ID 1-4)
-- Password: $2a$10$UCfkwoD89wngZbtACKqVbueESnw4ORgQA/p6QnbjUE0nmQLHdRN8m
INSERT INTO usuario (id_persona, username, password, is_active, created_at, updated_at)
VALUES
    (1, 'FORF040807HCSLVRA8', '$2a$10$UCfkwoD89wngZbtACKqVbueESnw4ORgQA/p6QnbjUE0nmQLHdRN8m', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 'HEMM900215MCHRRL02', '$2a$10$UCfkwoD89wngZbtACKqVbueESnw4ORgQA/p6QnbjUE0nmQLHdRN8m', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3, 'PESC880312HCHRRL03', '$2a$10$UCfkwoD89wngZbtACKqVbueESnw4ORgQA/p6QnbjUE0nmQLHdRN8m', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (4, 'ROGA920420MCHRDN04', '$2a$10$UCfkwoD89wngZbtACKqVbueESnw4ORgQA/p6QnbjUE0nmQLHdRN8m', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insertar usuarios para personas adicionales (ID 5-11)
-- Password para ID 5-10: $2a$10$wv963y/KNW.S/QZZQbGoAuaVjlT3twwfhW28orveib1uMpoMCQLse
-- Password para ID 11: $2a$10$UCfkwoD89wngZbtACKqVbueESnw4ORgQA/p6QnbjUE0nmQLHdRN8m
INSERT INTO usuario (id_persona, username, password, is_active, created_at, updated_at)
VALUES
    (5, 'MOJR750505HCHRRS05', '$2a$10$wv963y/KNW.S/QZZQbGoAuaVjlT3twwfhW28orveib1uMpoMCQLse', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (6, 'TOVL820606MCHRRL06', '$2a$10$wv963y/KNW.S/QZZQbGoAuaVjlT3twwfhW28orveib1uMpoMCQLse', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (7, 'RADJ880707HCHRRL07', '$2a$10$wv963y/KNW.S/QZZQbGoAuaVjlT3twwfhW28orveib1uMpoMCQLse', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (8, 'GOCP900808MCHRRL08', '$2a$10$wv963y/KNW.S/QZZQbGoAuaVjlT3twwfhW28orveib1uMpoMCQLse', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (9, 'LORM920909HCHRRL09', '$2a$10$wv963y/KNW.S/QZZQbGoAuaVjlT3twwfhW28orveib1uMpoMCQLse', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (10, 'MAOC851010MCHRRL10', '$2a$10$wv963y/KNW.S/QZZQbGoAuaVjlT3twwfhW28orveib1uMpoMCQLse', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (11, 'LORD051111HCHRRL11', '$2a$10$UCfkwoD89wngZbtACKqVbueESnw4ORgQA/p6QnbjUE0nmQLHdRN8m', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Sincronizar secuencia de usuario (si es necesario)
SELECT setval(pg_get_serial_sequence('usuario', 'id_user'), COALESCE((SELECT MAX(id_user) FROM usuario), 1), true);

