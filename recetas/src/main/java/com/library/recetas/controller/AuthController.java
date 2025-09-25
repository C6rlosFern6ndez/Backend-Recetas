package com.library.recetas.controller;

import com.library.recetas.dto.AuthRequest;
import com.library.recetas.dto.AuthResponse;
import com.library.recetas.dto.RegisterRequest;
import com.library.recetas.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para manejar las operaciones de autenticación, registro, restablecimiento de contraseña y verificación de email.
 * Proporciona endpoints para el registro de nuevos usuarios, la autenticación de usuarios existentes,
 * la solicitud de restablecimiento de contraseña, el restablecimiento de la misma, y la verificación de email.
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

    /**
     * Endpoint para solicitar el restablecimiento de contraseña.
     * Recibe el correo electrónico del usuario y llama al servicio para iniciar el proceso de restablecimiento.
     * @param email El correo electrónico del usuario.
     * @return ResponseEntity con un mensaje de éxito o error.
     */
    @PostMapping("/request-password-reset")
    public ResponseEntity<String> requestPasswordReset(@RequestParam String email) {
        try {
            authService.requestPasswordReset(email);
            return ResponseEntity.ok("Password reset request submitted. Check your email for instructions.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Endpoint para restablecer la contraseña de un usuario.
     * Recibe el token de restablecimiento y la nueva contraseña.
     * @param token El token de restablecimiento de contraseña.
     * @param newPassword La nueva contraseña.
     * @return ResponseEntity con un mensaje de éxito o error.
     */
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(
            @RequestParam String token,
            @RequestParam String newPassword) {
        try {
            authService.resetPassword(token, newPassword);
            return ResponseEntity.ok("Password reset successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Endpoint para solicitar la verificación del correo electrónico.
     * Recibe el correo electrónico del usuario y llama al servicio para iniciar el proceso de verificación.
     * @param email El correo electrónico del usuario.
     * @return ResponseEntity con un mensaje de éxito o error.
     */
    @PostMapping("/request-email-verification")
    public ResponseEntity<String> requestEmailVerification(@RequestParam String email) {
        try {
            authService.requestEmailVerification(email);
            return ResponseEntity.ok("Email verification request submitted. Check your email for instructions.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Endpoint para verificar el correo electrónico de un usuario.
     * Recibe el token de verificación.
     * @param token El token de verificación de correo electrónico.
     * @return ResponseEntity con un mensaje de éxito o error.
     */
    @GetMapping("/verify-email")
    public ResponseEntity<String> verifyEmail(@RequestParam String token) {
        try {
            authService.verifyEmail(token);
            return ResponseEntity.ok("Email verified successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
