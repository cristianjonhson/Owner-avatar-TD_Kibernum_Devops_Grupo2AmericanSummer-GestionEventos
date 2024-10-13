package cl.td.g2.eventos.service;

import cl.td.g2.eventos.dto.UsuarioDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioService usuarioService;

    public CustomUserDetailsService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UsuarioDTO usuario = usuarioService.findUsuarioByEmail(email);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con el email: " + email);
        }
        
        // Crear un UserDetails con los detalles del usuario
        return User.withUsername(usuario.getEmail())
                .password(usuario.getContrasena())  // La contraseña debe estar codificada
                .authorities(Collections.singletonList(() -> usuario.getRol()))  // Asignar el rol
                .build();
    }
}
