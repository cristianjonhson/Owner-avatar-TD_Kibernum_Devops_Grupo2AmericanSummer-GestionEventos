package cl.td.g2.eventos.controller.views;

import cl.td.g2.eventos.dto.CategoriaDTO;
import cl.td.g2.eventos.service.CategoriaService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/categoria/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Long id, Model model) {
        CategoriaDTO categoria = categoriaService.getCategoriaById(id);
        if (categoria == null) {
            return "redirect:/error"; // O cualquier página de error que prefieras
        }
        model.addAttribute("categoria", categoria);
        return "category/edit";
    }

    @PostMapping("/categoria/editar/{id}")
    public String editarCategoria(
            @PathVariable Long id,
            @Valid @ModelAttribute("categoria") CategoriaDTO categoriaDTO,
            BindingResult result,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "category/edit"; // Volver a mostrar el formulario si hay errores
        }

        categoriaService.updateCategoria(id, categoriaDTO);
        redirectAttributes.addAttribute("success", true);
        return "redirect:/categoria/editar/{id}";
    }

    @PostMapping("/categoria/eliminar/{id}")
    public String eliminarCategoria(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            categoriaService.deleteCategoria(id);
            // Agregar el atributo de éxito
            redirectAttributes.addAttribute("deleted", true);
            return "redirect:/categoria/editar/{id}";
        } catch (Exception e) {
            return "redirect:/categorias?error=true"; // Redirige con un mensaje de error
        }
    }

}
