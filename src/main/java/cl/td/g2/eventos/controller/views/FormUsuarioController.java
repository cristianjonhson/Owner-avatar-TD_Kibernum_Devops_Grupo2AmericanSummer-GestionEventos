package cl.td.g2.eventos.controller.views;

import cl.td.g2.eventos.dto.UsuarioDTO;
import cl.td.g2.eventos.service.UsuarioService;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormUsuarioController {

    @Autowired
    private UsuarioService usuarioService;

  
    // Mostrar el formulario para registrar un nuevo usuario
    @GetMapping("/usuarios/nuevo")
    public String mostrarFormularioUsuario(Model model) {
        model.addAttribute("usuarioDTO", new UsuarioDTO());
        return "user/form"; // Asegúrate de que la plantilla esté en templates/usuario/form.html
    }

    // Guardar el usuario
    @PostMapping("/usuarios/guardar")
    public String guardarUsuario(@ModelAttribute @Validated UsuarioDTO usuarioDTO, Model model) {
        try {
             // Establece la fecha de registro aquí en el backend por seguridad
            usuarioDTO.setFechaRegistro(LocalDateTime.now());
            usuarioService.createUsuario(usuarioDTO);
            model.addAttribute("message", "Usuario registrado exitosamente.");
            return "redirect:/usuarios/nuevo"; // Redirige después de guardar
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Hubo un error al registrar el usuario.");
            return "user/form";
        }
    }
}
