package General;

import java.util.ArrayList;
import java.util.Objects;

public class Cliente {// constructores getters and setters y toString hasta la linea 80
	protected String dni;
	protected String direccion;
	protected int telefono;
	protected String nombre;
	protected ArrayList<Animal> animalesAdoptados;
	protected ArrayList<Animal> animalesAcogidos;
	protected boolean permiso;
	public boolean isPermiso() {
		return permiso;
	}
	public void setPermiso(boolean permiso) {
		this.permiso = permiso;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ArrayList<Animal> getAnimalesAdoptados() {
		return animalesAdoptados;
	}
	public void setAnimalesAdoptados(ArrayList<Animal> animalesAdoptados) {
		this.animalesAdoptados = animalesAdoptados;
	}
	public ArrayList<Animal> getAnimalesAcogidos() {
		return animalesAcogidos;
	}
	public void setAnimalesAcogidos(ArrayList<Animal> animalesAcogidos) {
		this.animalesAcogidos = animalesAcogidos;
	}
	@Override
	public String toString() {
		return "Cliente [dni=" + dni + ", direccion=" + direccion + ", telefono=" + telefono + ", nombre=" + nombre
				+ ", animalesAdoptados=" + animalesAdoptados + ", animalesAcogidos=" + animalesAcogidos + "]";
	}
	public Cliente(String dni, String direccion, int telefono, String nombre, ArrayList<Animal> animalesAdoptados,
			ArrayList<Animal> animalesAcogidos, boolean permiso) {
		this.dni = dni;
		this.direccion = direccion;
		this.telefono = telefono;
		this.nombre = nombre;
		this.animalesAdoptados = animalesAdoptados;
		this.animalesAcogidos = animalesAcogidos;
		this.permiso = permiso;
	}// al leer de la base de datos arriba  nuevo abajo.
	public Cliente(String dni, String direccion, int telefono, String nombre) {
		this.dni = dni;
		this.direccion = direccion;
		this.telefono = telefono;
		this.nombre = nombre;
		this.animalesAdoptados = new ArrayList<Animal>();
		this.animalesAcogidos = new ArrayList<Animal>();
		this.permiso = true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(animalesAcogidos, animalesAdoptados, direccion, dni, nombre, permiso, telefono);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(animalesAcogidos, other.animalesAcogidos)
				&& Objects.equals(animalesAdoptados, other.animalesAdoptados)
				&& Objects.equals(direccion, other.direccion) && Objects.equals(dni, other.dni)
				&& Objects.equals(nombre, other.nombre) && permiso == other.permiso && telefono == other.telefono;
	}
	
	
	
	
	
	
}
