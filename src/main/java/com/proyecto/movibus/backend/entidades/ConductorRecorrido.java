package com.proyecto.movibus.backend.entidades;

import com.proyecto.movibus.backend.entidades.ids.ConductorRecorridoId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "conductor_recorrido")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ConductorRecorrido {
    @EmbeddedId
    private ConductorRecorridoId id;

    @ManyToOne
    @MapsId("codigoConductor")
    @JoinColumn(name = "codigo_conductor")
    private Conductor conductor;

    @ManyToOne
    @MapsId("codigoRecorrido")
    @JoinColumn(name = "codigo_recorrido")
    private Recorrido recorrido;

    @ManyToOne
    @JoinColumn(name = "codigo_administrador", nullable = false)
    private Administrador administrador;
}
