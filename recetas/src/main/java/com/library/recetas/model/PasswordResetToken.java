package com.library.recetas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "password_reset_tokens")
@Getter
@Setter
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_expiracion", nullable = false)
    private LocalDateTime fechaExpiracion;

    public PasswordResetToken() {
        this.token = UUID.randomUUID().toString();
        this.fechaCreacion = LocalDateTime.now();
        this.fechaExpiracion = this.fechaCreacion.plusHours(1); // Token válido por 1 hora
    }

    public PasswordResetToken(Usuario usuario) {
        this(); // Llama al constructor por defecto para inicializar token, fechaCreacion y fechaExpiracion
        this.usuario = usuario;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(fechaExpiracion);
    }
}
