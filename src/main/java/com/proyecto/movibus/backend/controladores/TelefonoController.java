package com.proyecto.movibus.backend.controladores;

import com.proyecto.movibus.backend.entidades.Telefono;
import com.proyecto.movibus.backend.repositorios.TelefonoRepository;
import com.proyecto.movibus.backend.servicios.TelefonoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/telefonos")
public class TelefonoController {

    @Autowired
    private TelefonoService telefonoService;

    @Autowired
    private TelefonoRepository telefonoRepository;

    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            return ResponseEntity.ok(
                    telefonoService.listar()
            );
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "error", e.getMessage()
                    ));
        }
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer codigo) {
        try {
            Telefono telefono = telefonoService.buscarPorId(codigo);
            if (telefono == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Map.of(
                                "error", "Telefono no encontrado"
                        ));
            }
            return ResponseEntity.ok(telefono);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "error", e.getMessage()
                    ));
        }
    }

    @PostMapping
    public ResponseEntity<?> insertar(@RequestBody Telefono telefono) {
        try {
            if (telefonoRepository.existsById(telefono.getCodigo())) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(Map.of(
                                "error", "El telefono ya existe"
                        ));
            }
            telefonoService.insertar(
                    telefono.getCodigo(),
                    telefono.getNumero()
            );
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(Map.of(
                            "mensaje", "Telefono insertado correctamente"
                    ));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "error", e.getMessage()
                    ));
        }
    }

    @PutMapping
    public ResponseEntity<?> actualizar(@RequestBody Telefono telefono) {
        try {
            if (!telefonoRepository.existsById(telefono.getCodigo())) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Map.of(
                                "error", "Telefono no encontrado"
                        ));
            }
            telefonoService.actualizar(
                    telefono.getCodigo(),
                    telefono.getNumero()
            );
            return ResponseEntity.ok(
                    Map.of(
                            "mensaje", "Telefono actualizado correctamente"
                    )
            );
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "error", e.getMessage()
                    ));
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<?> eliminar(@PathVariable Integer codigo) {
        try {
            if (!telefonoRepository.existsById(codigo)) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Map.of(
                                "error", "Telefono no encontrado"
                        ));
            }
            telefonoService.eliminar(codigo);
            return ResponseEntity.ok(
                    Map.of(
                            "mensaje", "Telefono eliminado correctamente"
                    )
            );
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "error", e.getMessage()
                    ));
        }
    }
}
