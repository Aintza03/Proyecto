package General;

import java.sql.Date;
import java.util.Objects;


public abstract class Servicio {
	protected Date fechaInicio;
	protected Animal animal;
	
	
	
	public Servicio(Date fechaInicio, Animal animal) {
		super();
		this.fechaInicio = fechaInicio;
		this.animal = animal;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Animal getAnimal() {
		return animal;
	}
	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
	@Override
	public String toString() {
		return "Servicio [fechaInicio=" + fechaInicio + ", animal=" + animal + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(animal, fechaInicio);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Servicio other = (Servicio) obj;
		return Objects.equals(animal, other.animal) && Objects.equals(fechaInicio, other.fechaInicio);
	}
	
	

}
