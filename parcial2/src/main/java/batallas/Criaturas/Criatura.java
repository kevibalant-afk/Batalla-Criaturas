package com.batalla.criaturas;

import com.batalla.armas.Arma;

/**
 * Clase abstracta que representa una criatura en el sistema de batalla.
 *
 * DECISIÓN DE DISEÑO: Se usa clase abstracta en lugar de interfaz porque:
 * 1. Las criaturas comparten estado común (nombre, salud, fuerza).
 * 2. Se necesita lógica concreta compartida (estaViva, equiparArma).
 * 3. Los métodos atacar() y defender() deben ser implementados
 *    de forma distinta por cada tipo de criatura (polimorfismo).
 */
public abstract class Criatura {

    // ── Atributos básicos ──────────────────────────────────────────────────
    protected String nombre;
    protected int salud;
    protected int fuerza;

    /**
     * Arma equipada por la criatura (composición).
     * Puede ser null si la criatura no porta arma.
     *
     * DECISIÓN DE DISEÑO: Se usa composición en lugar de herencia para el arma,
     * ya que el arma es un "tiene" y no un "es". Esto permite cambiar o quitar
     * el arma en tiempo de ejecución sin alterar la jerarquía de clases.
     */
    protected Arma armaEquipada;

    // ── Constructor ────────────────────────────────────────────────────────

    /**
     * Construye una criatura con sus atributos base.
     *
     * @param nombre Nombre identificador de la criatura.
     * @param salud  Puntos de vida iniciales.
     * @param fuerza Puntos de ataque base.
     */
    public Criatura(String nombre, int salud, int fuerza) {
        this.nombre = nombre;
        this.salud  = salud;
        this.fuerza = fuerza;
        this.armaEquipada = null;
    }

    // ── Métodos abstractos (polimorfismo) ──────────────────────────────────

    /**
     * Realiza un ataque contra otra criatura.
     * Cada subclase define su propia lógica de ataque.
     *
     * @param objetivo La criatura que recibe el ataque.
     */
    public abstract void atacar(Criatura objetivo);

    /**
     * Aplica la defensa ante un ataque recibido.
     * Cada subclase puede tener un mecanismo de defensa diferente.
     *
     * @param daño Cantidad de daño recibido (equivale a la fuerza del atacante).
     */
    public abstract void defender(int daño);

    // ── Método concreto ────────────────────────────────────────────────────

    /**
     * Indica si la criatura sigue con vida.
     *
     * @return true si la salud es mayor que 0, false en caso contrario.
     */
    public boolean estaViva() {
        return this.salud > 0;
    }

    // ── Composición: gestión de armas ──────────────────────────────────────

    /**
     * Equipa un arma a la criatura.
     *
     * @param arma El arma a equipar.
     */
    public void equiparArma(Arma arma) {
        this.armaEquipada = arma;
        System.out.println(nombre + " ha equipado el arma: " + arma.getNombre());
    }

    /**
     * Desequipa el arma actual de la criatura.
     */
    public void desequiparArma() {
        if (armaEquipada != null) {
            System.out.println(nombre + " ha desequipado el arma: " + armaEquipada.getNombre());
            this.armaEquipada = null;
        } else {
            System.out.println(nombre + " no tiene arma equipada.");
        }
    }

    // ── Getters y Setters ──────────────────────────────────────────────────

    public String getNombre()      { return nombre; }
    public int    getSalud()       { return salud; }
    public int    getFuerza()      { return fuerza; }
    public Arma   getArmaEquipada(){ return armaEquipada; }

    public void setSalud(int salud) {
        // La salud mínima es 0; no puede ser negativa
        this.salud = Math.max(0, salud);
    }

    // ── toString ───────────────────────────────────────────────────────────

    @Override
    public String toString() {
        String armaInfo = (armaEquipada != null) ? " | Arma: " + armaEquipada.getNombre() : "";
        return String.format("[%s | Salud: %d | Fuerza: %d%s]",
                nombre, salud, fuerza, armaInfo);
    }
}
