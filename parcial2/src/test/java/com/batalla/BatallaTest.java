package com.batalla;

import com.batalla.armas.Arma;
import com.batalla.criaturas.*;
import com.batalla.interfaces.Magico;
import com.batalla.interfaces.Volador;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para el sistema de batalla de criaturas.
 *
 * Se usa JUnit 5 (Jupiter). Cada clase de prueba verifica un aspecto
 * específico del sistema:
 *   - Clase Criatura (salud, viva/muerta).
 *   - Clase Arma (daño adicional, composición).
 *   - Dragon (ataque doble, interfaz Volador).
 *   - Mago (interfaz Magico, hechizos).
 *   - Guerrero (defensa con escudo).
 *
 * Para ejecutar: mvn test
 */
@DisplayName("Suite de pruebas — Sistema de Batalla de Criaturas")
class BatallaTest {

    // ═══════════════════════════════════════════════════════
    // 1. Pruebas de la clase Criatura (a través de Guerrero)
    // ═══════════════════════════════════════════════════════

    @Nested
    @DisplayName("1. Criatura — atributos y estado de vida")
    class CriaturaTests {

        private Guerrero guerrero;

        @BeforeEach
        void setUp() {
            guerrero = new Guerrero("TestGuerrero", 100, 20);
        }

        @Test
        @DisplayName("La criatura está viva con salud > 0")
        void criaturaEstaVivaCuandoTieneSalud() {
            assertTrue(guerrero.estaViva(), "Debe estar viva con salud = 100");
        }

        @Test
        @DisplayName("La criatura muere cuando la salud llega a 0")
        void criaturaNoEstaVivaConSaludCero() {
            guerrero.setSalud(0);
            assertFalse(guerrero.estaViva(), "Debe estar muerta con salud = 0");
        }

        @Test
        @DisplayName("La salud no puede ser negativa")
        void saludNoPuedeSerNegativa() {
            guerrero.setSalud(-50);
            assertEquals(0, guerrero.getSalud(), "La salud mínima es 0");
        }

        @Test
        @DisplayName("Los getters retornan los valores del constructor")
        void gettersRetornanValoresCorrectos() {
            assertEquals("TestGuerrero", guerrero.getNombre());
            assertEquals(100,            guerrero.getSalud());
            assertEquals(20,             guerrero.getFuerza());
        }
    }

    // ═══════════════════════════════════════════════════════
    // 2. Pruebas de la clase Arma
    // ═══════════════════════════════════════════════════════

    @Nested
    @DisplayName("2. Arma — composición y daño")
    class ArmaTests {

        @Test
        @DisplayName("El arma retorna su daño adicional correctamente")
        void armaRetornaDañoAdicional() {
            Arma espada = new Arma("Espada Élfica", 15, "espada");
            assertEquals(15, espada.getDañoAdicional());
        }

        @Test
        @DisplayName("Equipar arma asigna el arma a la criatura")
        void equiparArmaAsignaCorrectamente() {
            Guerrero g = new Guerrero("Héroe", 100, 20);
            Arma espada = new Arma("Espada", 10, "espada");

            assertNull(g.getArmaEquipada(), "No debe tener arma inicial");
            g.equiparArma(espada);
            assertNotNull(g.getArmaEquipada(), "Debe tener arma después de equipar");
            assertEquals("Espada", g.getArmaEquipada().getNombre());
        }

        @Test
        @DisplayName("Desequipar arma elimina el arma de la criatura")
        void desequiparArmaFuncionaCorrectamente() {
            Guerrero g = new Guerrero("Héroe", 100, 20);
            Arma espada = new Arma("Espada", 10, "espada");

            g.equiparArma(espada);
            g.desequiparArma();
            assertNull(g.getArmaEquipada(), "No debe tener arma tras desequipar");
        }

        @ParameterizedTest(name = "Arma con daño {0} debe retornar {0}")
        @CsvSource({"5, 5", "15, 15", "30, 30", "0, 0"})
        @DisplayName("El daño adicional se configura correctamente")
        void dañoAdicionalParametrizado(int daño, int esperado) {
            Arma arma = new Arma("Arma Test", daño, "tipo");
            assertEquals(esperado, arma.getDañoAdicional());
        }
    }

    // ═══════════════════════════════════════════════════════
    // 3. Pruebas del Dragon
    // ═══════════════════════════════════════════════════════

    @Nested
    @DisplayName("3. Dragon — ataque doble, Volador y defensa con escamas")
    class DragonTests {

        private Dragon dragon;
        private Guerrero victima;

        @BeforeEach
        void setUp() {
            dragon = new Dragon("Ignis", 200, 40);
            victima = new Guerrero("Victima", 1000, 10); // Mucha salud para no morir
        }

        @Test
        @DisplayName("El Dragon implementa la interfaz Volador")
        void dragonImplementaVolador() {
            assertInstanceOf(Volador.class, dragon);
        }

        @Test
        @DisplayName("El Dragon puede volar y aterrizar")
        void dragonPuedeVolarYAterrizar() {
            Dragon d = (Dragon) dragon;
            assertFalse(d.isEstaVolando(), "No debe estar volando inicialmente");
            d.volar();
            assertTrue(d.isEstaVolando(), "Debe estar volando después de volar()");
            d.aterrizar();
            assertFalse(d.isEstaVolando(), "No debe estar volando después de aterrizar()");
        }

        @Test
        @DisplayName("El ataque del Dragon reduce la salud del objetivo")
        void dragonAtaqueReduceSalud() {
            int saludAntes = victima.getSalud();
            dragon.atacar(victima);
            assertTrue(victima.getSalud() < saludAntes, "La salud debe reducirse tras el ataque");
        }

        @Test
        @DisplayName("La defensa del Dragon reduce el daño un 20%")
        void dragonDefensaReduceDaño() {
            int saludAntes = dragon.getSalud();
            dragon.defender(100); // Debe recibir solo 80
            assertEquals(saludAntes - 80, dragon.getSalud(),
                    "El dragón debe recibir 80 de daño (100 * 0.80)");
        }
    }

    // ═══════════════════════════════════════════════════════
    // 4. Pruebas del Mago
    // ═══════════════════════════════════════════════════════

    @Nested
    @DisplayName("4. Mago — interfaz Magico y hechizos")
    class MagoTests {

        private Mago mago;

        @BeforeEach
        void setUp() {
            mago = new Mago("Merlín", 120, 35, "Bola de Fuego");
        }

        @Test
        @DisplayName("El Mago implementa la interfaz Magico")
        void magoImplementaMagico() {
            assertInstanceOf(Magico.class, mago);
        }

        @Test
        @DisplayName("El Mago comienza con el hechizo inicial")
        void magoTieneHechizoInicial() {
            assertEquals("Bola de Fuego", mago.getHechizoActivo());
            assertEquals(1, mago.getHechizosAprendidos().size());
        }

        @Test
        @DisplayName("aprenderHechizo agrega el hechizo y lo activa")
        void aprenderHechizoFunciona() {
            mago.aprenderHechizo("Rayo Helado");
            assertEquals("Rayo Helado", mago.getHechizoActivo());
            assertEquals(2, mago.getHechizosAprendidos().size());
        }

        @Test
        @DisplayName("La defensa del Mago reduce el daño un 15%")
        void magoDefensaReduceDaño() {
            int saludAntes = mago.getSalud();
            mago.defender(100); // Debe recibir solo 85
            assertEquals(saludAntes - 85, mago.getSalud(),
                    "El mago debe recibir 85 de daño (100 * 0.85)");
        }
    }

    // ═══════════════════════════════════════════════════════
    // 5. Pruebas del Guerrero
    // ═══════════════════════════════════════════════════════

    @Nested
    @DisplayName("5. Guerrero — defensa con escudo")
    class GuerreroTests {

        private Guerrero guerrero;

        @BeforeEach
        void setUp() {
            guerrero = new Guerrero("Thorin", 160, 30);
        }

        @Test
        @DisplayName("La defensa del Guerrero reduce el daño un 25%")
        void guerreroDefensaReduceDaño() {
            int saludAntes = guerrero.getSalud();
            guerrero.defender(100); // Debe recibir solo 75
            assertEquals(saludAntes - 75, guerrero.getSalud(),
                    "El guerrero debe recibir 75 de daño (100 * 0.75)");
        }

        @Test
        @DisplayName("El ataque del Guerrero reduce la salud del objetivo")
        void guerreroAtaqueReduceSalud() {
            Dragon dragon = new Dragon("Víctima", 500, 10); // mucha salud
            int saludAntes = dragon.getSalud();
            guerrero.atacar(dragon);
            assertTrue(dragon.getSalud() < saludAntes, "La salud debe reducirse");
        }

        @Test
        @DisplayName("El Guerrero NO implementa Volador ni Magico")
        void guerreroNoEsVoladorNiMagico() {
            assertFalse(guerrero instanceof Volador, "El guerrero no debe volar");
            assertFalse(guerrero instanceof Magico,  "El guerrero no debe ser mágico");
        }
    }

    // ═══════════════════════════════════════════════════════
    // 6. Prueba de batalla completa
    // ═══════════════════════════════════════════════════════

    @Nested
    @DisplayName("6. Batalla — resultado final")
    class BatallaTests {

        @Test
        @DisplayName("Al final de una batalla, al menos una criatura está muerta")
        void batallaProduceGanador() {
            // Criatura muy débil para garantizar que la batalla termina rápido
            Dragon  fuerte = new Dragon("Fuerte",  500, 50);
            Guerrero debil = new Guerrero("Débil",  10, 5);

            // Simulamos manual para no imprimir en test (o podemos llamar Batalla)
            while (fuerte.estaViva() && debil.estaViva()) {
                fuerte.atacar(debil);
                if (debil.estaViva()) debil.atacar(fuerte);
            }

            // Al menos uno debe haber caído
            assertFalse(fuerte.estaViva() && debil.estaViva(),
                    "Al menos una criatura debe haber muerto");
        }
    }
}
