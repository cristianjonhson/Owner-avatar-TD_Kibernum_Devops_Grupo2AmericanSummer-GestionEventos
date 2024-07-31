package cl.td.g2.eventos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import cl.td.g2.eventos.dto.EventoDTO;
import cl.td.g2.eventos.model.Evento;

/**
 * Mapper para convertir entre entidades Evento y DTOs de Evento.
 */
@Mapper
public interface EventoMapper {

    EventoMapper INSTANCE = Mappers.getMapper(EventoMapper.class);

    /**
     * Convierte una entidad Evento a un DTO de Evento.
     *
     * @param evento la entidad Evento a convertir
     * @return el DTO de Evento resultante
     */
    @Mapping(source = "organizador.id", target = "organizadorId")
    @Mapping(source = "categoria.id", target = "categoriaId")
    @Mapping(source = "ciudad.id", target = "ciudadId")
    EventoDTO toDTO(Evento evento);

    /**
     * Convierte un DTO de Evento a una entidad Evento.
     *
     * @param eventoDTO el DTO de Evento a convertir
     * @return la entidad Evento resultante
     */
    @Mapping(source = "organizadorId", target = "organizador.id")
    @Mapping(source = "categoriaId", target = "categoria.id")
    @Mapping(source = "ciudadId", target = "ciudad.id")
    Evento toEntity(EventoDTO eventoDTO);
}
