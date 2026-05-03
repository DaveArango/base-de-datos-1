package com.proyecto.movibus.backend.entidades.dtos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FacturaDTO {
    private Integer codigo;
    private Integer valor;
    private String descripcion; // nullable
    private LocalDate fecha;
    private Integer idPasajero;
    private Integer idViaje;
}
