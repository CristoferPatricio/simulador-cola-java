import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Clase principal que gestiona el flujo del simulador y el menú de usuario.
 */
public class Simulador {
    private static Cola<Cliente> colaEspera = new Cola<>();
    private static Pila<Cliente> historialAtencion = new Pila<>();
    private static final String ARCHIVO_DATOS = "data/clientes.txt";

    public static void main(String[] args) {
        System.out.println("===============================================");
        System.out.println("   SIMULADOR DE COLA - BANCO DA VINCI");
        System.out.println("===============================================");
        cargarDesdeArchivo(); // Carga automática al iniciar
        menuPrincipal();
    }

    /**
     * Gestiona el menú interactivo de la consola.
     */
    private static void menuPrincipal() {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n-----------------------------------------------");
            System.out.println(" ESTADO: [" + colaEspera.getTamanio() + "] en espera | [" + historialAtencion.getTamanio() + "] atendidos");
            System.out.println("-----------------------------------------------");
            System.out.println("1. Cargar clientes desde archivo");
            System.out.println("2. Agregar cliente manualmente");
            System.out.println("3. Atender siguiente cliente");
            System.out.println("4. Ver cola de espera");
            System.out.println("5. Ver historial de atenciones");
            System.out.println("6. Consultar último atendido");
            System.out.println("7. Salir");
            System.out.print(" >> Seleccione una opción: ");

            try {
                String entrada = scanner.nextLine();
                if (entrada.isEmpty()) continue;
                opcion = Integer.parseInt(entrada);

                switch (opcion) {
                    case 1:
                        if (!colaEspera.estaVacia()) {
                            System.out.print("La cola no está vacía. ¿Sobrescribir? (s/n): ");
                            if (scanner.nextLine().equalsIgnoreCase("s")) {
                                colaEspera = new Cola<>();
                                cargarDesdeArchivo();
                            }
                        } else {
                            cargarDesdeArchivo();
                        }
                        break;
                    case 2:
                        agregarClienteManual(scanner);
                        break;
                    case 3:
                        atenderCliente();
                        break;
                    case 4:
                        System.out.println("\n--- LISTA DE ESPERA ---");
                        colaEspera.mostrar();
                        break;
                    case 5:
                        System.out.println("\n--- HISTORIAL DE ATENCIÓN (Recientes primero) ---");
                        historialAtencion.mostrar();
                        break;
                    case 6:
                        consultarUltimoAtendido();
                        break;
                    case 7:
                        System.out.println("\nCerrando sistema. ¡Feliz día!");
                        break;
                    default:
                        System.out.println(" (!) Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println(" (!) Error: Ingrese solo números.");
            }
        } while (opcion != 7);

        scanner.close();
    }

    /**
     * Lee el archivo clientes.txt y encola los datos.
     */
    private static void cargarDesdeArchivo() {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_DATOS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    Cliente cliente = new Cliente(partes[0].trim(), partes[1].trim(), partes[2].trim());
                    colaEspera.encolar(cliente);
                    count++;
                }
            }
            System.out.println(" [+] OK: Se cargaron " + count + " clientes.");
        } catch (IOException e) {
            System.out.println(" [!] ERROR al leer archivo: " + e.getMessage());
        }
    }

    /**
     * Permite al usuario ingresar un cliente desde el teclado.
     */
    private static void agregarClienteManual(Scanner scanner) {
        System.out.println("\n-- Registro Manual --");
        System.out.print("ID: ");
        String id = scanner.nextLine().trim();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine().trim();
        System.out.print("Servicio: ");
        String servicio = scanner.nextLine().trim();

        if (id.isEmpty() || nombre.isEmpty() || servicio.isEmpty()) {
            System.out.println(" [!] Error: No se permiten campos vacíos.");
            return;
        }

        colaEspera.encolar(new Cliente(id, nombre, servicio));
        System.out.println(" [+] Cliente encolado correctamente.");
    }

    /**
     * Desencola al cliente y lo mueve a la pila de historial.
     */
    private static void atenderCliente() {
        if (colaEspera.estaVacia()) {
            System.out.println(" [!] No hay clientes esperando.");
            return;
        }
        Cliente atendido = colaEspera.desencolar();
        historialAtencion.push(atendido);
        System.out.println("\n >>> ATENDIENDO A: " + atendido.getNombre().toUpperCase());
        System.out.println(" >>> SERVICIO: " + atendido.getServicio());
    }

    /**
     * Muestra el último cliente en la pila sin sacarlo.
     */
    private static void consultarUltimoAtendido() {
        Cliente ultimo = historialAtencion.peek();
        if (ultimo == null) {
            System.out.println(" [!] Historial vacío.");
        } else {
            System.out.println("\n--- ÚLTIMO ATENDIDO ---");
            System.out.printf("ID: %s | Nombre: %s | Servicio: %s%n", 
                ultimo.getId(), ultimo.getNombre(), ultimo.getServicio());
        }
    }
}
