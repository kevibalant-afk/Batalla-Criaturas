package com.batalla.armas;

/**
 * Clase que representa un arma que las criaturas pueden usar en combate.
 *
 * DECISIÓN DE DISEÑO: Se modela mediante composición y NO mediante herencia.
 * Una criatura "tiene" un arma, no "es" un arma. Esto permite:
 *   - Equipar/desequipar armas en tiempo de ejecución.
 *   - Reutilizar la misma instancia de arma con distintas criaturas.
 *   - Agregar nuevos tipos de arma sin modificar la jerarquía de Criatura.
 */
public class Arma {

    // ── Atributos ──────────────────────────────────────────────────────────
    private final String nombre;
    private final int    dañoBase;
    private final String tipo;     // p.ej. "espada", "bastón", "hacha"

    // ── Constructor ────────────────────────────────────────────────────────

    /**
     * Construye un arma con sus características.
     *
     * @param nombre    Nombre descriptivo del arma.
     * @param dañoBase  Daño adicional que aporta al portador.
     * @param tipo      Tipo o categoría del arma.
     */
    public Arma(String nombre, int dañoBase, String tipo) {
        this.nombre   = nombre;
        this.dañoBase = dañoBase;
        this.tipo     = tipo;
    }

    // ── Comportamiento ─────────────────────────────────────────────────────

    /**
     * Realiza un ataque usando el arma y muestra el resultado por consola.
     *
     * @param portador Nombre de la criatura que usa el arma.
     * @param objetivo Nombre de la criatura que recibe el ataque.
     */
    public void atacarConArma(String portador, String objetivo) {
        System.out.printf("  ⚔  %s usa '%s' (%s) contra %s — daño adicional: +%d%n",
                portador, nombre, tipo, objetivo, dañoBase);
    }

    /**
     * Retorna el daño adicional que proporciona el arma.
     *
     * @return Puntos de daño extra que se suman al ataque base.
     */
    public int getDañoAdicional() {
        return dañoBase;
    }

    // ── Getters ────────────────────────────────────────────────────────────

    public String getNombre() { return nombre; }
    public String getTipo()   { return tipo; }

    @Override
    public String toString() {
        return String.format("Arma[%s | Tipo: %s | Daño adicional: %d]", nombre, tipo, dañoBase);
    }
}
