package com.batalla.criaturas;

import com.batalla.interfaces.Magico;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase concreta que representa un Mago.
 *
 * Características especiales:
 *  - Implementa la interfaz {@link Magico}: puede lanzar y aprender hechizos.
 *  - Ataca usando sus hechizos con daño = fuerza.
 *  - Defensa mágica: escudo arcano que reduce el daño recibido en un 15%.
 *
 * DECISIÓN DE DISEÑO: Mago extiende Criatura e implementa Magico.
 * La lista de hechizos se gestiona internamente; aprenderHechizo()
 * la expande en tiempo de ejecución sin necesidad de subclases adicionales.
 */
public class Mago extends Criatura implements Magico {

    /** Hechizos que el mago ha aprendido durante la partida. */
    private final List<String> hechizosAprendidos;

    /** Hechizo activo que se lanza al atacar. */
    private String hechizoActivo;

    // ── Constructor ────────────────────────────────────────────────────────

    /**
     * Crea un mago con su hechizo base ya conocido.
     *
     * @param nombre         Nombre del mago.
     * @param salud          Puntos de vida iniciales.
     * @param fuerza         Fuerza mágica base.
     * @param hechizoInicial Hechizo con el que comienza la batalla.
     */
    public Mago(String nombre, int salud, int fuerza, String hechizoInicial) {
        super(nombre, salud, fuerza);
        this.hechizosAprendidos = new ArrayList<>();
        this.hechizoActivo      = hechizoInicial;
        hechizosAprendidos.add(hechizoInicial);
    }

    // ── Implementación de métodos abstractos ───────────────────────────────

    /**
     * El mago ataca lanzando su hechizo activo.
     * Daño = fuerza (+ daño del arma/bastón si tiene equipado).
     *
     * @param objetivo Criatura objetivo del ataque.
     */
    @Override
    public void atacar(Criatura objetivo) {
        // Primero activa el efecto del hechizo
        lanzarHechizo();

        int dañoBase  = fuerza;
        int dañoExtra = 0;

        if (armaEquipada != null) {
            armaEquipada.atacarConArma(nombre, objetivo.getNombre());
            dañoExtra = armaEquipada.getDañoAdicional();
        }

        int dañoTotal = dañoBase + dañoExtra;
        System.out.printf("✨ %s canaliza '%s' sobre %s — daño total: %d%n",
                nombre, hechizoActivo, objetivo.getNombre(), dañoTotal);

        objetivo.defender(dañoTotal);
    }

    /**
     * El escudo arcano del mago reduce el daño recibido en un 15%.
     *
     * @param daño Daño entrante.
     */
    @Override
    public void defender(int daño) {
        int dañoReducido = (int) (daño * 0.85);  // Escudo arcano reduce 15%
        System.out.printf("🔮 %s activa su escudo arcano — recibe %d de daño (reducido de %d)%n",
                nombre, dañoReducido, daño);
        setSalud(salud - dañoReducido);
        System.out.printf("   Salud restante de %s: %d%n", nombre, salud);
    }

    // ── Implementación de Magico ───────────────────────────────────────────

    /**
     * Lanza el hechizo activo mostrando el efecto por consola.
     */
    @Override
    public void lanzarHechizo() {
        System.out.printf("🌟 %s lanza el hechizo '%s'!%n", nombre, hechizoActivo);
    }

    /**
     * El mago aprende un nuevo hechizo y lo establece como activo.
     *
     * @param nombreHechizo Nombre del nuevo hechizo.
     */
    @Override
    public void aprenderHechizo(String nombreHechizo) {
        hechizosAprendidos.add(nombreHechizo);
        hechizoActivo = nombreHechizo;          // El último aprendido queda activo
        System.out.printf("📖 %s ha aprendido el nuevo hechizo: '%s'%n", nombre, nombreHechizo);
    }

    // ── Getters auxiliares ─────────────────────────────────────────────────

    public List<String> getHechizosAprendidos() { return hechizosAprendidos; }
    public String       getHechizoActivo()       { return hechizoActivo; }
}
