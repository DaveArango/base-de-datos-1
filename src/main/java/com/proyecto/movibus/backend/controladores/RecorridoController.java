package com.proyecto.movibus.backend.controladores;

import com.proyecto.movibus.backend.entidades.Recorrido;
import com.proyecto.movibus.backend.entidades.dtos.RecorridoDTO;
import com.proyecto.movibus.backend.repositorios.RecorridoRepository;
import com.proyecto.movibus.backend.servicios.RecorridoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/recorridos")
public class RecorridoController {

    @Autowired
    private RecorridoService recorridoService;

    @Autowired
    private RecorridoRepository recorridoRepository;

    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            return ResponseEntity.ok(recorridoService.listarRecorridos());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        try {
            Recorrido recorrido = recorridoService.buscarRecorrido(id);
            if (recorrido == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error",
                                "Recorrido no encontrado"));
            return ResponseEntity.ok(recorrido);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> insertar(@RequestBody RecorridoDTO dto) {
        try {
            if (recorridoRepository.existsById(dto.getId()))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error",
                                "El recorrido ya existe"));
            recorridoService.crearRecorrido(dto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("mensaje",
                            "Recorrido insertado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<?> actualizar(@RequestBody RecorridoDTO dto) {
        try {
            if (!recorridoRepository.existsById(dto.getId()))
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error",
                                "Recorrido no encontrado"));
            recorridoService.actualizarRecorrido(dto);
            return ResponseEntity.ok(Map.of("mensaje",
                    "Recorrido actualizado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            if (!recorridoRepository.existsById(id))
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error",
                                "Recorrido no encontrado"));
            recorridoService.eliminarRecorrido(id);
            return ResponseEntity.ok(Map.of("mensaje",
                    "Recorrido eliminado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}