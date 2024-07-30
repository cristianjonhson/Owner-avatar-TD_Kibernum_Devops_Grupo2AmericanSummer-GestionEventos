package cl.td.g2.eventos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    public List<InscripcionDTO> getAllInscripciones() {
        return inscripcionRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    public InscripcionDTO getInscripcionById(Long id) {
        Inscripcion inscripcion = inscripcionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscripción no encontrada"));
        return convertToDTO(inscripcion);
    }

    public InscripcionDTO createInscripcion(InscripcionDTO inscripcionDTO) {
        Inscripcion inscripcion = convertToEntity(inscripcionDTO);
        Inscripcion savedInscripcion = inscripcionRepository.save(inscripcion);
        return convertToDTO(savedInscripcion);
    }

    public InscripcionDTO updateInscripcion(Long id, InscripcionDTO inscripcionDTO) {
        Inscripcion inscripcion = inscripcionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscripción no encontrada"));
        inscripcion.setUsuarioId(inscripcionDTO.getUsuarioId());
        inscripcion.setEventoId(inscripcionDTO.getEventoId());
        Inscripcion updatedInscripcion = inscripcionRepository.save(inscripcion);
        return convertToDTO(updatedInscripcion);
    }

    public void deleteInscripcion(Long id) {
        Inscripcion inscripcion = inscripcionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscripción no encontrada"));
        inscripcionRepository.delete(inscripcion);
    }

    private InscripcionDTO convertToDTO(Inscripcion inscripcion) {
        InscripcionDTO dto = new InscripcionDTO();
        dto.setId(inscripcion.getId());
        dto.setUsuarioId(inscripcion.getUsuarioId());
        dto.setEventoId(inscripcion.getEventoId());
        return dto;
    }

    private Inscripcion convertToEntity(InscripcionDTO dto) {
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setUsuarioId(dto.getUsuarioId());
        inscripcion.setEventoId(dto.getEventoId());
        return inscripcion;
    }
}
