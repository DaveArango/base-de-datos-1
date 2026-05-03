package com.proyecto.movibus.backend.entidades.dtos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDTO {
    private Integer id;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private LocalDate fechaNacimiento;
    private LocalDate fechaRegistro;
    private String username;
    private String password;
    private String correo;
    private Integer estado;
    private Integer codigoTelefono;
    private Integer codigoDocumento;
}
