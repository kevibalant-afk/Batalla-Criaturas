package com.batalla.interfaces;

/**
 * Interfaz que define el comportamiento de las criaturas voladoras.
 *
 * DECISIÓN DE DISEÑO: Se usa interfaz porque "volar" es una capacidad
 * independiente de la jerarquía de herencia. Permite que diferentes
 * tipos de criaturas (Dragón, etc.) compartan este comportamiento
 * sin necesidad de una clase base común.
 */
public interface Volador {

    /**
     * La criatura alza el vuelo.
     * Puede otorgar ventajas en combate (p.ej. esquivar ataques terrestres).
     */
    void volar();

    /**
     * La criatura aterriza desde el vuelo.
     */
    void aterrizar();
}
