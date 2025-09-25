package com.library.recetas.service;

import com.library.recetas.dto.AuthRequest;
import com.library.recetas.dto.AuthResponse;
import com.library.recetas.dto.RegisterRequest;
import com.library.recetas.model.PasswordResetToken;
import com.library.recetas.model.RoleEnum; // Import RoleEnum
import com.library.recetas.model.Usuario;
import com.library.recetas.repository.PasswordResetTokenRepository;
import com.library.recetas.repository.UsuarioRepository;
import com.library.recetas.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID; // Import UUID

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordResetTokenRepository passwordResetTokenRepository; // Inject the new repository
    // private final RoleRepository roleRepository; // RoleRepository is no longer needed

    public AuthResponse register(RegisterRequest request) {
        var user = new Usuario(
                request.getNombreUsuario(),
                request.getEmail(),
                passwordEncoder.encode(request.getContrasena())
        );

        // Assign default role (e.g., ROLE_USER) using the enum
        user.setRolEnum(RoleEnum.USUARIO); // Set the default role directly

        usuarioRepository.save(user);
        var jwtToken = jwtUtil.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getContrasena()
                )
        );
        var user = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found after authentication")); // More specific exception might be better
        var jwtToken = jwtUtil.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    /**
     * Requests a password reset for a given email address.
     * Creates a password reset token and saves it.
     * In a real application, this would also trigger an email to the user.
     * @param email The email address of the user requesting the reset.
     */
    public void requestPasswordReset(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email)); // Consider a specific exception

        // Invalidate any existing tokens for this user before creating a new one
        passwordResetTokenRepository.findByUsuario(usuario).ifPresent(passwordResetTokenRepository::delete);

        PasswordResetToken newToken = new PasswordResetToken(usuario);
        passwordResetTokenRepository.save(newToken);

        // TODO: Send an email to the user with the token:
        // emailService.sendPasswordResetEmail(usuario.getEmail(), newToken.getToken());
    }

    /**
     * Resets the user's password using a valid password reset token.
     * @param token The password reset token.
     * @param newPassword The new password to set.
     */
    public void resetPassword(String token, String newPassword) {
        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid or expired password reset token")); // Consider a specific exception

        if (resetToken.isExpired()) {
            throw new RuntimeException("Password reset token has expired"); // Consider a specific exception
        }

        Usuario usuario = resetToken.getUsuario();
        usuario.setContrasena(passwordEncoder.encode(newPassword));
        usuarioRepository.save(usuario);

        // Delete the token after successful reset
        passwordResetTokenRepository.delete(resetToken);
    }

    /**
     * Requests an email verification for a given email address.
     * Creates a verification token and saves it.
     * In a real application, this would also trigger an email to the user.
     * @param email The email address of the user requesting verification.
     */
    public void requestEmailVerification(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email)); // Consider a specific exception

        // If the email is already verified, do nothing or inform the user.
        if (usuario.isEmailVerificado()) {
            throw new RuntimeException("Email is already verified.");
        }

        // Invalidate any existing verification tokens for this user before creating a new one
        // Assuming a similar mechanism for verification tokens as password reset tokens
        // For simplicity, we'll just overwrite or create a new one if not found.
        // A more robust solution would involve a separate VerificationToken entity.
        // For now, we'll use a field in Usuario for the token and expiry.
        // Let's assume we need a new entity for verification tokens for better management.
        // For now, let's simulate by setting a token directly on the user and marking it as not verified.
        // This is a simplification and a dedicated entity is recommended.

        // For now, let's just set a placeholder token and assume email sending logic would handle expiry.
        // A proper implementation would involve a separate entity for verification tokens.
        usuario.setVerificationToken(UUID.randomUUID().toString());
        // Set expiry for verification token (e.g., 24 hours)
        // This would require adding expiry logic to the Usuario entity or a separate token entity.
        // For now, we'll skip the expiry logic for simplicity in this example.

        usuarioRepository.save(usuario);

        // TODO: Send an email to the user with the verification token:
        // emailService.sendVerificationEmail(usuario.getEmail(), usuario.getVerificationToken());
    }

    /**
     * Verifies the user's email using a valid verification token.
     * @param token The verification token.
     */
    public void verifyEmail(String token) {
        Usuario usuario = usuarioRepository.findByVerificationToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid or expired verification token")); // Consider a specific exception

        // Check if the token has expired (this logic would be in a dedicated token entity or managed carefully)
        // For now, we assume the token is valid if found.

        usuario.setEmailVerificado(true);
        usuario.setVerificationToken(null); // Clear the token after use
        usuarioRepository.save(usuario);
    }
}
