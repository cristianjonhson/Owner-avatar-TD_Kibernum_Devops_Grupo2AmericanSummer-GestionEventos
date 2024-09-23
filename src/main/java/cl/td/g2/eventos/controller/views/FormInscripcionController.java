package cl.td.g2.eventos.controller.views;

import cl.td.g2.eventos.dto.InscripcionDTO;
import cl.td.g2.eventos.service.InscripcionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormInscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    // Mostrar el formulario de inscripción
    @GetMapping("/inscripciones/nueva")
    public String mostrarFormularioInscripcion(Model model) {
        model.addAttribute("inscripcionDTO", new InscripcionDTO());
        return "inscription/form"; // Asegúrate de que la plantilla esté en templates/inscripcion/form.html
    }

    // Guardar la inscripción cuando se envía el formulario
    @PostMapping("/inscripciones/guardar")
    public String guardarInscripcion(@ModelAttribute @Validated InscripcionDTO inscripcionDTO, Model model) {
        try {
            inscripcionService.createInscripcion(inscripcionDTO);
            model.addAttribute("message", "Inscripción guardada exitosamente.");
            return "redirect:/inscripciones/nueva"; // Redirige después de guardar
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Hubo un error al guardar la inscripción.");
            return "inscripcion/form";
        }
    }
}
