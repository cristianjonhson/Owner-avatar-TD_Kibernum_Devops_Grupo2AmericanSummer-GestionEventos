package cl.td.g2.eventos.service;

import cl.td.g2.eventos.dto.InscripcionDTO;
import cl.td.g2.eventos.mapper.InscripcionMapper;
import cl.td.g2.eventos.model.Inscripcion;
import cl.td.g2.eventos.repository.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private InscripcionMapper inscripcionMapper;

    public List<InscripcionDTO> getAllInscripciones() {
        List<Inscripcion> inscripciones = inscripcionRepository.findAll();
        return inscripcionMapper.toDTO(inscripciones);
    }

    public Optional<InscripcionDTO> getInscripcionById(Long id) {
        return inscripcionRepository.findById(id).map(inscripcionMapper::toDTO);
    }

    public InscripcionDTO createInscripcion(InscripcionDTO inscripcionDTO) {
        Inscripcion inscripcion = inscripcionMapper.toEntity(inscripcionDTO);
        Inscripcion savedInscripcion = inscripcionRepository.save(inscripcion);
        return inscripcionMapper.toDTO(savedInscripcion);
    }

    public InscripcionDTO updateInscripcion(Long id, InscripcionDTO inscripcionDTO) {
        if (inscripcionRepository.existsById(id)) {
            Inscripcion inscripcion = inscripcionMapper.toEntity(inscripcionDTO);
            inscripcion.setId(id);
            Inscripcion updatedInscripcion = inscripcionRepository.save(inscripcion);
            return inscripcionMapper.toDTO(updatedInscripcion);
        }
        return null;
    }

    public void deleteInscripcion(Long id) {
        inscripcionRepository.deleteById(id);
    }
}
