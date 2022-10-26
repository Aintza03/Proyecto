package General;

import java.sql.Date;

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
		return tipo + " " + raza +  ", nacido en " + fechaNac + "??"+ especial;
	}
	
}
