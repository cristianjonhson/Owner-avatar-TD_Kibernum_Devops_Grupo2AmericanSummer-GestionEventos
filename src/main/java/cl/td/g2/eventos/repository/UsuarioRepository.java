package cl.td.g2.eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.td.g2.eventos.model.Usuario;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // Encontrar por ID
    Optional<Usuario> findById(Long id);

    // Encontrar todos
    List<Usuario> findAll();

    // Guardar
    @SuppressWarnings("unchecked")
	Usuario save(Usuario usuario);

    // Eliminar por ID
    void deleteById(Long id);
    
    // Encontrar por email
    Optional<Usuario> findByEmail(String email);
}
