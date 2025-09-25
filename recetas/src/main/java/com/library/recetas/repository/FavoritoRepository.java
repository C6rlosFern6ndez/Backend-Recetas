package com.library.recetas.repository;

import com.library.recetas.model.Favorito;
import com.library.recetas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, Integer> {

    /**
     * Encuentra todos los favoritos de un usuario específico.
     * @param usuario El usuario cuyos favoritos se buscan.
     * @return Una lista de objetos Favorito asociados al usuario.
     */
    List<Favorito> findByUsuario(Usuario usuario);

    /**
     * Busca un favorito específico por el ID del usuario y el ID original de la receta.
     * Esto es útil para verificar si una receta ya está en favoritos o para eliminarla.
     * @param usuarioId El ID del usuario.
     * @param recetaOriginalId El ID de la receta original.
     * @return Un Optional que contiene el Favorito si se encuentra, o vacío en caso contrario.
     */
    Optional<Favorito> findByUsuarioIdAndRecetaOriginalId(Integer usuarioId, Integer recetaOriginalId);

    /**
     * Elimina un favorito específico por el ID del usuario y el ID original de la receta.
     * @param usuarioId El ID del usuario.
     * @param recetaOriginalId El ID de la receta original.
     */
    void deleteByUsuarioIdAndRecetaOriginalId(Integer usuarioId, Integer recetaOriginalId);
}
