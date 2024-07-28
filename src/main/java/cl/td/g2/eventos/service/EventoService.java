package cl.td.g2.eventos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public List<EventoDTO> getAllEventos() {
        return eventoRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    public EventoDTO getEventoById(Long id) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
        return convertToDTO(evento);
    }

    public EventoDTO createEvento(EventoDTO eventoDTO) {
        Evento evento = convertToEntity(eventoDTO);
        Evento savedEvento = eventoRepository.save(evento);
        return convertToDTO(savedEvento);
    }

    public EventoDTO updateEvento(Long id, EventoDTO eventoDTO) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
        evento.setTitulo(eventoDTO.getTitulo());
        evento.setDescripcion(eventoDTO.getDescripcion());
        evento.setFechaInicio(eventoDTO.getFechaInicio());
        evento.setFechaFin(eventoDTO.getFechaFin());
        evento.setUbicacion(eventoDTO.getUbicacion());
        evento.setValor(eventoDTO.getValor());
        evento.setImagenHtml(eventoDTO.getImagenHtml());
        Evento updatedEvento = eventoRepository.save(evento);
        return convertToDTO(updatedEvento);
    }

    public void deleteEvento(Long id) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
        eventoRepository.delete(evento);
    }

    private EventoDTO convertToDTO(Evento evento) {
        EventoDTO dto = new EventoDTO();
        dto.setId(evento.getId());
        dto.setTitulo(evento.getTitulo());
        dto.setDescripcion(evento.getDescripcion());
        dto.setFechaInicio(evento.getFechaInicio());
        dto.setFechaFin(evento.getFechaFin());
        dto.setUbicacion(evento.getUbicacion());
        dto.setValor(evento.getValor());
        dto.setImagenHtml(evento.getImagenHtml());
        return dto;
    }

    private Evento convertToEntity(EventoDTO dto) {
        Evento evento = new Evento();
        evento.setTitulo(dto.getTitulo());
        evento.setDescripcion(dto.getDescripcion());
        evento.setFechaInicio(dto.getFechaInicio());
        evento.setFechaFin(dto.getFechaFin());
        evento.setUbicacion(dto.getUbicacion());
        evento.setValor(dto.getValor());
        evento.setImagenHtml(dto.getImagenHtml());
        return evento;
    }
}
