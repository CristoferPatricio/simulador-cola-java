/**
 * Clase que implementa una estructura de datos tipo Pila (LIFO)
 * utilizando nodos y referencias manuales.
 */
public class Pila<T> {
    private Nodo<T> tope;
    private int tamanio;

    public Pila() {
        this.tope = null;
        this.tamanio = 0;
    }

    /**
     * Agrega un elemento a la cima de la pila.
     * @param dato El objeto a insertar.
     */
    public void push(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        nuevoNodo.setSiguiente(tope);
        tope = nuevoNodo;
        tamanio++;
    }

    /**
     * Elimina y retorna el elemento en la cima de la pila.
     * @return El dato del tope o null si está vacía.
     */
    public T pop() {
        if (estaVacia()) {
            return null;
        }
        T dato = tope.getDato();
        tope = tope.getSiguiente();
        tamanio--;
        return dato;
    }

    /**
     * Retorna el elemento en la cima sin eliminarlo.
     * @return El dato del tope o null si está vacía.
     */
    public T peek() {
        if (estaVacia()) {
            return null;
        }
        return tope.getDato();
    }

    /**
     * Verifica si la pila no tiene elementos.
     */
    public boolean estaVacia() {
        return tope == null;
    }

    public int getTamanio() {
        return tamanio;
    }

    /**
     * Recorre la pila desde el tope hasta la base y muestra los elementos.
     */
    public void mostrar() {
        if (estaVacia()) {
            System.out.println(" > El historial está vacío.");
            return;
        }
        System.out.printf("%-10s | %-20s | %-20s%n", "ID", "Nombre", "Servicio");
        System.out.println("------------------------------------------------------------");
        Nodo<T> actual = tope;
        while (actual != null) {
            System.out.println(actual.getDato().toString());
            actual = actual.getSiguiente();
        }
    }
}
