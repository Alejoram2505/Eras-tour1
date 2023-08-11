import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Localidad {
    String nombre;
    int precio;
    int capacidad;
    int boletosVendidos;

    public Localidad(String nombre, int precio, int capacidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.capacidad = capacidad;
        this.boletosVendidos = 0;
    }

    public boolean venderBoletos(int cantidad) {
        if (capacidad - boletosVendidos >= cantidad) {
            boletosVendidos += cantidad;
            return true;
        }
        return false;
    }
}

class Comprador {
    String nombre;
    String email;
    double presupuesto;
    int cantidadBoletosDeseados;
    int ticket;

    public Comprador(String nombre, String email, double presupuesto) {
        this.nombre = nombre;
        this.email = email;
        this.presupuesto = presupuesto;
        this.cantidadBoletosDeseados = 0;
        this.ticket = -1;
    }
}

lic static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Localidad> localidades = new ArrayList<>();
        localidades.add(new Localidad("Localidad 1", 100, 20));
        localidades.add(new Localidad("Localidad 5", 500, 20));
        localidades.add(new Localidad("Localidad 10", 1000, 20));
        Comprador compradorActual = null;
        Random random = new Random();

        while (true) {
            System.out.println("\nMenú:");
            System.out.println("1. Nuevo comprador");
            System.out.println("2. Nueva solicitud de boletos");
            System.out.println("3. Consultar disponibilidad total");
            System.out.println("4. Consultar disponibilidad individual");
            System.out.println("5. Reporte de caja");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre del comprador: ");
                    String nombre = scanner.next();
                    System.out.print("Ingrese el email del comprador: ");
                    String email = scanner.next();
                    System.out.print("Ingrese el presupuesto del comprador: ");
                    double presupuesto = scanner.nextDouble();
                    compradorActual = new Comprador(nombre, email, presupuesto);
                    System.out.println("Comprador creado exitosamente.");
                    break;

                case 2:
                    if (compradorActual == null) {
                        System.out.println("Debe crear un comprador primero.");
                        break;
                    }

                    System.out.print("Ingrese la cantidad de boletos que desea comprar: ");
                    compradorActual.cantidadBoletosDeseados = scanner.nextInt();
                    compradorActual.ticket = random.nextInt(15000) + 1;
                    int a = random.nextInt(15000) + 1;
                    int b = random.nextInt(15000) + 1;

                    boolean boletosVendidos = false;
                    for (Localidad localidad : localidades) {
                        if (a <= localidad.boletosVendidos && localidad.boletosVendidos <= b) {
                            if (localidad.venderBoletos(compradorActual.cantidadBoletosDeseados)) {
                                localidad.boletosVendidos = compradorActual.ticket;
                                System.out.println("Boletos vendidos con éxito.");
                                boletosVendidos = true;
                                break;
                            }
                        }
                    }
                    if (!boletosVendidos) {
                        System.out.println("No se pudo vender boletos para el ticket generado.");
                    }
                    break;

                case 3:
                    System.out.println("Disponibilidad total:");
                    for (Localidad localidad : localidades) {
                        System.out.println(localidad.nombre + ": " + (localidad.capacidad - localidad.boletosVendidos) + " boletos disponibles");
                    }
                    break;

                case 4:
                    if (compradorActual == null) {
                        System.out.println("Debe crear un comprador primero.");
                        break;
                    }

                    System.out.print("Ingrese el número de la localidad (1, 2 o 3): ");
                    int localidadElegida = scanner.nextInt();
                    if (localidadElegida >= 1 && localidadElegida <= 3) {
                        Localidad localidad = localidades.get(localidadElegida - 1);
                        System.out.println("Disponibilidad en " + localidad.nombre + ": " + (localidad.capacidad - localidad.boletosVendidos) + " boletos disponibles");
                    } else {
                        System.out.println("Opción inválida.");
                    }
                    break;

                case 5:
                    double totalRecaudado = 0;
                    for (Localidad localidad : localidades) {
                        totalRecaudado += localidad.precio * localidad.boletosVendidos;
                    }
                    System.out.println("Total recaudado: $" + totalRecaudado);
                    break;

                case 6:
                    System.out.println("Saliendo del programa.");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opción inválida. Por favor, elija una opción válida.");
                    break;
            }
        }
    }
}