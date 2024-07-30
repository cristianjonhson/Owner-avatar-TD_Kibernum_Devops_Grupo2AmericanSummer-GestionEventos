package cl.td.g2.eventos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.td.g2.eventos.dto.EventoDTO;
import cl.td.g2.eventos.service.EventoService;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping
    public List<EventoDTO> getAllEventos() {
        return eventoService.getAllEventos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoDTO> getEventoById(@PathVariable Long id) {
        EventoDTO evento = eventoService.getEventoById(id);
        return ResponseEntity.ok(evento);
    }

    @PostMapping
    public ResponseEntity<EventoDTO> createEvento(@RequestBody EventoDTO eventoDTO) {
        EventoDTO createdEvento = eventoService.createEvento(eventoDTO);
        return ResponseEntity.ok(createdEvento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventoDTO> updateEvento(@PathVariable Long id, @RequestBody EventoDTO eventoDTO) {
        EventoDTO updatedEvento = eventoService.updateEvento(id, eventoDTO);
        return ResponseEntity.ok(updatedEvento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable Long id) {
        eventoService.deleteEvento(id);
        return ResponseEntity.noContent().build();
    }
}
