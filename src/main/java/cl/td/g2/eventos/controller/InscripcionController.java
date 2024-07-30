package cl.td.g2.eventos.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.td.g2.eventos.dto.InscripcionDTO;
import cl.td.g2.eventos.service.InscripcionService;

import java.util.List;

@RestController
@RequestMapping("/inscripciones")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    @GetMapping
    public List<InscripcionDTO> getAllInscripciones() {
        return inscripcionService.getAllInscripciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InscripcionDTO> getInscripcionById(@PathVariable Long id) {
        InscripcionDTO inscripcion = inscripcionService.getInscripcionById(id);
        return ResponseEntity.ok(inscripcion);
    }

    @PostMapping
    public ResponseEntity<InscripcionDTO> createInscripcion(@RequestBody InscripcionDTO inscripcionDTO) {
        InscripcionDTO createdInscripcion = inscripcionService.createInscripcion(inscripcionDTO);
        return ResponseEntity.ok(createdInscripcion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InscripcionDTO> updateInscripcion(@PathVariable Long id, @RequestBody InscripcionDTO inscripcionDTO) {
        InscripcionDTO updatedInscripcion = inscripcionService.updateInscripcion(id, inscripcionDTO);
        return ResponseEntity.ok(updatedInscripcion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInscripcion(@PathVariable Long id) {
        inscripcionService.deleteInscripcion(id);
        return ResponseEntity.noContent().build();
    }
}
