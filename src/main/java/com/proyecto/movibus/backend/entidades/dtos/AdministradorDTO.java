package com.proyecto.movibus.backend.entidades.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdministradorDTO {
    private Integer id;
    private String cargo;
    private Integer codigoNivelPermiso;
    private Integer codigoAdministrador; // nullable (jefe)
}
