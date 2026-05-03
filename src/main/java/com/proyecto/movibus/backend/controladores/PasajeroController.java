package com.proyecto.movibus.backend.controladores;

import com.proyecto.movibus.backend.entidades.Pasajero;
import com.proyecto.movibus.backend.entidades.dtos.PasajeroDTO;
import com.proyecto.movibus.backend.repositorios.PasajeroRepository;
import com.proyecto.movibus.backend.servicios.PasajeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/pasajeros")
public class PasajeroController {

    @Autowired
    private PasajeroService pasajeroService;

    @Autowired
    private PasajeroRepository pasajeroRepository;

    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            return ResponseEntity.ok(pasajeroService.listarPasajeros());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        try {
            Pasajero pasajero = pasajeroService.buscarPasajero(id);
            if (pasajero == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error",
                                "Pasajero no encontrado"));
            }
            return ResponseEntity.ok(pasajero);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> insertar(@RequestBody PasajeroDTO dto) {
        try {
            if (pasajeroRepository.existsById(dto.getId())) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error",
                                "El pasajero ya existe"));
            }
            pasajeroService.crearPasajero(dto);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(Map.of("mensaje",
                            "Pasajero insertado correctamente"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<?> actualizar(@RequestBody PasajeroDTO dto) {
        try {
            if (!pasajeroRepository.existsById(dto.getId())) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error",
                                "Pasajero no encontrado"));
            }
            pasajeroService.actualizarPasajero(dto);
            return ResponseEntity.ok(Map.of("mensaje",
                    "Pasajero actualizado correctamente"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            if (!pasajeroRepository.existsById(id)) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error",
                                "Pasajero no encontrado"));
            }
            pasajeroService.eliminarPasajero(id);
            return ResponseEntity.ok(Map.of("mensaje",
                    "Pasajero eliminado correctamente"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}