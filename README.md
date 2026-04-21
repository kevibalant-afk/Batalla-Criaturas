# Integrantes
- Kevin Adrian Balanta  
- Luis Alberto Fernandez Viveros  

```mermaid
classDiagram
  class Volador <<interface>> {
    +volar(): void
    +aterrizar(): void
  }

  class Magico <<interface>> {
    +lanzarHechizo(): void
    +aprenderHechizo(n: String): void
  }

  class Criatura <<abstract>> {
    -nombre: String
    -salud: int
    -fuerza: int
    -armaEquipada: Arma
    +atacar(obj: Criatura): void
    +defender(d: int): void
  }

  class Arma {
    -nombre: String
    -dañoBase: int
    -tipo: String
    +atacarConArma(): void
    +getDañoAdicional(): int
    +getNombre(): String
  }

  class Dragon {
    -estaVolando: boolean
    +atacar(obj): void
    +defender(d): void
    +volar(): void
    +aterrizar(): void
    +equiparArma(a): void
    // fuerza * 2
    // reduce daño 20%
    // implementa Volador
  }

  class Mago {
    -hechizos: List
    -hechizoActivo: String
    +atacar(obj): void
    +defender(d): void
    +lanzarHechizo(): void
    +aprenderHechizo(n): void
    // daño = fuerza
    // reduce daño 15%
    // implementa Magico
  }

  class Guerrero {
    +atacar(obj): void
    +defender(d): void
    +equiparArma(a): void
    // daño = fuerza
    // golpe crítico 20%
    // reduce daño 25%
  }

  class Batalla {
    +simularBatalla(c1, c2: Criatura): void
    -mostrarResultado(): void
    -activarHabilidades(): void
  }

  Criatura --> Arma
  Criatura <|-- Dragon
  Criatura <|-- Mago
  Criatura <|-- Guerrero
  Volador <|.. Dragon
  Magico <|.. Mago



# Sistema de Batalla de Criaturas

Proyecto en Java que simula batallas entre criaturas usando POO: clases abstractas, interfaces, composición y polimorfismo.

---

## Requisitos

- Java 11+
- Maven 3.6+

## Cómo ejecutar

```bash
mvn compile
mvn exec:java -Dexec.mainClass="com.batalla.Main"
```

## Cómo correr las pruebas





## Decisiones de diseño

**Clase abstracta `Criatura`** — se usa abstracta porque todas las criaturas comparten atributos (`nombre`, `salud`, `fuerza`) y el método `estaViva()` es igual para todas. Los métodos `atacar()` y `defender()` son abstractos porque cada criatura los implementa diferente.

**Interfaces `Volador` y `Magico`** — se usan interfaces porque volar y la magia son capacidades opcionales. El `Dragon` implementa `Volador` y el `Mago` implementa `Magico`, sin afectar la jerarquía base.

**Composición en `Arma`** — el arma es un atributo de `Criatura` porque una criatura *tiene* un arma, no *es* un arma. Esto permite equipar y desequipar en tiempo de ejecución.

**Clase `Batalla` separada** — la lógica del combate no le pertenece a `Criatura`. Cada clase tiene una sola responsabilidad.


## Criaturas

| Criatura | Daño | Defensa | Habilidad |
| Dragon | fuerza × 2 | −20% daño | Vuela (`Volador`) |
| Mago | fuerza | −15% daño | Hechizos (`Magico`) |
| Guerrero | fuerza | −25% daño | Golpe crítico 20% |



- Integrante 1
- Integrante 2
