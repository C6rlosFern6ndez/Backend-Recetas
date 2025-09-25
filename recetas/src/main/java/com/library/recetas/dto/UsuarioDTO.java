package com.library.recetas.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList; // Import for List
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private Integer id;
    private String nombreUsuario;
    private String email;
    private String nombreCompleto;
    private String bio;
    private String avatarUrl;
    private List<String> sitiosWeb = new ArrayList<>();
    private LocalDateTime fechaRegistro;
}
