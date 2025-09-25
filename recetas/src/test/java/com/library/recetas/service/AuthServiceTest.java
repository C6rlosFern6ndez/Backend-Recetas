package com.library.recetas.service;

import com.library.recetas.dto.AuthRequest;
import com.library.recetas.dto.AuthResponse;
import com.library.recetas.dto.RegisterRequest;
import com.library.recetas.model.Usuario;
import com.library.recetas.repository.UsuarioRepository;
import com.library.recetas.security.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestPropertySource(properties = {
    "application.security.jwt.secret-key=404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970",
    "application.security.jwt.expiration=86400000"
})
class AuthServiceTest {

    @MockBean
    private UsuarioRepository usuarioRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @MockBean
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthService authService;

    private Usuario usuario;
    private String encodedPassword;
    private String jwtToken;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(1);
        usuario.setNombreUsuario("testuser");
        usuario.setEmail("test@example.com");
        encodedPassword = "encodedPassword";
        usuario.setContrasena(encodedPassword);

        jwtToken = "mockJwtToken";
    }

    @Test
    void testRegister_Success() {
        // Arrange
        RegisterRequest request = new RegisterRequest("newuser", "new@example.com", "password");
        Usuario newUser = new Usuario("newuser", "new@example.com", encodedPassword);
        newUser.setId(2); // Assuming ID is assigned upon save

        when(passwordEncoder.encode("password")).thenReturn(encodedPassword);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(newUser);
        // No mock for jwtUtil.generateToken, it will use the real implementation

        // Act
        AuthResponse response = authService.register(request);

        // Assert
        assertNotNull(response);
        assertNotNull(response.getToken());
        verify(passwordEncoder, times(1)).encode("password");
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void testAuthenticate_Success() {
        // Arrange
        AuthRequest request = new AuthRequest("test@example.com", "password");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null); // AuthenticationManager returns null on success for mock
        when(usuarioRepository.findByEmail("test@example.com")).thenReturn(Optional.of(usuario));
        // No mock for jwtUtil.generateToken, it will use the real implementation

        // Act
        AuthResponse response = authService.authenticate(request);

        // Assert
        assertNotNull(response);
        assertNotNull(response.getToken());
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(usuarioRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    void testAuthenticate_UserNotFound() {
        // Arrange
        AuthRequest request = new AuthRequest("nonexistent@example.com", "password");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);
        when(usuarioRepository.findByEmail("nonexistent@example.com")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(Exception.class, () -> { // Spring Security might throw a generic exception or a specific one like BadCredentialsException
            authService.authenticate(request);
        });
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(usuarioRepository, times(1)).findByEmail("nonexistent@example.com");
    }
}
