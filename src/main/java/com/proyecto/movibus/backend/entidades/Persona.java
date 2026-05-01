package com.proyecto.movibus.backend.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="persona")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "primer_nombre")
    private String primerNombre;

    @Column(name = "segundo_nombre")
    private String segundoNombre;

    @Column(name = "primer_apellido")
    private String primerApellido;

    @Column(name = "segundo_apellido")
    private String segundoApellido;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "correo")
    private String correo;

    @Column(name = "estado")
    private Integer estado;

    @ManyToOne
    @JoinColumn(name="codigo_telefono", nullable = false)
    private Telefono telefono;

    @ManyToOne
    @JoinColumn(name="codigo_tipo_documento", nullable = false)
    private Documento documento;
}
