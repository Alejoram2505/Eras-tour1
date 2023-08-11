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

