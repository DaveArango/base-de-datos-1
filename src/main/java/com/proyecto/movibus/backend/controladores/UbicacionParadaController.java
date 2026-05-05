package com.proyecto.movibus.backend.controladores;

import com.proyecto.movibus.backend.entidades.UbicacionParada;
import com.proyecto.movibus.backend.entidades.dtos.UbicacionParadaDTO;
import com.proyecto.movibus.backend.repositorios.UbicacionParadaRepository;
import com.proyecto.movibus.backend.servicios.UbicacionParadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ubicaciones-parada")
public class UbicacionParadaController {

    @Autowired
    private UbicacionParadaService ubicacionParadaService;

    @Autowired
    private UbicacionParadaRepository ubicacionParadaRepository;

    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            return ResponseEntity.ok(ubicacionParadaService.listarUbicaciones());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> buscarPorCodigo(@PathVariable Integer codigo) {
        try {
            UbicacionParada ubicacion = ubicacionParadaService.buscarUbicacion(codigo);
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
    public ResponseEntity<?> insertar(@RequestBody UbicacionParadaDTO dto) {
        try {
            if (ubicacionParadaRepository.existsById(dto.getCodigo()))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error",
                                "La ubicación ya existe"));
            ubicacionParadaService.crearUbicacion(dto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("mensaje",
                            "Ubicación insertada correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<?> actualizar(@RequestBody UbicacionParadaDTO dto) {
        try {
            if (!ubicacionParadaRepository.existsById(dto.getCodigo()))
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error",
                                "Ubicación no encontrada"));
            ubicacionParadaService.actualizarUbicacion(dto);
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
            if (!ubicacionParadaRepository.existsById(codigo))
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error",
                                "Ubicación no encontrada"));
            ubicacionParadaService.eliminarUbicacion(codigo);
            return ResponseEntity.ok(Map.of("mensaje",
                    "Ubicación eliminada correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
