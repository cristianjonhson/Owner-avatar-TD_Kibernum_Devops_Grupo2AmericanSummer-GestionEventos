package cl.td.g2.eventos.service;

import cl.td.g2.eventos.dto.EventoDTO;
import cl.td.g2.eventos.exception.BadRequestException;
import cl.td.g2.eventos.exception.NotFoundException;
import cl.td.g2.eventos.mapper.EventoMapper;
import cl.td.g2.eventos.model.Evento;
import cl.td.g2.eventos.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private EventoMapper eventoMapper;

    public List<EventoDTO> getAllEventos() {
        List<Evento> eventos = eventoRepository.findAll();
        if (eventos == null || eventos.isEmpty()) {
        	throw new NotFoundException("Eventos");
        }
        return eventoMapper.toDTO(eventos);
    }

    public EventoDTO getEventoById(Long id) {
    	return eventoRepository.findById(id).map(eventoMapper::toDTO).orElseThrow(() -> new NotFoundException("Evento", "id", id));
    }

    public EventoDTO createEvento(EventoDTO eventoDTO) {
        Evento evento = eventoMapper.toEntity(eventoDTO);
        try {
        	Evento savedEvento = eventoRepository.save(evento);
            return eventoMapper.toDTO(savedEvento);
		} catch (Exception ex) {
			throw new BadRequestException(ex.getMessage());
		}
    }

    public EventoDTO updateEvento(Long id, EventoDTO eventoDTO) {
        if (eventoRepository.existsById(id)) {
            Evento evento = eventoMapper.toEntity(eventoDTO);
            evento.setId(id);
            try {
            	Evento updatedEvento = eventoRepository.save(evento);
                return eventoMapper.toDTO(updatedEvento);
    		} catch (Exception ex) {
    			throw new BadRequestException(ex.getMessage());
    		}
        }
        throw new NotFoundException("Evento", "id", id);
    }

    public void deleteEvento(Long id) {
    	if (eventoRepository.existsById(id)) {
    		try {
    			eventoRepository.deleteById(id);
    		} catch (Exception ex) {
    			throw new BadRequestException(ex.getMessage());
    		}
        }
    	else {
    		throw new NotFoundException("Evento", "id", id);
    	}
    }
}
