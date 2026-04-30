# Simulador de Cola - Java

Este proyecto es un simulador de cola de atención al cliente para una sucursal bancaria, desarrollado para la Universidad da Vinci de Guatemala.

## Descripción
El sistema gestiona la llegada de clientes mediante una **Cola (Queue)** y mantiene un registro de las atenciones realizadas en una **Pila (Stack)**. 

### Restricción Fundamental
Todas las estructuras de datos (Cola y Pila) han sido implementadas desde cero utilizando **Nodos y Referencias**, sin utilizar las colecciones integradas de Java (`java.util.*`).

## Funcionalidades
1. **Carga desde archivo:** Lee una lista inicial de clientes desde `data/clientes.txt`.
2. **Agregar cliente manual:** Permite registrar nuevos clientes en tiempo de ejecución.
3. **Atender cliente:** Procesa al siguiente cliente en la cola y lo mueve al historial.
4. **Ver cola:** Muestra los clientes que están esperando.
5. **Ver historial:** Muestra los clientes atendidos (del más reciente al más antiguo).
6. **Consultar último atendido:** Muestra la información del último cliente procesado sin eliminarlo del historial.

## Estructura del Proyecto
- `src/Cliente.java`: Clase que representa a un cliente.
- `src/Nodo.java`: Clase genérica para los nodos de las estructuras.
- `src/Cola.java`: Implementación de la estructura de Cola (FIFO).
- `src/Pila.java`: Implementación de la estructura de Pila (LIFO).
- `src/Simulador.java`: Clase principal con el menú interactivo.
- `data/clientes.txt`: Archivo de entrada de datos.

## Ejecución
Para compilar y ejecutar el proyecto:
```bash
javac src/*.java
java -cp src Simulador
```
