package com.proyecto.movibus.backend.controladores;

import com.proyecto.movibus.backend.entidades.UbicacionVehiculo;
import com.proyecto.movibus.backend.entidades.dtos.UbicacionVehiculoDTO;
import com.proyecto.movibus.backend.repositorios.UbicacionVehiculoRepository;
import com.proyecto.movibus.backend.servicios.UbicacionVehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ubicaciones-vehiculo")
public class UbicacionVehiculoController {

    @Autowired
    private UbicacionVehiculoService ubicacionVehiculoService;

    @Autowired
    private UbicacionVehiculoRepository ubicacionVehiculoRepository;

    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            return ResponseEntity.ok(ubicacionVehiculoService.listarUbicaciones());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> buscarPorCodigo(@PathVariable Integer codigo) {
        try {
            UbicacionVehiculo ubicacion = ubicacionVehiculoService.buscarUbicacion(codigo);
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
    public ResponseEntity<?> insertar(@RequestBody UbicacionVehiculoDTO dto) {
        try {
            if (ubicacionVehiculoRepository.existsById(dto.getCodigo()))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error",
                                "La ubicación ya existe"));
            ubicacionVehiculoService.crearUbicacion(dto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("mensaje",
                            "Ubicación insertada correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<?> actualizar(@RequestBody UbicacionVehiculoDTO dto) {
        try {
            if (!ubicacionVehiculoRepository.existsById(dto.getCodigo()))
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error",
                                "Ubicación no encontrada"));
            ubicacionVehiculoService.actualizarUbicacion(dto);
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
            if (!ubicacionVehiculoRepository.existsById(codigo))
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error",
                                "Ubicación no encontrada"));
            ubicacionVehiculoService.eliminarUbicacion(codigo);
            return ResponseEntity.ok(Map.of("mensaje",
                    "Ubicación eliminada correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
