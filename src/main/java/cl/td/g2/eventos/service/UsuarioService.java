package cl.td.g2.eventos.service;

import cl.td.g2.eventos.dto.UsuarioDTO;
import cl.td.g2.eventos.exception.BadRequestException;
import cl.td.g2.eventos.exception.NotFoundException;
import cl.td.g2.eventos.mapper.UsuarioMapper;
import cl.td.g2.eventos.model.Usuario;
import cl.td.g2.eventos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    public List<UsuarioDTO> getAllUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        if (usuarios == null || usuarios.isEmpty()) {
        	throw new NotFoundException("Usuarios");
        }
        return usuarioMapper.toDTO(usuarios);
    }

    public UsuarioDTO getUsuarioById(Long id) {
        return usuarioRepository.findById(id).map(usuarioMapper::toDTO).orElseThrow(() -> new NotFoundException("Usuario", "id", id));
    }

    public UsuarioDTO createUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        try {
        	Usuario savedUsuario = usuarioRepository.save(usuario);
            return usuarioMapper.toDTO(savedUsuario);
		} catch (Exception ex) {
			throw new BadRequestException(ex.getMessage());
		}
    }

    public UsuarioDTO updateUsuario(Long id, UsuarioDTO usuarioDTO) {
        if (usuarioRepository.existsById(id)) {
        	Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
            usuario.setId(id);
            try {
            	Usuario updatedUsuario = usuarioRepository.save(usuario);
                return usuarioMapper.toDTO(updatedUsuario);
    		} catch (Exception ex) {
    			throw new BadRequestException(ex.getMessage());
    		}
        }
        throw new NotFoundException("Usuario", "id", id);
    }

    public void deleteUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
    		try {
    			usuarioRepository.deleteById(id);
    		} catch (Exception ex) {
    			throw new BadRequestException(ex.getMessage());
    		}
        }
    	else {
    		throw new NotFoundException("Usuario", "id", id);
    	}
    }
}