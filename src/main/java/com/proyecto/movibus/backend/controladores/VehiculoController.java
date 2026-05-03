package com.proyecto.movibus.backend.controladores;

import com.proyecto.movibus.backend.entidades.Vehiculo;
import com.proyecto.movibus.backend.entidades.dtos.VehiculoDTO;
import com.proyecto.movibus.backend.repositorios.VehiculoRepository;
import com.proyecto.movibus.backend.servicios.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            return ResponseEntity.ok(vehiculoService.listarVehiculos());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> buscarPorCodigo(@PathVariable Integer codigo) {
        try {
            Vehiculo vehiculo = vehiculoService.buscarVehiculo(codigo);
            if (vehiculo == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error",
                                "Vehículo no encontrado"));
            return ResponseEntity.ok(vehiculo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> insertar(@RequestBody VehiculoDTO dto) {
        try {
            if (vehiculoRepository.existsById(dto.getCodigo()))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error",
                                "El vehículo ya existe"));
            vehiculoService.crearVehiculo(dto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("mensaje",
                            "Vehículo insertado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<?> actualizar(@RequestBody VehiculoDTO dto) {
        try {
            if (!vehiculoRepository.existsById(dto.getCodigo()))
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error",
                                "Vehículo no encontrado"));
            vehiculoService.actualizarVehiculo(dto);
            return ResponseEntity.ok(Map.of("mensaje",
                    "Vehículo actualizado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<?> eliminar(@PathVariable Integer codigo) {
        try {
            if (!vehiculoRepository.existsById(codigo))
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error",
                                "Vehículo no encontrado"));
            vehiculoService.eliminarVehiculo(codigo);
            return ResponseEntity.ok(Map.of("mensaje",
                    "Vehículo eliminado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
