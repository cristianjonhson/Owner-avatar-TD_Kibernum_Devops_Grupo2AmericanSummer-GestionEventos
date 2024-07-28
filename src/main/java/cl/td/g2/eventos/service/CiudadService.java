package cl.td.g2.eventos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.td.g2.eventos.dto.CiudadDTO;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CiudadService {

    @Autowired
    private CiudadRepository ciudadRepository;

    public List<CiudadDTO> getAllCiudades() {
        return ciudadRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    public CiudadDTO getCiudadById(Long id) {
        Ciudad ciudad = ciudadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));
        return convertToDTO(ciudad);
    }

    public CiudadDTO createCiudad(CiudadDTO ciudadDTO) {
        Ciudad ciudad = convertToEntity(ciudadDTO);
        Ciudad savedCiudad = ciudadRepository.save(ciudad);
        return convertToDTO(savedCiudad);
    }

    public CiudadDTO updateCiudad(Long id, CiudadDTO ciudadDTO) {
        Ciudad ciudad = ciudadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));
        ciudad.setNombre(ciudadDTO.getNombre());
        Ciudad updatedCiudad = ciudadRepository.save(ciudad);
        return convertToDTO(updatedCiudad);
    }

    public void deleteCiudad(Long id) {
        Ciudad ciudad = ciudadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));
        ciudadRepository.delete(ciudad);
    }

    private CiudadDTO convertToDTO(Ciudad ciudad) {
        CiudadDTO dto = new CiudadDTO();
        dto.setId(ciudad.getId());
        dto.setNombre(ciudad.getNombre());
        return dto;
    }

    private Ciudad convertToEntity(CiudadDTO dto) {
        Ciudad ciudad = new Ciudad();
        ciudad.setNombre(dto.getNombre());
        return ciudad;
    }
}
