# AprendIA API - Docker Setup

Este proyecto incluye una configuración completa de Docker para ejecutar la aplicación Spring Boot con PostgreSQL.

## Archivos de configuración

- `docker-compose.yml`: Configuración de servicios (BD + API)
- `Dockerfile`: Imagen de la aplicación Spring Boot
- `env.example`: Variables de entorno de ejemplo

## Servicios incluidos

### 1. Base de datos (db-aprend-ia)
- **Imagen**: pgvector/pgvector:pg16
- **Puerto**: 5432 (configurable)
- **Base de datos**: authdb
- **Usuario**: postgres
- **Contraseña**: 1234567

### 2. Aplicación Spring Boot (aprendia-api)
- **Puerto**: 8080 (configurable)
- **Context path**: /api
- **Health check**: /api/actuator/health

## Cómo usar

### 1. Configurar variables de entorno

Copia el archivo de ejemplo y modifica las variables según necesites:

```bash
cp env.example .env
```

Edita el archivo `.env` con tus valores:

```env
# Configuración de la base de datos
DB_USERNAME=postgres
DB_PASSWORD=tu_password_seguro
DB_NAME=authdb
DB_PORT_EXT=5432

# Configuración de la aplicación
APP_PORT=8080

# Configuración JWT
JWT_SECRET=tu_jwt_secret_muy_seguro
JWT_EXPIRATION=3600

# Configuración de encriptación
ENCRYPTION_SECRET=tu_encryption_secret_muy_seguro
```

### 2. Construir y ejecutar

```bash
# Construir y ejecutar todos los servicios
docker-compose up --build

# Ejecutar en segundo plano
docker-compose up -d --build

# Ver logs
docker-compose logs -f

# Ver logs de un servicio específico
docker-compose logs -f aprendia-api
```

### 3. Comandos útiles

```bash
# Detener servicios
docker-compose down

# Detener y eliminar volúmenes (¡CUIDADO! Esto borra la BD)
docker-compose down -v

# Reconstruir solo la aplicación
docker-compose up --build aprendia-api

# Acceder a la base de datos
docker-compose exec db-aprend-ia psql -U postgres -d authdb

# Ver estado de los servicios
docker-compose ps

# Ver logs en tiempo real
docker-compose logs -f
```

## Acceso a la aplicación

- **API**: http://localhost:8080/api
- **Swagger UI**: http://localhost:8080/api/swagger-ui
- **Health Check**: http://localhost:8080/api/actuator/health
- **Base de datos**: localhost:5432

## Características incluidas

- ✅ Health checks para ambos servicios
- ✅ Red interna para comunicación entre servicios
- ✅ Volúmenes persistentes para la base de datos
- ✅ Variables de entorno configurables
- ✅ Dependencias entre servicios (API espera a que BD esté lista)
- ✅ Logs detallados para debugging
- ✅ Configuración de seguridad JWT
- ✅ Soporte para pgvector (embeddings)

## Troubleshooting

### La aplicación no puede conectarse a la base de datos
- Verifica que la base de datos esté corriendo: `docker-compose ps`
- Revisa los logs: `docker-compose logs db-aprend-ia`
- Asegúrate de que las variables de entorno estén correctas

### Error de permisos en Docker
- En Linux/Mac, puede necesitar `sudo` para algunos comandos
- En Windows, asegúrate de que Docker Desktop esté ejecutándose

### Puerto ya en uso
- Cambia el puerto en el archivo `.env` (ej: `APP_PORT=8081`)
- O detén el servicio que esté usando el puerto

### Reconstruir desde cero
```bash
docker-compose down -v
docker-compose up --build
```
