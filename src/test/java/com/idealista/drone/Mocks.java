package com.idealista.drone;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.idealista.drone.model.Urbanizacion;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Slf4j
@Getter
@Setter
public class Mocks {

    public static final String NO_URB_AVAILABLE = "no_urb_available";
    private List<Urbanizacion> urbanizaciones;

    public void initTestUrbanizacionesMap(String jsonFile) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        urbanizaciones = mapper.convertValue(mapper.readTree(loadResource(jsonFile)), new TypeReference<List<Urbanizacion>>() {});
    }

    public String obtenerIdentificador(Double coordenadaX, Double coordenadaY) {
        return urbanizaciones.stream()
                .filter(urb -> coordenadaX.equals(urb.getCoordX()) && coordenadaY.equals(urb.getCoordY()))
                .map(urb -> urb.getId()).findFirst().orElse(NO_URB_AVAILABLE);

    }

    public String obtenerAdyacente(String identificacionUrbanizacionOrigen, Direccion direccion) {
        return urbanizaciones.stream()
                .filter(urb -> identificacionUrbanizacionOrigen.equals(urb.getId()))
                .map(urb -> getDireccion(urb, direccion)).findFirst().orElse(NO_URB_AVAILABLE);
    }

    private String getDireccion(Urbanizacion urbanizacion, Direccion direccion) {
        switch (direccion) {
            case ARRIBA:
                return Optional.ofNullable(urbanizacion.getArriba()).orElse(NO_URB_AVAILABLE);
            case ABAJO:
                return Optional.ofNullable(urbanizacion.getAbajo()).orElse(NO_URB_AVAILABLE);
            case DERECHA:
                return Optional.ofNullable(urbanizacion.getDerecha()).orElse(NO_URB_AVAILABLE);
            case IZQUIERDA:
                return Optional.ofNullable(urbanizacion.getIzquierda()).orElse(NO_URB_AVAILABLE);
            default:
                return null;
        }
    }

    private String loadResource(String json) {

        try {
            URI file = getClass().getClassLoader().getResource(json).toURI();
            return new String(Files.readAllBytes(Paths.get(file)));
        } catch (Exception e) {
            log.error("Error parsing json file", e);
            return "";
        }
    }
}
