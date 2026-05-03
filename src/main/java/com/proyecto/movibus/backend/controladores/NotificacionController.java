package com.proyecto.movibus.backend.controladores;

import com.proyecto.movibus.backend.entidades.Notificacion;
import com.proyecto.movibus.backend.entidades.dtos.NotificacionDTO;
import com.proyecto.movibus.backend.repositorios.NotificacionRepository;
import com.proyecto.movibus.backend.servicios.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @Autowired
    private NotificacionRepository notificacionRepository;

    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            return ResponseEntity.ok(notificacionService.listarNotificaciones());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> buscarPorCodigo(@PathVariable Integer codigo) {
        try {
            Notificacion notificacion = notificacionService.buscarNotificacion(codigo);
            if (notificacion == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error",
                                "Notificación no encontrada"));
            return ResponseEntity.ok(notificacion);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> insertar(@RequestBody NotificacionDTO dto) {
        try {
            if (notificacionRepository.existsById(dto.getCodigo()))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error",
                                "La notificación ya existe"));
            notificacionService.crearNotificacion(dto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("mensaje",
                            "Notificación insertada correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<?> actualizar(@RequestBody NotificacionDTO dto) {
        try {
            if (!notificacionRepository.existsById(dto.getCodigo()))
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error",
                                "Notificación no encontrada"));
            notificacionService.actualizarNotificacion(dto);
            return ResponseEntity.ok(Map.of("mensaje",
                    "Notificación actualizada correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<?> eliminar(@PathVariable Integer codigo) {
        try {
            if (!notificacionRepository.existsById(codigo))
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error",
                                "Notificación no encontrada"));
            notificacionService.eliminarNotificacion(codigo);
            return ResponseEntity.ok(Map.of("mensaje",
                    "Notificación eliminada correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
