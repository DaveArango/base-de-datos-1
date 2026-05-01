package com.proyecto.movibus.backend.entidades.ids;

import com.proyecto.movibus.backend.entidades.Conductor;
import com.proyecto.movibus.backend.entidades.Recorrido;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ConductorRecorridoId implements Serializable {
    @Column(name = "codigo_conductor")
    private int codigoConductor;

    @Column(name = "codigo_recorrido")
    private int codigoRecorrido;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ConductorRecorridoId that = (ConductorRecorridoId) o;
        return Objects.equals(codigoConductor, that.codigoConductor) && Objects.equals(codigoRecorrido, that.codigoRecorrido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoConductor, codigoRecorrido);
    }
}
