# Backend para Aplicación de Recetas

Este es el backend de una aplicación de recetas, desarrollado con Spring Boot. Proporciona una API RESTful para gestionar recetas, usuarios, ingredientes y otros aspectos relacionados. Está diseñado siguiendo una arquitectura en capas para mantener el código organizado y escalable.

## Tecnologías Utilizadas

*   **Lenguaje**: Java 17
*   **Framework**: Spring Boot 3.2.5
*   **Acceso a Datos**: Spring Data JPA con MySQL Connector/J.
*   **Mapeo**: MapStruct para la conversión eficiente y segura entre entidades (`model/`) y DTOs (`dto/`).
*   **Migraciones de Base de Datos**: **Flyway** para un control de versiones del esquema de la base de datos.
*   **Documentación API**: Springdoc OpenAPI (integrado con Swagger UI).
*   **Base de Datos**: MySQL.

## Mejoras Implementadas

Este proyecto ha sido refactorizado para incorporar mejores prácticas de seguridad, configuración y calidad de código:

*   **Gestión de Secretos**: Las credenciales de la base de datos y la clave secreta de JWT se gestionan a través de **variables de entorno**, eliminando información sensible del código fuente.
*   **Perfiles de Spring**: Se utilizan perfiles (`dev`, `prod`) para gestionar configuraciones específicas de cada entorno.
*   **Calidad de Código**: Se ha refactorizado la inyección de dependencias (usando inyección por constructor), se ha mejorado la transaccionalidad y se ha optimizado la lógica de negocio en la capa de servicio.

## Seguridad y Autenticación (JWT)

La aplicación implementa un sistema de autenticación y autorización basado en JSON Web Tokens (JWT) utilizando Spring Security.

*   **Spring Security**: Proporciona la base para la seguridad de la aplicación.
*   **JWT (JSON Web Tokens)**: Se utilizan para la autenticación sin estado. La clave secreta para firmar los tokens se configura mediante la variable de entorno `JWT_SECRET_KEY`.

### Componentes Clave de Seguridad:

*   `JwtUtil.java`: Clase de utilidad para la generación y validación de JWTs.
*   `JwtAuthenticationFilter.java`: Filtro que intercepta las solicitudes, extrae el JWT y autentica al usuario.
*   `SecurityConfig.java`: Clase de configuración principal de Spring Security.
*   `AuthController.java`: Controlador para el registro e inicio de sesión.

## Estructura del Proyecto

El proyecto sigue una arquitectura en capas típica de Spring Boot:

*   `src/main/java/com/library/recetas/`: Código fuente principal.
    *   `config/`: Clases de configuración de Spring, OpenAPI, etc.
    *   `controller/`: Controladores REST que manejan las solicitudes HTTP.
    *   `dto/`: Objetos de Transferencia de Datos (DTOs) para la comunicación entre capas.
    *   `exception/`: Clases de excepción personalizadas.
    *   `mapper/`: Mapeadores de MapStruct para la conversión entre entidades y DTOs.
    *   `model/`: Entidades JPA que representan las tablas de la base de datos.
    *   `repository/`: Repositorios de Spring Data JPA para la interacción con la base de datos.
    *   `service/`: Capa de lógica de negocio.
*   `src/main/resources/`:
    *   `application.properties`: Fichero de configuración principal.
    *   `application-dev.properties` / `application-prod.properties`: Configuraciones específicas por perfil.
    *   `db/migration/`: **Scripts de migración de Flyway** para la gestión del esquema de la base de datos.

## Base de Datos y Migraciones

La aplicación utiliza **Flyway** para gestionar el versionado del esquema de la base de datos. Al arrancar, Flyway aplicará automáticamente los scripts de migración SQL que se encuentren en `src/main/resources/db/migration/`.

El primer script, `V1__Initial_schema.sql`, crea la estructura inicial de tablas. Cualquier cambio futuro en el esquema se debe realizar añadiendo un nuevo script de migración numerado (ej. `V2__Add_new_table.sql`).

## Configuración

La configuración de la aplicación se gestiona mediante perfiles de Spring y variables de entorno para la información sensible.

### Perfiles de Spring

*   **dev (por defecto)**: Optimizado para desarrollo. Habilita `spring.jpa.show-sql=true`.
*   **prod**: Optimizado para producción. Deshabilita `spring.jpa.show-sql`.

Para activar un perfil, se puede pasar como argumento al ejecutar la aplicación: `--spring.profiles.active=prod`.

### Variables de Entorno

Es necesario configurar las siguientes variables de entorno, especialmente para producción:

*   `DB_USERNAME`: El nombre de usuario de la base de datos.
*   `DB_PASSWORD`: La contraseña de la base de datos.
*   `JWT_SECRET_KEY`: Una clave secreta segura para firmar los tokens JWT.

## Cómo Ejecutar la Aplicación

1.  **Prerrequisitos**:
    *   JDK 17 o superior.
    *   Maven.
    *   Servidor MySQL en ejecución y una base de datos vacía creada.

2.  **Configuración**:
    *   Asegúrate de que la URL de la base de datos en `application.properties` es correcta.
    *   (Opcional, para desarrollo) Puedes dejar los valores por defecto en `application.properties` o configurar las variables de entorno mencionadas anteriormente.

3.  **Compilar y Ejecutar con Maven**:
    Desde la raíz del proyecto (`d:\PROYECTOS\Backend-Recetas\recetas`), ejecuta:
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```
    Para ejecutar con un perfil específico (ej. `prod`):
    ```bash
    mvn spring-boot:run -Dspring-boot.run.profiles=prod
    ```

4.  **Acceder a la API**: La API estará disponible en `http://localhost:8081`.
5.  **Documentación (Swagger UI)**: Accede a la documentación interactiva en `http://localhost:8081/swagger-ui.html`.
