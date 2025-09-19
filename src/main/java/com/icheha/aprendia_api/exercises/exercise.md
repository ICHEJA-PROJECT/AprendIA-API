# Servicio de Ejercicios - Documentación de Entidades

## Descripción General
Este servicio está diseñado con **Arquitectura Hexagonal** (Clean Architecture) para la gestión de ejercicios educativos, templates, layouts, temas y rutas de aprendizaje.

## Arquitectura del Servicio

### Estructura de Capas
```
src/
├── core/                    # Capa de configuración y utilidades centrales
├── exercises/              # Módulo de ejercicios
├── layouts/                # Módulo de layouts
├── templates/              # Módulo de templates
├── topics/                 # Módulo de temas
└── shared/                 # Utilidades compartidas
```

### Patrón de Arquitectura por Módulo
Cada módulo sigue el patrón hexagonal con las siguientes capas:

```
módulo/
├── domain/                 # Capa de Dominio (Entidades de Negocio)
│   ├── entities/          # Entidades de dominio
│   ├── entitiesI/         # Interfaces de entidades
│   └── repositories/      # Interfaces de repositorios
├── data/                  # Capa de Datos (Infraestructura)
│   ├── entities/          # Entidades de TypeORM
│   ├── dtos/              # Data Transfer Objects
│   └── repositories/      # Implementaciones de repositorios
├── services/              # Capa de Aplicación (Casos de Uso)
└── controllers/           # Capa de Presentación (API REST)
```

## Entidades del Sistema

### 🎯 **Módulo de Ejercicios (Exercises)**
**Ubicación en Arquitectura**: `src/exercises/data/entities/`

1. **`ExerciseEntity`** (`ejercicio`)
   - **Propósito**: Representa un ejercicio específico
   - **Tabla**: `ejercicio`
   - **Campos principales**:
     - `id`: Identificador único (PK)
     - `context`: Contexto del ejercicio (JSONB)
     - `template`: Relación con el template del ejercicio

### 🎨 **Módulo de Layouts**
**Ubicación en Arquitectura**: `src/layouts/data/entities/`

2. **`LayoutEntity`** (`layout`)
   - **Propósito**: Define la estructura visual de recursos y templates
   - **Tabla**: `layout`
   - **Campos principales**:
     - `id`: Identificador único (PK)
     - `name`: Nombre del layout
     - `attributes`: Atributos del layout (JSONB)
     - `typeLayout`: Tipo de layout asociado

3. **`TypeLayoutEntity`** (`tipo_layouts`)
   - **Propósito**: Categoriza los tipos de layouts disponibles
   - **Tabla**: `tipo_layouts`
   - **Campos principales**:
     - `id`: Identificador único (PK)
     - `name`: Nombre del tipo de layout

### 📋 **Módulo de Templates**
**Ubicación en Arquitectura**: `src/templates/data/entities/`

4. **`TemplateEntity`** (`reactivo`)
   - **Propósito**: Define plantillas de ejercicios reutilizables
   - **Tabla**: `reactivo`
   - **Campos principales**:
     - `id`: Identificador único (PK)
     - `title`: Título del template
     - `instructions`: Instrucciones del ejercicio
     - `suggestTime`: Tiempo sugerido para completar
     - `topic`: Tema asociado
     - `layout`: Layout utilizado

5. **`SkillEntity`** (`habilidad`)
   - **Propósito**: Define habilidades que se pueden evaluar
   - **Tabla**: `habilidad`
   - **Campos principales**:
     - `id`: Identificador único (PK)
     - `name`: Nombre de la habilidad

6. **`TemplateSkillEntity`** (`reactivo_habilidades`)
   - **Propósito**: Relaciona templates con habilidades y sus porcentajes
   - **Tabla**: `reactivo_habilidades`
   - **Campos principales**:
     - `templateId`: ID del template (PK compuesta)
     - `skillId`: ID de la habilidad (PK compuesta)
     - `porcentage`: Porcentaje de la habilidad en el template
     - `flag`: Bandera booleana

7. **`TemplateInstructionMediaEntity`** (`reactivo_instruccion_media`)
   - **Propósito**: Asocia medios de instrucción con templates
   - **Tabla**: `reactivo_instruccion_media`
   - **Campos principales**:
     - `templateId`: ID del template (PK compuesta)
     - `typeMediaId`: ID del tipo de media (PK compuesta)
     - `pathMedia`: Ruta del archivo de media

8. **`TypeInstructionMediaEntity`** (`tipo_instruccion_media`)
   - **Propósito**: Define tipos de medios de instrucción
   - **Tabla**: `tipo_instruccion_media`
   - **Campos principales**:
     - `id`: Identificador único (PK)
     - `name`: Nombre del tipo de media

### 📖 **Módulo de Topics (Temas)**
**Ubicación en Arquitectura**: `src/topics/data/entities/`

9. **`TopicEntity`** (`tema`)
   - **Propósito**: Representa temas o materias de estudio
   - **Tabla**: `tema`
   - **Campos principales**:
     - `id`: Identificador único (PK)
     - `name`: Nombre del tema
     - `resources`: Recursos asociados
     - `templates`: Templates del tema

10. **`ResourceEntity`** (`recurso`)
    - **Propósito**: Contiene contenido educativo
    - **Tabla**: `recurso`
    - **Campos principales**:
      - `id`: Identificador único (PK)
      - `title`: Título del recurso
      - `content`: Contenido del recurso (JSONB)
      - `layout`: Layout utilizado

11. **`TopicResourceEntity`** (`tema_recursos`)
    - **Propósito**: Tabla de relación muchos a muchos entre temas y recursos
    - **Tabla**: `tema_recursos`
    - **Campos principales**:
      - `topicId`: ID del tema (PK compuesta)
      - `resourceId`: ID del recurso (PK compuesta)

12. **`TopicSequenceEntity`** (`secuencia_temas`)
    - **Propósito**: Define el orden de secuencia entre temas
    - **Tabla**: `secuencia_temas`
    - **Campos principales**:
      - `currentTopicId`: ID del tema actual (PK compuesta)
      - `nextTopicId`: ID del tema siguiente (PK compuesta)
      - `learningPath`: Ruta de aprendizaje asociada

13. **`LearningPathEntity`** (`ruta_aprendizaje`)
    - **Propósito**: Define rutas de aprendizaje estructuradas
    - **Tabla**: `ruta_aprendizaje`
    - **Campos principales**:
      - `id`: Identificador único (PK)
      - `name`: Nombre de la ruta de aprendizaje
      - `sequences`: Secuencias de temas

## Relaciones Principales

### Diagrama de Relaciones
```
LearningPath (1) ──→ (N) TopicSequence (N) ──→ (1) Topic
    │                                                      │
    └──────────────────────────────────────────────────────┘
                                                           │
Topic (N) ──→ (N) Resource ──→ (1) Layout ──→ (1) TypeLayout
    │
    └──→ (N) Template ──→ (1) Layout
            │
            ├──→ (N) Exercise
            ├──→ (N) TemplateSkill ──→ (1) Skill
            └──→ (N) TemplateInstructionMedia ──→ (1) TypeInstructionMedia
```

## Tecnologías Utilizadas

- **Framework**: NestJS
- **ORM**: TypeORM
- **Base de Datos**: PostgreSQL (inferido por el uso de JSONB)
- **Arquitectura**: Hexagonal (Clean Architecture)
- **Lenguaje**: TypeScript

## Estructura de Archivos por Entidad

Cada entidad sigue el patrón:
```
módulo/
├── data/entities/entidad.entity.ts          # Entidad de TypeORM
├── domain/entities/Entidad.ts               # Entidad de dominio
├── domain/entitiesI/EntidadI.ts            # Interface de entidad
├── data/dtos/create-entidad.dto.ts          # DTO para creación
├── data/repositories/entidad.repository.impl.ts  # Implementación del repositorio
├── domain/repositories/EntidadRepository.ts # Interface del repositorio
└── services/entidad.service.ts              # Servicio de aplicación
```

## Resumen
Este servicio maneja **13 entidades propias** distribuidas en **4 módulos principales**, implementando una arquitectura hexagonal que separa claramente las responsabilidades entre la capa de dominio, datos y aplicación, facilitando el mantenimiento, testing y escalabilidad del sistema.
