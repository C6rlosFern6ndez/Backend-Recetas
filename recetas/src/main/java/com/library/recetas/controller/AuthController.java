package com.library.recetas.controller;

import com.library.recetas.dto.AuthRequest;
import com.library.recetas.dto.AuthResponse;
import com.library.recetas.dto.RegisterRequest;
import com.library.recetas.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para manejar las operaciones de autenticación y registro.
 * Proporciona endpoints para el registro de nuevos usuarios y la autenticación de usuarios existentes.
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    // Servicio de autenticación que maneja la lógica de negocio.
    private final AuthService authService;

    /**
     * Endpoint para registrar un nuevo usuario.
     * Recibe los datos del registro en el cuerpo de la solicitud y devuelve una respuesta de autenticación.
     * @param request Objeto RegisterRequest con los datos del nuevo usuario.
     * @return ResponseEntity con AuthResponse si el registro es exitoso.
     */
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authService.register(request));
    }

    /**
     * Endpoint para autenticar un usuario existente.
     * Recibe las credenciales de autenticación en el cuerpo de la solicitud y devuelve una respuesta de autenticación.
     * @param request Objeto AuthRequest con las credenciales del usuario.
     * @return ResponseEntity con AuthResponse si la autenticación es exitosa.
     */
    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(
            @RequestBody AuthRequest request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
