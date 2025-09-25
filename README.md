# Aplicación de Recetas - Backend y Base de Datos

Este proyecto es el backend de una aplicación de recetas, desarrollado con Spring Boot. Proporciona una API RESTful para gestionar recetas, usuarios, ingredientes y otros aspectos relacionados, junto con una base de datos relacional para almacenar toda la información.

## Tecnologías y Arquitectura del Backend

*   **Lenguaje**: Java 17
*   **Framework**: Spring Boot 3.2.5
*   **Seguridad**: Spring Security con JWT para autenticación.
*   **Acceso a Datos**: Spring Data JPA con MySQL.
*   **Mapeo**: MapStruct para entidades y DTOs.
*   **Migraciones DB**: Flyway para control de versiones del esquema.
*   **Documentación API**: Springdoc OpenAPI (Swagger UI).
*   **Configuración**: Variables de entorno para secretos y perfiles de Spring (`dev`, `prod`).

## Estructura del Proyecto

El backend sigue una arquitectura en capas (config, controller, dto, exception, mapper, model, repository, service). Las migraciones de base de datos se gestionan en `src/main/resources/db/migration/`.

## Base de Datos (`recetas_db`)

La base de datos está diseñada para almacenar:
*   **Usuarios**: Información de los usuarios registrados.
*   **Recetas**: Detalles principales, pasos de preparación, tiempo, dificultad.
*   **Ingredientes**: Lista maestra y su cantidad por receta.
*   **Categorías**: Clasificación de recetas.
*   **Comentarios y Calificaciones**: Interacción de usuarios con recetas.
*   **Relaciones**: Tablas intermedias para `receta_categorias` y `receta_ingredientes` (relaciones muchos a muchos).

## Cómo Ejecutar

1.  **Prerrequisitos**: JDK 17+, Maven, Servidor MySQL.
2.  **Configuración**: Asegurar la conexión a la base de datos (variables de entorno `DB_USERNAME`, `DB_PASSWORD`, `JWT_SECRET_KEY` recomendadas).
3.  **Ejecución**: Desde la raíz del proyecto (`recetas/`), compilar e iniciar con Maven:
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```
    (Opcional: `--spring.profiles.active=prod` para perfil de producción).
4.  **Acceso**: API en `http://localhost:8081`, Swagger UI en `http://localhost:8081/swagger-ui.html`.
