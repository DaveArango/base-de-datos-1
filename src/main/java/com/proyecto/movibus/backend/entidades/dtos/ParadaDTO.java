package com.proyecto.movibus.backend.entidades.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParadaDTO {
    private Integer codigo;
    private String nombre;
    private String direccion;
    private Integer codigoUbicacionParada;
}
