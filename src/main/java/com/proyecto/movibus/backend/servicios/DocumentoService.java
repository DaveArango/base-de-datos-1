package com.proyecto.movibus.backend.servicios;

import com.proyecto.movibus.backend.entidades.Documento;
import com.proyecto.movibus.backend.repositorios.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentoService {

    @Autowired
    private DocumentoRepository documentoRepository;

    public List<Documento> listar() {
        return documentoRepository.listar();
    }

    public Documento buscarPorId(Integer codigo) {
        return documentoRepository.buscarPorId(codigo)
                .orElseThrow(() -> new RuntimeException("Documento no encontrado"));
    }

    public void insertar(
            Integer codigo,
            String tipo
    ) {
        documentoRepository.insertar(
                codigo,
                tipo
        );
    }

    public void actualizar(
            Integer codigo,
            String tipo
    ) {
        documentoRepository.actualizar(
                codigo,
                tipo
        );
    }

    public void eliminar(Integer codigo) {
        documentoRepository.eliminar(codigo);
    }
}
