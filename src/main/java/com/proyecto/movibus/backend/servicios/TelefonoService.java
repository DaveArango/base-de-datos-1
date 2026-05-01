package com.proyecto.movibus.backend.servicios;

import com.proyecto.movibus.backend.entidades.Telefono;
import com.proyecto.movibus.backend.repositorios.TelefonoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelefonoService {

    @Autowired
    private TelefonoRepository telefonoRepository;

    public List<Telefono> listar() {
        return telefonoRepository.listar();
    }

    public Telefono buscarPorId(Integer codigo) {
        return telefonoRepository.buscarPorId(codigo)
                .orElseThrow(() -> new RuntimeException("Telefono no encontrado"));
    }

    public void insertar(
            Integer codigo,
            String numero
    ) {
        telefonoRepository.insertar(
                codigo,
                numero
        );
    }

    public void actualizar(
            Integer codigo,
            String numero
    ) {
        telefonoRepository.actualizar(
                codigo,
                numero
        );
    }

    public void eliminar(Integer codigo) {
        telefonoRepository.eliminar(codigo);
    }
}
