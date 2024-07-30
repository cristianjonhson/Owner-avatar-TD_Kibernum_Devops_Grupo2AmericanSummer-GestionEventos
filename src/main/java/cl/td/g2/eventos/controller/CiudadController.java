package cl.td.g2.eventos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.td.g2.eventos.dto.CiudadDTO;
import cl.td.g2.eventos.service.CiudadService;

import java.util.List;

@RestController
@RequestMapping("/ciudades")
public class CiudadController {

    @Autowired
    private CiudadService ciudadService;

    @GetMapping
    public List<CiudadDTO> getAllCiudades() {
        return ciudadService.getAllCiudades();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CiudadDTO> getCiudadById(@PathVariable Long id) {
        CiudadDTO ciudad = ciudadService.getCiudadById(id);
        return ResponseEntity.ok(ciudad);
    }

    @PostMapping
    public ResponseEntity<CiudadDTO> createCiudad(@RequestBody CiudadDTO ciudadDTO) {
        CiudadDTO createdCiudad = ciudadService.createCiudad(ciudadDTO);
        return ResponseEntity.ok(createdCiudad);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CiudadDTO> updateCiudad(@PathVariable Long id, @RequestBody CiudadDTO ciudadDTO) {
        CiudadDTO updatedCiudad = ciudadService.updateCiudad(id, ciudadDTO);
        return ResponseEntity.ok(updatedCiudad);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCiudad(@PathVariable Long id) {
        ciudadService.deleteCiudad(id);
        return ResponseEntity.noContent().build();
    }
}

