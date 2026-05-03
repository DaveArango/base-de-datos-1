package com.proyecto.movibus.backend.controladores;

import com.proyecto.movibus.backend.entidades.Conductor;
import com.proyecto.movibus.backend.entidades.dtos.ConductorDTO;
import com.proyecto.movibus.backend.repositorios.ConductorRepository;
import com.proyecto.movibus.backend.servicios.ConductorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/conductores")
public class ConductorController {

    @Autowired
    private ConductorService conductorService;

    @Autowired
    private ConductorRepository conductorRepository;

    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            return ResponseEntity.ok(conductorService.listarConductores());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        try {
            Conductor conductor = conductorService.buscarConductor(id);
            if (conductor == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error",
                                "Conductor no encontrado"));
            return ResponseEntity.ok(conductor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> insertar(@RequestBody ConductorDTO dto) {
        try {
            if (conductorRepository.existsById(dto.getId()))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error",
                                "El conductor ya existe"));
            conductorService.crearConductor(dto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("mensaje",
                            "Conductor insertado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<?> actualizar(@RequestBody ConductorDTO dto) {
        try {
            if (!conductorRepository.existsById(dto.getId()))
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error",
                                "Conductor no encontrado"));
            conductorService.actualizarConductor(dto);
            return ResponseEntity.ok(Map.of("mensaje",
                    "Conductor actualizado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            if (!conductorRepository.existsById(id))
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error",
                                "Conductor no encontrado"));
            conductorService.eliminarConductor(id);
            return ResponseEntity.ok(Map.of("mensaje",
                    "Conductor eliminado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
