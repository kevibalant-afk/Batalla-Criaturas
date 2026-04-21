# Batalla-Criaturas
# Diagrama de Clases - Sistema de Batalla Fantástica

## Interfaces
**Volador**
- volar(): void  
- aterrizar(): void  

**Magico**
- lanzarHechizo(): void  
- aprenderHechizo(n: String): void  

---

## Clases Abstractas
**Criatura (abstracta)**
- nombre: String  
- salud: int  
- fuerza: int  
- armaEquipada: Arma  
+ atacar(obj: Criatura): void  
+ defender(d: int): void  

---

## Clases Concretas
**Arma**
- nombre: String  
- dañoBase: int  
- tipo: String  
+ atacarConArma(): void  
+ getDañoAdicional(): int  
+ getNombre(): String  

**Dragon**
- estaVolando: boolean  
+ atacar(obj): void  
+ defender(d): void  
+ volar(): void  
+ aterrizar(): void  
+ equiparArma(a): void  
// daño = fuerza * 2  
// reduce daño 20%  
// implementa Volador  

**Mago**
- hechizos: List  
- hechizoActivo: String  
+ atacar(obj): void  
+ defender(d): void  
+ lanzarHechizo(): void  
+ aprenderHechizo(n): void  
// daño = fuerza  
// reduce daño 15%  
// implementa Magico  

**Guerrero**
+ atacar(obj): void  
+ defender(d): void  
+ equiparArma(a): void  
// daño = fuerza  
// golpe crítico 20%  
// reduce daño 25%  

---

## Clase de Control
**Batalla**
+ simularBatalla(c1, c2: Criatura): void  
- mostrarResultado(): void  
- activarHabilidades(): void  
