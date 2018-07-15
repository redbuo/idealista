package com.idealista.drone;

import java.util.List;

public interface Driver {

    /**
     * Método que dado un rango (radio) determinado recupera todas las urbanizaciones a su alrededor.
     *
     * @param coordenadaX coordenadaX de origen
     * @param coordenadaY coordenadaY de origen
     * @param rango       Rango de busqueda
     * @return listado de urbanizaciones encontradas
     */
    List<String> obtenerUrbanizaciones(Double coordenadaX, Double coordenadaY, Integer rango);
}
