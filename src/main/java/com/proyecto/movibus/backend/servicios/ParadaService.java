package com.proyecto.movibus.backend.servicios;

import com.proyecto.movibus.backend.entidades.Parada;
import com.proyecto.movibus.backend.entidades.dtos.ParadaDTO;
import com.proyecto.movibus.backend.repositorios.ParadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ParadaService {

    @Autowired
    private ParadaRepository paradaRepository;

    @Transactional(readOnly = true)
    public List<Parada> listarParadas() {
        return paradaRepository.listarTodos();
    }

    @Transactional(readOnly = true)
    public Parada buscarParada(Integer codigo) {
        return paradaRepository.buscarPorCodigo(codigo)
                .orElse(null);
    }

    @Transactional
    public void crearParada(ParadaDTO dto) {
        paradaRepository.insertar(
                dto.getCodigo(),
                dto.getNombre(),
                dto.getDireccion(),
                dto.getCodigoUbicacionParada()
        );
    }

    @Transactional
    public void actualizarParada(ParadaDTO dto) {
        paradaRepository.actualizar(
                dto.getCodigo(),
                dto.getNombre(),
                dto.getDireccion(),
                dto.getCodigoUbicacionParada()
        );
    }

    @Transactional
    public void eliminarParada(Integer codigo) {
        paradaRepository.eliminarPorCodigo(codigo);
    }
}