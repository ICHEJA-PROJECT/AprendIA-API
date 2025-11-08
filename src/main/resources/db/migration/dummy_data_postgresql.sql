-- Script de datos dummy para PostgreSQL
-- Este script se ejecuta automáticamente al iniciar la aplicación Spring Boot
-- Configurado en application.yml con spring.sql.init.mode=always

INSERT INTO persona (id_persona, primer_nombre, segundo_nombre, apellido_paterno, apellido_materno, curp, numero_ine, fecha_nacimiento, genero, codigo_postal, estado, municipio, localidad, vialidad_nombre, id_vialidad_tipo, asentamiento, id_asentamiento_tipo, password)
VALUES
    (1, 'Fernando', 'José', 'García', 'López', 'GALF850101HCHRPS01', '1234567890123', '1985-01-01', 'MASCULINO', '29000', 'Chiapas', 'Tuxtla Gutiérrez', 'Tuxtla Gutiérrez', '5 de Mayo', 1, 'Centro', 1, '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy'),
    (2, 'María', 'Elena', 'Hernández', 'Martínez', 'HEMM900215MCHRRL02', '2345678901234', '1990-02-15', 'FEMENINO', '29010', 'Chiapas', 'Tuxtla Gutiérrez', 'Tuxtla Gutiérrez', 'Insurgentes', 2, 'Las Flores', 1, '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy'),
    (3, 'Carlos', 'Alberto', 'Pérez', 'Sánchez', 'PESC880312HCHRRL03', '3456789012345', '1988-03-12', 'MASCULINO', '29020', 'Chiapas', 'Tuxtla Gutiérrez', 'Tuxtla Gutiérrez', 'Revolución', 3, 'San José', 2, '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy'),
    (4, 'Ana', 'Patricia', 'Rodríguez', 'González', 'ROGA920420MCHRDN04', '4567890123456', '1992-04-20', 'FEMENINO', '29030', 'Chiapas', 'Tuxtla Gutiérrez', 'Tuxtla Gutiérrez', 'Hidalgo', 1, 'El Carmen', 1, '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy');

INSERT INTO rol (id_rol, nombre, descripcion)
VALUES
    (1, 'Educador', 'Rol para educadores que pueden crear y asignar ejercicios'),
    (2, 'Administrador', 'Rol para administradores del sistema'),
    (3, 'Coordinador', 'Rol para coordinadores que supervisan el proceso educativo'),
    (4, 'Estudiante', 'Rol para estudiantes que realizan ejercicios');
INSERT INTO persona_rol (id_persona_rol, id_persona, id_rol)
VALUES
    (1, 1, 4),
    (2, 2, 1),
    (3, 3, 1),
    (4, 4, 4);

INSERT INTO tema (id_tema, nombre, id_unidad)
VALUES (1, 'Introducción', NULL),
       (2, 'Caligrafía', NULL),
       (3, 'Abecedario', NULL),
       (4, 'Nombre propio', NULL),
       (5, 'Calendario', NULL);

-- =====================================================
-- DATOS PARA RUTAS DE APRENDIZAJE Y ENTIDADES RELACIONADAS
-- =====================================================

-- Metodologías
INSERT INTO metodologia (id_metodologia, nombre, descripcion, created_at, update_at)
VALUES 
    (1, 'Metodología Visual', 'Enfoque educativo basado en recursos visuales y señas', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 'Metodología Multisensorial', 'Enfoque que integra múltiples sentidos en el aprendizaje', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3, 'Metodología Inclusiva', 'Enfoque diseñado para personas con diferentes discapacidades', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Perfiles
INSERT INTO perfil (id_perfil, nombre, created_at, update_at)
VALUES 
    (1, 'Perfil Básico', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 'Perfil Intermedio', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3, 'Perfil Avanzado', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Rutas de Aprendizaje (actualizado con nueva estructura)
INSERT INTO ruta_aprendizaje (id_ruta, id_metodologia, id_perfil, nombre, descripcion, url_imagen, created_at, update_at)
VALUES 
    (1, 1, 1, 'Alfabetización en personas sordas', 'Ruta de aprendizaje diseñada específicamente para personas con discapacidad auditiva, utilizando recursos visuales y lenguaje de señas', 'https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581287/ICHEJA/ICHEJA/ruta_sordas.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 2, 2, 'Alfabetización Inicial', 'Ruta básica para iniciar el proceso de alfabetización', 'https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581287/ICHEJA/ICHEJA/ruta_inicial.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3, 3, 1, 'Alfabetización Inclusiva', 'Ruta diseñada para personas con diferentes necesidades educativas', 'https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581287/ICHEJA/ICHEJA/ruta_inclusiva.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO secuencia_temas (id_ruta, id_tema, id_tema_siguiente)
VALUES
    (1, 1, 2),
    (1, 1, 3),
    (1, 2, 4),
    (1, 3, 5),
    (1, 4, 5),
    (2, 1, 2),
    (2, 2, 3),
    (3, 1, 3),
    (3, 3, 4);

INSERT INTO tipo_layouts (id_tipo_layout, nombre)
VALUES (1, 'recurso'), (2, 'reactivo');

INSERT INTO layout (id_layout, nombre, atributos, id_tipo_layout)
VALUES
    (1, 'Escritura', '{"image_reference": "", "image_base": ""}', 2),
    (2, 'Columnas de cartas', '{"material": [{"title": "", "subtitle": "","image_path": ""}]}', 1),
    (3, 'Seleccion', '{"image_reference": "", "options_types": "", "options": [], "right_option": 1}', 2),
    (4, 'Memorama con imagenes', '{"content": [{"title": "", "subtitle": "", "path_image":""}]}', 2),
    (5, 'Carrusel', '{"material": [{"title": "", "image_path": ""}]}', 1),
    (6, 'Deletreo', '{"base_sentence": "", "options": []}', 2),
    (7, 'Relacion simple', '{"base_sentence": "", "options": [], "right_option": 1}', 2),
    (8, 'Relacion compleja', '{"content": [{"question": "", "answer": ""}]}', 2),
    (9, 'Lista', '{"material": [{"element": "", "image_path": ""}]}', 1),
    (10, 'Relacion con imagenes', '{"content": [{"element": "", "image_path": ""}]}', 2),
    (11, 'Relacion con enunciados', '{"content": [{"element": "", "image_path": "", "sentence": ""}]}', 2);

INSERT INTO recurso (id_recurso, titulo, contenido, id_layout)
VALUES
    (1, 'Abecedario con señas', '{
    "material": [
                {
            "title": "A",
            "subtitle": "a",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581287/ICHEJA/ICHEJA/T2_R1_1.svg"
            },
            {
            "title": "B",
            "subtitle": "b",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581457/ICHEJA/ICHEJA/T2_R1_2.svg"
            },
            {
            "title": "C",
            "subtitle": "c",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581488/ICHEJA/ICHEJA/T2_R1_3.svg"
            },
            {
            "title": "Ch",
            "subtitle": "ch",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751584072/ICHEJA/ICHEJA/T2_R1_4.svg"
            },
            {
            "title": "D",
            "subtitle": "d",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581517/ICHEJA/ICHEJA/T2_R1_5.svg"
            },
            {
            "title": "E",
            "subtitle": "e",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581562/ICHEJA/ICHEJA/T2_R1_6.svg"
            },
            {
            "title": "F",
            "subtitle": "f",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581590/ICHEJA/ICHEJA/T2_R1_7.svg"
            },
            {
            "title": "G",
            "subtitle": "g",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751572953/ICHEJA/ICHEJA/T2_R1_8.svg"
            },
            {
            "title": "H",
            "subtitle": "h",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751572956/ICHEJA/ICHEJA/T2_R1_9.svg"
            },
            {
            "title": "I",
            "subtitle": "i",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751572961/ICHEJA/ICHEJA/T2_R1_10.svg"
            },
            {
            "title": "J",
            "subtitle": "j",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751572964/ICHEJA/ICHEJA/T2_R1_11.svg"
            },
            {
            "title": "K",
            "subtitle": "k",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751572967/ICHEJA/ICHEJA/T2_R1_12.svg"
            },
            {
            "title": "L",
            "subtitle": "l",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751572972/ICHEJA/ICHEJA/T2_R1_13.svg"
            },
            {
            "title": "Ll",
            "subtitle": "ll",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751575317/ICHEJA/ICHEJA/T2_R1_30.svg"
            },
            {
            "title": "M",
            "subtitle": "m",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751572975/ICHEJA/ICHEJA/T2_R1_14.svg"
            },
            {
            "title": "N",
            "subtitle": "n",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751572978/ICHEJA/ICHEJA/T2_R1_15.svg"
            },
            {
            "title": "Ñ",
            "subtitle": "ñ",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751572982/ICHEJA/ICHEJA/T2_R1_16.svg"
            },
            {
            "title": "O",
            "subtitle": "o",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751572985/ICHEJA/ICHEJA/T2_R1_17.svg"
            },
            {
            "title": "P",
            "subtitle": "p",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751572988/ICHEJA/ICHEJA/T2_R1_18.svg"
            },
            {
            "title": "Q",
            "subtitle": "q",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751572992/ICHEJA/ICHEJA/T2_R1_19.svg"
            },
            {
            "title": "R",
            "subtitle": "r",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751572996/ICHEJA/ICHEJA/T2_R1_20.svg"
            },
            {
            "title": "RR",
            "subtitle": "rr",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751572999/ICHEJA/ICHEJA/T2_R1_21.svg"
            },
            {
            "title": "S",
            "subtitle": "s",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751573003/ICHEJA/ICHEJA/T2_R1_22.svg"
            },
            {
            "title": "T",
            "subtitle": "t",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751573006/ICHEJA/ICHEJA/T2_R1_23.svg"
            },
            {
            "title": "U",
            "subtitle": "u",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751573009/ICHEJA/ICHEJA/T2_R1_24.svg"
            },
            {
            "title": "V",
            "subtitle": "v",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751573012/ICHEJA/ICHEJA/T2_R1_25.svg"
            },
            {
            "title": "W",
            "subtitle": "w",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751573018/ICHEJA/ICHEJA/T2_R1_26.svg"
            },
            {
            "title": "X",
            "subtitle": "x",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751573022/ICHEJA/ICHEJA/T2_R1_27.svg"
            },
            {
            "title": "Y",
            "subtitle": "y",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751573025/ICHEJA/ICHEJA/T2_R1_28.svg"
            },
            {
            "title": "Z",
            "subtitle": "z",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751573029/ICHEJA/ICHEJA/T2_R1_29.svg"
            }
    ]
}', 2),
    (2, 'Familia', '{
        "material": [
            {
                "title": "Mamá",
                "image_path": "https://res.cloudinary.com/dsiamqhuu/image/upload/v1752096511/ICHEJA/ICHEJA/T3_R1_1.svg"
            },
            {
                "title": "Papá",
                "image_path": "https://res.cloudinary.com/dsiamqhuu/image/upload/v1752096633/ICHEJA/ICHEJA/T3_R1_2.svg"
            },
            {
                "title": "Hermano",
                "image_path": "https://res.cloudinary.com/dsiamqhuu/raw/upload/v1751581189/ICHEJA/ICHEJA/T3_R1_3"
            },
            {
                "title": "Hermana",
                "image_path": "https://res.cloudinary.com/dsiamqhuu/raw/upload/v1751581248/ICHEJA/ICHEJA/T3_R1_4"
            }
        ]
    }', 5),
    (3, 'Calendario - días', '    {
        "material": [
            {
                "element": "Domingo",
                "image_path": "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581287/ICHEJA/ICHEJA/T2_R1_1.svg"
            },
            {
                "element": "Lunes",
                "image_path": "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581287/ICHEJA/ICHEJA/T2_R1_1.svg"
            },
            {
                "element": "Martes",
                "image_path": "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581287/ICHEJA/ICHEJA/T2_R1_1.svg"
            },
            {
                "element": "Miércoles",
                "image_path": "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581287/ICHEJA/ICHEJA/T2_R1_1.svg"
            },
            {
                "element": "Jueves",
                "image_path": "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581287/ICHEJA/ICHEJA/T2_R1_1.svg"
            },
            {
                "element": "Viernes",
                "image_path": "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581287/ICHEJA/ICHEJA/T2_R1_1.svg"
            },
            {
                "element": "Sábado",
                "image_path": "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581287/ICHEJA/ICHEJA/T2_R1_1.svg"
            }
        ]
    }', 9),
    (4, 'Calendario - meses', '    {
        "material": [
            {
                "element": "Enero",
                "image_path": "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581287/ICHEJA/ICHEJA/T2_R1_1.svg"
            },
            {
                "element": "Febrero",
                "image_path": "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581287/ICHEJA/ICHEJA/T2_R1_1.svg"
            },
            {
                "element": "Marzo",
                "image_path": "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581287/ICHEJA/ICHEJA/T2_R1_1.svg"
            },
            {
                "element": "Abril",
                "image_path": "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581287/ICHEJA/ICHEJA/T2_R1_1.svg"
            },
            {
                "element": "Mayo",
                "image_path": "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581287/ICHEJA/ICHEJA/T2_R1_1.svg"
            },
            {
                "element": "Junio",
                "image_path": "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581287/ICHEJA/ICHEJA/T2_R1_1.svg"
            },
            {
                "element": "Julio",
                "image_path": "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581287/ICHEJA/ICHEJA/T2_R1_1.svg"
            },
            {
                "element": "Agosto",
                "image_path": "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581287/ICHEJA/ICHEJA/T2_R1_1.svg"
            },
            {
                "element": "Septiembre",
                "image_path": "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581287/ICHEJA/ICHEJA/T2_R1_1.svg"
            },
            {
                "element": "Octubre",
                "image_path": "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581287/ICHEJA/ICHEJA/T2_R1_1.svg"
            },
            {
                "element": "Noviembre",
                "image_path": "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581287/ICHEJA/ICHEJA/T2_R1_1.svg"
            },
            {
                "element": "Diciembre",
                "image_path": "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581287/ICHEJA/ICHEJA/T2_R1_1.svg"
            }
        ]
    }', 9);

INSERT INTO tema_recursos (id_tema, id_recurso)
VALUES (3, 1), (4, 2), (5, 3), (5, 4);

INSERT INTO plantilla (id_plantilla, titulo, instrucciones, tiempo_sugerido, id_layout, id_tema)
VALUES
    (1, 'Aprendiendo a hacer Líneas Rectas Horizontales', 'Desliza tu lápiz de izquierda a derecha, siguiendo las líneas punteadas y las flechas.', '00:10:00', 1, 2),
    (2, 'Aprendiendo a hacer Líneas Inclinadas', 'Sigue las flechas, moviendo tu lápiz de arriba hacia abajo a la izquierda sobre las líneas punteadas.', '00:10:00', 1, 2),
    (3, 'Aprendiendo a hacer Líneas Declinadas', 'Desliza tu lápiz hacia abajo y hacia la derecha, guiándote por las líneas punteadas y las flechas.', '00:10:00', 1, 2),
    (4, '¡De Señas a Letras: Descifra el Alfabeto!', 'Mira con atención la seña que te presentamos. Luego selecciona la letra que corresponda a la seña.', '00:10:00', 3, 3),
    (5, '¡Desafía tu Memoria: El Juego del Memorama!', 'Toca y encuentra el par.', '00:10:00', 4, 3),
    (6, 'Aprendiendo mi propio nombre', 'Usa tu dedo para tocar cada letra de tu nombre, una por una.', '00:10:00', 6, 4),
    (7, 'Aprendiendo y recordado mi nombre', 'Busca tu nombre en la lista y, cuando lo veas, selecciónalo.', '00:10:00', 7, 4),
    (8, 'Aprendiendo sobre mi familia', 'Usa tu dedo para unir cada pregunta sobre tu familia con el nombre correcto.', '00:10:00', 8, 4),
    (9, '¡Conecta el Día con su Seña!', 'Arrastra el nombre del día y colócalo junto a la seña correcta.', '00:10:00', 10, 5),
    (10, '¿Qué Día Fue, Es y Será?', 'Arrastra el día correcto según: ayer, hoy y mañana.', '00:10:00', 11, 5);


INSERT INTO tipo_instruccion_media (id_tipo_media, nombre)
VALUES (1, 'gif');

INSERT INTO reactivo_instruccion_media (id_reactivo, id_tipo_media, ruta_media)
VALUES
    (1, 1, 'http://res.cloudinary.com/dsiamqhuu/image/upload/v1751583872/ICHEJA/ICHEJA/T1_E1_I.gif'),
    (2, 1, 'https://res.cloudinary.com/dsiamqhuu/image/upload/v1752094105/ICHEJA/ICHEJA/T1_E2_I.gif'),
    (3, 1, 'https://res.cloudinary.com/dsiamqhuu/image/upload/v1752094026/ICHEJA/ICHEJA/T1_E3_I.gif'),
    (4, 1, 'http://res.cloudinary.com/dsiamqhuu/image/upload/v1751583957/ICHEJA/ICHEJA/T2_E1_I.gif'),
    (5, 1, 'https://res.cloudinary.com/dsiamqhuu/image/upload/v1752094214/ICHEJA/ICHEJA/T2_E6_I.gif'),
    (6, 1, 'https://res.cloudinary.com/dsiamqhuu/image/upload/v1752094403/ICHEJA/ICHEJA/T3_E1_I.gif'),
    (7, 1, 'https://res.cloudinary.com/dsiamqhuu/image/upload/v1752094441/ICHEJA/ICHEJA/T3_E2_I.gif'),
    (8, 1, 'https://res.cloudinary.com/dsiamqhuu/image/upload/v1752094321/ICHEJA/ICHEJA/T3_E3_I.gif'),
    (9, 1, 'https://res.cloudinary.com/dsiamqhuu/image/upload/v1752094321/ICHEJA/ICHEJA/T3_E3_I.gif'),
    (10, 1, 'https://res.cloudinary.com/dsiamqhuu/image/upload/v1752094321/ICHEJA/ICHEJA/T3_E3_I.gif');

INSERT INTO habilidad (id_habilidad, nombre)
VALUES (1, 'Grafomotricidad'), (2, 'Memoria'), (3, 'Asociación'), (4, 'Identificación'), (5, 'Comprensión');

INSERT INTO reactivo_habilidades (id_reactivo, id_habilidad, porcentaje, bandera)
VALUES
    (1, 1, 1, true),
    (2, 1, 1, true),
    (3, 1, 1, true),
    (4, 3, 0.6, true),
    (4, 2, 0.4, true),
    (5, 3, 0.5, true),
    (5, 2, 0.5, true),
    (6, 2, 0.4, true),
    (6, 3, 0.6, true),
    (7, 3, 0.2, true),
    (7, 4, 0.6, true),
    (7, 2, 0.2, true),
    (8, 5, 0.7, true),
    (8, 4, 0.3, true);

INSERT INTO ejercicio (id_ejercicio, contexto, id_plantilla)
VALUES
    (1, '{
  "image_reference": "http://res.cloudinary.com/dsiamqhuu/image/upload/v1751571141/ICHEJA/ICHEJA/T1_E1.jpg",
  "imagen_base": "http://res.cloudinary.com/dsiamqhuu/image/upload/v1751571597/ICHEJA/ICHEJA/T1_M1.jpg"
}', 1),
    (2, '{
        "image_reference": "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751571204/ICHEJA/ICHEJA/T1_E2.jpg",
        "imagen_base":
            "http://res.cloudinary.com/dsiamqhuu/image/upload/v1751571550/ICHEJA/ICHEJA/T1_M2.jpg"
    }', 2),
    (3, '{
        "image_reference": "http://res.cloudinary.com/dsiamqhuu/image/upload/v1751571261/ICHEJA/ICHEJA/T1_E3.jpg",
        "imagen_base":
            "http://res.cloudinary.com/dsiamqhuu/image/upload/v1751571574/ICHEJA/ICHEJA/T1_M3.jpg"
    }', 3),
    (4, '{
        "image_reference": "http://res.cloudinary.com/dsiamqhuu/image/upload/v1751573029/ICHEJA/ICHEJA/T2_R1_29.svg",
        "options_types": "texto",
        "options": ["m", "E", "Z"],
        "right_option": 2
    }', 4),
    (5, '{
        "image_reference": "http://res.cloudinary.com/dsiamqhuu/image/upload/v1751572978/ICHEJA/ICHEJA/T2_R1_15.svg",
        "options_types": "texto",
        "options": ["A", "N", "r"],
        "right_option": 1
    }', 4),
    (6, '{
        "image_reference": "http://res.cloudinary.com/dsiamqhuu/image/upload/v1751581457/ICHEJA/ICHEJA/T2_R1_2.svg",
        "options_types": "texto",
        "options": ["B", "i", "C"],
        "right_option": 0
    }', 4),
    (7, '{
        "image_reference": "http://res.cloudinary.com/dsiamqhuu/image/upload/v1751572972/ICHEJA/ICHEJA/T2_R1_13.svg",
        "options_types": "texto",
        "options": ["Y", "l", "Z"],
        "right_option": 1
    }', 4),
    (8, '{
        "image_reference": "http://res.cloudinary.com/dsiamqhuu/image/upload/v1751572956/ICHEJA/ICHEJA/T2_R1_9.svg",
        "options_types": "texto",
        "options": ["x", "o", "H"],
        "right_option": 2
    }', 4),
    (9, '{
    "material": [
                {
            "title": "A",
            "subtitle": "a",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581287/ICHEJA/ICHEJA/T2_R1_1.svg"
            },
            {
            "title": "B",
            "subtitle": "b",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581457/ICHEJA/ICHEJA/T2_R1_2.svg"
            },
            {
            "title": "C",
            "subtitle": "c",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581488/ICHEJA/ICHEJA/T2_R1_3.svg"
            },
            {
            "title": "Ch",
            "subtitle": "ch",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751584072/ICHEJA/ICHEJA/T2_R1_4.svg"
            },
            {
            "title": "D",
            "subtitle": "d",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581517/ICHEJA/ICHEJA/T2_R1_5.svg"
            },
            {
            "title": "E",
            "subtitle": "e",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581562/ICHEJA/ICHEJA/T2_R1_6.svg"
            },
            {
            "title": "F",
            "subtitle": "f",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581590/ICHEJA/ICHEJA/T2_R1_7.svg"
            },
            {
            "title": "G",
            "subtitle": "g",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751572953/ICHEJA/ICHEJA/T2_R1_8.svg"
            },
            {
            "title": "H",
            "subtitle": "h",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751572956/ICHEJA/ICHEJA/T2_R1_9.svg"
            },
            {
            "title": "I",
            "subtitle": "i",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751572961/ICHEJA/ICHEJA/T2_R1_10.svg"
            },
            {
            "title": "J",
            "subtitle": "j",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751572964/ICHEJA/ICHEJA/T2_R1_11.svg"
            },
            {
            "title": "K",
            "subtitle": "k",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751572967/ICHEJA/ICHEJA/T2_R1_12.svg"
            },
            {
            "title": "L",
            "subtitle": "l",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751572972/ICHEJA/ICHEJA/T2_R1_13.svg"
            },
            {
            "title": "Ll",
            "subtitle": "ll",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751575317/ICHEJA/ICHEJA/T2_R1_30.svg"
            },
            {
            "title": "M",
            "subtitle": "m",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751572975/ICHEJA/ICHEJA/T2_R1_14.svg"
            },
            {
            "title": "N",
            "subtitle": "n",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751572978/ICHEJA/ICHEJA/T2_R1_15.svg"
            },
            {
            "title": "Ñ",
            "subtitle": "ñ",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751572982/ICHEJA/ICHEJA/T2_R1_16.svg"
            },
            {
            "title": "O",
            "subtitle": "o",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751572985/ICHEJA/ICHEJA/T2_R1_17.svg"
            },
            {
            "title": "P",
            "subtitle": "p",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751572988/ICHEJA/ICHEJA/T2_R1_18.svg"
            },
            {
            "title": "Q",
            "subtitle": "q",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751572992/ICHEJA/ICHEJA/T2_R1_19.svg"
            },
            {
            "title": "R",
            "subtitle": "r",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751572996/ICHEJA/ICHEJA/T2_R1_20.svg"
            },
            {
            "title": "RR",
            "subtitle": "rr",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751572999/ICHEJA/ICHEJA/T2_R1_21.svg"
            },
            {
            "title": "S",
            "subtitle": "s",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751573003/ICHEJA/ICHEJA/T2_R1_22.svg"
            },
            {
            "title": "T",
            "subtitle": "t",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751573006/ICHEJA/ICHEJA/T2_R1_23.svg"
            },
            {
            "title": "U",
            "subtitle": "u",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751573009/ICHEJA/ICHEJA/T2_R1_24.svg"
            },
            {
            "title": "V",
            "subtitle": "v",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751573012/ICHEJA/ICHEJA/T2_R1_25.svg"
            },
            {
            "title": "W",
            "subtitle": "w",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751573018/ICHEJA/ICHEJA/T2_R1_26.svg"
            },
            {
            "title": "X",
            "subtitle": "x",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751573022/ICHEJA/ICHEJA/T2_R1_27.svg"
            },
            {
            "title": "Y",
            "subtitle": "y",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751573025/ICHEJA/ICHEJA/T2_R1_28.svg"
            },
            {
            "title": "Z",
            "subtitle": "z",
            "image_path":
                "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751573029/ICHEJA/ICHEJA/T2_R1_29.svg"
            }
    ]
}', 5),
    (10, '{
        "base_sentence": "Fernando", "options": ["O", "F", "S", "D", "E", "N", "R", "A", "Z", "P", "W", "I"]
    }', 6),
    (11, '{
        "base_sentence": "Fernando",
        "options": ["Alberto", "Eduardo", "Enrique", "Fernando", "Emiliano"],
        "right_option": 3
    }', 7),
    (12, '{
        "content": [
            {
                "question": "¿Cómo te llama tú?",
                "answer": "Fernando"
            },
            {
                "question": "¿Cómo se llama tu mamá?",
                "answer": "Rigoberto"
            },
            {
                "question": "¿Cómo te llama tu papá?",
                "answer": "Eugenia"
            }
        ]
    }', 8),
    (13, '{
        "content": [
            {
                "element": "Domingo",
                "image_path":
                    "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581287/ICHEJA/ICHEJA/T2_R1_1.svg"
            },
            {
                "element": "Lunes",
                "image_path":
                    "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581457/ICHEJA/ICHEJA/T2_R1_2.svg"
            },
            {
                "element": "Martes",
                "image_path":
                    "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581488/ICHEJA/ICHEJA/T2_R1_3.svg"
            },
            {
                "element": "Miércoles",
                "image_path":
                    "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581517/ICHEJA/ICHEJA/T2_R1_5.svg"
            },
            {
                "element": "Jueves",
                "image_path":
                    "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581562/ICHEJA/ICHEJA/T2_R1_6.svg"
            },
            {
                "elemento": "Viernes",
                "image_path":
                    "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581562/ICHEJA/ICHEJA/T2_R1_6.svg"
            },
            {
                "elemento": "Sábado",
                "image_path":
                    "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581590/ICHEJA/ICHEJA/T2_R1_7.svg"
            }
        ]
    }', 9),
    (14, '{
        "content": [
            {
                "element": "Jueves",
                "image_path": "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581562/ICHEJA/ICHEJA/T2_R1_6.svg",
                "sentence": "Hoy"
            },
            {
                "element": "Miércoles",
                "image_path": "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581562/ICHEJA/ICHEJA/T2_R1_6.svg",
                "sentence": "Ayer"
            },
            {
                "element": "Sábado",
                "image_path": "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581562/ICHEJA/ICHEJA/T2_R1_6.svg",
                "sentence": "Mañana"
            }
        ]
    }', 10);

INSERT INTO educando_temas (id, id_educando, id_tema)
VALUES (1, 1, 1), (2, 1, 2);

/*
INSERT INTO educando_ejercicios (id_educando_ejercicio, id_educando, id_ejercicio, fecha_asignacion, fecha_completado, tiempo_asignado, por_educador)
VALUES (1, 1, 1, '2025-07-21 14:30:00', '2025-07-21 14:40:00', '00:10:00', true);

/*Se debe utilizar la api para subir los puntajes por habilidad del ejercicio*/
*/

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

INSERT INTO educando_discapacidades (id_educando, id_discapacidad) VALUES (1, 2);

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
-- DATOS DUMMY PARA MÓDULOS DE USUARIOS
-- =====================================================

-- =====================================================
-- 2.01. PERSONS - Más personas
-- =====================================================
INSERT INTO persona (id_persona, primer_nombre, segundo_nombre, apellido_paterno, apellido_materno, curp, numero_ine, fecha_nacimiento, genero, codigo_postal, estado, municipio, localidad, vialidad_nombre, id_vialidad_tipo, asentamiento, id_asentamiento_tipo, password)
VALUES
    (5, 'Roberto', 'Antonio', 'Morales', 'Jiménez', 'MOJR750505HCHRRS05', '5678901234567', '1975-05-05', 'MASCULINO', '29000', 'Chiapas', 'Tuxtla Gutiérrez', 'Tuxtla Gutiérrez', 'Central', 1, 'Centro', 1, '$2b$10$hashedpassword5'),
    (6, 'Laura', 'Isabel', 'Torres', 'Vargas', 'TOVL820606MCHRRL06', '6789012345678', '1982-06-06', 'FEMENINO', '29010', 'Chiapas', 'Tuxtla Gutiérrez', 'Tuxtla Gutiérrez', 'Norte', 2, 'Las Flores', 1, '$2b$10$hashedpassword6'),
    (7, 'Juan', 'Carlos', 'Ramírez', 'Díaz', 'RADJ880707HCHRRL07', '7890123456789', '1988-07-07', 'MASCULINO', '29020', 'Chiapas', 'Tuxtla Gutiérrez', 'Tuxtla Gutiérrez', 'Sur', 1, 'San José', 2, '$2b$10$hashedpassword7'),
    (8, 'Patricia', 'Sofía', 'Gómez', 'Castro', 'GOCP900808MCHRRL08', '8901234567890', '1990-08-08', 'FEMENINO', '29030', 'Chiapas', 'Tuxtla Gutiérrez', 'Tuxtla Gutiérrez', 'Oriente', 3, 'El Carmen', 1, '$2b$10$hashedpassword8'),
    (9, 'Miguel', 'Ángel', 'López', 'Ruiz', 'LORM920909HCHRRL09', '9012345678901', '1992-09-09', 'MASCULINO', '29000', 'Chiapas', 'Tuxtla Gutiérrez', 'Tuxtla Gutiérrez', 'Poniente', 1, 'Centro', 1, '$2b$10$hashedpassword9'),
    (10, 'Carmen', 'Rosa', 'Martínez', 'Ortega', 'MAOC851010MCHRRL10', '0123456789012', '1985-10-10', 'FEMENINO', '29010', 'Chiapas', 'Tuxtla Gutiérrez', 'Tuxtla Gutiérrez', 'Norte', 2, 'Las Flores', 1, '$2b$10$hashedpassword10');

-- =====================================================
-- 2.02. ROLES - Ya existen, pero verificamos
-- =====================================================
-- Los roles ya están insertados en las líneas 12-17

-- =====================================================
-- 2.03. ROLE PERSONS - Más asignaciones de roles
-- =====================================================
INSERT INTO persona_rol (id_persona_rol, id_persona, id_rol)
VALUES
    (5, 5, 3),  -- Roberto es Coordinador
    (6, 6, 1),  -- Laura es Educador
    (7, 7, 1),  -- Juan es Educador
    (8, 8, 1),  -- Patricia es Educador
    (9, 9, 4),  -- Miguel es Estudiante
    (10, 10, 4); -- Carmen es Estudiante

-- =====================================================
-- 2.06. INSTITUTIONS - Instituciones educativas
-- =====================================================
INSERT INTO institucion (id_institucion, rfc, rct, nombre)
VALUES
    (1, 'ICHEJA001234', 'RCT-2024-001', 'Instituto Chiapaneco de Educación para Jóvenes y Adultos - Sede Central'),
    (2, 'ICHEJA005678', 'RCT-2024-002', 'ICHEJA - Sede San Cristóbal'),
    (3, 'ICHEJA009012', 'RCT-2024-003', 'ICHEJA - Sede Tapachula'),
    (4, 'ICHEJA003456', 'RCT-2024-004', 'ICHEJA - Sede Palenque'),
    (5, 'ICHEJA007890', 'RCT-2024-005', 'ICHEJA - Sede Comitán');

-- =====================================================
-- 2.05. CELLS - Células educativas
-- =====================================================
INSERT INTO celula (id_celula, id_institucion, id_persona, fecha_inicio, fecha_final)
VALUES
    (1, 1, 5, '2024-01-15 08:00:00', '2024-12-20 18:00:00'),  -- Célula 1 en Institución 1, coordinada por Roberto (ID 5)
    (2, 1, 5, '2024-02-01 08:00:00', '2024-12-20 18:00:00'),  -- Célula 2 en Institución 1, coordinada por Roberto (ID 5)
    (3, 2, 5, '2024-03-01 08:00:00', '2024-12-20 18:00:00'),  -- Célula 3 en Institución 2, coordinada por Roberto (ID 5)
    (4, 3, 5, '2024-04-01 08:00:00', '2024-12-20 18:00:00'),  -- Célula 4 en Institución 3, coordinada por Roberto (ID 5)
    (5, 4, 5, '2024-05-01 08:00:00', '2024-12-20 18:00:00');  -- Célula 5 en Institución 4, coordinada por Roberto (ID 5)

-- =====================================================
-- 2.07. TEACHER CELLS - Relaciones educador-célula
-- =====================================================
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

-- =====================================================
-- 2.04. STUDENTS - Progenitores y Estudiantes
-- =====================================================

-- Progenitores (padres y madres)
INSERT INTO progenitor (id_progenitor, curp, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido)
VALUES
    (1, 'GALF850101HCHRPS01', 'Fernando', 'José', 'García', 'López'),      -- Padre del estudiante 1
    (2, 'HEMM900215MCHRRL02', 'María', 'Elena', 'Hernández', 'Martínez'),  -- Madre del estudiante 1
    (3, 'PESC880312HCHRRL03', 'Carlos', 'Alberto', 'Pérez', 'Sánchez'),    -- Padre del estudiante 2
    (4, 'ROGA920420MCHRDN04', 'Ana', 'Patricia', 'Rodríguez', 'González'), -- Madre del estudiante 2
    (5, 'MOJR750505HCHRRS05', 'Roberto', 'Antonio', 'Morales', 'Jiménez'),  -- Padre del estudiante 3
    (6, 'TOVL820606MCHRRL06', 'Laura', 'Isabel', 'Torres', 'Vargas'),      -- Madre del estudiante 3
    (7, 'RADJ880707HCHRRL07', 'Juan', 'Carlos', 'Ramírez', 'Díaz'),        -- Padre del estudiante 4
    (8, 'GOCP900808MCHRRL08', 'Patricia', 'Sofía', 'Gómez', 'Castro'),     -- Madre del estudiante 4
    (9, 'LORM920909HCHRRL09', 'Miguel', 'Ángel', 'López', 'Ruiz'),         -- Padre del estudiante 5
    (10, 'MAOC851010MCHRRL10', 'Carmen', 'Rosa', 'Martínez', 'Ortega');    -- Madre del estudiante 5

-- Estudiantes (educandos)
-- Nota: Los estudiantes usan las personas con ID 1, 4, 9, 10 y creamos una nueva persona 11 para el estudiante 5
INSERT INTO persona (id_persona, primer_nombre, segundo_nombre, apellido_paterno, apellido_materno, curp, numero_ine, fecha_nacimiento, genero, codigo_postal, estado, municipio, localidad, vialidad_nombre, id_vialidad_tipo, asentamiento, id_asentamiento_tipo, password)
VALUES
    (11, 'Diego', 'Alejandro', 'López', 'Ruiz', 'LORD051111HCHRRL11', '1123456789012', '2005-11-11', 'MASCULINO', '29000', 'Chiapas', 'Tuxtla Gutiérrez', 'Tuxtla Gutiérrez', 'Poniente', 1, 'Centro', 1, '$2b$10$hashedpassword11');

-- Asignar rol de Estudiante a la nueva persona
INSERT INTO persona_rol (id_persona_rol, id_persona, id_rol)
VALUES
    (11, 11, 4);  -- Diego es Estudiante

-- Crear progenitores adicionales para el estudiante 5
INSERT INTO progenitor (id_progenitor, curp, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido)
VALUES
    (11, 'LOPR751212HCHRRL11', 'Pedro', 'Luis', 'López', 'Ruiz'),         -- Padre del estudiante 5 (diferente a persona 9)
    (12, 'MART801212MCHRRL12', 'Rosa', 'María', 'Martínez', 'Ortega');     -- Madre del estudiante 5 (diferente a persona 10)

-- Insertar estudiantes
INSERT INTO educando (id_educando, id_persona, id_educador, qr_ruta, id_padre, id_madre)
VALUES
    (1, 1, 2, 'https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581287/ICHEJA/ICHEJA/qr_estudiante_1.png', 1, 2),   -- Fernando (persona 1) con educador María (persona 2)
    (2, 4, 3, 'https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581287/ICHEJA/ICHEJA/qr_estudiante_2.png', 3, 4),   -- Ana (persona 4) con educador Carlos (persona 3)
    (3, 9, 6, 'https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581287/ICHEJA/ICHEJA/qr_estudiante_3.png', 5, 6),   -- Miguel (persona 9) con educador Laura (persona 6)
    (4, 10, 7, 'https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581287/ICHEJA/ICHEJA/qr_estudiante_4.png', 7, 8),  -- Carmen (persona 10) con educador Juan (persona 7)
    (5, 11, 8, 'https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581287/ICHEJA/ICHEJA/qr_estudiante_5.png', 11, 12); -- Diego (persona 11) con educador Patricia (persona 8)

-- Fin del script de datos dummy
