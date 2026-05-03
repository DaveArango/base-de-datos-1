package com.proyecto.movibus.backend.controladores;

import com.proyecto.movibus.backend.entidades.Factura;
import com.proyecto.movibus.backend.entidades.dtos.FacturaDTO;
import com.proyecto.movibus.backend.repositorios.FacturaRepository;
import com.proyecto.movibus.backend.servicios.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private FacturaRepository facturaRepository;

    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            return ResponseEntity.ok(facturaService.listarFacturas());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> buscarPorCodigo(@PathVariable Integer codigo) {
        try {
            Factura factura = facturaService.buscarFactura(codigo);
            if (factura == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error",
                                "Factura no encontrada"));
            return ResponseEntity.ok(factura);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> insertar(@RequestBody FacturaDTO dto) {
        try {
            if (facturaRepository.existsById(dto.getCodigo()))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error",
                                "La factura ya existe"));
            facturaService.crearFactura(dto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("mensaje",
                            "Factura insertada correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<?> actualizar(@RequestBody FacturaDTO dto) {
        try {
            if (!facturaRepository.existsById(dto.getCodigo()))
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error",
                                "Factura no encontrada"));
            facturaService.actualizarFactura(dto);
            return ResponseEntity.ok(Map.of("mensaje",
                    "Factura actualizada correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<?> eliminar(@PathVariable Integer codigo) {
        try {
            if (!facturaRepository.existsById(codigo))
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error",
                                "Factura no encontrada"));
            facturaService.eliminarFactura(codigo);
            return ResponseEntity.ok(Map.of("mensaje",
                    "Factura eliminada correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}