package com.batalla.batalla;

import com.batalla.criaturas.Criatura;
import com.batalla.interfaces.Volador;
import com.batalla.interfaces.Magico;

/**
 * Clase encargada de gestionar la simulación de batallas entre criaturas.
 *
 * DECISIÓN DE DISEÑO: Se extrae la lógica de batalla a una clase separada
 * (patrón "Service / Manager") para cumplir el principio de responsabilidad
 * única. La clase Criatura no debe saber cómo se organiza un combate;
 * solo debe saber cómo atacar y defenderse.
 */
public class Batalla {

    // ── Constantes ─────────────────────────────────────────────────────────
    private static final String SEPARADOR =
            "═══════════════════════════════════════════════════════";
    private static final int MAX_TURNOS = 100; // Límite de seguridad para evitar bucles infinitos

    // ── Simulación principal ───────────────────────────────────────────────

    /**
     * Simula una batalla completa entre dos criaturas por turnos.
     * La batalla continúa hasta que una de las criaturas muere o se alcanza
     * el límite de turnos (MAX_TURNOS).
     *
     * @param criatura1 Primera criatura participante.
     * @param criatura2 Segunda criatura participante.
     */
    public static void simularBatalla(Criatura criatura1, Criatura criatura2) {
        System.out.println(SEPARADOR);
        System.out.printf("⚔  BATALLA: %s  VS  %s%n",
                criatura1.getNombre(), criatura2.getNombre());
        System.out.println(SEPARADOR);
        System.out.println("Estado inicial:");
        System.out.println("  " + criatura1);
        System.out.println("  " + criatura2);
        System.out.println(SEPARADOR);

        int turno = 1;

        // Activar habilidades especiales antes del combate
        activarHabilidadesIniciales(criatura1);
        activarHabilidadesIniciales(criatura2);

        while (criatura1.estaViva() && criatura2.estaViva() && turno <= MAX_TURNOS) {
            System.out.printf("%n--- Turno %d ---%n", turno);

            // Turno de criatura1
            if (criatura1.estaViva()) {
                System.out.printf("%n  [%s ataca]%n", criatura1.getNombre());
                criatura1.atacar(criatura2);
            }

            // Verificar si criatura2 murió tras el ataque
            if (!criatura2.estaViva()) break;

            // Turno de criatura2
            if (criatura2.estaViva()) {
                System.out.printf("%n  [%s ataca]%n", criatura2.getNombre());
                criatura2.atacar(criatura1);
            }

            turno++;
        }

        // Anunciar resultado
        mostrarResultado(criatura1, criatura2, turno - 1);
    }

    // ── Habilidades iniciales ──────────────────────────────────────────────

    /**
     * Activa las habilidades especiales de la criatura al inicio de la batalla.
     * Si es voladora, despega. Si es mágica, lanza un hechizo preparatorio.
     *
     * @param criatura La criatura a preparar.
     */
    private static void activarHabilidadesIniciales(Criatura criatura) {
        if (criatura instanceof Volador volador) {
            volador.volar();
        }
        if (criatura instanceof Magico mago) {
            System.out.printf("📚 %s prepara sus hechizos para la batalla.%n",
                    criatura.getNombre());
        }
    }

    // ── Resultado ──────────────────────────────────────────────────────────

    /**
     * Muestra el resultado final de la batalla.
     *
     * @param c1    Primera criatura.
     * @param c2    Segunda criatura.
     * @param turnos Número de turnos que duró el combate.
     */
    private static void mostrarResultado(Criatura c1, Criatura c2, int turnos) {
        System.out.println("\n" + SEPARADOR);
        System.out.printf("🏁 FIN DE LA BATALLA (duración: %d turnos)%n", turnos);

        if (c1.estaViva() && !c2.estaViva()) {
            System.out.printf("🏆 ¡%s ha ganado la batalla!%n", c1.getNombre());
            System.out.printf("   %s queda en pie con %d puntos de salud.%n",
                    c1.getNombre(), c1.getSalud());
        } else if (!c1.estaViva() && c2.estaViva()) {
            System.out.printf("🏆 ¡%s ha ganado la batalla!%n", c2.getNombre());
            System.out.printf("   %s queda en pie con %d puntos de salud.%n",
                    c2.getNombre(), c2.getSalud());
        } else if (!c1.estaViva() && !c2.estaViva()) {
            System.out.println("💀 ¡Ambas criaturas han caído! Es un empate.");
        } else {
            System.out.printf("⏱ Batalla detenida tras %d turnos. Ninguna criatura murió.%n",
                    turnos);
        }
        System.out.println(SEPARADOR);
    }
}
