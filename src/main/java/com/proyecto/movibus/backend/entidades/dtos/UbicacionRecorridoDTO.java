package com.proyecto.movibus.backend.entidades.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UbicacionRecorridoDTO {
    private Integer id;
    private Double latitud;
    private Double longitud;
}
