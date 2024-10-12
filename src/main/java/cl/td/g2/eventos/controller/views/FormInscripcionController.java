package cl.td.g2.eventos.controller.views;


import cl.td.g2.eventos.dto.InscripcionDTO;
import cl.td.g2.eventos.service.InscripcionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.validation.Valid;

import java.util.List;

@Controller
public class FormInscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    // Mostrar el formulario de inscripción
    @GetMapping("/inscripciones/nueva")
    public String mostrarFormularioInscripcion(Model model) {
        model.addAttribute("inscripcionDTO", new InscripcionDTO());
        return "inscription/form"; // Asegúrate de que la plantilla esté en templates/inscription/form.html
    }

    // Guardar la inscripción cuando se envía el formulario
    @PostMapping("/inscripciones/guardar")
    public String guardarInscripcion(@ModelAttribute @Validated InscripcionDTO inscripcionDTO,
                                     BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "inscription/form"; // Volver al formulario si hay errores
        }
        try {
            inscripcionService.createInscripcion(inscripcionDTO);
            redirectAttributes.addFlashAttribute("success", "Inscripción guardada exitosamente.");
            return "redirect:/inscripciones/lista"; // Redirigir después de guardar
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Hubo un error al guardar la inscripción.");
            return "inscription/form"; // Si hay error, volver a mostrar el formulario
        }
    }

    // Mostrar el formulario de edición de una inscripción existente
    @GetMapping("/inscripciones/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Long id, Model model) {
        InscripcionDTO inscripcion = inscripcionService.getInscripcionById(id);
        if (inscripcion == null) {
            return "redirect:/error"; // Redirige a una página de error si no se encuentra la inscripción
        }
        model.addAttribute("inscripcion", inscripcion);
        return "inscription/edit"; // Asegúrate de tener un formulario de edición en templates/inscription/edit.html
    }

    // Actualizar una inscripción existente
    @PostMapping("/inscripciones/editar/{id}")
    public String editarInscripcion(@PathVariable Long id,
                                    @Valid @ModelAttribute("inscripcionDTO") InscripcionDTO inscripcionDTO,
                                    BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "inscription/edit"; // Volver al formulario si hay errores
        }

        try {
            inscripcionService.updateInscripcion(id, inscripcionDTO);
            redirectAttributes.addFlashAttribute("success", "Inscripción actualizada exitosamente.");
            return "redirect:/inscripciones/lista";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Hubo un error al actualizar la inscripción.");
            return "inscription/edit";
        }
    }

    // Eliminar una inscripción
    @GetMapping("/inscripciones/eliminar/{id}")
    public String eliminarInscripcion(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            inscripcionService.deleteInscripcion(id);
            redirectAttributes.addFlashAttribute("deleted", true);
            return "redirect:/inscripciones/lista";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Hubo un error al eliminar la inscripción.");
            return "redirect:/inscripciones/lista";
        }
    }

    // Listar todas las inscripciones
    @GetMapping("/inscripciones/lista")
    public String listarInscripciones(Model model) {
        List<InscripcionDTO> inscripciones = inscripcionService.getAllInscripciones(); // Obtener todas las inscripciones
        model.addAttribute("inscripciones", inscripciones);
        return "inscription/list"; // Nombre de la plantilla que lista las inscripciones en templates/inscription/list.html
    }
}
