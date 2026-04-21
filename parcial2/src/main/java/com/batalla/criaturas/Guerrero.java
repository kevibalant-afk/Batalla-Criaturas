package com.batalla.criaturas;

/**
 * Clase concreta que representa un Guerrero.
 *
 * Características especiales:
 *  - Ataca con su espada usando daño = fuerza base.
 *  - Defensa física: bloqueo con escudo que reduce el daño recibido en un 25%.
 *  - Habilidad de "golpe crítico": 20% de probabilidad de doblar el daño.
 *
 * DECISIÓN DE DISEÑO: El Guerrero solo extiende Criatura (sin interfaces adicionales)
 * porque sus habilidades son puramente físicas y no requieren comportamientos
 * transversales como volar o usar magia.
 */
public class Guerrero extends Criatura {

    /** Probabilidad de golpe crítico (0.0 – 1.0). */
    private static final double PROB_CRITICO = 0.20;

    // ── Constructor ────────────────────────────────────────────────────────

    /**
     * Crea un guerrero con sus atributos base.
     *
     * @param nombre Nombre del guerrero.
     * @param salud  Puntos de vida iniciales.
     * @param fuerza Fuerza física base.
     */
    public Guerrero(String nombre, int salud, int fuerza) {
        super(nombre, salud, fuerza);
    }

    // ── Implementación de métodos abstractos ───────────────────────────────

    /**
     * El guerrero ataca con su espada.
     * Daño base = fuerza. Tiene un 20% de probabilidad de golpe crítico (x2).
     * Si tiene arma equipada, se añade el daño adicional del arma.
     *
     * @param objetivo Criatura objetivo del ataque.
     */
    @Override
    public void atacar(Criatura objetivo) {
        int dañoBase  = fuerza;
        int dañoExtra = 0;
        boolean esCritico = Math.random() < PROB_CRITICO;

        if (armaEquipada != null) {
            armaEquipada.atacarConArma(nombre, objetivo.getNombre());
            dañoExtra = armaEquipada.getDañoAdicional();
        }

        if (esCritico) {
            dañoBase *= 2;
            System.out.printf("💥 ¡GOLPE CRÍTICO! ");
        }

        int dañoTotal = dañoBase + dañoExtra;
        System.out.printf("⚔  %s golpea a %s con su espada — daño total: %d%n",
                nombre, objetivo.getNombre(), dañoTotal);

        objetivo.defender(dañoTotal);
    }

    /**
     * El guerrero bloquea con su escudo, reduciendo el daño un 25%.
     *
     * @param daño Daño entrante.
     */
    @Override
    public void defender(int daño) {
        int dañoReducido = (int) (daño * 0.75);  // Escudo bloquea 25%
        System.out.printf("🛡  %s bloquea con su escudo — recibe %d de daño (reducido de %d)%n",
                nombre, dañoReducido, daño);
        setSalud(salud - dañoReducido);
        System.out.printf("   Salud restante de %s: %d%n", nombre, salud);
    }
}
