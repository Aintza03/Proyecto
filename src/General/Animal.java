package General;

import java.util.Date;
import java.util.Objects;

public class Animal {
	protected int id;
	protected String raza;
	protected String especial;
	protected String tipo;
	protected Date fechaNac;
	public Animal(int id, String raza, String especial, String tipo, Date fechaNac) {
		super();
		this.id = id;
		this.raza = raza;
		this.especial = especial;
		this.tipo = tipo;
		this.fechaNac = fechaNac;
	}
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getRaza() {
		return raza;
	}
	public void setRaza(String raza) {
		this.raza = raza;
	}
	public String getEspecial() {
		return especial;
	}
	public void setEspecial(String especial) {
		this.especial = especial;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Date getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}
	
	@Override
	public String toString() {
		return id + " " +tipo + " " + raza +  ", nacido en " + fechaNac + "??"+ especial;
	}

	@Override
	public int hashCode() {
		return Objects.hash(especial, id, raza, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		return Objects.equals(especial, other.especial) && id == other.id && Objects.equals(raza, other.raza)
				&& Objects.equals(tipo, other.tipo);
	}
	
}
