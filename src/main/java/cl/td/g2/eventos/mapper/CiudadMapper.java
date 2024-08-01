package cl.td.g2.eventos.mapper;

import cl.td.g2.eventos.dto.CiudadDTO;
import cl.td.g2.eventos.model.Ciudad;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CiudadMapper {
    CiudadDTO toDTO(Ciudad ciudad);
    Ciudad toEntity(CiudadDTO ciudadDTO);
	List<CiudadDTO> toDTO(List<Ciudad> ciudades);
}
