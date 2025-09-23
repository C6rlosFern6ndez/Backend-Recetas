package com.library.recetas.service;

import com.library.recetas.dto.AuthRequest;
import com.library.recetas.dto.AuthResponse;
import com.library.recetas.dto.RegisterRequest;
import com.library.recetas.model.Usuario;
import com.library.recetas.repository.UsuarioRepository;
import com.library.recetas.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        var user = new Usuario(
                request.getNombreUsuario(),
                request.getEmail(),
                passwordEncoder.encode(request.getContrasena())
        );
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
                .orElseThrow(); // Debería encontrarlo si la autenticación fue exitosa
        var jwtToken = jwtUtil.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}
