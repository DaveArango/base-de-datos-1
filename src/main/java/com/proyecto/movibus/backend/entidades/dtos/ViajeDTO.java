package com.proyecto.movibus.backend.entidades.dtos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ViajeDTO {
    private Integer id;
    private LocalDate fechaInicio;
    private LocalDate fechaFin; // nullable
    private Double valorPagado; // nullable
    private Integer codigoEstadoViaje;
    private Integer codigoPasajero;
    private Integer codigoRecorrido;
    private String origen;
    private String destino;
}