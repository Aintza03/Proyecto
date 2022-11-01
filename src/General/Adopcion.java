package General;

import java.sql.Date;
import java.util.Objects;


public class Adopcion extends Servicio {
	protected String contrato;

	public Adopcion(Date fechaInicio, Animal animal) {
		super(fechaInicio, animal);
		this.contrato= "";
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
	
	
	public String getContrato() {
		return contrato;
		
	}
	public void setContrato(String contrato) {
		this.contrato = contrato;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(contrato);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adopcion other = (Adopcion) obj;
		return Objects.equals(contrato, other.contrato);
	}
	@Override
	public String toString() {
		return "Adopcion [contrato=" + contrato + "]";
	}
	
	
	
	

}
