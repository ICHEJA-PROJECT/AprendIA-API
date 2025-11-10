-- =====================================================
-- DATOS PARA TIPOS DE VIALIDAD (RoadType)
-- =====================================================

INSERT INTO tipo_vialidad (id_tipo_vialidad, nombre)
VALUES 
    (1, 'Calle'),
    (2, 'Avenida'),
    (3, 'Boulevard'),
    (4, 'Callejón'),
    (5, 'Privada'),
    (6, 'Cerrada'),
    (7, 'Pasaje'),
    (8, 'Andador'),
    (9, 'Carretera'),
    (10, 'Camino'),
    (11, 'Vialidad'),
    (12, 'Eje Vial'),
    (13, 'Periférico'),
    (14, 'Circuito'),
    (15, 'Glorieta');

-- =====================================================
-- DATOS PARA ESTADOS, MUNICIPIOS, CIUDADES Y ASENTAMIENTOS
-- =====================================================

-- Estados
INSERT INTO estado (id_estado, nombre)
VALUES 
    (1, 'Chiapas'),
    (2, 'Tabasco'),
    (3, 'Veracruz'),
    (4, 'Oaxaca'),
    (5, 'Yucatán'),
    (6, 'Campeche'),
    (7, 'Quintana Roo'),
    (8, 'Guerrero');

-- Municipios
INSERT INTO municipio (id_municipio, nombre, id_estado)
VALUES 
    (1, 'Tuxtla Gutiérrez', 1),
    (2, 'San Cristóbal de las Casas', 1),
    (3, 'Tapachula', 1),
    (4, 'Villahermosa', 2),
    (5, 'Cárdenas', 2),
    (6, 'Xalapa', 3),
    (7, 'Veracruz', 3),
    (8, 'Oaxaca de Juárez', 4),
    (9, 'Palenque', 1),
    (10, 'Comitán de Domínguez', 1),
    (11, 'Tonalá', 1),
    (12, 'Mérida', 5),
    (13, 'Valladolid', 5),
    (14, 'Campeche', 6),
    (15, 'Ciudad del Carmen', 6),
    (16, 'Cancún', 7),
    (17, 'Playa del Carmen', 7),
    (18, 'Chetumal', 7),
    (19, 'Acapulco de Juárez', 8),
    (20, 'Chilpancingo de los Bravo', 8);

-- Ciudades
INSERT INTO ciudad (id_ciudad, nombre, id_municipio)
VALUES 
    (1, 'Tuxtla Gutiérrez', 1),
    (2, 'San Cristóbal de las Casas', 2),
    (3, 'Tapachula', 3),
    (4, 'Villahermosa', 4),
    (5, 'Cárdenas', 5),
    (6, 'Xalapa', 6),
    (7, 'Veracruz', 7),
    (8, 'Oaxaca de Juárez', 8),
    (9, 'Palenque', 9),
    (10, 'Comitán de Domínguez', 10),
    (11, 'Tonalá', 11),
    (12, 'Mérida', 12),
    (13, 'Valladolid', 13),
    (14, 'Campeche', 14),
    (15, 'Ciudad del Carmen', 15),
    (16, 'Cancún', 16),
    (17, 'Playa del Carmen', 17),
    (18, 'Chetumal', 18),
    (19, 'Acapulco de Juárez', 19),
    (20, 'Chilpancingo de los Bravo', 20);

-- Códigos Postales
INSERT INTO codigo_postal (id_codigo_postal, codigo)
VALUES 
    (1, '29000'),
    (2, '29010'),
    (3, '29020'),
    (4, '29030'),
    (5, '29040'),
    (6, '29200'),
    (7, '29300'),
    (8, '86000'),
    (9, '86010'),
    (10, '91000'),
    (11, '91700'),
    (12, '29960'),
    (13, '30000'),
    (14, '31000'),
    (15, '97000'),
    (16, '97780'),
    (17, '24000'),
    (18, '24100'),
    (19, '77500'),
    (20, '77710'),
    (21, '77000'),
    (22, '39300'),
    (23, '39310');

-- Tipos de Asentamiento
INSERT INTO tipo_asentamiento (id_tipo_asentamiento, nombre)
VALUES 
    (1, 'Colonia'),
    (2, 'Fraccionamiento'),
    (3, 'Unidad Habitacional'),
    (4, 'Barrio'),
    (5, 'Condominio'),
    (6, 'Ejido'),
    (7, 'Ranchería'),
    (8, 'Pueblo'),
    (9, 'Parque Industrial'),
    (10, 'Zona Comercial'),
    (11, 'Residencial');

-- Asentamientos
INSERT INTO asentamiento (id_asentamiento, nombre, id_codigo_postal, id_tipo_asentamiento, id_municipio, id_ciudad)
VALUES 
    (1, 'Centro', 1, 1, 1, 1),
    (2, 'Las Flores', 2, 1, 1, 1),
    (3, 'San José', 3, 2, 1, 1),
    (4, 'El Carmen', 4, 1, 1, 1),
    (5, 'Los Pinos', 5, 2, 1, 1),
    (6, 'Centro Histórico', 6, 4, 2, 2),
    (7, 'La Merced', 7, 1, 3, 3),
    (8, 'Centro', 8, 1, 4, 4),
    (9, 'Zona Industrial', 9, 3, 4, 4),
    (10, 'Centro', 10, 1, 6, 6),
    (11, 'Boca del Río', 11, 1, 7, 7),
    (12, 'Zona Industrial', 12, 9, 1, 1),
    (13, 'Vista Hermosa', 2, 2, 1, 1),
    (14, 'Los Ángeles', 3, 1, 1, 1),
    (15, 'San Juan', 4, 1, 1, 1),
    (16, 'La Esperanza', 5, 2, 1, 1),
    (17, 'Barrio de Guadalupe', 6, 4, 2, 2),
    (18, 'San Francisco', 7, 1, 3, 3),
    (19, 'Zona Centro', 8, 1, 4, 4),
    (20, 'Parque Industrial', 9, 9, 4, 4),
    (21, 'Centro Histórico', 10, 4, 6, 6),
    (22, 'Zona Hotelera', 11, 10, 7, 7),
    (23, 'Centro de Palenque', 12, 1, 9, 9),
    (24, 'Zona Arqueológica', 12, 8, 9, 9),
    (25, 'Centro de Comitán', 13, 1, 10, 10),
    (26, 'Barrio San Caralampio', 13, 4, 10, 10),
    (27, 'Centro de Tonalá', 14, 1, 11, 11),
    (28, 'Zona Portuaria', 14, 10, 11, 11),
    (29, 'Centro Histórico', 15, 4, 12, 12),
    (30, 'Paseo de Montejo', 15, 2, 12, 12),
    (31, 'Centro de Valladolid', 16, 1, 13, 13),
    (32, 'Zona Maya', 16, 8, 13, 13),
    (33, 'Centro Histórico', 17, 4, 14, 14),
    (34, 'Zona Portuaria', 17, 10, 14, 14),
    (35, 'Centro de Ciudad del Carmen', 18, 1, 15, 15),
    (36, 'Zona Petrolera', 18, 9, 15, 15),
    (37, 'Zona Hotelera', 19, 10, 16, 16),
    (38, 'Centro', 19, 1, 16, 16),
    (39, 'Playa Norte', 20, 2, 17, 17),
    (40, 'Centro', 20, 1, 17, 17),
    (41, 'Centro de Chetumal', 21, 1, 18, 18),
    (42, 'Zona Fronteriza', 21, 10, 18, 18),
    (43, 'Zona Dorada', 22, 10, 19, 19),
    (44, 'Centro', 22, 1, 19, 19),
    (45, 'Centro de Chilpancingo', 23, 1, 20, 20),
    (46, 'Zona Universitaria', 23, 1, 20, 20);

-- =====================================================
-- DOMICILIOS PARA LAS PERSONAS (debe ir después de estados y municipios)
