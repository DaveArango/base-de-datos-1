package com.proyecto.movibus.backend.entidades.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehiculoDTO {
    private Integer codigo;
    private Integer modelo;
    private String placa;
    private Integer capacidad;
    private Integer codigoEstadoVehiculo;
    private Integer codigoMarca;
    private Integer codigoTipoCombustible;
    private Integer codigoUbicacionVehiculo;
}
