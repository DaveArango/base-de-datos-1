package com.proyecto.movibus.backend.entidades.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RutaDTO {
    private Integer codigo;
    private Double tarifa;
    private String nombre;
    private Integer codigoEstadoRuta;
    private Integer codigoIniciaEn;
    private Integer codigoFinalizaEn;
    private Integer codigoRecorrido;
}