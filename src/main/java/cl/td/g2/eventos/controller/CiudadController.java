package cl.td.g2.eventos.controller;

import cl.td.g2.eventos.dto.CiudadDTO;
import cl.td.g2.eventos.service.CiudadService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ciudades")
public class CiudadController {

    @Autowired
    private CiudadService ciudadService;

    @GetMapping
    public ResponseEntity<List<CiudadDTO>> getAllCiudades() {
        return ResponseEntity.ok(ciudadService.getAllCiudades());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CiudadDTO> getCiudadById(@PathVariable Long id) {
        Optional<CiudadDTO> ciudadDTO = ciudadService.getCiudadById(id);
        return ciudadDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CiudadDTO> createCiudad(@Valid @RequestBody CiudadDTO ciudadDTO) {
        CiudadDTO createdCiudad = ciudadService.createCiudad(ciudadDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCiudad);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CiudadDTO> updateCiudad(@PathVariable Long id, @Valid @RequestBody CiudadDTO ciudadDTO) {
        CiudadDTO updatedCiudad = ciudadService.updateCiudad(id, ciudadDTO);
        return updatedCiudad != null ? ResponseEntity.ok(updatedCiudad) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCiudad(@PathVariable Long id) {
        ciudadService.deleteCiudad(id);
        return ResponseEntity.noContent().build();
    }
}
