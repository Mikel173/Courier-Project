package util;

import java.util.PriorityQueue;

public class AlertasNotificaciones {
    private PriorityQueue<Alerta> colaDePrioridad;

    public AlertasNotificaciones() {
        this.colaDePrioridad = new PriorityQueue<>();
    }

    public void agregarAlerta(Alerta alerta) {
        colaDePrioridad.offer(alerta);
    }

    public void procesarProximaAlerta() {
        Alerta proximaAlerta = colaDePrioridad.poll();

        if (proximaAlerta != null) {
            System.out.println("Alerta: " + proximaAlerta.getMensaje());
        }
    }
}
