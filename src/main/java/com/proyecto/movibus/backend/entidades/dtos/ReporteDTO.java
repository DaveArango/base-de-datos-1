package com.proyecto.movibus.backend.entidades.dtos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReporteDTO {
    private Integer id;
    private LocalDate fechaGeneracion;
    private Integer codigoFormato;
    private Integer codigoTipoReporte;
}
