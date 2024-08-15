package cl.td.g2.eventos.controller;

import cl.td.g2.eventos.dto.InscripcionDTO;
import cl.td.g2.eventos.service.InscripcionService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscripciones")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    @GetMapping
    public ResponseEntity<List<InscripcionDTO>> getAllInscripciones() {
        return ResponseEntity.ok(inscripcionService.getAllInscripciones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InscripcionDTO> getInscripcionById(@PathVariable Long id) {
        InscripcionDTO inscripcionDTO = inscripcionService.getInscripcionById(id);
        return ResponseEntity.ok(inscripcionDTO);
    }

    @PostMapping
    public ResponseEntity<InscripcionDTO> createInscripcion(@Valid @RequestBody InscripcionDTO inscripcionDTO) {
        InscripcionDTO createdInscripcion = inscripcionService.createInscripcion(inscripcionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInscripcion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InscripcionDTO> updateInscripcion(@PathVariable Long id, @Valid @RequestBody InscripcionDTO inscripcionDTO) {
        InscripcionDTO updatedInscripcion = inscripcionService.updateInscripcion(id, inscripcionDTO);
        return ResponseEntity.ok(updatedInscripcion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInscripcion(@PathVariable Long id) {
        inscripcionService.deleteInscripcion(id);
        return ResponseEntity.noContent().build();
    }
}
