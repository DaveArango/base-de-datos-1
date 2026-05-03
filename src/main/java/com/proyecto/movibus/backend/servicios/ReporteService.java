package com.proyecto.movibus.backend.servicios;

import com.proyecto.movibus.backend.entidades.Reporte;
import com.proyecto.movibus.backend.entidades.dtos.ReporteDTO;
import com.proyecto.movibus.backend.repositorios.ReporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    @Transactional(readOnly = true)
    public List<Reporte> listarReportes() {
        return reporteRepository.listarTodos();
    }

    @Transactional(readOnly = true)
    public Reporte buscarReporte(Integer id) {
        return reporteRepository.buscarPorId(id)
                .orElse(null);
    }

    @Transactional
    public void crearReporte(ReporteDTO dto) {
        reporteRepository.insertar(
                dto.getId(),
                dto.getFechaGeneracion(),
                dto.getCodigoFormato(),
                dto.getCodigoTipoReporte()
        );
    }

    @Transactional
    public void actualizarReporte(ReporteDTO dto) {
        reporteRepository.actualizar(
                dto.getId(),
                dto.getFechaGeneracion(),
                dto.getCodigoFormato(),
                dto.getCodigoTipoReporte()
        );
    }

    @Transactional
    public void eliminarReporte(Integer id) {
        reporteRepository.eliminarPorId(id);
    }
}
