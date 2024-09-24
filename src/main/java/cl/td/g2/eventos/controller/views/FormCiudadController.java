package cl.td.g2.eventos.controller.views;

import cl.td.g2.eventos.dto.CiudadDTO;
import cl.td.g2.eventos.service.CiudadService; // Asegúrate de tener este servicio
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormCiudadController {

    @Autowired
    private CiudadService ciudadService; 
    // Mostrar formulario para crear una nueva ciudad
    @GetMapping("/ciudades/nuevo")
    public String mostrarFormularioDeCrearCiudad(Model model) {
        model.addAttribute("ciudadDTO", new CiudadDTO());
        return "city/form";
    }

    // Guardar ciudad
    @PostMapping("/ciudades/guardar")
    public String guardarCiudad(@Valid @ModelAttribute CiudadDTO ciudadDTO, Model model) {
        ciudadService.createCiudad(ciudadDTO);
        return "redirect:/ciudades"; // Redirige a una lista de ciudades o a otra página
    }
}
