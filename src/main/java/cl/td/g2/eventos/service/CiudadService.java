package cl.td.g2.eventos.service;

import cl.td.g2.eventos.dto.CiudadDTO;
import cl.td.g2.eventos.mapper.CiudadMapper;
import cl.td.g2.eventos.model.Ciudad;
import cl.td.g2.eventos.repository.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CiudadService {

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private CiudadMapper ciudadMapper;

    public List<CiudadDTO> getAllCiudades() {
        List<Ciudad> ciudades = ciudadRepository.findAll();
        return ciudadMapper.toDTO(ciudades);
    }

    public Optional<CiudadDTO> getCiudadById(Long id) {
        return ciudadRepository.findById(id).map(ciudadMapper::toDTO);
    }

    public CiudadDTO createCiudad(CiudadDTO ciudadDTO) {
        Ciudad ciudad = ciudadMapper.toEntity(ciudadDTO);
        Ciudad savedCiudad = ciudadRepository.save(ciudad);
        return ciudadMapper.toDTO(savedCiudad);
    }

    public CiudadDTO updateCiudad(Long id, CiudadDTO ciudadDTO) {
        if (ciudadRepository.existsById(id)) {
            Ciudad ciudad = ciudadMapper.toEntity(ciudadDTO);
            ciudad.setId(id);
            Ciudad updatedCiudad = ciudadRepository.save(ciudad);
            return ciudadMapper.toDTO(updatedCiudad);
        }
        return null;
    }

    public void deleteCiudad(Long id) {
        ciudadRepository.deleteById(id);
    }
}
