-- =====================================================
-- DATOS PARA EJERCICIOS, REACTIVOS (TEMPLATES) Y HABILIDADES
-- IMPORTANTE: Requiere layouts y recursos (02-learning-paths.sql)
-- =====================================================

INSERT INTO reactivo (id_reactivo, titulo, instrucciones, tiempo_sugerido, id_layout, id_tema, created_at, update_at)
VALUES
    (1, 'Aprendiendo a hacer Líneas Rectas Horizontales', 'Desliza tu lápiz de izquierda a derecha, siguiendo las líneas punteadas y las flechas.', 10, 1, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 'Aprendiendo a hacer Líneas Inclinadas', 'Sigue las flechas, moviendo tu lápiz de arriba hacia abajo a la izquierda sobre las líneas punteadas.', 10, 1, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3, 'Aprendiendo a hacer Líneas Declinadas', 'Desliza tu lápiz hacia abajo y hacia la derecha, guiándote por las líneas punteadas y las flechas.', 10, 1, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (4, '¡De Señas a Letras: Descifra el Alfabeto!', 'Mira con atención la seña que te presentamos. Luego selecciona la letra que corresponda a la seña.', 10, 3, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (5, '¡Desafía tu Memoria: El Juego del Memorama!', 'Toca y encuentra el par.', 10, 4, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (6, 'Aprendiendo mi propio nombre', 'Usa tu dedo para tocar cada letra de tu nombre, una por una.', 10, 6, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (7, 'Aprendiendo y recordado mi nombre', 'Busca tu nombre en la lista y, cuando lo veas, selecciónalo.', 10, 7, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (8, 'Aprendiendo sobre mi familia', 'Usa tu dedo para unir cada pregunta sobre tu familia con el nombre correcto.', 10, 8, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (9, '¡Conecta el Día con su Seña!', 'Arrastra el nombre del día y colócalo junto a la seña correcta.', 10, 10, 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (10, '¿Qué Día Fue, Es y Será?', 'Arrastra el día correcto según: ayer, hoy y mañana.', 10, 11, 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


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

INSERT INTO habilidad (id_agenda, nombre)
VALUES (1, 'Grafomotricidad'), (2, 'Memoria'), (3, 'Asociación'), (4, 'Identificación'), (5, 'Comprensión');

INSERT INTO reactivo_habilidades (id_reactivo, id_habilidad, peso)
VALUES
    (1, 1, 1.00),
    (2, 1, 1.00),
    (3, 1, 1.00),
    (4, 3, 0.60),
    (4, 2, 0.40),
    (5, 3, 0.50),
    (5, 2, 0.50),
    (6, 2, 0.40),
    (6, 3, 0.60),
    (7, 3, 0.20),
    (7, 4, 0.60),
    (7, 2, 0.20),
    (8, 5, 0.70),
    (8, 4, 0.30);

INSERT INTO ejercicio (id_ejercicio, contexto, id_reactivo, created_at, update_at)
VALUES
    (1, '{
  "image_reference": "http://res.cloudinary.com/dsiamqhuu/image/upload/v1751571141/ICHEJA/ICHEJA/T1_E1.jpg",
  "imagen_base": "http://res.cloudinary.com/dsiamqhuu/image/upload/v1751571597/ICHEJA/ICHEJA/T1_M1.jpg"
}', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, '{
        "image_reference": "https://res.cloudinary.com/dsiamqhuu/image/upload/v1751571204/ICHEJA/ICHEJA/T1_E2.jpg",
        "imagen_base":
            "http://res.cloudinary.com/dsiamqhuu/image/upload/v1751571550/ICHEJA/ICHEJA/T1_M2.jpg"
    }', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3, '{
        "image_reference": "http://res.cloudinary.com/dsiamqhuu/image/upload/v1751571261/ICHEJA/ICHEJA/T1_E3.jpg",
        "imagen_base":
            "http://res.cloudinary.com/dsiamqhuu/image/upload/v1751571574/ICHEJA/ICHEJA/T1_M3.jpg"
    }', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (4, '{
        "image_reference": "http://res.cloudinary.com/dsiamqhuu/image/upload/v1751573029/ICHEJA/ICHEJA/T2_R1_29.svg",
        "options_types": "texto",
        "options": ["m", "E", "Z"],
        "right_option": 2
    }', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (5, '{
        "image_reference": "http://res.cloudinary.com/dsiamqhuu/image/upload/v1751572978/ICHEJA/ICHEJA/T2_R1_15.svg",
        "options_types": "texto",
        "options": ["A", "N", "r"],
        "right_option": 1
    }', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (6, '{
        "image_reference": "http://res.cloudinary.com/dsiamqhuu/image/upload/v1751581457/ICHEJA/ICHEJA/T2_R1_2.svg",
        "options_types": "texto",
        "options": ["B", "i", "C"],
        "right_option": 0
    }', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (7, '{
        "image_reference": "http://res.cloudinary.com/dsiamqhuu/image/upload/v1751572972/ICHEJA/ICHEJA/T2_R1_13.svg",
        "options_types": "texto",
        "options": ["Y", "l", "Z"],
        "right_option": 1
    }', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (8, '{
        "image_reference": "http://res.cloudinary.com/dsiamqhuu/image/upload/v1751572956/ICHEJA/ICHEJA/T2_R1_9.svg",
        "options_types": "texto",
        "options": ["x", "o", "H"],
        "right_option": 2
    }', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
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
}', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (10, '{
        "base_sentence": "Fernando", "options": ["O", "F", "S", "D", "E", "N", "R", "A", "Z", "P", "W", "I"]
    }', 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (11, '{
        "base_sentence": "Fernando",
        "options": ["Alberto", "Eduardo", "Enrique", "Fernando", "Emiliano"],
        "right_option": 3
    }', 7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
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
    }', 8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
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
    }', 9, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
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
    }', 10, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Comentado: Se debe utilizar la api para subir los puntajes por habilidad del ejercicio
-- NOTA: educando_temas se inserta en 08-students.sql
-- INSERT INTO educando_ejercicios (id_educando_ejercicio, id_educando, id_ejercicio, fecha_asignacion, fecha_completado, tiempo_asignado, por_educador)
-- VALUES (1, 1, 1, '2025-07-21 14:30:00', '2025-07-21 14:40:00', '00:10:00', true);
