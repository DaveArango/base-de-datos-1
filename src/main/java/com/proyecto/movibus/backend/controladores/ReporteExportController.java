package com.proyecto.movibus.backend.controladores;

import com.proyecto.movibus.backend.repositorios.ReporteRepository;
import com.proyecto.movibus.backend.servicios.ReporteGeneradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reportes/exportar")
public class ReporteExportController {

    @Autowired
    private ReporteRepository reporteRepository;
    @Autowired
    private ReporteGeneradorService generador;

    // FÁCILES

    @GetMapping("/viajes/{formato}")
    public ResponseEntity<?> exportarViajes(@PathVariable String formato) {
        String[] columnas = {"ID",
                "Origen",
                "Destino",
                "Fecha Inicio",
                "Valor Pagado",
                "Estado Viaje"};
        return exportar(
                "Reporte de Viajes",
                "Listado completo de viajes registrados en el sistema",
                columnas,
                reporteRepository.reporteViajes(),
                formato,
                "reporte_viajes"
        );
    }

    @GetMapping("/incidentes/{formato}")
    public ResponseEntity<?> exportarIncidentes(@PathVariable String formato) {
        String[] columnas = {"Código",
                "Fecha",
                "Descripción",
                "Estado",
                "Tipo de Evento"};
        return exportar(
                "Reporte de Incidentes",
                "Listado de incidentes reportados en el sistema",
                columnas,
                reporteRepository.reporteIncidentes(),
                formato,
                "reporte_incidentes"
        );
    }

    @GetMapping("/vehiculos/{formato}")
    public ResponseEntity<?> exportarVehiculos(@PathVariable String formato) {
        String[] columnas = {"Código",
                "Placa",
                "Modelo",
                "Capacidad",
                "Marca",
                "Combustible",
                "Estado"};
        return exportar(
                "Reporte de Vehículos",
                "Inventario completo de la flota vehicular",
                columnas,
                reporteRepository.reporteVehiculos(),
                formato,
                "reporte_vehiculos"
        );
    }

    // MEDIOS

    @GetMapping("/conductores-recorridos/{formato}")
    public ResponseEntity<?> exportarConductoresRecorridos(@PathVariable String formato) {
        String[] columnas = {"Nombre",
                "Apellido",
                "Correo",
                "Experiencia",
                "Placa Vehículo",
                "Modelo",
                "Total Recorridos"};
        return exportar(
                "Reporte de Conductores y Recorridos",
                "Conductores activos con sus vehículos asignados y recorridos realizados",
                columnas,
                reporteRepository.reporteConductoresConRecorridos(),
                formato,
                "reporte_conductores"
        );
    }

    @GetMapping("/pasajeros-viajes/{formato}")
    public ResponseEntity<?> exportarPasajerosViajes(@PathVariable String formato) {
        String[] columnas = {"Nombre",
                "Apellido",
                "Correo",
                "Método Pago",
                "Tarifa",
                "Total Viajes",
                "Total Pagado"};
        return exportar(
                "Reporte de Pasajeros y Viajes",
                "Pasajeros registrados con su historial de viajes y montos pagados",
                columnas,
                reporteRepository.reportePasajerosConViajes(),
                formato,
                "reporte_pasajeros"
        );
    }

    @GetMapping("/rutas-completas/{formato}")
    public ResponseEntity<?> exportarRutasCompletas(@PathVariable String formato) {
        String[] columnas = {"Código",
                "Ruta",
                "Tarifa",
                "Estado Ruta",
                "Parada Inicio",
                "Parada Fin",
                "Fecha Inicio",
                "Fecha Fin",
                "Estado Recorrido"};
        return exportar(
                "Reporte de Rutas",
                "Rutas del sistema con sus paradas y estado del recorrido",
                columnas,
                reporteRepository.reporteRutasCompletas(),
                formato,
                "reporte_rutas"
        );
    }

    @GetMapping("/facturas-detalladas/{formato}")
    public ResponseEntity<?> exportarFacturas(@PathVariable String formato) {
        String[] columnas = {"N° Factura",
                "Fecha",
                "Valor",
                "Descripción",
                "Nombre",
                "Apellido",
                "Origen",
                "Destino",
                "Fecha Viaje",
                "Estado"};
        return exportar(
                "Reporte de Facturas",
                "Facturas generadas con información del pasajero y viaje asociado",
                columnas,
                reporteRepository.reporteFacturasDetalladas(),
                formato,
                "reporte_facturas"
        );
    }

    // DIFÍCILES

    @GetMapping("/pasajeros-gasto-superior/{formato}")
    public ResponseEntity<?> exportarGastoSuperior(@PathVariable String formato) {
        String[] columnas = {"Nombre",
                "Apellido",
                "Correo",
                "Total Gastado"};
        return exportar(
                "Pasajeros con Gasto Superior al Promedio",
                "Pasajeros cuyo gasto total supera el promedio general del sistema",
                columnas,
                reporteRepository.reportePasajerosConGastoSuperiorAlPromedio(),
                formato,
                "reporte_gasto_superior"
        );
    }

    @GetMapping("/conductores-en-curso/{formato}")
    public ResponseEntity<?> exportarConductoresEnCurso(@PathVariable String formato) {
        String[] columnas = {"Nombre",
                "Apellido",
                "Experiencia",
                "Placa",
                "Fecha Inicio",
                "Estado Recorrido"};
        return exportar(
                "Conductores con Recorridos en Curso",
                "Conductores que actualmente tienen recorridos activos asignados",
                columnas,
                reporteRepository.reporteConductoresEnCurso(),
                formato,
                "reporte_conductores_en_curso"
        );
    }

    @GetMapping("/rutas-incidentes-pendientes/{formato}")
    public ResponseEntity<?> exportarRutasIncidentes(@PathVariable String formato) {
        String[] columnas = {"Ruta",
                "Tarifa",
                "Parada Inicio",
                "Parada Fin",
                "Total Incidentes"};
        return exportar(
                "Rutas con Incidentes Pendientes",
                "Rutas que tienen incidentes sin resolver registrados",
                columnas,
                reporteRepository.reporteRutasConIncidentesPendientes(),
                formato,
                "reporte_rutas_incidentes"
        );
    }

    // Metodo central de exportación

    private ResponseEntity<?> exportar(String titulo,
                                       String subtitulo,
                                       String[] columnas,
                                       List<Object[]> datos,
                                       String formato,
                                       String nombreArchivo) {
        try {
            byte[] archivo;
            MediaType mediaType;
            String extension;

            switch (formato.toLowerCase()) {
                case "pdf" -> {
                    archivo    = generador.generarPDF(titulo, subtitulo, columnas, datos);
                    mediaType  = MediaType.APPLICATION_PDF;
                    extension  = ".pdf";
                }
                case "excel" -> {
                    archivo    = generador.generarExcel(titulo, subtitulo, columnas, datos);
                    mediaType  = MediaType.parseMediaType(
                            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                    extension  = ".xlsx";
                }
                case "word" -> {
                    archivo    = generador.generarWord(titulo, subtitulo, columnas, datos);
                    mediaType  = MediaType.parseMediaType(
                            "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                    extension  = ".docx";
                }
                default -> {
                    return ResponseEntity.badRequest()
                            .body(Map.of("error",
                                    "Formato no válido. Use: pdf, excel o word"));
                }
            }
            ContentDisposition disposition = ContentDisposition.attachment()
                    .filename(nombreArchivo + extension)
                    .build();
            return ResponseEntity.ok()
                    .contentType(mediaType)
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            disposition.toString())
                    .body(archivo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}