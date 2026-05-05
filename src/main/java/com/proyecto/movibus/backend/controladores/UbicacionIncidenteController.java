package com.proyecto.movibus.backend.controladores;

import com.proyecto.movibus.backend.entidades.UbicacionIncidente;
import com.proyecto.movibus.backend.entidades.dtos.UbicacionIncidenteDTO;
import com.proyecto.movibus.backend.repositorios.UbicacionIncidenteRepository;
import com.proyecto.movibus.backend.servicios.UbicacionIncidenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ubicaciones-incidente")
public class UbicacionIncidenteController {

    @Autowired
    private UbicacionIncidenteService ubicacionIncidenteService;

    @Autowired
    private UbicacionIncidenteRepository ubicacionIncidenteRepository;

    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            return ResponseEntity.ok(ubicacionIncidenteService.listarUbicaciones());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> buscarPorCodigo(@PathVariable Integer codigo) {
        try {
            UbicacionIncidente ubicacion = ubicacionIncidenteService.buscarUbicacion(codigo);
            if (ubicacion == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error",
                                "Ubicación no encontrada"));
            return ResponseEntity.ok(ubicacion);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> insertar(@RequestBody UbicacionIncidenteDTO dto) {
        try {
            if (ubicacionIncidenteRepository.existsById(dto.getCodigo()))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error",
                                "La ubicación ya existe"));
            ubicacionIncidenteService.crearUbicacion(dto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("mensaje",
                            "Ubicación insertada correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<?> actualizar(@RequestBody UbicacionIncidenteDTO dto) {
        try {
            if (!ubicacionIncidenteRepository.existsById(dto.getCodigo()))
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error",
                                "Ubicación no encontrada"));
            ubicacionIncidenteService.actualizarUbicacion(dto);
            return ResponseEntity.ok(Map.of("mensaje",
                    "Ubicación actualizada correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<?> eliminar(@PathVariable Integer codigo) {
        try {
            if (!ubicacionIncidenteRepository.existsById(codigo))
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error",
                                "Ubicación no encontrada"));
            ubicacionIncidenteService.eliminarUbicacion(codigo);
            return ResponseEntity.ok(Map.of("mensaje",
                    "Ubicación eliminada correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
