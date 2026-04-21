package com.batalla;

import com.batalla.armas.Arma;
import com.batalla.batalla.Batalla;
import com.batalla.criaturas.*;
import com.batalla.interfaces.Magico;
import com.batalla.interfaces.Volador;

/**
 * Clase principal del sistema de batalla de criaturas.
 *
 * Aquí se instancian las criaturas, se les equipa armamento y se simulan
 * distintos escenarios de combate para demostrar polimorfismo, composición
 * e interfaces en acción.
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("╔═══════════════════════════════════════════════╗");
        System.out.println("║      SISTEMA DE BATALLA DE CRIATURAS          ║");
        System.out.println("╚═══════════════════════════════════════════════╝");

        // ── 1. Crear criaturas ─────────────────────────────────────────────
        Dragon  ignis    = new Dragon("Ignis",         200, 40);
        Mago    merlin   = new Mago("Merlín",          120, 35, "Bola de Fuego");
        Guerrero thorin  = new Guerrero("Thorin",      160, 30);

        // ── 2. Crear armas ─────────────────────────────────────────────────
        Arma espada      = new Arma("Espada Élfica",   15, "espada");
        Arma baston      = new Arma("Bastón Arcano",   10, "bastón");
        Arma garra       = new Arma("Garra de Hierro", 20, "garra");

        // ── 3. Equipar armas (composición) ────────────────────────────────
        ignis.equiparArma(garra);
        merlin.equiparArma(baston);
        thorin.equiparArma(espada);

        // ── 4. Demostrar interfaces independientemente ────────────────────
        System.out.println("\n>>> Demo de habilidades especiales:");

        // Volador
        Volador dragonVolador = ignis;
        dragonVolador.volar();
        dragonVolador.aterrizar();

        // Mágico
        Magico magoMagico = merlin;
        magoMagico.aprenderHechizo("Rayo de Escarcha");
        magoMagico.lanzarHechizo();

        // ── 5. Batallas ────────────────────────────────────────────────────
        System.out.println("\n\n>>> COMBATE 1: Dragón vs Guerrero");
        // Reiniciar salud para la demostración
        Dragon  ignis2   = new Dragon("Ignis",    200, 40);
        Guerrero thorin2 = new Guerrero("Thorin", 160, 30);
        ignis2.equiparArma(new Arma("Garra de Hierro", 20, "garra"));
        thorin2.equiparArma(new Arma("Espada Élfica",  15, "espada"));

        Batalla.simularBatalla(ignis2, thorin2);

        System.out.println("\n\n>>> COMBATE 2: Mago vs Guerrero");
        Mago     merlin2  = new Mago("Merlín",    120, 35, "Rayo Arcano");
        Guerrero thorin3  = new Guerrero("Thorin", 160, 30);
        merlin2.equiparArma(new Arma("Bastón Arcano", 10, "bastón"));
        thorin3.equiparArma(new Arma("Espada Élfica", 15, "espada"));

        Batalla.simularBatalla(merlin2, thorin3);

        System.out.println("\n\n>>> COMBATE 3: Dragón vs Mago");
        Dragon ignis3   = new Dragon("Ignis",    200, 40);
        Mago   merlin3  = new Mago("Merlín",     120, 35, "Bola de Fuego");
        ignis3.equiparArma(new Arma("Garra de Hierro", 20, "garra"));
        merlin3.equiparArma(new Arma("Bastón Arcano",  10, "bastón"));

        Batalla.simularBatalla(ignis3, merlin3);

        // ── 6. Demo de desequipar arma ────────────────────────────────────
        System.out.println("\n>>> Demo de desequipar arma:");
        thorin.desequiparArma();
        thorin.desequiparArma(); // Intento cuando ya no tiene arma
    }
}
