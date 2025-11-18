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
-- NOTA: La columna se llama id_ruta según la entidad LearningPath
INSERT INTO ruta_aprendizaje (id_ruta, id_metodologia, id_perfil, nombre, descripcion, url_imagen, created_at, update_at)
VALUES 
    (1, 1, 1, 'Alfabetización en personas sordas', 'Ruta de aprendizaje diseñada específicamente para personas con discapacidad auditiva, utilizando recursos visuales y lenguaje de señas', 'https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581287/ICHEJA/ICHEJA/ruta_sordas.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 2, 2, 'Alfabetización Inicial', 'Ruta básica para iniciar el proceso de alfabetización', 'https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581287/ICHEJA/ICHEJA/ruta_inicial.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3, 3, 1, 'Alfabetización Inclusiva', 'Ruta diseñada para personas con diferentes necesidades educativas', 'https://res.cloudinary.com/dsiamqhuu/image/upload/v1751581287/ICHEJA/ICHEJA/ruta_inclusiva.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Cuadernillos (contenido) - Requiere rutas de aprendizaje
INSERT INTO contenido (id_cuadernillo, id_ruta_aprendizaje, nombre, descripcion, objetivo, url_imagen, created_at, update_at)
VALUES
    (1, 1, 'Cuadernillo Básico', 'Cuadernillo básico para alfabetización', 'Aprender lo básico', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 2, 'Cuadernillo Inicial', 'Cuadernillo inicial para alfabetización', 'Iniciar el aprendizaje', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3, 3, 'Cuadernillo Inclusivo', 'Cuadernillo inclusivo para diferentes necesidades', 'Aprendizaje inclusivo', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Unidades - Requiere cuadernillos
INSERT INTO unidad (id_unidad, nombre, descripcion, id_cuadernillo)
VALUES
    (1, 'Unidad 1', 'Primera unidad del cuadernillo básico', 1),
    (2, 'Unidad 1', 'Primera unidad del cuadernillo inicial', 2),
    (3, 'Unidad 1', 'Primera unidad del cuadernillo inclusivo', 3);

-- Temas - Requiere unidades
INSERT INTO tema (id_tema, nombre, id_unidad, created_at, update_at)
VALUES 
    (1, 'Introducción', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 'Caligrafía', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3, 'Abecedario', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (4, 'Nombre propio', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (5, 'Calendario', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Secuencias de temas
-- NOTA: La clave primaria es (id_tema, id_tema_siguiente), por lo que cada combinación debe ser única
-- Si diferentes rutas necesitan la misma secuencia, solo se inserta una vez
INSERT INTO secuencia_temas (id_ruta, id_tema, id_tema_siguiente)
VALUES
    (1, 1, 2),  -- Ruta 1: Introducción -> Caligrafía
    (1, 1, 3),  -- Ruta 1: Introducción -> Abecedario
    (1, 2, 4),  -- Ruta 1: Caligrafía -> Nombre propio
    (1, 3, 5),  -- Ruta 1: Abecedario -> Calendario
    (1, 4, 5),  -- Ruta 1: Nombre propio -> Calendario
    (2, 2, 3),  -- Ruta 2: Caligrafía -> Abecedario (diferente a ruta 1)
    (3, 3, 4);  -- Ruta 3: Abecedario -> Nombre propio (diferente a ruta 1)

INSERT INTO tipo_layouts (id_tipo_layout, nombre, created_at, update_at)
VALUES 
    (1, 'recurso', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 'reactivo', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO layout (id_layout, nombre, id_tipo_layout)
VALUES
    (1, 'Escritura', 2),
    (2, 'Columnas de cartas', 1),
    (3, 'Seleccion', 2),
    (4, 'Memorama con imagenes', 2),
    (5, 'Carrusel', 1),
    (6, 'Deletreo', 2),
    (7, 'Relacion simple', 2),
    (8, 'Relacion compleja', 2),
    (9, 'Lista', 1),
    (10, 'Relacion con imagenes', 2),
    (11, 'Relacion con enunciados', 2);

INSERT INTO recurso (id_recurso, nombre, id_tema, contenido, id_layout, created_at, update_at)
VALUES
    (1, 'Abecedario con señas', 3, '{
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
}', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 'Familia', 4, '{
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
    }', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3, 'Calendario - días', 5, '    {
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
    }', 9, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (4, 'Calendario - meses', 5, '    {
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
    }', 9, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO tema_recursos (id_tema, id_recurso)
VALUES (3, 1), (4, 2), (5, 3), (5, 4);

