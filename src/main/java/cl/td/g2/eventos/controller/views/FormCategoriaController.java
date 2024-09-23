package cl.td.g2.eventos.controller.views;

import cl.td.g2.eventos.dto.CategoriaDTO;
import cl.td.g2.eventos.service.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormCategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // Mostrar el formulario para crear una nueva categoría
    @GetMapping("/categorias/nueva")
    public String mostrarFormularioCategoria(Model model) {
        model.addAttribute("categoriaDTO", new CategoriaDTO());
        return "category/form"; // Asegúrate de que la plantilla esté en templates/categoria/form.html
    }

    // Guardar la categoría
    @PostMapping("/categorias/guardar")
    public String guardarCategoria(@ModelAttribute @Validated CategoriaDTO categoriaDTO, Model model) {
        try {
            categoriaService.createCategoria(categoriaDTO);
            model.addAttribute("message", "Categoría guardada exitosamente.");
            return "redirect:/categorias/nueva"; // Redirige después de guardar
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Hubo un error al guardar la categoría.");
            return "category/form";
        }
    }
}
