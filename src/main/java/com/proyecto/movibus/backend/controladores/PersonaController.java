package com.proyecto.movibus.backend.controladores;

import com.proyecto.movibus.backend.entidades.Persona;
import com.proyecto.movibus.backend.repositorios.PersonaRepository;
import com.proyecto.movibus.backend.servicios.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @Autowired
    private PersonaRepository personaRepository;

    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            return ResponseEntity.ok(
                    personaService.listarPersonas()
            );
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "error", e.getMessage()
                    ));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        try {
            Persona persona = personaService.buscarPersona(id);
            if (persona == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Map.of(
                                "error", "Persona no encontrada"
                        ));
            }
            return ResponseEntity.ok(persona);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "error", e.getMessage()
                    ));
        }
    }

    @PostMapping
    public ResponseEntity<?> insertar(@RequestBody Persona persona) {
        try {
            if (personaRepository.existsById(persona.getId())) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(Map.of(
                                "error", "La persona ya existe"
                        ));
            }
            personaService.crearPersona(persona);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(Map.of(
                            "mensaje", "Persona insertada correctamente"
                    ));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "error", e.getMessage()
                    ));
        }
    }

    @PutMapping
    public ResponseEntity<?> actualizar(@RequestBody Persona persona) {
        try {
            if (!personaRepository.existsById(persona.getId())) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Map.of(
                                "error", "Persona no encontrada"
                        ));
            }
            personaService.actualizarPersona(persona);
            return ResponseEntity.ok(
                    Map.of(
                            "mensaje", "Persona actualizada correctamente"
                    )
            );
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "error", e.getMessage()
                    ));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            if (!personaRepository.existsById(id)) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Map.of(
                                "error", "Persona no encontrada"
                        ));
            }
            personaService.eliminarPersona(id);
            return ResponseEntity.ok(
                    Map.of(
                            "mensaje", "Persona eliminada correctamente"
                    )
            );
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "error", e.getMessage()
                    ));
        }
    }
}