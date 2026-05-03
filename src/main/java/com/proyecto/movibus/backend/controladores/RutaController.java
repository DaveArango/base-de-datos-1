package com.proyecto.movibus.backend.controladores;

import com.proyecto.movibus.backend.entidades.Ruta;
import com.proyecto.movibus.backend.entidades.dtos.RutaDTO;
import com.proyecto.movibus.backend.repositorios.RutaRepository;
import com.proyecto.movibus.backend.servicios.RutaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/rutas")
public class RutaController {

    @Autowired
    private RutaService rutaService;

    @Autowired
    private RutaRepository rutaRepository;

    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            return ResponseEntity.ok(rutaService.listarRutas());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> buscarPorCodigo(@PathVariable Integer codigo) {
        try {
            Ruta ruta = rutaService.buscarRuta(codigo);
            if (ruta == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error",
                                "Ruta no encontrada"));
            return ResponseEntity.ok(ruta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> insertar(@RequestBody RutaDTO dto) {
        try {
            if (rutaRepository.existsById(dto.getCodigo()))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error",
                                "La ruta ya existe"));
            rutaService.crearRuta(dto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("mensaje",
                            "Ruta insertada correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<?> actualizar(@RequestBody RutaDTO dto) {
        try {
            if (!rutaRepository.existsById(dto.getCodigo()))
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error",
                                "Ruta no encontrada"));
            rutaService.actualizarRuta(dto);
            return ResponseEntity.ok(Map.of("mensaje",
                    "Ruta actualizada correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<?> eliminar(@PathVariable Integer codigo) {
        try {
            if (!rutaRepository.existsById(codigo))
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error",
                                "Ruta no encontrada"));
            rutaService.eliminarRuta(codigo);
            return ResponseEntity.ok(Map.of("mensaje",
                    "Ruta eliminada correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}