package cl.td.g2.eventos.controller.calendar;

import cl.td.g2.eventos.dto.EventoDTO;
import cl.td.g2.eventos.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CalendarController {

    @Autowired
    private EventoService eventoService;

    @GetMapping("/calendario")
    public String verCalendario(Model model) {
        List<EventoDTO> eventos = eventoService.getAllEventos();  // Llamada al servicio para obtener eventos
        model.addAttribute("eventosJson", convertirEventosAJson(eventos));
        return "calendario/view";  
    }

    private String convertirEventosAJson(List<EventoDTO> eventos) {
        String eventosJson = eventos.stream()
                .map(evento -> String.format(
                        "{ title: '%s', start: '%s', end: '%s' }",
                        evento.getTitulo(),
                        evento.getFechaInicio().toString(),
                        evento.getFechaFin() != null ? evento.getFechaFin().toString() : evento.getFechaInicio().toString()
                ))
                .collect(Collectors.joining(",", "[", "]"));
    
        System.out.println("Eventos JSON: " + eventosJson);  // Agrega esto para verificar el JSON
    
        return eventosJson;
    }
    
}
