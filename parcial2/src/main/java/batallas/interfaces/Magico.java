package com.batalla.interfaces;

/**
 * Interfaz que define el comportamiento de las criaturas mágicas.
 *
 * DECISIÓN DE DISEÑO: Al igual que Volador, se modela como interfaz
 * porque la magia es una habilidad transversal. En el futuro podría
 * ser implementada por criaturas de distintas familias sin romper
 * la jerarquía de herencia existente.
 */
public interface Magico {

    /**
     * Lanza un hechizo ofensivo o de apoyo.
     * El efecto concreto depende de la criatura que lo implemente.
     */
    void lanzarHechizo();

    /**
     * La criatura aprende un nuevo hechizo.
     *
     * @param nombreHechizo Nombre del hechizo a aprender.
     */
    void aprenderHechizo(String nombreHechizo);
}
