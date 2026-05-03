package com.proyecto.movibus.backend.entidades.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PasajeroDTO {
    private Integer id;
    private Integer codigoMetodoPago;
    private Integer codigoTarifa;
}