package com.library.recetas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.core.env.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Clase principal de la aplicación Spring Boot para la gestión de recetas.
 * Configura el escaneo de repositorios JPA y entidades del modelo.
 */
@SpringBootApplication
@EnableJpaRepositories("com.library.recetas.repository")
@EntityScan("com.library.recetas.model")
public class App {

    // Logger para registrar mensajes de la aplicación.
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    /**
     * Método principal que inicia la aplicación Spring Boot.
     * Configura y ejecuta la aplicación, obteniendo el entorno para registrar
     * información de inicio como el puerto del servidor y la URL de la base de datos.
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(App.class);
        Environment env = app.run(args).getEnvironment();

        String port = env.getProperty("server.port");
        String dbUrl = env.getProperty("spring.datasource.url");

        logger.info("Aplicación iniciada correctamente en el puerto {} con base de datos: {}", port, dbUrl);
    }
}
