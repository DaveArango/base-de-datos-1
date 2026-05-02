package com.proyecto.movibus.backend.controladores;

import com.proyecto.movibus.backend.entidades.Documento;
import com.proyecto.movibus.backend.repositorios.DocumentoRepository;
import com.proyecto.movibus.backend.servicios.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/documentos")
public class DocumentoController {

    @Autowired
    private DocumentoService documentoService;

    @Autowired
    private DocumentoRepository documentoRepository;

    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            return ResponseEntity.ok(
                    documentoService.listar()
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
            Documento documento = documentoService.buscarPorId(codigo);
            if (documento == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Map.of(
                                "error", "Documento no encontrado"
                        ));
            }
            return ResponseEntity.ok(documento);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "error", e.getMessage()
                    ));
        }
    }

    @PostMapping
    public ResponseEntity<?> insertar(@RequestBody Documento documento) {
        try {
            if (documentoRepository.existsById(documento.getCodigo())) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(Map.of(
                                "error", "El documento ya existe"
                        ));
            }
            documentoService.insertar(
                    documento.getCodigo(),
                    documento.getTipo()
            );
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(Map.of(
                            "mensaje", "Documento insertado correctamente"
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
    public ResponseEntity<?> actualizar(@RequestBody Documento documento) {
        try {
            if (!documentoRepository.existsById(documento.getCodigo())) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Map.of(
                                "error", "Documento no encontrado"
                        ));
            }
            documentoService.actualizar(
                    documento.getCodigo(),
                    documento.getTipo()
            );
            return ResponseEntity.ok(
                    Map.of(
                            "mensaje", "Documento actualizado correctamente"
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
            if (!documentoRepository.existsById(codigo)) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Map.of(
                                "error", "Documento no encontrado"
                        ));
            }
            documentoService.eliminar(codigo);
            return ResponseEntity.ok(
                    Map.of(
                            "mensaje", "Documento eliminado correctamente"
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
