# Backend para Aplicación de Recetas

Este es el backend de una aplicación de recetas, desarrollado con Spring Boot. Proporciona una API RESTful para gestionar recetas, usuarios, ingredientes y otros aspectos relacionados. Está diseñado siguiendo una arquitectura en capas para mantener el código organizado y escalable.

## Tecnologías Utilizadas

*   **Lenguaje**: Java 17
*   **Framework**: Spring Boot 3.2.5
*   **Acceso a Datos**: Spring Data JPA con MySQL Connector/J para la persistencia de datos en una base de datos MySQL.
*   **Mapeo**: MapStruct para la conversión eficiente y segura entre entidades de base de datos (`model/`) y objetos de transferencia de datos (`dto/`).
*   **Documentación API**: Springdoc OpenAPI (integrado con Swagger UI) para generar documentación interactiva de los endpoints de la API.
*   **Base de Datos**: MySQL. La configuración de conexión se encuentra en `application.properties` (o `application.yml`), y los scripts SQL para la estructura de la base de datos están en la carpeta `DataBase/`.

## Seguridad y Autenticación (JWT)

La aplicación implementa un sistema de autenticación y autorización basado en JSON Web Tokens (JWT) utilizando Spring Security.

*   **Spring Security**: Proporciona la base para la seguridad de la aplicación, manejando la autenticación y la autorización de las solicitudes.
*   **JWT (JSON Web Tokens)**: Se utilizan para la autenticación sin estado. Cuando un usuario inicia sesión, se genera un JWT que se envía al cliente. Este token se incluye en las solicitudes posteriores para verificar la identidad del usuario y sus permisos.

### Componentes Clave de Seguridad:

*   `JwtUtil.java`: Clase de utilidad para la generación, validación y extracción de información de los JWTs. Contiene la lógica para firmar y verificar los tokens.
*   `JwtAuthenticationFilter.java`: Un filtro de Spring Security que intercepta las solicitudes HTTP, extrae el JWT del encabezado de autorización y valida el token. Si el token es válido, autentica al usuario en el contexto de seguridad de Spring.
*   `SecurityConfig.java`: Clase de configuración principal de Spring Security. Define las reglas de autorización para los diferentes endpoints (qué roles pueden acceder a qué rutas), configura el proveedor de autenticación y añade el `JwtAuthenticationFilter` a la cadena de filtros de seguridad.
*   `AuthController.java`: Controlador REST que maneja las solicitudes de registro (`/auth/register`) e inicio de sesión (`/auth/authenticate`), generando y devolviendo JWTs a los usuarios autenticados.

### Flujo de Autenticación:

1.  **Registro/Login**: Un usuario envía sus credenciales (nombre de usuario y contraseña) al endpoint `/auth/register` o `/auth/authenticate`.
2.  **Generación de JWT**: Si las credenciales son válidas, la aplicación genera un JWT utilizando `JwtUtil` y lo devuelve al cliente.
3.  **Acceso a Recursos Protegidos**: El cliente incluye el JWT en el encabezado `Authorization` (como `Bearer <token>`) de las solicitudes a los endpoints protegidos.
4.  **Validación del JWT**: El `JwtAuthenticationFilter` intercepta la solicitud, extrae y valida el JWT.
5.  **Autorización**: Spring Security verifica si el usuario autenticado (basado en el JWT) tiene los permisos necesarios para acceder al recurso solicitado, según las reglas definidas en `SecurityConfig`.

## Estructura del Proyecto

El proyecto sigue una arquitectura en capas típica de Spring Boot, donde cada paquete tiene una responsabilidad específica:

*   `src/main/java/com/library/recetas/`: Contiene el código fuente principal de la aplicación.
    *   `App.java`: La clase principal de la aplicación Spring Boot. Es el punto de entrada que inicia el servidor de aplicaciones y carga todo el contexto.
    *   `config/`: Contiene clases de configuración.
        *   `MapperConfig.java`: Configuración específica para el funcionamiento de MapStruct.
        *   `OpenApiConfig.java`: Configuración para la generación y personalización de la documentación OpenAPI (Swagger).
    *   `controller/`: Los "front-doors" de la API. Estos controladores (ej. `UsuarioController`, `RecetaController`) reciben las solicitudes HTTP de los clientes (navegadores, apps), las validan y delegan la lógica de negocio a la capa de servicio.
    *   `dto/` (Data Transfer Objects): Objetos que se utilizan para transferir datos entre capas, especialmente entre el backend y el cliente. Ayudan a exponer una estructura de datos controlada y desacoplada de las entidades de base de datos. Son como "paquetes" de datos para la comunicación.
    *   `exception/`: Clases de excepción personalizadas. Permiten manejar errores de forma más específica y controlada (ej. `ResourceNotFoundException` cuando un recurso no se encuentra, `InvalidDataException` para datos incorrectos).
    *   `mapper/`: Mapeadores de MapStruct. Actúan como traductores entre las entidades JPA (`model/`) y los DTOs (`dto/`). Facilitan la conversión de datos de un formato a otro de manera automática y eficiente.
    *   `model/`: Entidades JPA (Java Persistence API). Son clases Java que representan las tablas de la base de datos. Utilizan anotaciones como `@Entity`, `@Table`, `@Column` para definir la estructura de los datos y su mapeo a la base de datos.
    *   `repository/`: Repositorios de Spring Data JPA. Son interfaces que extienden `JpaRepository`. Proporcionan métodos listos para usar para interactuar con la base de datos (CRUD: Crear, Leer, Actualizar, Eliminar) para una entidad específica. Son los "gestores de datos".
    *   `service/`: La capa de lógica de negocio. Los servicios (ej. `UsuarioService`, `RecetaService`) contienen la lógica principal de la aplicación. Orquestan las operaciones, aplican reglas de negocio y utilizan los repositorios para acceder y manipular los datos. Son el "cerebro" de la aplicación.
*   `src/main/resources/`: Contiene archivos de configuración no Java, como `application.properties` (o `application.yml`) para configurar la aplicación, la conexión a la base de datos, puertos, etc.
*   `DataBase/`: Contiene scripts SQL para la creación y/o inicialización de la base de datos, facilitando la configuración del entorno de desarrollo.

## Flujo de la Aplicación

1.  **Solicitud del Cliente**: Un cliente (navegador, aplicación móvil) envía una solicitud HTTP (GET, POST, PUT, DELETE) a un endpoint específico expuesto por un controlador (`controller/`).
2.  **Controlador**: El controlador recibe la solicitud. Realiza validaciones iniciales y luego llama al método correspondiente en la capa de servicio (`service/`) para procesar la solicitud.
3.  **Servicio**: El servicio contiene la lógica de negocio principal. Puede realizar validaciones más complejas, orquestar varias operaciones, interactuar con otros servicios y utiliza los repositorios (`repository/`) para acceder o modificar datos en la base de datos.
4.  **Repositorio**: El repositorio interactúa directamente con la base de datos (MySQL en este caso) para realizar operaciones de persistencia (guardar, buscar, actualizar, eliminar registros).
5.  **Entidad (`model/`)**: Las entidades JPA representan la estructura de los datos en la base de datos. El repositorio trabaja con estas entidades.
6.  **Mapeo y DTOs (`mapper/`, `dto/`)**: Antes de devolver una respuesta al cliente, los datos de las entidades JPA se convierten a DTOs utilizando los mappers de MapStruct. Esto asegura que la API exponga una estructura de datos limpia y controlada, separada de la estructura interna de las entidades JPA.
7.  **Respuesta**: El controlador recibe los DTOs del servicio y los devuelve al cliente como respuesta HTTP, típicamente en formato JSON.

## Base de Datos

La aplicación está configurada para usar una base de datos MySQL. Los detalles de conexión (URL, usuario, contraseña) se encuentran típicamente en `src/main/resources/application.properties` o `application.yml`. Los scripts SQL para la estructura de la base de datos se encuentran en la carpeta `DataBase/`, lo que facilita la configuración del entorno de desarrollo.

## Documentación de la API (Swagger UI)

La integración de Springdoc OpenAPI genera automáticamente documentación interactiva para la API. Para acceder a ella, ejecuta la aplicación y navega a la ruta `/swagger-ui.html` (ej. `http://localhost:8081/swagger-ui.html`). Podrás ver todos los endpoints disponibles, sus parámetros, y probarlos directamente desde la interfaz, lo cual es muy útil para entender y consumir la API.

## Cómo Ejecutar la Aplicación

1.  **Prerrequisitos**:
    *   Java Development Kit (JDK) 17 o superior instalado.
    *   Maven instalado.
    *   Servidor MySQL en ejecución y la base de datos creada (usando los scripts de la carpeta `DataBase/`).
    *   Configurar las credenciales de la base de datos en `src/main/resources/application.properties` (o `application.yml`).

2.  **Compilar y Ejecutar con Maven**:
    Desde la raíz del proyecto (`d:\PROYECTOS\Backend-Recetas\recetas`), ejecuta el siguiente comando en la terminal:
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```
    Alternativamente, puedes ejecutar la clase `App.java` directamente desde tu IDE.

3.  **Acceder a la API**: Una vez que la aplicación esté en ejecución, los endpoints estarán disponibles en `http://localhost:8081` (o el puerto configurado en `application.properties`).
