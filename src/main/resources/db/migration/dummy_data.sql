BEGIN;

INSERT INTO persona (primer_nombre, segundo_nombre, apellido_paterno, apellido_materno, curp, numero_ine, fecha_nacimiento, genero, codigo_postal, estado, municipio, localidad, vialidad_nombre, id_vialidad_tipo, asentamiento, id_asentamiento_tipo, password)
VALUES
    ('Fernando', 'José', 'García', 'López', 'GALF850101HCHRPS01', '1234567890123', '1985-01-01', 'M', '29000', 'Chiapas', 'Tuxtla Gutiérrez', 'Tuxtla Gutiérrez', '5 de Mayo', 1, 'Centro', 1, '$2b$10$hashedpassword1'),
    ('María', 'Elena', 'Hernández', 'Martínez', 'HEMM900215MCHRRL02', '2345678901234', '1990-02-15', 'F', '29010', 'Chiapas', 'Tuxtla Gutiérrez', 'Tuxtla Gutiérrez', 'Insurgentes', 2, 'Las Flores', 1, '$2b$10$hashedpassword2'),
    ('Carlos', 'Alberto', 'Pérez', 'Sánchez', 'PESC880312HCHRRL03', '3456789012345', '1988-03-12', 'M', '29020', 'Chiapas', 'Tuxtla Gutiérrez', 'Tuxtla Gutiérrez', 'Revolución', 3, 'San José', 2, '$2b$10$hashedpassword3'),
    ('Ana', 'Patricia', 'Rodríguez', 'González', 'ROGA920420MCHRDN04', '4567890123456', '1992-04-20', 'F', '29030', 'Chiapas', 'Tuxtla Gutiérrez', 'Tuxtla Gutiérrez', 'Hidalgo', 1, 'El Carmen', 1, '$2b$10$hashedpassword4');

INSERT INTO rol (nombre)
VALUES
    ('Educador'),
    ('Administrador'),
    ('Coordinador'),
    ('Estudiante');
INSERT INTO persona_rol (id_persona, id_rol)
VALUES
    (1, 4),
    (2, 1),
    (3, 1),
    (4, 4);

INSERT INTO tema (nombre)
VALUES ('Introducción'),
       ('Caligrafía'),
       ('Abecedario'),
       ('Nombre propio'),
       ('Calendario');

INSERT INTO ruta_aprendizaje (nombre)
VALUES ('Alfabetización en personas sordas');

INSERT INTO secuencia_temas (id_ruta_aprendizaje, id_tema, id_tema_siguiente)
VALUES
    (1, 1, 2),
    (1, 1, 3),
    (1, 2, 4),
    (1, 3, 5),
    (1, 4, 5);

INSERT INTO tipo_layouts (nombre)
VALUES ('recurso'), ('reactivo');

INSERT INTO layout (nombre, atributos, id_tipo_layout)
VALUES
    ('Escritura', '{"image_reference": "", "image_base": ""}', 2),
    ('Columnas de cartas', '{"material": [{"title": "", "subtitle": "","image_path": ""}]}', 1),
    ('Seleccion', '{"image_reference": "", "options_types": "", "options": [], "right_option": 1}', 2),
    ('Memorama con imagenes', '{"content": [{"title": "", "subtitle": "", "path_image":""}]}', 2),
    ('Carrusel', '{"material": [{"title": "", "image_path": ""}]}', 1),
    ('Deletreo', '{"base_sentence": "", "options": []}', 2),
    ('Relacion simple', '{"base_sentence": "", "options": [], "right_option": 1}', 2),
    ('Relacion compleja', '{"content": [{"question": "", "answer": ""}]}', 2),
    ('Lista', '{"material": [{"element": "", "image_path": ""}]}', 1),
    ('Relacion con imagenes', '{"content": [{"element": "", "image_path": ""}]}', 2),
    ('Relacion con enunciados', '{"content": [{"element": "", "image_path": "", "sentence": ""}]}', 2);

INSERT INTO recurso (titulo, contenido, id_layout)
VALUES
    ('Abecedario con señas', '{
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
    ('Familia', '{
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
    ('Calendario - días', '    {
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
    ('Calendario - meses', '    {
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

INSERT INTO reactivo (titulo, instrucciones, tiempo_sugerido, id_layout, id_tema)
VALUES
    ('Aprendiendo a hacer Líneas Rectas Horizontales', 'Desliza tu lápiz de izquierda a derecha, siguiendo las líneas punteadas y las flechas.', '00:10:00', 1, 2),
    ('Aprendiendo a hacer Líneas Inclinadas', 'Sigue las flechas, moviendo tu lápiz de arriba hacia abajo a la izquierda sobre las líneas punteadas.', '00:10:00', 1, 2),
    ('Aprendiendo a hacer Líneas Declinadas', 'Desliza tu lápiz hacia abajo y hacia la derecha, guiándote por las líneas punteadas y las flechas.', '00:10:00', 1, 2),
    ('¡De Señas a Letras: Descifra el Alfabeto!', 'Mira con atención la seña que te presentamos. Luego selecciona la letra que corresponda a la seña.', '00:10:00', 3, 3),
    ('¡Desafía tu Memoria: El Juego del Memorama!', 'Toca y encuentra el par.', '00:10:00', 4, 3),
    ('Aprendiendo mi propio nombre', 'Usa tu dedo para tocar cada letra de tu nombre, una por una.', '00:10:00', 6, 4),
    ('Aprendiendo y recordado mi nombre', 'Busca tu nombre en la lista y, cuando lo veas, selecciónalo.', '00:10:00', 7, 4),
    ('Aprendiendo sobre mi familia', 'Usa tu dedo para unir cada pregunta sobre tu familia con el nombre correcto.', '00:10:00', 8, 4),
    ('¡Conecta el Día con su Seña!', 'Arrastra el nombre del día y colócalo junto a la seña correcta.', '00:10:00', 10, 5),
    ('¿Qué Día Fue, Es y Será?', 'Arrastra el día correcto según: ayer, hoy y mañana.', '00:10:00', 11, 5);


INSERT INTO tipo_instruccion_media (nombre)
VALUES ('gif');

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

INSERT INTO habilidad (nombre)
VALUES ('Grafomotricidad'), ('Memoria'), ('Asociación'), ('Identificación'), ('Comprensión');

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

INSERT INTO ejercicio (id_reactivo, contexto)
VALUES
    (1, '{
  "image_reference": "http://res.cloudinary.com/dsiamqhuu/image/upload/v1751571141/ICHEJA/ICHEJA/T1_E1.jpg",
  "imagen_base": "http://res.cloudinary.com/dsiamqhuu/image/upload/v1751571597/ICHEJA/ICHEJA/T1_M1.jpg"
}'),
    (2, '{
        "image_reference": "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751571204/ICHEJA/ICHEJA/T1_E2.jpg",
        "imagen_base":
            "http://res.cloudinary.com/dsiamqhuu/image/upload/v1751571550/ICHEJA/ICHEJA/T1_M2.jpg"
    }'),
    (3, '{
        "image_reference": "http://res.cloudinary.com/dsiamqhuu/image/upload/v1751571261/ICHEJA/ICHEJA/T1_E3.jpg",
        "imagen_base":
            "http://res.cloudinary.com/dsiamqhuu/image/upload/v1751571574/ICHEJA/ICHEJA/T1_M3.jpg"
    }'),
    (4, '{
        "image_reference": "http://res.cloudinary.com/dsiamqhuu/image/upload/v1751573029/ICHEJA/ICHEJA/T2_R1_29.svg",
        "options_types": "texto",
        "options": ["m", "E", "Z"],
        "right_option": 2
    }'),
    (4, '{
        "image_reference": "http://res.cloudinary.com/dsiamqhuu/image/upload/v1751572978/ICHEJA/ICHEJA/T2_R1_15.svg",
        "options_types": "texto",
        "options": ["A", "N", "r"],
        "right_option": 1
    }'),
    (4, '{
        "image_reference": "http://res.cloudinary.com/dsiamqhuu/image/upload/v1751581457/ICHEJA/ICHEJA/T2_R1_2.svg",
        "options_types": "texto",
        "options": ["B", "i", "C"],
        "right_option": 0
    }'),
    (4, '{
        "image_reference": "http://res.cloudinary.com/dsiamqhuu/image/upload/v1751572972/ICHEJA/ICHEJA/T2_R1_13.svg",
        "options_types": "texto",
        "options": ["Y", "l", "Z"],
        "right_option": 1
    }'),
    (4, '{
        "image_reference": "http://res.cloudinary.com/dsiamqhuu/image/upload/v1751572956/ICHEJA/ICHEJA/T2_R1_9.svg",
        "options_types": "texto",
        "options": ["x", "o", "H"],
        "right_option": 2
    }'),
    (5, '    {
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
}'),
    (6, '{
        "base_sentence": "Fernando", "options": ["O", "F", "S", "D", "E", "N", "R", "A", "Z", "P", "W", "I"]
    }'),
    (7, '    {
        "base_sentence": "Fernando",
        "options": ["Alberto", "Eduardo", "Enrique", "Fernando", "Emiliano"],
        "right_option": 3
    }'),
    (8, '    {
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
    }'),
    (9, '{
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
    }'),
    (10, '{
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
    }');

INSERT INTO educando_temas (id_educando, id_tema)
VALUES (1, 1), (1, 2);

/*
INSERT INTO educando_ejercicios (id_educando, id_ejercicio, fecha_asignacion, fecha_completado, tiempo_asignado)
VALUES (1, 1, '2025-07-21 14:30:00', '2025-07-21 14:40:00', '00:10:00');

/*Se debe utilizar la api para subir los puntajes por habilidad del ejercicio*/
*/

INSERT INTO discapacidad (nombre) VALUES ('Ninguna'), ('Auditiva');

INSERT INTO educando_discapacidades (id_educando, id_discapacidad) VALUES (1, 2);

INSERT INTO ruta_aprendizaje_discapacidades (id_ruta_aprendizaje, id_discapacidad ) VALUES (1, 2);

INSERT INTO recurso_discapacidades (id_recurso, id_discapacidad)
VALUES (1, 2), (2, 2), (3, 2), (4, 2);

INSERT INTO reactivo_discapacidades (id_reactivo, id_discapacidad)
VALUES (1, 1), (1, 2), (2, 1), (2, 2), (3, 1), (3, 2), (4, 2), (5, 2), (6, 2), (7, 2), (8, 2), (9, 2), (10, 2);

COMMIT;