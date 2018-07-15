package com.idealista.drone.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Urbanizacion {

    private String id;
    private Double coordX;
    private Double coordY;
    private String izquierda;
    private String derecha;
    private String arriba;
    private String abajo;

}
