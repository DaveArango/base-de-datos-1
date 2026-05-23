package com.proyecto.movibus.backend.controladores;

import com.proyecto.movibus.backend.entidades.Reporte;
import com.proyecto.movibus.backend.entidades.dtos.ReporteDTO;
import com.proyecto.movibus.backend.repositorios.ReporteRepository;
import com.proyecto.movibus.backend.servicios.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired private ReporteService reporteService;
    @Autowired private ReporteRepository reporteRepository;

    // --- CRUD estándar ---
    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            return ResponseEntity.ok(reporteService.listarReportes());
        }
        catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        try {
            Reporte r = reporteService.buscarReporte(id);
            if (r == null) return ResponseEntity.status(404)
                    .body(Map.of("error", "Reporte no encontrado"));
            return ResponseEntity.ok(r);
        } catch (Exception e) {
            return ResponseEntity.status(500)
                .body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> insertar(@RequestBody ReporteDTO dto) {
        try {
            if (reporteRepository.existsById(dto.getId()))
                return ResponseEntity.status(400)
                        .body(Map.of("error", "El reporte ya existe"));
            reporteService.crearReporte(dto);
            return ResponseEntity.status(201)
                    .body(Map.of("mensaje", "Reporte creado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<?> actualizar(@RequestBody ReporteDTO dto) {
        try {
            if (!reporteRepository.existsById(dto.getId()))
                return ResponseEntity.status(404)
                        .body(Map.of("error", "Reporte no encontrado"));
            reporteService.actualizarReporte(dto);
            return ResponseEntity.ok(Map.of("mensaje", "Reporte actualizado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            if (!reporteRepository.existsById(id))
                return ResponseEntity.status(404)
                        .body(Map.of("error", "Reporte no encontrado"));
            reporteService.eliminarReporte(id);
            return ResponseEntity.ok(Map.of("mensaje", "Reporte eliminado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // --- FÁCILES ---
    @GetMapping("/consultas/viajes")
    public ResponseEntity<?> reporteViajes() {
        try {
            return ResponseEntity.ok(reporteRepository.reporteViajes());
        }
        catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/consultas/incidentes")
    public ResponseEntity<?> reporteIncidentes() {
        try {
            return ResponseEntity.ok(reporteRepository.reporteIncidentes());
        }
        catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/consultas/vehiculos")
    public ResponseEntity<?> reporteVehiculos() {
        try {
            return ResponseEntity.ok(reporteRepository.reporteVehiculos());
        }
        catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // --- MEDIOS ---
    @GetMapping("/consultas/conductores-recorridos")
    public ResponseEntity<?> reporteConductores() {
        try {
            return ResponseEntity.ok(reporteRepository.reporteConductoresConRecorridos());
        }
        catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/consultas/pasajeros-viajes")
    public ResponseEntity<?> reportePasajeros() {
        try {
            return ResponseEntity.ok(reporteRepository.reportePasajerosConViajes());
        }
        catch (Exception e) {
            return ResponseEntity.status(500)
                .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/consultas/rutas-completas")
    public ResponseEntity<?> reporteRutas() {
        try {
            return ResponseEntity.ok(reporteRepository.reporteRutasCompletas());
        }
        catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/consultas/facturas-detalladas")
    public ResponseEntity<?> reporteFacturas() {
        try {
            return ResponseEntity.ok(reporteRepository.reporteFacturasDetalladas());
        }
        catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // --- DIFÍCILES ---
    @GetMapping("/consultas/pasajeros-gasto-superior")
    public ResponseEntity<?> reporteGastoSuperior() {
        try {
            return ResponseEntity.ok(reporteRepository.reportePasajerosConGastoSuperiorAlPromedio());
        }
        catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/consultas/conductores-en-curso")
    public ResponseEntity<?> reporteConductoresEnCurso() {
        try {
            return ResponseEntity.ok(reporteRepository.reporteConductoresEnCurso());
        }
        catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/consultas/rutas-incidentes-pendientes")
    public ResponseEntity<?> reporteRutasIncidentes() {
        try {
            return ResponseEntity.ok(reporteRepository.reporteRutasConIncidentesPendientes());
        }
        catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}