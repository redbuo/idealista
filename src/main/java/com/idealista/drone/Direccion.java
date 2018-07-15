package com.idealista.drone;

public enum Direccion {

    ARRIBA("arriba"), ABAJO("abajo"), DERECHA("derecha"), IZQUIERDA("izquierda");

    private String name;

    private Direccion(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
