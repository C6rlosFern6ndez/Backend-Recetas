# Base de Datos de Recetas (recetas_db)

Este documento describe la estructura de la base de datos `recetas_db`, las tablas que la componen y las relaciones entre ellas.

## Esquema de la Base de Datos

La base de datos está diseñada para almacenar información sobre recetas, usuarios, ingredientes, categorías, pasos de preparación, comentarios y calificaciones.

---

### Tablas Principales

1.  **`usuarios`**
    *   **Descripción**: Almacena la información de los usuarios registrados en la aplicación.
    *   **Columnas**:
        *   `id` (INT, PK, AUTO_INCREMENT): Identificador único del usuario.
        *   `nombre_usuario` (VARCHAR(50), UNIQUE): Nombre de usuario.
        *   `email` (VARCHAR(100), UNIQUE): Correo electrónico del usuario.
        *   `contrasena` (VARCHAR(255)): Contraseña del usuario (cifrada).
        *   `fecha_registro` (TIMESTAMP, DEFAULT CURRENT_TIMESTAMP): Fecha y hora en que el usuario se registró.

2.  **`categorias`**
    *   **Descripción**: Define las diferentes categorías que pueden tener las recetas (ej. "Postres", "Platos Principales", "Ensaladas").
    *   **Columnas**:
        *   `id` (INT, PK, AUTO_INCREMENT): Identificador único de la categoría.
        *   `nombre` (VARCHAR(50), UNIQUE): Nombre de la categoría.

3.  **`ingredientes`**
    *   **Descripción**: Lista maestra de todos los ingredientes que pueden ser utilizados en las recetas.
    *   **Columnas**:
        *   `id` (INT, PK, AUTO_INCREMENT): Identificador único del ingrediente.
        *   `nombre` (VARCHAR(100), UNIQUE): Nombre del ingrediente.

4.  **`recetas`**
    *   **Descripción**: Contiene los detalles principales de cada receta.
    *   **Columnas**:
        *   `id` (INT, PK, AUTO_INCREMENT): Identificador único de la receta.
        *   `titulo` (VARCHAR(100)): Título de la receta.
        *   `descripcion` (TEXT): Descripción detallada de la receta.
        *   `tiempo_preparacion` (INT): Tiempo estimado de preparación en minutos.
        *   `dificultad` (ENUM('Fácil', 'Media', 'Difícil')): Nivel de dificultad de la receta.
        *   `porciones` (INT): Número de porciones que rinde la receta.
        *   `usuario_id` (INT, FK): Identificador del usuario que creó la receta. Referencia a `usuarios.id`.
        *   `fecha_creacion` (TIMESTAMP, DEFAULT CURRENT_TIMESTAMP): Fecha y hora de creación de la receta.

5.  **`pasos`**
    *   **Descripción**: Detalla la secuencia de pasos para preparar una receta específica.
    *   **Columnas**:
        *   `id` (INT, PK, AUTO_INCREMENT): Identificador único del paso.
        *   `receta_id` (INT, FK): Identificador de la receta a la que pertenece este paso. Referencia a `recetas.id`.
        *   `orden` (INT): El orden secuencial del paso dentro de la receta.
        *   `descripcion` (TEXT): Descripción detallada de la acción a realizar en este paso.

6.  **`comentarios`**
    *   **Descripción**: Almacena los comentarios que los usuarios dejan sobre las recetas.
    *   **Columnas**:
        *   `id` (INT, PK, AUTO_INCREMENT): Identificador único del comentario.
        *   `receta_id` (INT, FK): Identificador de la receta comentada. Referencia a `recetas.id`.
        *   `usuario_id` (INT, FK): Identificador del usuario que realizó el comentario. Referencia a `usuarios.id`.
        *   `comentario` (TEXT): El texto del comentario.
        *   `fecha_comentario` (TIMESTAMP, DEFAULT CURRENT_TIMESTAMP): Fecha y hora en que se realizó el comentario.

7.  **`calificaciones`**
    *   **Descripción**: Guarda las puntuaciones (calificaciones) que los usuarios otorgan a las recetas.
    *   **Columnas**:
        *   `id` (INT, PK, AUTO_INCREMENT): Identificador único de la calificación.
        *   `receta_id` (INT, FK): Identificador de la receta calificada. Referencia a `recetas.id`.
        *   `usuario_id` (INT, FK): Identificador del usuario que realizó la calificación. Referencia a `usuarios.id`.
        *   `puntuacion` (INT): La puntuación otorgada (ej. de 1 a 5).
        *   `fecha_calificacion` (TIMESTAMP, DEFAULT CURRENT_TIMESTAMP): Fecha y hora en que se realizó la calificación.
        *   `unique_calificacion` (UNIQUE INDEX): Restricción para asegurar que cada usuario solo pueda calificar una receta una vez.

---

### Tablas Intermedias (Relaciones Muchos a Muchos)

8.  **`receta_categorias`**
    *   **Descripción**: Tabla de unión que permite asociar una receta con múltiples categorías y una categoría con múltiples recetas.
    *   **Columnas**:
        *   `receta_id` (INT, PK, FK): Identificador de la receta. Referencia a `recetas.id`.
        *   `categoria_id` (INT, PK, FK): Identificador de la categoría. Referencia a `categorias.id`.
    *   **Clave Primaria**: Compuesta por `receta_id` y `categoria_id`.

9.  **`receta_ingredientes`**
    *   **Descripción**: Tabla de unión que asocia ingredientes a recetas, especificando la cantidad requerida para cada uno.
    *   **Columnas**:
        *   `receta_id` (INT, PK, FK): Identificador de la receta. Referencia a `recetas.id`.
        *   `ingrediente_id` (INT, PK, FK): Identificador del ingrediente. Referencia a `ingredientes.id`.
        *   `cantidad` (VARCHAR(50)): La cantidad específica del ingrediente para la receta (ej. "250g", "1 taza", "al gusto").
    *   **Clave Primaria**: Compuesta por `receta_id` y `ingrediente_id`.

---

## Relaciones Clave

*   **Usuario y Recetas**: Un `usuario` puede crear muchas `recetas`. La tabla `recetas` tiene una clave foránea (`usuario_id`) que apunta a la tabla `usuarios`.
*   **Receta y Pasos**: Una `receta` se compone de múltiples `pasos`. La tabla `pasos` tiene una clave foránea (`receta_id`) que apunta a la tabla `recetas`.
*   **Receta y Categorías**: Una `receta` puede pertenecer a varias `categorias`, y una `categoria` puede agrupar varias `recetas`. Esta relación muchos a muchos se gestiona a través de la tabla intermedia `receta_categorias`.
*   **Receta e Ingredientes**: Una `receta` requiere varios `ingredientes`, y un `ingrediente` puede ser parte de múltiples `recetas`. Esta relación muchos a muchos se gestiona a través de la tabla intermedia `receta_ingredientes`, que además almacena la `cantidad` necesaria.
*   **Receta, Usuarios y Comentarios**: Los `usuarios` pueden dejar `comentarios` en las `recetas`. Las tablas `comentarios` tienen claves foráneas (`receta_id` y `usuario_id`) que apuntan a `recetas` y `usuarios` respectivamente.
*   **Receta, Usuarios y Calificaciones**: Los `usuarios` pueden asignar `calificaciones` a las `recetas`. Las tablas `calificaciones` tienen claves foráneas (`receta_id` y `usuario_id`) que apuntan a `recetas` y `usuarios` respectivamente. La tabla `calificaciones` asegura que cada usuario solo califique una receta una vez.

---

Este README proporciona una visión general de la estructura de la base de datos `recetas_db`, facilitando la comprensión de cómo se organizan y relacionan los datos.
