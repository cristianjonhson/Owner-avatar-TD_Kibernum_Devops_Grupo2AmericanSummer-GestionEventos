package cl.td.g2.eventos.controller.views;

import cl.td.g2.eventos.dto.CategoriaDTO;
import cl.td.g2.eventos.service.CategoriaService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
            return "redirect:/categoria/lista"; // Volver al formulario con el mensaje de éxito
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Hubo un error al guardar la categoría.");
            return "category/form"; // Si hay error, se queda en el formulario
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

        @GetMapping("/categoria/lista")
    public String listarCategorias(Model model, @RequestParam(required = false) Boolean deleted) {
        List<CategoriaDTO> categorias = categoriaService.getAllCategorias(); // Método para obtener las categorías
        model.addAttribute("categorias", categorias);
        return "category/list"; // Nombre de la plantilla Thymeleaf
    }


}
