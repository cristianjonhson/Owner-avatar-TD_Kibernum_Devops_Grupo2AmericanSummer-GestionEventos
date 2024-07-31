package cl.td.g2.eventos.mapper;

import cl.td.g2.eventos.dto.CategoriaDTO;
import cl.td.g2.eventos.model.Categoria;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    CategoriaDTO toDTO(Categoria categoria);
    Categoria toEntity(CategoriaDTO categoriaDTO);
}
