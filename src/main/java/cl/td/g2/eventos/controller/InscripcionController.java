package cl.td.g2.eventos.controller;

import cl.td.g2.eventos.dto.InscripcionDTO;
import cl.td.g2.eventos.service.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        Optional<InscripcionDTO> inscripcionDTO = inscripcionService.getInscripcionById(id);
        return inscripcionDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<InscripcionDTO> createInscripcion(@RequestBody InscripcionDTO inscripcionDTO) {
        InscripcionDTO createdInscripcion = inscripcionService.createInscripcion(inscripcionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInscripcion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InscripcionDTO> updateInscripcion(@PathVariable Long id, @RequestBody InscripcionDTO inscripcionDTO) {
        InscripcionDTO updatedInscripcion = inscripcionService.updateInscripcion(id, inscripcionDTO);
        return updatedInscripcion != null ? ResponseEntity.ok(updatedInscripcion) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInscripcion(@PathVariable Long id) {
        inscripcionService.deleteInscripcion(id);
        return ResponseEntity.noContent().build();
    }
}
