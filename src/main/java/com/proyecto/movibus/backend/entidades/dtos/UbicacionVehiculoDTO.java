package com.proyecto.movibus.backend.entidades.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UbicacionVehiculoDTO {
    private Integer codigo;
    private Double latitud;
    private Double longitud;
}
