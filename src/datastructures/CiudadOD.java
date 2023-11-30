package datastructures;

public class CiudadOD {
    private String nombre;

    public CiudadOD(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
}
