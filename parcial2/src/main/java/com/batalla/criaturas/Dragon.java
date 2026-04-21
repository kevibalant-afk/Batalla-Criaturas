package com.batalla.criaturas;

import com.batalla.interfaces.Volador;

/**
 * Clase concreta que representa un Dragón.
 *
 * Características especiales:
 *  - Implementa la interfaz {@link Volador}: puede volar y aterrizar.
 *  - Ataque más poderoso: inflige fuerza * 2 de daño base.
 *  - Defensa natural con escamas: reduce el daño recibido en un 20%.
 *
 * DECISIÓN DE DISEÑO: Dragon extiende Criatura e implementa Volador.
 * Esto refleja correctamente que "un dragón es una criatura" (herencia)
 * y que "un dragón puede volar" (interfaz/capacidad adicional).
 */
public class Dragon extends Criatura implements Volador {

    private boolean estaVolando;

    // ── Constructor ────────────────────────────────────────────────────────

    /**
     * Crea un dragón con sus atributos base.
     *
     * @param nombre Nombre del dragón.
     * @param salud  Puntos de vida iniciales.
     * @param fuerza Fuerza base (el ataque real será fuerza * 2).
     */
    public Dragon(String nombre, int salud, int fuerza) {
        super(nombre, salud, fuerza);
        this.estaVolando = false;
    }

    // ── Implementación de métodos abstractos ───────────────────────────────

    /**
     * El dragón ataca con su aliento de fuego.
     * Daño = fuerza * 2 (+ daño del arma si tiene equipada).
     *
     * @param objetivo Criatura objetivo del ataque.
     */
    @Override
    public void atacar(Criatura objetivo) {
        int dañoBase  = fuerza * 2;               // Multiplicador propio del dragón
        int dañoExtra = 0;

        if (armaEquipada != null) {
            armaEquipada.atacarConArma(nombre, objetivo.getNombre());
            dañoExtra = armaEquipada.getDañoAdicional();
        }

        int dañoTotal = dañoBase + dañoExtra;
        System.out.printf("🔥 %s lanza un aliento de fuego sobre %s — daño total: %d%n",
                nombre, objetivo.getNombre(), dañoTotal);

        if (estaVolando) {
            System.out.println("  (ataque aéreo — el dragón golpea desde las alturas)");
        }

        objetivo.defender(dañoTotal);
    }

    /**
     * Las escamas del dragón absorben el 20% del daño recibido.
     *
     * @param daño Daño entrante.
     */
    @Override
    public void defender(int daño) {
        int dañoReducido = (int) (daño * 0.80);  // Escamas reducen 20%
        System.out.printf("🛡  %s resiste con sus escamas — recibe %d de daño (reducido de %d)%n",
                nombre, dañoReducido, daño);
        setSalud(salud - dañoReducido);
        System.out.printf("   Salud restante de %s: %d%n", nombre, salud);
    }

    // ── Implementación de Volador ──────────────────────────────────────────

    @Override
    public void volar() {
        estaVolando = true;
        System.out.println("🐉 " + nombre + " despliega sus alas y remonta el vuelo.");
    }

    @Override
    public void aterrizar() {
        estaVolando = false;
        System.out.println("🐉 " + nombre + " aterriza con un estruendo.");
    }

    // ── Getter auxiliar ────────────────────────────────────────────────────

    public boolean isEstaVolando() { return estaVolando; }
}
