package com.proyecto.movibus.backend.servicios;

import com.proyecto.movibus.backend.entidades.Recorrido;
import com.proyecto.movibus.backend.entidades.dtos.RecorridoDTO;
import com.proyecto.movibus.backend.repositorios.RecorridoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecorridoService {

    @Autowired
    private RecorridoRepository recorridoRepository;

    @Transactional(readOnly = true)
    public List<Recorrido> listarRecorridos() {
        return recorridoRepository.listarTodos();
    }

    @Transactional(readOnly = true)
    public Recorrido buscarRecorrido(Integer id) {
        return recorridoRepository.buscarPorId(id)
                .orElse(null);
    }

    @Transactional
    public void crearRecorrido(RecorridoDTO dto) {
        recorridoRepository.insertar(
                dto.getId(),
                dto.getFechaInicio(),
                dto.getFechaFin(),
                dto.getCodigoEstadoRecorrido(),
                dto.getIdUbicacionRecorrido()
        );
    }

    @Transactional
    public void actualizarRecorrido(RecorridoDTO dto) {
        recorridoRepository.actualizar(
                dto.getId(),
                dto.getFechaInicio(),
                dto.getFechaFin(),
                dto.getCodigoEstadoRecorrido(),
                dto.getIdUbicacionRecorrido()
        );
    }

    @Transactional
    public void eliminarRecorrido(Integer id) {
        recorridoRepository.eliminarPorId(id);
    }
}