package com.proyecto.movibus.backend.controladores;

import com.proyecto.movibus.backend.entidades.Incidente;
import com.proyecto.movibus.backend.entidades.dtos.IncidenteDTO;
import com.proyecto.movibus.backend.repositorios.IncidenteRepository;
import com.proyecto.movibus.backend.servicios.IncidenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/incidentes")
public class IncidenteController {

    @Autowired
    private IncidenteService incidenteService;

    @Autowired
    private IncidenteRepository incidenteRepository;

    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            return ResponseEntity.ok(incidenteService.listarIncidentes());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> buscarPorCodigo(@PathVariable Integer codigo) {
        try {
            Incidente incidente = incidenteService.buscarIncidente(codigo);
            if (incidente == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error",
                                "Incidente no encontrado"));
            return ResponseEntity.ok(incidente);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> insertar(@RequestBody IncidenteDTO dto) {
        try {
            if (incidenteRepository.existsById(dto.getCodigo()))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error",
                                "El incidente ya existe"));
            incidenteService.crearIncidente(dto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("mensaje",
                            "Incidente insertado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<?> actualizar(@RequestBody IncidenteDTO dto) {
        try {
            if (!incidenteRepository.existsById(dto.getCodigo()))
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error",
                                "Incidente no encontrado"));
            incidenteService.actualizarIncidente(dto);
            return ResponseEntity.ok(Map.of("mensaje",
                    "Incidente actualizado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<?> eliminar(@PathVariable Integer codigo) {
        try {
            if (!incidenteRepository.existsById(codigo))
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error",
                                "Incidente no encontrado"));
            incidenteService.eliminarIncidente(codigo);
            return ResponseEntity.ok(Map.of("mensaje",
                    "Incidente eliminado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}