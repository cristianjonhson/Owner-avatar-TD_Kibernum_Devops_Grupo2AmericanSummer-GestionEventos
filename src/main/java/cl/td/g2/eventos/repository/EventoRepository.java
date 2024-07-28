package cl.td.g2.eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.td.g2.eventos.model.Evento;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
    
    // Encontrar por ID
    Optional<Evento> findById(Long id);

    // Encontrar todos
    List<Evento> findAll();

    // Guardar
    @SuppressWarnings("unchecked")
	Evento save(Evento evento);

    // Eliminar por ID
    void deleteById(Long id);
    
    // Encontrar por título
    List<Evento> findByTitulo(String titulo);
    
    // Encontrar por categoría
    List<Evento> findByCategoriaId(Long categoriaId);
    
    // Encontrar por ciudad
    List<Evento> findByCiudadId(Long ciudadId);
}
