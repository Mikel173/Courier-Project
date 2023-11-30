package ui;

import java.util.Scanner;

import datastructures.CiudadOD;
import Model.Envio;
import Model.InformacionPaquete;
import datastructures.ArbolBinarioPaquetes;
import datastructures.RegistroEnvios;
import util.Alerta;
import util.AlertasNotificaciones;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        RegistroEnvios registroEnvios = new RegistroEnvios();
        ArbolBinarioPaquetes arbolPaquetes = new ArbolBinarioPaquetes();
        AlertasNotificaciones alertasNotificaciones = new AlertasNotificaciones();

        CiudadOD ciudadOrigen = new CiudadOD("CiudadOrigen");
        CiudadOD ciudadDestino = new CiudadOD("CiudadDestino");

        int opcion;

        do {
            System.out.println("Menú:");
            System.out.println("1. Registrar Venta");
            System.out.println("2. Registrar Paquete");
            System.out.println("3. Realizar Envío");
            System.out.println("4. Salir");
            System.out.print("Ingrese la opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    registrarVenta();
                    break;
                case 2:
                    registrarPaquete(arbolPaquetes);
                    break;
                case 3:
                    realizarEnvio(registroEnvios, arbolPaquetes, alertasNotificaciones, ciudadOrigen, ciudadDestino);
                    break;
                case 4:
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }

        } while (opcion != 4);

        scanner.close();
    }

    private static void registrarVenta() {
        // Implementa la lógica para registrar una venta según tus necesidades
        System.out.println("Función no implementada: Registrar Venta");
    }

    private static void registrarPaquete(ArbolBinarioPaquetes arbolPaquetes) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el contenido del paquete: ");
        String contenido = scanner.nextLine();

        System.out.print("Ingrese el peso del paquete: ");
        double peso = scanner.nextDouble();

        System.out.print("Ingrese las dimensiones del paquete: ");
        double dimensiones = scanner.nextDouble();

        // Supongamos que la ciudad de origen y destino son predefinidas para simplificar el ejemplo
        CiudadOD ciudadOrigen = new CiudadOD("CiudadOrigen");
        CiudadOD ciudadDestino = new CiudadOD("CiudadDestino");

        // Crear instancia de InformacionPaquete
        InformacionPaquete informacionPaquete = new InformacionPaquete(1, contenido, peso, dimensiones, ciudadOrigen, ciudadDestino);

        // Insertar paquete en el árbol
        arbolPaquetes.insertarPaquete(informacionPaquete);

        System.out.println("Paquete registrado con éxito.");
    }

    private static void realizarEnvio(RegistroEnvios registroEnvios, ArbolBinarioPaquetes arbolPaquetes,
                                      AlertasNotificaciones alertasNotificaciones, CiudadOD ciudadOrigen, CiudadOD ciudadDestino) {
        Scanner scanner = new Scanner(System.in);

        // Supongamos que el paquete se selecciona de la lista de paquetes registrados
        System.out.print("Ingrese el ID del paquete a enviar: ");
        int idPaquete = scanner.nextInt();

        // Buscar paquete en el árbol
        InformacionPaquete informacionPaquete = arbolPaquetes.buscarPaquete(idPaquete);

        if (informacionPaquete != null) {
            // Crear instancia de Envio
            Envio envio = new Envio(informacionPaquete, ciudadOrigen, ciudadDestino);

            // Insertar envío en el registro
            registroEnvios.insertarEnvio(envio);

            // Realizar envío automáticamente
            registroEnvios.moverEnvioAutomaticamente(envio.getNumeroSeguimiento());

            // Procesar alertas y notificaciones
            procesarAlertasNotificaciones(alertasNotificaciones);
        } else {
            System.out.println("Paquete no encontrado.");
        }
    }

    private static void procesarAlertasNotificaciones(AlertasNotificaciones alertasNotificaciones) {
        // Procesar y mostrar la próxima alerta
        alertasNotificaciones.procesarProximaAlerta();
    }
}
