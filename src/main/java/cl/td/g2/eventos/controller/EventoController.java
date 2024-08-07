package cl.td.g2.eventos.controller;

import cl.td.g2.eventos.dto.EventoDTO;
import cl.td.g2.eventos.service.EventoService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos") // TODO sugerir como es la api, si es mejor poner la ruta /api/eventos
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping
    public ResponseEntity<List<EventoDTO>> getAllEventos() {
        return ResponseEntity.ok(eventoService.getAllEventos());
    }

    @GetMapping("/{id}")
    /*
    public ResponseEntity<EventoDTO> getEventoById(@PathVariable Long id) {
        Optional<EventoDTO> eventoDTO = eventoService.getEventoById(id);
        return eventoDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    */
    public ResponseEntity<EventoDTO> getEventoById(@PathVariable Long id) { // TODO probar si devuelve lo mismo que lo anterior comentado
        EventoDTO eventoDTO = eventoService.getEventoById(id);
        //return eventoDTO != null ? ResponseEntity.ok(eventoDTO) : ResponseEntity.notFound().build();
        return ResponseEntity.ok(eventoDTO);
    }

    @PostMapping
    public ResponseEntity<EventoDTO> createEvento(@Valid @RequestBody EventoDTO eventoDTO) {
        EventoDTO createdEvento = eventoService.createEvento(eventoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEvento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventoDTO> updateEvento(@Valid @PathVariable Long id, @RequestBody EventoDTO eventoDTO) {
        EventoDTO updatedEvento = eventoService.updateEvento(id, eventoDTO);
        return updatedEvento != null ? ResponseEntity.ok(updatedEvento) : ResponseEntity.notFound().build(); // TODO preguntar porqué el return no es como el return del getEventoById
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable Long id) {
        eventoService.deleteEvento(id);
        return ResponseEntity.noContent().build();
    }
}
