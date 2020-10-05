package dominio;

public class Propietario {
	
	private String nombre;
	private String telefono;
	
	public Propietario() {
		
	}
	public Propietario(String nombre, String telefono) {
		this.nombre = nombre;
		this.telefono = telefono;
	}
	public String getNombre() {
		return this.nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return this.telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	@Override
	public String toString() {
		return "Propietario [nombre= " + nombre + ", telefono= " + telefono + "]";
	}
}