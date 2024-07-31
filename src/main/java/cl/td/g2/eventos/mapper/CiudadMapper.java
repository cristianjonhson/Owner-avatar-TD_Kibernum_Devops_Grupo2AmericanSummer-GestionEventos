package cl.td.g2.eventos.mapper;

import cl.td.g2.eventos.dto.CiudadDTO;
import cl.td.g2.eventos.model.Ciudad;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CiudadMapper {
    CiudadDTO toDTO(Ciudad ciudad);
    Ciudad toEntity(CiudadDTO ciudadDTO);
}
