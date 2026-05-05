package com.proyecto.movibus.backend.controladores;

import com.proyecto.movibus.backend.entidades.UbicacionRecorrido;
import com.proyecto.movibus.backend.entidades.dtos.UbicacionRecorridoDTO;
import com.proyecto.movibus.backend.repositorios.UbicacionRecorridoRepository;
import com.proyecto.movibus.backend.servicios.UbicacionRecorridoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ubicaciones-recorrido")
public class UbicacionRecorridoController {

    @Autowired
    private UbicacionRecorridoService ubicacionRecorridoService;

    @Autowired
    private UbicacionRecorridoRepository ubicacionRecorridoRepository;

    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            return ResponseEntity.ok(ubicacionRecorridoService.listarUbicaciones());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        try {
            UbicacionRecorrido ubicacion = ubicacionRecorridoService.buscarUbicacion(id);
            if (ubicacion == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Ubicación no encontrada"));
            return ResponseEntity.ok(ubicacion);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> insertar(@RequestBody UbicacionRecorridoDTO dto) {
        try {
            if (ubicacionRecorridoRepository.existsById(dto.getId()))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error",
                                "La ubicación ya existe"));
            ubicacionRecorridoService.crearUbicacion(dto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("mensaje",
                            "Ubicación insertada correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<?> actualizar(@RequestBody UbicacionRecorridoDTO dto) {
        try {
            if (!ubicacionRecorridoRepository.existsById(dto.getId()))
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error",
                                "Ubicación no encontrada"));
            ubicacionRecorridoService.actualizarUbicacion(dto);
            return ResponseEntity.ok(Map.of("mensaje",
                    "Ubicación actualizada correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            if (!ubicacionRecorridoRepository.existsById(id))
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error",
                                "Ubicación no encontrada"));
            ubicacionRecorridoService.eliminarUbicacion(id);
            return ResponseEntity.ok(Map.of("mensaje",
                    "Ubicación eliminada correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
