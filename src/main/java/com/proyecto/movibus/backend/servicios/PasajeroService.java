package com.proyecto.movibus.backend.servicios;

import com.proyecto.movibus.backend.entidades.Pasajero;
import com.proyecto.movibus.backend.entidades.dtos.PasajeroDTO;
import com.proyecto.movibus.backend.repositorios.PasajeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PasajeroService {

    @Autowired
    private PasajeroRepository pasajeroRepository;

    @Transactional(readOnly = true)
    public List<Pasajero> listarPasajeros() {
        return pasajeroRepository.listarTodos();
    }

    @Transactional(readOnly = true)
    public Pasajero buscarPasajero(Integer id) {
        return pasajeroRepository.buscarPorId(id)
                .orElse(null);
    }

    @Transactional
    public void crearPasajero(PasajeroDTO dto) {
        pasajeroRepository.insertar(
                dto.getId(),
                dto.getCodigoMetodoPago(),
                dto.getCodigoTarifa()
        );
    }

    @Transactional
    public void actualizarPasajero(PasajeroDTO dto) {
        pasajeroRepository.actualizar(
                dto.getId(),
                dto.getCodigoMetodoPago(),
                dto.getCodigoTarifa()
        );
    }

    @Transactional
    public void eliminarPasajero(Integer id) {
        pasajeroRepository.eliminarPorId(id);
    }
}
