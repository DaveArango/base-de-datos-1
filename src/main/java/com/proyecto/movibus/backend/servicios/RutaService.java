package com.proyecto.movibus.backend.servicios;

import com.proyecto.movibus.backend.entidades.Ruta;
import com.proyecto.movibus.backend.entidades.dtos.RutaDTO;
import com.proyecto.movibus.backend.repositorios.RutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RutaService {

    @Autowired
    private RutaRepository rutaRepository;

    @Transactional(readOnly = true)
    public List<Ruta> listarRutas() {
        return rutaRepository.listarTodos();
    }

    @Transactional(readOnly = true)
    public Ruta buscarRuta(Integer codigo) {
        return rutaRepository.buscarPorCodigo(codigo)
                .orElse(null);
    }

    @Transactional
    public void crearRuta(RutaDTO dto) {
        rutaRepository.insertar(
                dto.getCodigo(),
                dto.getTarifa(),
                dto.getNombre(),
                dto.getCodigoEstadoRuta(),
                dto.getCodigoIniciaEn(),
                dto.getCodigoFinalizaEn(),
                dto.getCodigoRecorrido()
        );
    }

    @Transactional
    public void actualizarRuta(RutaDTO dto) {
        rutaRepository.actualizar(
                dto.getCodigo(),
                dto.getTarifa(),
                dto.getNombre(),
                dto.getCodigoEstadoRuta(),
                dto.getCodigoIniciaEn(),
                dto.getCodigoFinalizaEn(),
                dto.getCodigoRecorrido()
        );
    }

    @Transactional
    public void eliminarRuta(Integer codigo) {
        rutaRepository.eliminarPorCodigo(codigo);
    }
}