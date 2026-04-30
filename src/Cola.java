/**
 * Clase que implementa una estructura de datos tipo Cola (FIFO)
 * utilizando nodos y referencias manuales.
 */
public class Cola<T> {
    private Nodo<T> frente;
    private Nodo<T> finalCola;
    private int tamanio;

    public Cola() {
        this.frente = null;
        this.finalCola = null;
        this.tamanio = 0;
    }

    /**
     * Agrega un nuevo elemento al final de la cola.
     * 
     * @param dato El objeto a encolar.
     */
    public void encolar(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        if (estaVacia()) {
            frente = nuevoNodo;
        } else {
            finalCola.setSiguiente(nuevoNodo);
        }
        finalCola = nuevoNodo;
        tamanio++;
    }

    /**
     * Elimina y retorna el primer elemento de la cola.
     * 
     * @return El dato del primer nodo o null si está vacía.
     */
    public T desencolar() {
        if (estaVacia()) {
            return null;
        }
        T dato = frente.getDato();
        frente = frente.getSiguiente();
        if (frente == null) {
            finalCola = null;
        }
        tamanio--;
        return dato;
    }

    /**
     * Verifica si la cola no tiene elementos.
     */
    public boolean estaVacia() {
        return frente == null;
    }

    public int getTamanio() {
        return tamanio;
    }

    /**
     * Recorre la cola y muestra los elementos en formato de tabla.
     */
    public void mostrar() {
        if (estaVacia()) {
            System.out.println(" > No hay elementos en la cola.");
            return;
        }
        System.out.printf("%-10s | %-20s | %-20s%n", "ID", "Nombre", "Servicio");
        System.out.println("------------------------------------------------------------");
        Nodo<T> actual = frente;
        while (actual != null) {
            System.out.println(actual.getDato().toString());
            actual = actual.getSiguiente();
        }
    }
}
