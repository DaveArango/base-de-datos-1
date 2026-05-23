package com.proyecto.movibus.backend.repositorios;

import com.proyecto.movibus.backend.entidades.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Integer> {

    // FÁCILES
    // F1: Listar todos los viajes con su estado y valor pagado
    @Query(value = """
        SELECT v.id, 
               v.origen, 
               v.destino, 
               v.fecha_inicio,
               v.valor_pagado, 
               ev.estado AS estado_viaje
        FROM public.viaje v
        JOIN public.estado_viaje ev 
            ON v.codigo_estado_viaje = ev.codigo
        ORDER BY v.fecha_inicio DESC
        """, nativeQuery = true)
    List<Object[]> reporteViajes();

    // F2: Listar todos los incidentes con su estado y tipo de evento
    @Query(value = """
        SELECT i.codigo, 
               i.fecha, 
               i.descripcion,
               ei.estado_evento, 
               e.tipo_evento
        FROM public.incidente i
        JOIN public.estado_incidente ei
            ON i.codigo_estado_incidente = ei.codigo
        JOIN public.evento e 
            ON i.codigo_tipo_evento = e.codigo
        ORDER BY i.fecha DESC
        """, nativeQuery = true)
    List<Object[]> reporteIncidentes();

    // F3: Listar todos los vehículos con su estado, marca y combustible
    @Query(value = """
        SELECT v.codigo, 
               v.placa, 
               v.modelo, 
               v.capacidad,
               m.marca, 
               c.tipo_combustible, 
               ev.estado_vehiculo
        FROM public.vehiculo v
        JOIN public.marca m 
            ON v.codigo_marca = m.codigo
        JOIN public.combustible c 
            ON v.codigo_tipo_combustible = c.codigo
        JOIN public.estado_vehiculo ev 
            ON v.codigo_estado_vehiculo = ev.codigo
        ORDER BY v.codigo
        """, nativeQuery = true)
    List<Object[]> reporteVehiculos();

    // MEDIOS
    // M1: Conductores con su persona, vehículo asignado y cantidad de recorridos activos
    @Query(value = """
        SELECT p.primer_nombre, 
               p.primer_apellido, 
               p.correo,
               c.experiencia,
               ve.placa, 
               ve.modelo,
               COUNT(cr.codigo_recorrido) AS total_recorridos
        FROM public.conductor c
        JOIN public.persona p 
            ON c.id = p.id
        JOIN public.vehiculo ve 
            ON c.codigo_vehiculo = ve.codigo
        LEFT JOIN public.conductor_recorrido cr 
            ON c.id = cr.codigo_conductor
        GROUP BY p.primer_nombre, 
                 p.primer_apellido, 
                 p.correo,
                 c.experiencia, 
                 ve.placa, 
                 ve.modelo
        ORDER BY c.experiencia DESC
        """, nativeQuery = true)
    List<Object[]> reporteConductoresConRecorridos();

    // M2: Pasajeros con total de viajes realizados y monto total pagado
    @Query(value = """
        SELECT p.primer_nombre, 
               p.primer_apellido, 
               p.correo,
               mp.metodo_pago, 
               t.tipo_tarifa,
               COUNT(vi.id) AS total_viajes,
               COALESCE(SUM(vi.valor_pagado), 0) AS total_pagado
        FROM public.pasajero pa
        JOIN public.persona p 
            ON pa.id = p.id
        JOIN public.metodo_pago mp 
            ON pa.codigo_metodo_pago = mp.codigo
        JOIN public.tarifa t 
            ON pa.codigo_tipo_tarifa = t.codigo
        LEFT JOIN public.viaje vi 
            ON pa.id = vi.codigo_pasajero
        GROUP BY p.primer_nombre, 
                 p.primer_apellido, 
                 p.correo,
                 mp.metodo_pago, 
                 t.tipo_tarifa
        ORDER BY total_pagado DESC
        """, nativeQuery = true)
    List<Object[]> reportePasajerosConViajes();

    // M3: Rutas con sus paradas de inicio y fin, recorrido y estado
    @Query(value = """
        SELECT r.codigo, 
               r.nombre AS ruta, 
               r.tarifa,
               er.estado_ruta,
               pi.nombre AS parada_inicio,
               pf.nombre AS parada_fin,
               rec.fecha_inicio, 
               rec.fecha_fin,
               erec.estado_recorrido
        FROM public.ruta r
        JOIN public.estado_ruta er 
            ON r.codigo_estado_ruta = er.codigo
        JOIN public.parada pi 
            ON r.codigo_inicia_en = pi.codigo
        JOIN public.parada pf 
            ON r.codigo_finaliza_en = pf.codigo
        JOIN public.recorrido rec 
            ON r.codigo_recorrido = rec.id
        JOIN public.estado_recorrido erec 
            ON rec.codigo_estado_recorrido = erec.codigo
        ORDER BY r.tarifa DESC
        """, nativeQuery = true)
    List<Object[]> reporteRutasCompletas();

    // M4: Facturas con información de pasajero y viaje relacionado
    @Query(value = """
        SELECT f.codigo AS factura, 
               f.fecha, 
               f.valor, 
               f.descripcion,
               p.primer_nombre, 
               p.primer_apellido,
               vi.origen, 
               vi.destino,
               vi.fecha_inicio AS fecha_viaje,
               ev.estado AS estado_viaje
        FROM public.factura f
        JOIN public.pasajero pa 
            ON f.id_pasajero = pa.id
        JOIN public.persona p 
            ON pa.id = p.id
        JOIN public.viaje vi 
            ON f.id_viaje = vi.id
        JOIN public.estado_viaje ev 
            ON vi.codigo_estado_viaje = ev.codigo
        ORDER BY f.fecha DESC
        """, nativeQuery = true)
    List<Object[]> reporteFacturasDetalladas();

    // DIFÍCILES
    // D1: Pasajeros que han gastado más que el promedio general de gasto
    @Query(value = """
        SELECT p.primer_nombre, 
               p.primer_apellido, 
               p.correo,
               SUM(vi.valor_pagado) AS total_gastado
        FROM public.persona p
        JOIN public.pasajero pa 
            ON p.id = pa.id
        JOIN public.viaje vi 
            ON pa.id = vi.codigo_pasajero
        WHERE vi.valor_pagado IS NOT NULL
        GROUP BY p.primer_nombre, 
                 p.primer_apellido, 
                 p.correo
        HAVING SUM(vi.valor_pagado) > (
            SELECT AVG(total_por_pasajero)
            FROM (
                SELECT SUM(valor_pagado) AS total_por_pasajero
                FROM public.viaje
                WHERE valor_pagado IS NOT NULL
                GROUP BY codigo_pasajero
            ) AS subpromedio
        )
        ORDER BY total_gastado DESC
        """, nativeQuery = true)
    List<Object[]> reportePasajerosConGastoSuperiorAlPromedio();

    // D2: Conductores que tienen asignados recorridos que aún están en curso
    @Query(value = """
        SELECT p.primer_nombre, 
               p.primer_apellido,
               c.experiencia,
               ve.placa,
               rec.fecha_inicio,
               er.estado_recorrido
        FROM public.conductor c
        JOIN public.persona p 
            ON c.id = p.id
        JOIN public.vehiculo ve 
            ON c.codigo_vehiculo = ve.codigo
        JOIN public.conductor_recorrido cr 
            ON c.id = cr.codigo_conductor
        JOIN public.recorrido rec 
            ON cr.codigo_recorrido = rec.id
        JOIN public.estado_recorrido er 
            ON rec.codigo_estado_recorrido = er.codigo
        WHERE c.id IN (
            SELECT DISTINCT cr2.codigo_conductor
            FROM public.conductor_recorrido cr2
            JOIN public.recorrido r2 
                ON cr2.codigo_recorrido = r2.id
            WHERE r2.codigo_estado_recorrido = (
                SELECT codigo FROM public.estado_recorrido
                WHERE estado_recorrido = 'En Curso'
                LIMIT 1
            )
        )
        ORDER BY c.experiencia DESC
        """, nativeQuery = true)
    List<Object[]> reporteConductoresEnCurso();

    // D3: Rutas que han tenido incidentes y cuántos incidentes tuvieron
    @Query(value = """
        SELECT r.nombre AS ruta, r.tarifa,
               pi.nombre AS parada_inicio,
               pf.nombre AS parada_fin,
               COUNT(ri.codigo_incidente) AS total_incidentes
        FROM public.ruta r
        JOIN public.parada pi 
            ON r.codigo_inicia_en = pi.codigo
        JOIN public.parada pf 
            ON r.codigo_finaliza_en = pf.codigo
        JOIN public.recorrido rec 
            ON r.codigo_recorrido = rec.id
        JOIN public.recorrido_incidente ri 
            ON rec.id = ri.codigo_recorrido
        WHERE r.codigo_recorrido IN (
            SELECT DISTINCT ri2.codigo_recorrido
            FROM public.recorrido_incidente ri2
            WHERE ri2.codigo_incidente IN (
                SELECT codigo 
                FROM public.incidente
                WHERE codigo_estado_incidente != (
                    SELECT codigo 
                    FROM public.estado_incidente
                    WHERE estado_evento = 'Resuelto' LIMIT 1
                )
            )
        )
        GROUP BY r.nombre, 
                 r.tarifa, 
                 pi.nombre, 
                 pf.nombre
        ORDER BY total_incidentes DESC
        """, nativeQuery = true)
    List<Object[]> reporteRutasConIncidentesPendientes();

    // CRUD estándar

    @Query(value = "SELECT * FROM public.reporte", nativeQuery = true)
    List<Reporte> listarTodos();

    @Query(value = "SELECT * FROM public.reporte WHERE id = :id", nativeQuery = true)
    Optional<Reporte> buscarPorId(@Param("id") Integer id);

    @Modifying
    @Query(value = """
        INSERT INTO public.reporte(id, 
                                fecha_generacion, 
                                codigo_formato, 
                                codigo_tipo_reporte)
        VALUES(:id, 
                :fechaGeneracion, 
                :codigoFormato, 
                :codigoTipoReporte)
        """, nativeQuery = true)
    void insertar(
            @Param("id") Integer id,
            @Param("fechaGeneracion") LocalDate fechaGeneracion,
            @Param("codigoFormato") Integer codigoFormato,
            @Param("codigoTipoReporte") Integer codigoTipoReporte
    );

    @Modifying
    @Query(value = """
        UPDATE public.reporte
        SET fecha_generacion = :fechaGeneracion,
            codigo_formato = :codigoFormato,
            codigo_tipo_reporte = :codigoTipoReporte
        WHERE id = :id
        """, nativeQuery = true)
    void actualizar(
            @Param("id") Integer id,
            @Param("fechaGeneracion") LocalDate fechaGeneracion,
            @Param("codigoFormato") Integer codigoFormato,
            @Param("codigoTipoReporte") Integer codigoTipoReporte
    );

    @Modifying
    @Query(value = "DELETE FROM public.reporte WHERE id = :id", nativeQuery = true)
    void eliminarPorId(@Param("id") Integer id);
}