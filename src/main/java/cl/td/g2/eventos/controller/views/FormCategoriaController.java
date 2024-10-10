package cl.td.g2.eventos.controller.views;

import cl.td.g2.eventos.dto.CategoriaDTO;
import cl.td.g2.eventos.service.CategoriaService;
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
public class FormCategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // Mostrar el formulario para crear una nueva categoría
    @GetMapping("/categorias/nueva")
    public String mostrarFormularioCategoria(Model model) {
        model.addAttribute("categoriaDTO", new CategoriaDTO());
        return "category/form";
    }

    // Guardar la categoría y redirigir al listado
    @PostMapping("/categorias/guardar")
    public String guardarCategoria(@ModelAttribute @Validated CategoriaDTO categoriaDTO,
                                   BindingResult result,
                                   RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "category/form"; // Volver al formulario si hay errores
        }
        try {
            categoriaService.createCategoria(categoriaDTO);
             // Agregar un atributo para señalar que fue exitoso
            redirectAttributes.addFlashAttribute("success", true);
            return "redirect:/categoria/lista"; // Redirigir con éxito
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Hubo un error al guardar la categoría.");
            return "category/form"; // Si hay error, se queda en el formulario
        }
    }

    // Mostrar el formulario de edición de una categoría existente
    @GetMapping("/categoria/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Long id, Model model) {
        CategoriaDTO categoria = categoriaService.getCategoriaById(id);
        if (categoria == null) {
            return "redirect:/error"; // O cualquier página de error que prefieras
        }
        model.addAttribute("categoria", categoria);
        return "category/edit";
    }

    // Actualizar una categoría existente
    @PostMapping("/categoria/editar/{id}")
    public String editarCategoria(@PathVariable Long id,
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

    // Eliminar una categoría y redirigir al listado
    @GetMapping("/categoria/eliminar/{id}")
    public String eliminarCategoria(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            categoriaService.deleteCategoria(id);
            // Agregar el atributo de éxito y redirigir a la lista de categorías
            redirectAttributes.addFlashAttribute("deleted", true);
            return "redirect:/categoria/lista"; // Redirigir al método que lista las categorías
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Hubo un error al eliminar la categoría.");
            return "redirect:/categoria/lista?error=true"; // Redirige con un mensaje de error si falla
        }
    }

    // Listar todas las categorías
    @GetMapping("/categoria/lista")
    public String listarCategorias(Model model) {
        List<CategoriaDTO> categorias = categoriaService.getAllCategorias(); // Obtener todas las categorías
        model.addAttribute("categorias", categorias);
        return "category/list"; // Nombre de la plantilla que lista las categorías
    }
}
