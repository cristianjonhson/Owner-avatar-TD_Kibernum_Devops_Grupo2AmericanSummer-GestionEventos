package cl.td.g2.eventos.controller.views;

import cl.td.g2.eventos.dto.EventoDTO;
import cl.td.g2.eventos.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 
 */
@Controller
public class FormEventoController {

   @Autowired
   private EventoService eventoService;

   // Mostrar el formulario de creación de eventos
   @GetMapping("/eventos/nuevo")
   public String mostrarFormularioDeCrearEvento(Model model) {
       EventoDTO eventoDTO = new EventoDTO();
       model.addAttribute("eventoDTO", eventoDTO);
       System.out.println("EventoDTO: " + eventoDTO.toString());
       return "event/form";
   }
   
   @PostMapping("/eventos/guardar")
   public String guardarEvento(@ModelAttribute EventoDTO eventoDTO) {
       // Aquí guardas el evento
	   eventoService.createEvento(eventoDTO);
       return "redirect:/eventos"; // Redirige a una lista de eventos o a otra página
   }



   // Procesar la creación de un nuevo evento
   @PostMapping("/eventos1")
   public String crearEvento(@ModelAttribute("eventoDTO") EventoDTO eventoDTO) {
       eventoService.createEvento(eventoDTO);
       return "redirect:/eventos";  // Redirige a la página donde se muestran todos los eventos
   }

   // Método para mostrar la lista de eventos (opcional)
   @GetMapping("/eventos2")
   public String mostrarListaDeEventos(Model model) {
       model.addAttribute("eventos", eventoService.getAllEventos());
       return "event/list";  // Asegúrate de tener una plantilla para listar eventos
   }
}
