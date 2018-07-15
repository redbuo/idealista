package com.idealista.drone;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class DriverImpl implements Driver {

    /**
     * TODO: Implementación interna desarrollada por idealista i+d
     */
    protected String obtenerIdentificador(Double coordenadaX, Double coordenadaY) {
        return null;
    }

    /**
     * TODO: implementacion interna desarrollada por idealista i+d
     */
    protected String obtenerAdyacente(String identificacionUrbanizacionOrigen, Direccion direccion) {
        return null;
    }


    public List<String> obtenerUrbanizaciones(Double coordenadaX, Double coordenadaY, Integer rango) {
        List<String> urbanizaciones = new ArrayList<>();
        urbanizaciones.add(obtenerIdentificador(coordenadaX, coordenadaY));
        for (int currentRange = 1; currentRange <= rango; currentRange++) {
            int moves = currentRange * 2;
            urbanizaciones.add(obtenerAdyacente(getLast(urbanizaciones), Direccion.ARRIBA));
            urbanizaciones.addAll(movimientos(urbanizaciones, Direccion.DERECHA, moves - 1));
            urbanizaciones.addAll(movimientos(urbanizaciones, Direccion.ABAJO, moves));
            urbanizaciones.addAll(movimientos(urbanizaciones, Direccion.IZQUIERDA, moves));
            urbanizaciones.addAll(movimientos(urbanizaciones, Direccion.ARRIBA, moves));
        }
        return urbanizaciones.stream().distinct().collect(Collectors.toList());

    }

    /**
     * Método que dada una urbanización de origen se mueve N veces en la misma direccion
     *
     * @param urbanizaciones urbanizaciones actuales
     * @param direccion      direccion de movimiento
     * @param moves          numero de movimientos en esa direccion
     * @return listado de urbanizaciones encontradas
     */
    private List<String> movimientos(List<String> urbanizaciones, Direccion direccion, Integer moves) {
        String idUrb = getLast(urbanizaciones);
        log.debug(String.format("Move from %s to %s %s times", idUrb, direccion.getName(), String.valueOf(moves)));
        List<String> urbanizacionesEncontradas = new ArrayList<>();
        for (int i = 0; i < moves; i++) {
            urbanizacionesEncontradas.add(obtenerAdyacente(idUrb, direccion));
            idUrb = getLast(urbanizacionesEncontradas);
        }
        return urbanizacionesEncontradas;
    }


    private String getLast(List<String> urbanizaciones) {
        return urbanizaciones.stream().reduce((a, b) -> b).get();
    }
}
