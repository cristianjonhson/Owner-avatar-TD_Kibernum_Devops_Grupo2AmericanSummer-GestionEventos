package cl.td.g2.eventos.service;

import cl.td.g2.eventos.dto.CategoriaDTO;
import cl.td.g2.eventos.mapper.CategoriaMapper;
import cl.td.g2.eventos.model.Categoria;
import cl.td.g2.eventos.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaMapper categoriaMapper;

    public List<CategoriaDTO> getAllCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categoriaMapper.toDTO(categorias);
    }

    public Optional<CategoriaDTO> getCategoriaById(Long id) {
        return categoriaRepository.findById(id).map(categoriaMapper::toDTO);
    }

    public CategoriaDTO createCategoria(CategoriaDTO categoriaDTO) {
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        Categoria savedCategoria = categoriaRepository.save(categoria);
        return categoriaMapper.toDTO(savedCategoria);
    }

    public CategoriaDTO updateCategoria(Long id, CategoriaDTO categoriaDTO) {
        if (categoriaRepository.existsById(id)) {
            Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
            categoria.setId(id);
            Categoria updatedCategoria = categoriaRepository.save(categoria);
            return categoriaMapper.toDTO(updatedCategoria);
        }
        return null;
    }

    public void deleteCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }
}