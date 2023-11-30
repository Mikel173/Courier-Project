package util;

public class Alerta implements Comparable<Alerta> {
    private String mensaje;
    private int prioridad;

    public Alerta(String mensaje, int prioridad) {
        this.mensaje = mensaje;
        this.prioridad = prioridad;
    }

    public String getMensaje() {
        return mensaje;
    }

    public int getPrioridad() {
        return prioridad;
    }

    @Override
    public int compareTo(Alerta otraAlerta) {
        return Integer.compare(this.prioridad, otraAlerta.prioridad);
    }
}
