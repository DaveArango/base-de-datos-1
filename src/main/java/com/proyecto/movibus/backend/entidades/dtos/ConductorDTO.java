package com.proyecto.movibus.backend.entidades.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConductorDTO {
    private Integer id;
    private Integer experiencia;
    private Integer codigoVehiculo;
}
