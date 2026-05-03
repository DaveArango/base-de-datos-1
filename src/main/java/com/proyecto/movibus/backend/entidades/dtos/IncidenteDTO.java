package com.proyecto.movibus.backend.entidades.dtos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IncidenteDTO {
    private Integer codigo;
    private LocalDate fecha;
    private Integer codigoEstadoIncidente;
    private Integer codigoTipoEvento;
    private Integer codigoUbicacionIncidente;
    private String descripcion;
}
