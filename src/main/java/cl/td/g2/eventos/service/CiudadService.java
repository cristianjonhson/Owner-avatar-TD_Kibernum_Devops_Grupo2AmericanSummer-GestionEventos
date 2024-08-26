package cl.td.g2.eventos.service;

import cl.td.g2.eventos.dto.CiudadDTO;
import cl.td.g2.eventos.exception.BadRequestException;
import cl.td.g2.eventos.exception.NotFoundException;
import cl.td.g2.eventos.mapper.CiudadMapper;
import cl.td.g2.eventos.model.Ciudad;
import cl.td.g2.eventos.repository.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CiudadService {

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private CiudadMapper ciudadMapper;

    public List<CiudadDTO> getAllCiudades() {
        List<Ciudad> ciudades = ciudadRepository.findAll();
        if (ciudades == null || ciudades.isEmpty()) {
        	throw new NotFoundException("Ciudades");
        }
        return ciudadMapper.toDTO(ciudades);
    }

    public CiudadDTO getCiudadById(Long id) {
        return ciudadRepository.findById(id).map(ciudadMapper::toDTO).orElseThrow(() -> new NotFoundException("Ciudad", "id", id));
    }

    public CiudadDTO createCiudad(CiudadDTO ciudadDTO) {
        Ciudad ciudad = ciudadMapper.toEntity(ciudadDTO);
        try {
            Ciudad savedCiudad = ciudadRepository.save(ciudad);
            return ciudadMapper.toDTO(savedCiudad);
		} catch (Exception ex) {
			throw new BadRequestException(ex.getMessage());
		}
    }

    public CiudadDTO updateCiudad(Long id, CiudadDTO ciudadDTO) {
        if (ciudadRepository.existsById(id)) {
        	Ciudad ciudad = ciudadMapper.toEntity(ciudadDTO);
            ciudad.setId(id);
            try {
                Ciudad updatedCiudad = ciudadRepository.save(ciudad);
                return ciudadMapper.toDTO(updatedCiudad);
    		} catch (Exception ex) {
    			throw new BadRequestException(ex.getMessage());
    		}
        }
        throw new NotFoundException("Ciudad", "id", id);
    }

    public void deleteCiudad(Long id) {
        if (ciudadRepository.existsById(id)) {
    		try {
    			ciudadRepository.deleteById(id);
    		} catch (Exception ex) {
    			throw new BadRequestException(ex.getMessage());
    		}
        }
    	else {
    		throw new NotFoundException("Ciudad", "id", id);
    	}
    }
}
