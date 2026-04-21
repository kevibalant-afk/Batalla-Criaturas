# Integrantes
- Kevin Adrian Balanta  
- Luis Alberto Fernandez Viveros  

```mermaid
classDiagram
  interface Volador {
    +volar(): void
    +aterrizar(): void
  }

  interface Magico {
    +lanzarHechizo(): void
    +aprenderHechizo(n: String): void
  }

  abstract class Criatura {
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

