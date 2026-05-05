package com.proyecto.movibus.backend.servicios;

import com.proyecto.movibus.backend.entidades.UbicacionParada;
import com.proyecto.movibus.backend.entidades.dtos.UbicacionParadaDTO;
import com.proyecto.movibus.backend.repositorios.UbicacionParadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UbicacionParadaService {

    @Autowired
    private UbicacionParadaRepository ubicacionParadaRepository;

    @Transactional(readOnly = true)
    public List<UbicacionParada> listarUbicaciones() {
        return ubicacionParadaRepository.listarTodos();
    }

    @Transactional(readOnly = true)
    public UbicacionParada buscarUbicacion(Integer codigo) {
        return ubicacionParadaRepository.buscarPorCodigo(codigo)
                .orElse(null);
    }

    @Transactional
    public void crearUbicacion(UbicacionParadaDTO dto) {
        ubicacionParadaRepository.insertar(dto.getCodigo(),
                dto.getLatitud(),
                dto.getLongitud());
    }

    @Transactional
    public void actualizarUbicacion(UbicacionParadaDTO dto) {
        ubicacionParadaRepository.actualizar(dto.getCodigo(),
                dto.getLatitud(),
                dto.getLongitud());
    }

    @Transactional
    public void eliminarUbicacion(Integer codigo) {
        ubicacionParadaRepository.eliminarPorCodigo(codigo);
    }
}
