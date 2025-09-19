# AprendIA - Sistema de Microservicios

Este proyecto implementa una arquitectura de microservicios completa para la plataforma educativa AprendIA. Utiliza Docker y Docker Compose para orquestar los diferentes servicios, facilitando un entorno de desarrollo y despliegue consistente.

## ✨ Características

- **API Gateway**: Punto de entrada único para todos los servicios utilizando NestJS.
- **Autenticación JWT**: Microservicio de autenticación seguro basado en NestJS que utiliza JSON Web Tokens.
- **Gestión de Ejercicios**: Microservicio para crear y gestionar ejercicios educativos.
- **Gestión de Registros**: Microservicio para el manejo de registros y estadísticas de estudiantes.
- **Gestión de Imágenes**: Microservicio para subir, gestionar y servir imágenes utilizando Quarkus y Cloudinary.
- **Evaluación de Voz**: Servicio de Python para análisis y evaluación de audio/voz.
- **Comparación de Imágenes**: Servicio de IA para comparar y analizar imágenes educativas.
- **Message Broker**: Sistema de mensajería con RabbitMQ para comunicación entre servicios.
- **Base de Datos PostgreSQL**: Contenedor de base de datos para persistir la información.
- **Orquestación con Docker**: Totalmente contenedorizado y gestionado a través de `docker compose`.

## Arquitectura

El proyecto está compuesto por los siguientes servicios:

### Servicios Core

- `api-gateway`: **API Gateway** desarrollado en NestJS que actúa como punto de entrada único y enruta las peticiones a los microservicios correspondientes.
- `auth-ms`: Microservicio de **autenticación** desarrollado en NestJS que maneja el registro, login de usuarios y generación de JWT.
- `exercise-service`: Microservicio de **ejercicios** desarrollado en NestJS para la gestión de ejercicios educativos.
- `record-service`: Microservicio de **registros** desarrollado en NestJS para el manejo de estadísticas y historial de estudiantes.

### Servicios de Soporte

- `cloudinary-service`: Microservicio desarrollado en **Quarkus** que se encarga de la comunicación con la API de Cloudinary para la gestión de archivos.
- `voice-evaluator-service`: Servicio desarrollado en **Python** para análisis y evaluación de audio/voz.
- `comparar-imagen-service`: Servicio de **IA en Python** para comparación y análisis de imágenes educativas.

### Infraestructura

- `auth-db`: Instancia de **PostgreSQL** que sirve como base de datos principal para todos los servicios.
- `rabbitmq`: Broker de mensajes **RabbitMQ** para comunicación asíncrona entre microservicios.

## 🚀 Empezando

Sigue estos pasos para levantar el entorno completo en tu máquina local.

### Prerrequisitos

Asegúrate de tener instalado lo siguiente:

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)

### Instalación

1. **Clona el repositorio:**

    ```bash
    git clone --recurse-submodules <URL_DEL_REPOSITORIO>
    cd backend-launcher
    ```

2. **Configura las variables de entorno:**

    Crea un archivo `.env` en la raíz del proyecto. Puedes copiar el archivo `.env.example` si existe, o crearlo desde cero con el siguiente contenido:

    ```env
    # JWT Configuration
    JWT_SECRET=tu_secreto_para_jwt
    JWT_EXPIRATION=1h
    ENCRYPTION_KEY=tu_llave_de_encriptacion

    # Database Configuration
    DB_PORT_EXT=5432
    DB_USERNAME=postgres
    DB_PASSWORD=1234567
    DB_NAME=authdb

    # RabbitMQ Configuration
    RABBITMQ_DEFAULT_USER=guest
    RABBITMQ_DEFAULT_PASS=guest
    BROKER_HOSTS=amqp://rabbitmq:5672

    # API Gateway Configuration
    API_GATEWAY_PORT_EXT=80

    # Cloudinary Service Configuration
    CLOUDINARY_PORT_EXT=8080
    CLOUDINARY_CLOUD_NAME=tu_cloud_name_de_cloudinary
    CLOUDINARY_API_KEY=tu_api_key_de_cloudinary
    CLOUDINARY_API_SECRET=tu_api_secret_de_cloudinary

    # Voice Evaluator Service Configuration
    VOICE_PORT_EXT=5000
    ```

    **Importante:** Reemplaza los valores `tu_*` con tus credenciales y secretos reales.

3. **Levanta los servicios:**

    Utiliza Docker Compose para construir las imágenes y levantar los contenedores.

    ```bash
    docker compose up --build -d
    ```

    El flag `-d` ejecuta los contenedores en segundo plano (detached mode).

## 🌐 URLs de los Servicios

Una vez que los contenedores estén en ejecución, los servicios estarán disponibles en las siguientes URLs:

### Servicios Públicos

- **API Gateway**: `http://localhost:80` (puerto configurable con `API_GATEWAY_PORT_EXT`)
- **Cloudinary Service**: `http://localhost:8080` (puerto configurable con `CLOUDINARY_PORT_EXT`)
- **Voice Evaluator Service**: `http://localhost:5000` (puerto configurable con `VOICE_PORT_EXT`)
- **Comparar Imagen Service**: `http://localhost:5001`

### Servicios de Infraestructura

- **PostgreSQL Database**: `localhost:5432` (puerto configurable con `DB_PORT_EXT`)
- **RabbitMQ Management Console**: `http://localhost:15672` (usuario: `guest`, contraseña: `guest`)
- **RabbitMQ AMQP**: `localhost:5672`

### Servicios Internos

Los siguientes servicios están disponibles solo dentro de la red de Docker:

- **Auth Microservice**: Solo accesible a través del API Gateway
- **Exercise Service**: Solo accesible a través del API Gateway  
- **Record Service**: Solo accesible a través del API Gateway

**Nota:** El API Gateway actúa como punto de entrada único para todos los microservicios internos, proporcionando enrutamiento y balanceado de carga.

## 🏗️ Comunicación entre Servicios

La arquitectura utiliza los siguientes patrones de comunicación:

### Comunicación Síncrona

- **API Gateway → Microservicios**: HTTP/REST para consultas directas
- **Cliente → API Gateway**: HTTP/REST como punto de entrada único

### Comunicación Asíncrona  

- **RabbitMQ**: Message broker para comunicación entre microservicios
- **Event-driven**: Los microservicios publican y consumen eventos de manera asíncrona

## 🛠️ Tecnologías

- **Backend Framework**: NestJS (Node.js + TypeScript)
- **Java Framework**: Quarkus (para el servicio de Cloudinary)
- **Python Services**: Flask/FastAPI (servicios de IA)
- **Base de Datos**: PostgreSQL 16.2
- **Message Broker**: RabbitMQ 3 con Management UI
- **Gestión de Archivos**: Cloudinary
- **Contenedorización**: Docker & Docker Compose
- **Autenticación**: JWT (JSON Web Tokens)

## 🔧 Comandos Útiles

### Gestión de Contenedores

```bash
# Levantar todos los servicios
docker compose up -d

# Levantar servicios específicos
docker compose up -d auth-db rabbitmq api-gateway

# Ver logs de un servicio específico
docker compose logs -f api-gateway

# Reconstruir y levantar servicios
docker compose up --build -d

# Detener todos los servicios
docker compose down

# Detener y eliminar volúmenes
docker compose down -v

# Ver estado de los contenedores
docker compose ps
```

### Desarrollo y Debug

```bash
# Acceder a un contenedor en ejecución
docker compose exec auth-ms bash
docker compose exec auth-db psql -U postgres -d authdb

# Ver logs en tiempo real
docker compose logs -f --tail=100

# Reiniciar un servicio específico
docker compose restart api-gateway
```

## 🚨 Troubleshooting

### Problemas Comunes

1. **Error de conexión a la base de datos:**
   - Verifica que el contenedor `auth-db` esté ejecutándose
   - Revisa las variables de entorno de conexión a BD

2. **RabbitMQ no se conecta:**
   - Asegúrate de que el contenedor `rabbitmq` esté levantado antes que los microservicios
   - Verifica la variable `BROKER_HOSTS`

3. **Puertos ocupados:**
   - Cambia los puertos externos en el archivo `.env`
   - Verifica que no haya otros servicios usando los mismos puertos

4. **Problemas con Cloudinary:**
   - Verifica que las credenciales de Cloudinary sean correctas
   - Asegúrate de que el servicio `cloudinary-service` esté ejecutándose

### Logs y Monitoreo

```bash
# Ver todos los logs
docker compose logs

# Logs de un servicio específico con timestamp
docker compose logs -t auth-ms

# Seguir logs en tiempo real
docker compose logs -f
```

## ⚙️ Gestión de los Contenedores

- **Ver logs en tiempo real:**

    ```bash
    docker compose logs -f
    ```

    O para un servicio específico:

    ```bash
    docker compose logs -f auth-ms
    ```

- **Detener los servicios:**

    ```bash
    docker compose down
    ```

- **Acceder a la base de datos:**
    Puedes conectarte a la base de datos PostgreSQL usando tu cliente de base de datos preferido en `localhost:5432` con las credenciales que definiste en el archivo `.env`.
