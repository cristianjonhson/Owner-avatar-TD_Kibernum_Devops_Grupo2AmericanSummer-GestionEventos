package cl.td.g2.eventos.service;

import cl.td.g2.eventos.dto.EventoDTO;
import cl.td.g2.eventos.mapper.EventoMapper;
import cl.td.g2.eventos.model.Evento;
import cl.td.g2.eventos.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private EventoMapper eventoMapper;

    public List<EventoDTO> getAllEventos() {
        List<Evento> eventos = eventoRepository.findAll();
        return eventoMapper.toDTO(eventos);
    }

    public Optional<EventoDTO> getEventoById(Long id) {
        return eventoRepository.findById(id).map(eventoMapper::toDTO);
    }

    public EventoDTO createEvento(EventoDTO eventoDTO) {
        Evento evento = eventoMapper.toEntity(eventoDTO);
        Evento savedEvento = eventoRepository.save(evento);
        return eventoMapper.toDTO(savedEvento);
    }

    public EventoDTO updateEvento(Long id, EventoDTO eventoDTO) {
        if (eventoRepository.existsById(id)) {
            Evento evento = eventoMapper.toEntity(eventoDTO);
            evento.setId(id);
            Evento updatedEvento = eventoRepository.save(evento);
            return eventoMapper.toDTO(updatedEvento);
        }
        return null;
    }

    public void deleteEvento(Long id) {
        eventoRepository.deleteById(id);
    }
}
