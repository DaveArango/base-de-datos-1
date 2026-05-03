package com.proyecto.movibus.backend.entidades.dtos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecorridoDTO {
    private Integer id;
    private LocalDate fechaInicio;
    private LocalDate fechaFin; // nullable
    private Integer codigoEstadoRecorrido;
    private Integer idUbicacionRecorrido;
}
