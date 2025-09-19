# Servicio de Autenticación - Documentación de Entidades

## Resumen del Servicio

El servicio de autenticación es un microservicio basado en NestJS que maneja la autenticación de usuarios mediante credenciales (CURP/password) y tokens QR. Utiliza JWT para la generación de tokens y se comunica con otros servicios mediante AMQP.

## Entidades del Dominio

### 1. Persona (PersonaI)
**Tipo**: Interface de dominio  
**Propósito**: Representa la información personal de un usuario del sistema

**Propiedades**:
- `id_persona: number` - Identificador único de la persona
- `primer_nombre: string` - Primer nombre
- `segundo_nombre?: string` - Segundo nombre (opcional)
- `apellido_paterno: string` - Apellido paterno
- `apellido_materno: string` - Apellido materno
- `curp: string` - Clave Única de Registro de Población
- `numero_ine: string` - Número de credencial de elector
- `fecha_nacimiento: Date` - Fecha de nacimiento
- `genero: GenderEnum` - Género (M/F)
- `codigo_postal: string` - Código postal
- `estado: string` - Estado
- `municipio: string` - Municipio
- `localidad: string` - Localidad
- `vialidad_nombre: string` - Nombre de la vialidad
- `id_vialidad_tipo: number` - ID del tipo de vialidad
- `asentamiento: string` - Asentamiento
- `id_asentamiento_tipo: number` - ID del tipo de asentamiento
- `password: string` - Contraseña encriptada

**Relaciones**:
- Tiene muchos `PersonaRol` (relación One-to-Many)

### 2. Rol (RolI)
**Tipo**: Interface de dominio  
**Propósito**: Define los roles disponibles en el sistema

**Propiedades**:
- `id_rol: number` - Identificador único del rol
- `nombre: string` - Nombre del rol

**Relaciones**:
- Tiene muchos `PersonaRol` (relación One-to-Many)

### 3. PersonaRol (PersonaRolI)
**Tipo**: Interface de dominio  
**Propósito**: Tabla de relación entre personas y roles (Many-to-Many)

**Propiedades**:
- `id_persona_rol: number` - Identificador único de la relación
- `id_persona: number` - ID de la persona
- `id_rol: number` - ID del rol
- `persona?: PersonaI` - Objeto persona (opcional, eager loading)
- `rol?: RolI` - Objeto rol (opcional, eager loading)

**Relaciones**:
- Pertenece a una `Persona` (relación Many-to-One)
- Pertenece a un `Rol` (relación Many-to-One)

### 4. TokenPayload (TokenPayloadI)
**Tipo**: Interface de dominio  
**Propósito**: Estructura del payload del token JWT

**Propiedades**:
- `id_persona: number` - ID de la persona
- `nombre: string` - Nombre completo formateado
- `role_name: string` - Nombre del rol
- `disability_name?: string` - Nombre de discapacidad (opcional)
- `disability_id?: number` - ID de discapacidad (opcional)
- `learning_path_id?: number` - ID de ruta de aprendizaje (opcional)
- `iat?: number` - Timestamp de emisión (opcional)
- `exp?: number` - Timestamp de expiración (opcional)

**Relaciones**:
- No tiene relaciones directas (es un DTO para tokens)

## Entidades de Base de Datos (TypeORM)

### 1. PersonaEntity
**Tipo**: Entidad de TypeORM  
**Tabla**: `persona`  
**Implementa**: `PersonaI`

**Características**:
- Mapeo completo a la tabla `persona`
- Relación One-to-Many con `PersonaRolEntity`
- Validaciones de longitud y tipos de datos
- Campos únicos: `curp`

### 2. RolEntity
**Tipo**: Entidad de TypeORM  
**Tabla**: `rol`  
**Implementa**: `RolI`

**Características**:
- Mapeo completo a la tabla `rol`
- Relación One-to-Many con `PersonaRolEntity`
- Campo `nombre` único

### 3. PersonaRolEntity
**Tipo**: Entidad de TypeORM  
**Tabla**: `persona_rol`  
**Implementa**: `PersonaRolI`

**Características**:
- Tabla de relación Many-to-Many
- Eager loading de `persona` y `rol`
- Foreign keys a `persona` y `rol`

## Enumeraciones

### 1. GenderEnum
**Tipo**: Enum  
**Propósito**: Define los valores de género

**Valores**:
- `MASCULINE = 'M'` - Masculino
- `FEMININE = 'F'` - Femenino

## Endpoints del Servicio

El servicio utiliza **MessagePattern** para comunicación entre microservicios (AMQP), no endpoints HTTP tradicionales.

### 1. Login con Credenciales
**Comando**: `auth.loginCredentials`  
**Método**: `loginWithCredentials`  
**DTO de entrada**: `LoginCredentialsDto`  
**DTO de salida**: `LoginResponseAdapter`

**Funcionalidad**:
- Valida credenciales (CURP y password)
- Genera token JWT
- Obtiene información de discapacidad del servicio de preferencias
- Retorna token y payload

### 2. Login con QR
**Comando**: `auth.loginStudent`  
**Método**: `loginWithQR`  
**DTO de entrada**: `LoginQrDto`  
**DTO de salida**: `LoginResponseAdapter`

**Funcionalidad**:
- Desencripta token QR
- Valida token JWT
- Genera nuevo token de sesión
- Obtiene información de discapacidad
- Retorna token y payload

### 3. Validación de Token
**Comando**: `auth.validateToken`  
**Método**: `validateToken`  
**DTO de entrada**: `ValidateTokenDto`  
**DTO de salida**: `ValidateTokenResponseAdapter`

**Funcionalidad**:
- Decodifica token JWT
- Verifica validez y expiración
- Retorna estado de validación y payload

## DTOs (Data Transfer Objects)

### 1. LoginCredentialsDto
**Propósito**: Datos de entrada para login con credenciales
- `curp: string` - CURP del usuario
- `password: string` - Contraseña

### 2. LoginQrDto
**Propósito**: Datos de entrada para login con QR
- `encryptedToken: string` - Token encriptado del QR

### 3. ValidateTokenDto
**Propósito**: Datos de entrada para validación de token
- `token: string` - Token JWT a validar

## Adapters (Adaptadores de Respuesta)

### 1. LoginResponseAdapter
**Propósito**: Respuesta de login exitoso
- `token: string` - Token JWT generado
- `payload: TokenPayloadI` - Payload del token

### 2. ValidateTokenResponseAdapter
**Propósito**: Respuesta de validación de token
- `isValid: boolean` - Si el token es válido
- `isExpired: boolean` - Si el token está expirado
- `payload: TokenPayloadI | null` - Payload del token (si es válido)
- `message: string` - Mensaje descriptivo

## Servicios Externos

### Servicio de Preferencias
**Comando**: `findByStudent`  
**Propósito**: Obtener información de discapacidad del estudiante
**Respuesta**: Información de discapacidad y ruta de aprendizaje

## Configuración del Servicio

- **Nombre del servicio**: `AUTH_SERVICE`
- **Cola AMQP**: `AUTH_QUEUE`
- **Tecnología**: NestJS + TypeORM + JWT + AMQP
- **Base de datos**: Relacional (PostgreSQL/MySQL)

## Flujo de Autenticación

1. **Login con Credenciales**:
   - Validar CURP y password
   - Obtener información de discapacidad
   - Generar token JWT
   - Retornar respuesta

2. **Login con QR**:
   - Desencriptar token QR
   - Validar token JWT
   - Obtener información de discapacidad
   - Generar nuevo token de sesión
   - Retornar respuesta

3. **Validación de Token**:
   - Decodificar token
   - Verificar validez y expiración
   - Retornar estado de validación
